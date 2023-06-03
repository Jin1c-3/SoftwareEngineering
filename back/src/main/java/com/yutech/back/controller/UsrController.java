package com.yutech.back.controller;


import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yutech.back.common.exception.GlobalException;
import com.yutech.back.common.utils.*;
import com.yutech.back.entity.bo.PaymentBO;
import com.yutech.back.entity.dto.LoginDTO;
import com.yutech.back.entity.dto.PaymentDTO;
import com.yutech.back.entity.dto.RefundDTO;
import com.yutech.back.entity.dto.UsrDTO;
import com.yutech.back.entity.po.FlightTicket;
import com.yutech.back.entity.po.TrainTicket;
import com.yutech.back.entity.po.Usr;
import com.yutech.back.entity.vo.UsrVO;
import com.yutech.back.service.bussiness.AliSmsService;
import com.yutech.back.service.bussiness.AlipayService;
import com.yutech.back.service.bussiness.EMailService;
import com.yutech.back.service.persistence.FlightTicketService;
import com.yutech.back.service.persistence.TrainTicketService;
import com.yutech.back.service.persistence.UsrService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jin1c-3
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/usr")
@Api(tags = "用户总接口")
@Slf4j
@CrossOrigin
public class UsrController {
	@Autowired
	private UsrService usrService;

	@Autowired
	private EMailService eMailService;

	@Autowired
	private AliSmsService aliSmsService;

	@Autowired
	private AlipayService alipayService;

	@Autowired
	private FlightTicketService flightTicketService;

	@Autowired
	private TrainTicketService trainTicketService;

	/**
	 * 默认头像
	 */
	@Value("${default.avatar}")
	private String defaultAvatar;

	/**
	 * 用户注册
	 *
	 * @param usrDTO 用户注册信息
	 * @return 用户信息
	 */
	@ApiOperation(value = "用户注册", notes = "用户注册，会检验唯一性。注意传头像的时候，他的key应该是avatar而不是UsrAvatar")
	@PostMapping("/registry")
	public Result<UsrVO> usrRegistry(@RequestBody UsrDTO usrDTO) {
		log.debug("用户注册，前端信息===" + usrDTO);
		Usr usrPushInDB = new Usr(usrDTO);
		//验证账号唯一性
		if (Boolean.TRUE.equals(usrService.verifyUnique(usrPushInDB))) {
			usrPushInDB.setUsrId(RandomGeneratorUtil.getRandomUsrIdByUUID());
			usrPushInDB.setUsrAvatar(defaultAvatar);
			usrPushInDB.setUsrPwd(PasswordEncryptorUtil.encrypt(usrPushInDB.getUsrPwd()));
			log.debug("用户注册，即将存入数据库===" + usrPushInDB);
			//保存用户信息
			usrService.save(usrPushInDB);
			//发送欢迎邮件
			eMailService.sendGreetings(usrPushInDB.getUsrEmail(), usrPushInDB.getUsrAccount());
			log.info("用户注册成功，用户为==={}", usrPushInDB);
			return Result.ok(new UsrVO(usrPushInDB)).message("注册成功");
		}
		log.debug("用户注册失败，可能违反唯一性，用户为==={}", usrPushInDB);
		return Result.error(new UsrVO(usrPushInDB)).message("账号已存在");
	}

	/**
	 * 用户登录
	 *
	 * @param usrName     用户名
	 * @param usrPassword 用户密码
	 * @return 用户信息
	 */
	@ApiOperation(value = "用户登录", notes = "用户登录，返回详细用户对象Usr以及token")
	@GetMapping("/login")
	public Result<String> usrLogin(String usrName, String usrPassword) {
		LoginDTO loginDTO = new LoginDTO(usrName, usrPassword);
		log.debug("用户登录，前端信息======={}", loginDTO);
		Usr[] usrLogins = {usrService.getOne(new QueryWrapper<Usr>().eq("usr_account", loginDTO.getAccount())),
				usrService.getOne(new QueryWrapper<Usr>().eq("usr_phone", loginDTO.getAccount())),
				usrService.getOne(new QueryWrapper<Usr>().eq("usr_email", loginDTO.getAccount()))};
		int countVerifier = 0;
		Usr usrInDB = null;
		for (Usr usr : usrLogins) {
			if (usr != null) {
				usrInDB = usr;
				countVerifier++;
			}
		}
		if (countVerifier > 1) {
			log.warn("用户登录失败，账号存在多个，用户为==={}", loginDTO);
			return Result.error("").message("您的账号存在问题，待管理员核实");
		}
		if (countVerifier == 0) {
			log.info("用户登录失败，账号不存在，用户为==={}", loginDTO);
			return Result.error("").message("账号不存在");
		}
		if (!PasswordEncryptorUtil.match(loginDTO.getPwd(), usrInDB.getUsrPwd())) {
			log.info("用户登录失败，密码错误，用户为==={}", usrInDB);
			return Result.error("").message("密码错误");
		}
		log.info("用户登录成功==={}", usrInDB);
		return Result.ok(JwtUtil.sign(usrInDB.getUsrId(), usrInDB.getUsrPwd())).message("登录成功");
	}

