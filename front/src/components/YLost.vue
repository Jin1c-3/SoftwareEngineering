<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-steps :active="active" :align-center="true">
          <el-step
            title="步骤 1"
            description="输入绑定账号的相关邮箱/电话号码"
          ></el-step>
          <el-step title="步骤 2" description="请输入获取到的验证码"></el-step>
          <el-step title="步骤 3" description="输入你要修改的密码"></el-step>
        </el-steps>
      </el-col>
    </el-row>
    <el-row type="flex" justify="center">
      <el-col :span="16">
        <el-input
          v-model="eP"
          placeholder="邮箱/电话"
          class="el-input-plus"
          v-if="active == 0"
        ></el-input>
        <el-input
          v-model="vC"
          placeholder="验证码"
          class="el-input-plus"
          v-if="active == 1"
        ></el-input>
        <el-input
          v-model="pW"
          placeholder="密码"
          class="el-input-plus"
          v-if="active == 2"
        ></el-input>
        <el-input
          v-model="pW2"
          placeholder="确认密码"
          class="el-input-plus"
          v-if="active == 2"
        ></el-input>
      </el-col>
    </el-row>
    <el-row type="flex" justify="center">
      <el-col :xs="8" :sm="4">
        <el-button style="margin-top: 20px" @click="pre">上一步</el-button>
      </el-col>
      <el-col :xs="8" :sm="4">
        <el-button style="margin-top: 20px" @click="next">下一步</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      active: 0,
      eP: "",
      vC: "",
      pW: "",
      pW2: "",
    };
  },
  methods: {
    pre() {
      if (this.active-- < 1) this.active = 0;
    },
    next() {
      if (this.active == 0) {
        if (this.eP.trim() != "") {
          this.active++;
        } else {
          this.$message({
            message: "请输入完整的邮箱或电话号码",
            type: "warning",
          });
        }
      } else if (this.active == 1) {
        this.active++;
      } else if (this.active == 2) {
        this.$notify({
          title: "成功",
          message: "用户信息成功修改",
          type: "success",
        });
      }
    },
  },
};
</script>

<style scoped>
.el-input-plus {
  margin: 15px 0;
}
</style>
