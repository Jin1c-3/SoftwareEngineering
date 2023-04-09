<template>
  <el-row type="flex" justify="start" class="main-row">
    <el-col :xs="2" :sm="3" :md="3" :lg="3" :xl="3">
      <el-row class="tac">
        <el-col :span="24">
          <el-menu
            :default-active="this.$router.self"
            class="el-menu-vertical-demo"
            router
            @select="select"
          >
            <el-menu-item index="/self/info" class="el-menu-item-plus">
              <i
                class="el-icon-location"
                style="line-height: -1 !important"
              ></i>
              <span class="hidden-xs-only">个人信息</span>
            </el-menu-item>
            <el-menu-item index="/self/order" class="el-menu-item-plus">
              <i class="el-icon-menu"></i>
              <span slot="title" class="hidden-xs-only">订单中心</span>
            </el-menu-item>
            <el-menu-item index="/self/passenger" class="el-menu-item-plus">
              <i class="el-icon-document"></i>
              <span slot="title" class="hidden-xs-only">乘客中心</span>
            </el-menu-item>
            <el-menu-item index="/self/help" class="el-menu-item-plus">
              <i class="el-icon-setting"></i>
              <span slot="title" class="hidden-xs-only">帮助中心</span>
            </el-menu-item>
          </el-menu>
        </el-col>
      </el-row>
    </el-col>
    <el-col :xs="22" :sm="21" :md="21" :lg="21" :xl="21" class="side">
      <router-view></router-view>
    </el-col>
    <el-backtop target=".side"></el-backtop>
  </el-row>
</template>

<script>
import "element-ui/lib/theme-chalk/display.css";
export default {
  methods: {
    select(idx, idxPath) {
      console.log(idx, idxPath);
    },
  },
};
</script>

<style scoped>
.main-row {
  flex-wrap: wrap;
  height: 918px;
}
.tac {
  height: 100%;
  background-color: #ffffff;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.el-menu-item-plus {
  width: 100%;
  display: flex;
  justify-content: space-around;
}
.side {
  height: 918px;
  overflow-y: scroll;
}
i::before {
  line-height: 56px;
}
@media screen and (max-width: 768px) {
  i {
    margin: 0 !important;
  }
}
</style>