	/**
	 * 用户信息获取
	 *
	 * @param token token
	 * @return 用户信息
	 */
	@ApiOperation(value = "用户信息获取", notes = "用户信息获取，返回详细用户对象Usr")
	@GetMapping("/info")
	public Result<UsrVO> getInfo(String token) {
		String usrId = JwtUtil.getId(token);
		Usr usrInDB = usrService.getOne(new QueryWrapper<Usr>().eq("usr_ID", usrId));
		if (usrInDB == null) {
			log.info("用户信息获取失败，用户不存在，用户为======{}", usrId);
			return Result.error(new UsrVO()).message("该用户不存在");
		}
		log.debug("用户信息获取成功，用户为======{}", usrInDB);
		return Result.ok(new UsrVO(usrInDB)).message("用户信息获取成功");
	}


	/**
	 * 修改用户信息
	 *
	 * @param usrDTO 用户信息
	 * @return Result
	 */
	@PatchMapping("/update")
	@ApiOperation(value = "修改用户信息", notes = "修改用户信息，请传入usrId!!!!!!")
	public Result<UsrVO> updateUsrInfo(UsrDTO usrDTO, MultipartFile avatar, HttpServletRequest request) {
		log.debug("用户信息修改，前端信息===" + usrDTO);
		Usr usrInDB = usrService.getOne(new QueryWrapper<Usr>().eq("usr_ID", usrDTO.getUsrId()));
		if (usrInDB == null) {
			log.info("用户信息修改失败，用户不存在，用户为==={}", usrDTO);
			return Result.error(new UsrVO()).message("用户不存在");
		}
		Usr usrPushInDB = new Usr(usrDTO);
		//验证账号唯一性
		if (!usrService.verifyGoodUpdate(usrInDB)) {
			log.info("用户信息修改失败，账号已存在，用户为==={}", usrInDB);
			return Result.error(new UsrVO()).message("修改失败，账号已存在");
		}
		//如果头像不为空，就上传头像
		if (avatar != null) {
			log.debug("用户信息修改，头像不为空，开始上传头像");
			usrPushInDB.setUsrAvatar(FileUtil.storeMultipartFile(usrDTO.getUsrId(), avatar, request));
		}
		if (!StringUtils.isEmpty(usrDTO.getUsrPwd())) {
			log.debug("用户信息修改，密码不为空，开始加密");
			usrPushInDB.setUsrPwd(PasswordEncryptorUtil.encrypt(usrDTO.getUsrPwd()));
		}
		//更新用户信息
		log.debug("用户信息更新成功，用户为==={}", usrPushInDB);
		usrService.updateById(usrPushInDB);
		return Result.ok(new UsrVO(usrPushInDB)).message("修改成功");
	}

