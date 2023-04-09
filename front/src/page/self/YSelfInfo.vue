<template>
  <div id="selfInfo">
    <el-row type="flex" justify="center">
      <el-col :span="24"
        ><el-descriptions
          class="el-descriptions-plus"
          title="个人信息"
          :column="1"
          direction="vertical"
          :border="true"
        >
          <el-descriptions-item
            label="头像"
            content-class-name="el-descriptions-item-plus"
          >
            <el-image class="img" :src="url" :preview-src-list="srcList">
            </el-image>
          </el-descriptions-item>
          <el-descriptions-item
            label="ID"
            content-class-name="el-descriptions-item-plus"
            >{{ id }}</el-descriptions-item
          >
          <el-descriptions-item
            label="账号"
            content-class-name="el-descriptions-item-plus"
            >{{ account }}</el-descriptions-item
          >
          <el-descriptions-item
            label="密码"
            content-class-name="el-descriptions-item-plus"
            >{{ password }}</el-descriptions-item
          >
          <el-descriptions-item
            label="电话号码"
            content-class-name="el-descriptions-item-plus"
            >{{ phone }}</el-descriptions-item
          >
          <el-descriptions-item
            label="邮箱"
            content-class-name="el-descriptions-item-plus"
          >
            {{ email }}
          </el-descriptions-item>
          <el-descriptions-item
            label="邮箱"
            content-class-name="el-descriptions-item-plus"
          >
            {{ email }}
          </el-descriptions-item>
          <el-descriptions-item
            label="邮箱"
            content-class-name="el-descriptions-item-plus"
          >
            {{ email }}
          </el-descriptions-item>
          <el-descriptions-item
            label="邮箱"
            content-class-name="el-descriptions-item-plus"
          >
            {{ email }}
          </el-descriptions-item>
          <el-descriptions-item
            label="邮箱"
            content-class-name="el-descriptions-item-plus"
          >
            {{ email }}
          </el-descriptions-item>
        </el-descriptions></el-col
      >
    </el-row>
    <el-row type="flex" justify="center">
      <el-col :span="2">
        <el-button type="primary">修改信息</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: "id",
      account: "account",
      password: "password",
      phone: "phone",
      email: "email",
      url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      srcList: [
        "https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg",
        "https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg",
      ],
    };
  },
};
</script>

<style>
#selfInfo .el-descriptions-plus {
  margin: 40px;
}
#selfInfo .img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  transition: all 0.3s;
}
#selfInfo .img:hover {
  width: 200px;
  height: 200px;
}
#selfInfo .el-descriptions-item-plus {
  transition: all 0.3s;
}
#selfInfo .el-descriptions-item-plus:hover {
  background-color: rgb(226, 225, 225);
}
</style>
