<template>
  <div class="header">
    <el-row class="el-row-plus" type="flex" justify="center">
      <el-col :xs="0" :sm="6" :md="6" :lg="8" :xl="8">
        <div class="img">
          <el-image
            :src="require('@/assets/logo.png')"
            fit="contain"
          ></el-image>
        </div>
      </el-col>
      <el-col :xs="24" :sm="12" :md="10" :lg="8" :xl="8">
        <el-menu
          :default-active="this.$router.self"
          mode="horizontal"
          class="el-menu-plus"
          text-color="#84C1FF"
          active-text-color="#409EFF"
          router
          @select="select"
        >
          <el-menu-item index="/home" class="el-menu-item-plus"
            >首页</el-menu-item
          >
          <el-menu-item index="/plane" class="el-menu-item-plus"
            >飞机</el-menu-item
          >
          <el-menu-item index="/train" class="el-menu-item-plus"
            >火车</el-menu-item
          >
          <el-submenu index="set" class="el-menu-item-plus">
            <template slot="title">设置</template>
            <el-submenu index="language" class="el-menu-submenu-plus">
              <template slot="title">语言</template>
              <el-menu-item index="chinese" class="el-menu-submenu-item-plus"
                >中文</el-menu-item
              >
              <el-menu-item index="english" class="el-menu-submenu-item-plus"
                >English</el-menu-item
              >
            </el-submenu>
            <el-submenu index="theme" class="el-menu-submenu-plus">
              <template slot="title">主题</template>
              <el-menu-item index="morning" class="el-menu-submenu-item-plus"
                >白天</el-menu-item
              >
              <el-menu-item index="night" class="el-menu-submenu-item-plus"
                >夜间</el-menu-item
              >
            </el-submenu>
          </el-submenu>
          <el-submenu index="/admin" class="el-menu-item-plus">
            <template slot="title">{{ account }}</template>
            <el-menu-item index="/self" class="el-menu-submenu-item-plus"
              >个人主页</el-menu-item
            >
            <el-menu-item
              index="login"
              class="el-menu-submenu-item-plus"
              @click="login"
              >登录</el-menu-item
            >
            <el-menu-item
              index="register"
              class="el-menu-submenu-item-plus"
              @click="register"
              >注册</el-menu-item
            >
            <el-menu-item index="logout" class="el-menu-submenu-item-plus"
              >登出</el-menu-item
            >
          </el-submenu>
        </el-menu>
      </el-col>
    </el-row>
    <el-dialog
      title="注册"
      :visible.sync="registerDialogVisible"
      append-to-body
      @closed="resetRegisterInfo"
    >
      <y-register></y-register>
    </el-dialog>

    <el-dialog
      title="登录"
      :visible.sync="loginDialogVisible"
      append-to-body
      @closed="resetLoginInfo"
    >
      <y-login></y-login>
    </el-dialog>

    <el-dialog
      title="找回密码"
      :visible.sync="foundDialogVisible"
      append-to-body
      @closed="backLogin"
    >
      <y-lost></y-lost>
    </el-dialog>
  </div>
</template>

<script>
import YRegister from "./YRegister.vue";
import YLogin from "./YLogin.vue";
import YLost from "./YLost.vue";
import { mapState } from "vuex";
export default {
  name: "YFooter",
  components: {
    YRegister,
    YLogin,
    YLost,
  },
  data() {
    return {
      account: "用户",
      registerDialogVisible: false,
      loginDialogVisible: false,
      foundDialogVisible: false,
    };
  },
  methods: {
    select(idx, idxPath) {
      console.log(idx, idxPath);
    },
    login() {
      console.log(this);
      this.loginDialogVisible = !this.loginDialogVisible;
    },
    register() {
      this.registerDialogVisible = !this.registerDialogVisible;
    },
    resetRegisterInfo() {
      this.$store.state.register.address.resetFields();
    },
    resetLoginInfo() {
      this.$store.state.login.address.resetFields();
    },
    backLogin() {
      this.loginDialogVisible = !this.loginDialogVisible;
    },
  },
  computed: {
    ...mapState("user", ["userForm"]),
    ...mapState("register", ["rDV"]),
    ...mapState("login", ["lDV", "fDV"]),
  },
  watch: {
    rDV: {
      handler() {
        this.registerDialogVisible = !this.registerDialogVisible;
      },
    },
    lDV: {
      handler() {
        this.loginDialogVisible = !this.loginDialogVisible;
      },
    },
    fDV: {
      handler() {
        this.foundDialogVisible = !this.foundDialogVisible;
      },
    },
    userForm: {
      deep: true,
      handler() {
        this.account = this.userForm.account;
      },
    },
  },
};
</script>

<style>
label {
  text-align: center !important;
}
.header {
  position: sticky;
  top: 0;
  width: 100%;
  height: 100%;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.img {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
}
.el-image-plus {
  width: 25%;
  height: 25%;
}
.el-row-plus {
  background-color: #fff;
}
.el-img-plus {
  background-color: #fff;
}
.el-menu-plus {
  display: flex;
  justify-content: flex-end;
}
.el-menu-item-plus {
  width: 20%;
  text-align: center;
}
.el-menu-item:hover {
  color: #409eff !important;
  background-color: #d6e9fe !important;
}
.el-menu--collapse .el-menu .el-submenu,
.el-menu--popup {
  min-width: 120px !important;
}
.el-menu-submenu-item-plus {
  min-width: 120px !important;
}

@media screen and (max-width: 768px) {
  .el-dialog {
    width: 350px !important;
  }
}
@media screen and (min-width: 768px) {
  .el-dialog {
    width: 600px !important;
  }
}
</style>
