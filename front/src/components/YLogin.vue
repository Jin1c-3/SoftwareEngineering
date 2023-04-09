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
        ref="loginForm"
        :model="loginForm"
        label-width="80px"
        size="mini"
        :inline="true"
        v-loading="loading"
      >
        <el-row type="flex" justify="center">
          <el-form-item prop="account">
            <el-input
              v-model.trim="loginForm.account"
              :clearable="true"
              prefix-icon="el-icon-user"
              placeholder="账号/电话号码/邮箱"
            ></el-input>
          </el-form-item>
        </el-row>
        <el-row type="flex" justify="center">
          <el-form-item prop="password">
            <el-input
              v-model.trim="loginForm.password"
              :clearable="true"
              :show-password="true"
              prefix-icon="el-icon-lock"
              placeholder="密码"
            ></el-input>
          </el-form-item>
        </el-row>
        <el-row type="flex" justify="end">
          <el-link
            type="primary"
            class="forget-password"
            @click="
              changeFoundDialog();
              changeLoginDialog();
            "
            >忘记密码</el-link
          >
        </el-row>
        <el-row type="flex" justify="center">
          <el-form-item>
            <el-button
              type="primary"
              plain
              class="el-button-plus"
              @click="onSubmit"
              >登录</el-button
            >
          </el-form-item>
        </el-row>
        <el-row type="flex" justify="center">
          还未注册？
          <el-link
            type="primary"
            @click="
              changeLoginDialog();
              changeRegisterDialog();
              resetLoginInfo();
            "
            >注册</el-link
          >
        </el-row>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
import { mapActions, mapMutations } from "vuex";
export default {
  data() {
    return {
      loading: false,
      loginForm: {
        account: "",
        password: "",
      },
    };
  },
  methods: {
    ...mapActions("login", { checkLogin: "checkLogin" }),
    ...mapMutations("register", {
      changeRegisterDialog: "changeRegisterDialog",
    }),
    ...mapMutations("login", {
      changeLoginDialog: "changeLoginDialog",
      changeFoundDialog: "changeFoundDialog",
    }),
    onSubmit() {
      (this.loading = true), this.checkLogin(this.loginForm);
      if (this.$store.state.login.loginState == true) {
        this.$message({
          message: "登录成功",
          type: "success",
          duration: 1000,
        });
        this.loading = false;
        this.changeLoginDialog();
      } else {
        this.$message({
          message: "登录失败",
          type: "error",
          duration: 1000,
        });
      }
      this.loading = false;
    },
    resetLoginInfo() {
      this.$store.state.login.address.resetFields();
    },
  },
  mounted() {
    this.$store.state.login.address = this.$refs.loginForm;
  },
};
</script>

<style scoped>
.left {
  width: 100%;
  height: 150px;
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
.forget-password {
  font-size: 10px;
  margin-right: 60px;
  margin-bottom: 10px;
}
@media screen and (max-width: 768px) {
  .forget-password {
    margin-right: 30px;
  }
}
@media screen and (min-width: 768px) {
  .forget-password {
    margin-right: 60px;
  }
}
</style>
