<template>
  <el-row>
    <el-col :xs="0" :sm="8" :md="8" :lg="8" :xl="8">
      <div class="left">
        <el-image
          :src="require('@/assets/planet.png')"
          fit="contain"
        ></el-image>
        <div>网页名</div>
      </div>
    </el-col>
    <el-col :xs="24" :sm="16" :md="16" :l="16" g :xl="16">
      <el-form
        ref="registerForm"
        :model="registerForm"
        label-width="80px"
        size="mini"
        :inline="true"
        :rules="rules"
        v-loading="loading"
      >
        <el-row type="flex" justify="center">
          <el-form-item prop="account">
            <el-input
              v-model.trim="registerForm.account"
              class="el-input-plus"
              :clearable="true"
              prefix-icon="el-icon-user"
              placeholder="账号"
            ></el-input>
          </el-form-item>
        </el-row>
        <el-row type="flex" justify="center">
          <el-form-item prop="password">
            <el-input
              v-model.trim="registerForm.password"
              class="el-input-plus"
              :clearable="true"
              :show-password="true"
              prefix-icon="el-icon-lock"
              placeholder="密码"
            ></el-input>
          </el-form-item>
        </el-row>
        <el-row type="flex" justify="center">
          <el-form-item prop="phone">
            <el-input
              v-model.trim="registerForm.phone"
              class="el-input-plus"
              :clearable="true"
              prefix-icon="el-icon-phone-outline"
              placeholder="电话号码"
            ></el-input>
          </el-form-item>
        </el-row>
        <el-row type="flex" justify="center">
          <el-form-item prop="email">
            <el-input
              v-model.trim="registerForm.email"
              class="el-input-plus"
              :clearable="true"
              prefix-icon="el-icon-message"
              placeholder="邮箱"
            ></el-input>
          </el-form-item>
        </el-row>
        <el-row type="flex" justify="center">
          <el-form-item>
            <el-button
              type="primary"
              plain
              class="el-button-plus"
              @click="onSubmit"
              >注册</el-button
            >
          </el-form-item>
        </el-row>
        <el-row type="flex" justify="center">
          已有帐号？
          <el-link
            type="primary"
            @click="
              changeLoginDialog();
              changeRegisterDialog();
              resetRegisterInfo();
            "
            >登录</el-link
          >
        </el-row>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
import { mapActions, mapMutations } from "vuex";
export default {
  components: {},
  data() {
    return {
      loading: false,
      registerForm: {
        account: "",
        password: "",
        phone: "",
        email: "",
      },
      rules: {
        account: [
          { required: true, message: "请输入账号", trigger: "blur" },
          {
            pattern: /^[A-Za-z0-9\u4e00-\u9fa5]{6,15}$/,
            message: "6到15位的数字、大小写字母、中文",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            pattern: /^[A-Za-z0-9]{6,12}$/,
            message: "6到12位的数字、大小写字母",
            trigger: "blur",
          },
        ],
        phone: [
          { required: true, message: "请输入电话号码", trigger: "blur" },
          {
            pattern:
              /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/,
            message: "电话号码格式不符",
            trigger: "blur",
          },
        ],
        email: [
          { required: true, message: "请输入电子邮箱", trigger: "blur" },
          {
            type: "email",
            message: "电子邮箱格式不符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    onSubmit() {
      this.loading = true;
      this.checkRegister(this.registerForm);
      if (this.$store.state.register.registerState == true) {
        this.$store.state.register.registerState =
          !this.$store.state.register.registerState;
        this.$message({
          message: "注册成功",
          type: "success",
          duration: 1000,
        });
        this.loading = false;
        this.changeRegisterDialog();
        this.changeLoginDialog();
      } else {
        this.$message({
          message: "注册失败",
          type: "error",
          duration: 1000,
        });
      }
      this.loading = false;
    },
    ...mapActions("register", { checkRegister: "checkRegister" }),
    ...mapMutations("register", {
      changeRegisterDialog: "changeRegisterDialog",
    }),
    ...mapMutations("login", { changeLoginDialog: "changeLoginDialog" }),
    resetRegisterInfo() {
      this.$store.state.register.address.resetFields();
    },
  },
  mounted() {
    this.$store.state.register.address = this.$refs.registerForm;
  },
};
</script>

<style scoped>
.left {
  width: 100%;
  height: 250px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 0;
  border-right: 0.5px solid rgb(212, 212, 212);
}
.el-input-plus {
  transition: all 0.3s;
}
.el-input-plus {
  width: 110%;
}
.el-button-plus {
  width: 150px;
  box-shadow: 0 0 5px 0.5px rgb(168, 228, 255);
  transition: all 0.3s;
}
.el-button-plus:hover {
  box-shadow: 1px 0 10px 1px rgb(78, 199, 255);
}
</style>
