<template>
  <el-row type="flex" justify="center" class="nav">
    <el-col :xs="20" :sm="18" :md="16" :lg="14" :xl="14">
      <el-card
        shadow="always"
        class="info-el-card"
        :body-style="{ padding: 0 }"
      >
        <el-row
          type="flex"
          justify="space-between"
          align="middle"
          style="height: 120px"
        >
          <el-col :xs="5" :sm="4" :md="4" :lg="4" :xl="4">
            <div class="item company">航空公司</div>
          </el-col>
          <el-col :xs="10" :sm="8" :md="10" :lg="10" :xl="10">
            <div class="item time">15:00 - 16:00</div>
          </el-col>
          <el-col :xs="5" :sm="3" :md="3" :lg="3" :xl="3">
            <div class="item price">￥1234</div>
          </el-col>
          <el-col :xs="4" :sm="3" :md="3" :lg="3" :xl="3">
            <el-button type="primary" size="medium">购买</el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
export default {};
</script>

<style>
.nav {
  margin: 5px 0;
}
.info-el-card {
  min-height: 120px;
}
.info-el-card:hover {
  box-shadow: 0 2px 12px 1px rgba(0, 0, 0, 0.3) !important;
}
.company,
.time,
.price {
  text-align: center;
  line-height: 70px;
}
.item {
  height: 70px;
}
</style>
