----管理员表  00001为root管理员
create table super_usr(
	super_usr_ID char(5) primary key,
	super_usr_name nvarchar(20),
	super_usr_pwd varchar(30) check (len(super_usr_pwd) between 6 and 30)
)
----服务商表
create table service_provider(
	service_provider_ID int primary key identity(1,1),
<<<<<<< Updated upstream
	service_provider_pwd varchar(30) check (len(service_provider_pwd) between 6 and 30),	--密码6-30位
	service_provider_name nvarchar(30),
	push_money float	--约定提成
)
----飞机信息表
create table aircraft(
	aircraft_ID varchar(20) primary key,
	aircraft_type varchar(20),
	aircraft_T_num int,	--头等舱
	aircraft_M_num int,	--商务舱
	aircraft_L_num int,	--经济舱
	aircraft_status nvarchar(3) check (aircraft_status = '可用' or aircraft_status = '不可用' ) default('可用'),
	service_provider_ID int ,
	foreign key(service_provider_ID)  references service_provider(service_provider_ID)
)
----航班信息表
create table flight_info(
	--班期
	 T varchar(18),
	 flight_ID varchar(25) primary key,
	 flight_start_city nvarchar(10),
	 flight_end_city nvarchar(10),
	 flight_start_port nvarchar(10),
	 flight_end_port nvarchar(10),
	 flight_status nvarchar(20),
	 aircraft_type varchar(20),
	 direct_flag nvarchar(2) check(direct_flag = '是' or direct_flag = '否'),
	 aircraft_ID varchar(20),
	 foreign key(aircraft_ID) references Aircraft(aircraft_ID)
)
----飞机行程信息表
create table flight_time(
	 flight_start_time datetime,   
	 flight_end_time datetime,
	 flight_ID varchar(25),
	 ----航班号  外键
	 flight_start_city nvarchar(10),
	 flight_end_city nvarchar(10),
	 flight_start_port nvarchar(10),
	 flight_end_port nvarchar(10),
	 --站序
	 flight_order int default(1),
	 flight_price int,
	  foreign key (flight_ID) references flight_info(flight_ID)
)

---用户
create table usr(
	usr_account varchar(20) primary key,  --用户账号 注册时随机分配
	usr_ID varchar(20) unique,   --uuid
	usr_email varchar(35) unique,  
	usr_avater varchar(70) unique, --存头像地址
	usr_viplevel int,
	usr_pwd varchar(30)
)


--总订单表 包括飞机火车
create table whole_order(
	order_ID int identity(1,1) primary key,--订单编号
	order_time datetime,
	order_status nvarchar(10),  --是否已支付
	vehicle_type nvarchar(10),
	usr_ID varchar(20) 
)

create table flight_ticket(
	  ---主键
	 ticket_ID int identity(1,1) primary key,
	 --外键
	 flight_ID varchar(25),
	 order_ID int,--订单编号  自增

	 flight_start_time datetime,
	 flight_end_time datetime,
	 ticket_status nvarchar(10),
	 passenger_name nvarchar(15),
	 seat_type nvarchar(10),
	 flight_start_port nvarchar(10),
	 flight_end_port nvarchar(10),
	 flight_price int,
	 foreign key(flight_ID) references flight_info(flight_ID),
	 foreign key(order_ID) references whole_order(order_ID)
)