	/**
	 * 修改用户密码
	 *
	 * @param phoneOrEMail 账号或手机号
	 * @param pwd          新密码
	 * @return Result
	 */
	@PatchMapping("/update-pwd/{phoneOrEMail}")
	@ApiOperation(value = "修改用户密码", notes = "修改用户密码，传入账号或手机号，将新密码存入数据库")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phoneOrEMail", value = "账号或手机号", required = true, dataTypeClass = String.class, paramType = "path"),
			@ApiImplicitParam(name = "pwd", value = "新密码", required = true, dataTypeClass = String.class, paramType = "query")
	})
	public Result<Object> updateUsrPwd(@PathVariable String phoneOrEMail, String pwd) {
		Boolean isEMail = phoneOrEMail.contains("@");
		log.debug("修改用户密码，前端信息======{}======判断是否是邮箱======{}======", phoneOrEMail, isEMail);
		Usr usrInDB;
		if (isEMail) {
			usrInDB = usrService.getOne(new QueryWrapper<Usr>().eq("usr_email", phoneOrEMail));
		} else {
			usrInDB = usrService.getOne(new QueryWrapper<Usr>().eq("usr_phone", phoneOrEMail));
		}
		if (usrInDB == null) {
			log.info("修改用户密码失败，用户不存在，用户为======{}", phoneOrEMail);
			return Result.error().message("无此用户");
		}
		usrInDB.setUsrPwd(pwd);
		usrService.updateById(usrInDB);
		log.info("修改用户密码成功，用户为======{}", usrInDB);
		return Result.ok().message("修改成功");
	}

	@GetMapping("/before-update/{phoneOrEMail}")
	@ApiOperation(value = "修改用户信息前的验证", notes = "修改用户信息前的验证，返回用户信息")
	@ApiParam(name = "phoneOrEMail", value = "账号或手机号", required = true)
	public Result<String> beforeUpdate(@PathVariable String phoneOrEMail) {
		Boolean isEMail = phoneOrEMail.contains("@");
		log.debug("修改用户信息前的验证，前端信息==={}===判断是否是邮箱==={}===", phoneOrEMail, isEMail);
		String code = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
		if (isEMail) {
			try {
				eMailService.sendVerificationCode(phoneOrEMail, code);
			} catch (Exception e) {
				log.error("邮件发送失败，错误信息为==={}", e.getMessage());
				return Result.error("").message("邮件发送失败，请重试");
			}
		} else {
			try {
				//TODO 发送短信
				aliSmsService.sendSmsVerificationCode(phoneOrEMail, code);
			} catch (Exception e) {
				log.error("短信发送失败，错误信息为==={}", e.getMessage());
				return Result.error("").message("短信发送失败，请重试");
			}
		}
		log.debug("返回前端的验证码==={}", code);
		return Result.ok(code).message("验证码发送成功");
	}

	@ApiOperation(value = "用户支付宝支付接口", notes = "用户订单支付")
	@PostMapping("/alipay")
	public Result<String> alipay(@Validated @RequestBody PaymentDTO paymentDTO) {
		log.debug("用户支付，前端信息==={}", paymentDTO);
		String orderNO = RandomGeneratorUtil.generateTradeNo();
		String alipayForm = alipayService.toPay(new PaymentBO(paymentDTO));

		if (paymentDTO.getVehicleType().equals("飞机")) {

			List<FlightTicket> flightTickets = new ArrayList<>();
			for (String passengerName : paymentDTO.getPassengerNames()) {
				FlightTicket flightTicket = new FlightTicket(paymentDTO.getFlightOrTrainNO(),
						orderNO,
						paymentDTO.getStartTime(),
						paymentDTO.getEndTime(),
						paymentDTO.getStartPortOrStation(),
						paymentDTO.getEndPortOrStation(),
						StatusUtil.FLIGHT_TICKET_STATUS_UNPAID,
						passengerName,
						paymentDTO.getSeatType(),
						paymentDTO.getMoney());
				flightTicket.setTicketId(1000);
				flightTickets.add(flightTicket);
			}
//			FlightTicket flightTicket = new FlightTicket(paymentDTO);
//			flightTicket.setTicketStatus(StatusUtil.FLIGHT_TICKET_STATUS_UNPAID);
//			flightTicket.setOrderId(orderNO);
			try {
				for (FlightTicket flightTicket : flightTickets) {
					flightTicketService.mySave(flightTicket);
				}
			} catch (Exception e) {
				throw new GlobalException("飞机票保存失败，但订单已创建", e);
			}
		} else if (paymentDTO.getVehicleType().equals("火车")) {

			List<TrainTicket> trainTickets = new ArrayList<>();
			for (String passengerName : paymentDTO.getPassengerNames()) {
				TrainTicket trainTicket = new TrainTicket(paymentDTO.getFlightOrTrainNO(),
						orderNO,
						paymentDTO.getStartTime(),
						paymentDTO.getEndTime(),
						paymentDTO.getStartPortOrStation(),
						paymentDTO.getEndPortOrStation(),
						StatusUtil.TRAIN_TICKET_STATUS_UNPAID,
						passengerName,
						paymentDTO.getSeatType(),
						paymentDTO.getMoney());
				trainTickets.add(trainTicket);
			}
//			TrainTicket trainTicket = new TrainTicket(paymentDTO);
//			trainTicket.setTicketStatus(StatusUtil.TRAIN_TICKET_STATUS_UNPAID);
//			trainTicket.setOrderId(orderNO);
			try {
				trainTickets.forEach(trainTicket -> trainTicketService.mySave(trainTicket));
			} catch (Exception e) {
				throw new GlobalException("火车票保存失败，但订单已创建", e);
			}
		} else {
			throw new GlobalException("支付失败，未知交通工具，但订单已创建");
		}
		return Result.ok(alipayForm).message("正在跳转支付页面...");
	}

	@ApiOperation(value = "用户支付宝退款接口", notes = "支付宝退款接口")
	@PostMapping("/alipay-refund")
	public Result<AlipayTradeRefundResponse> alipayRefund(RefundDTO refundDTO) {
		return Result.ok(alipayService.refund(refundDTO)).message("正在退款...");
	}

}

