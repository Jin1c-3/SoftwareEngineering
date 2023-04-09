<template>
  <div>
    <el-row type="flex" justify="center" class="el-row-forCarousel">
      <el-col :xs="24" :sm="24" :md="16">
        <el-carousel :interval="4000" type="card">
          <el-carousel-item
            v-for="item in 6"
            :key="item"
            class="el-carousel-item--plus"
          >
            <h3 class="medium">广告位{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
      </el-col>
    </el-row>
    <el-row>
      <el-col
        :xs="24"
        :sm="12"
        :md="8"
        :lg="6"
        :xl="6"
        v-for="item in 8"
        :key="item"
      >
        <el-card class="el-card-plus"> </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" class="el-col-chart">
        <el-card :body-style="{ padding: 0 }">
          <y-echart-1></y-echart-1
        ></el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" class="el-col-chart"
        ><el-card :body-style="{ padding: 0 }">
          <y-echart-2></y-echart-2></el-card
      ></el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" class="el-col-chart">
        <el-card :body-style="{ padding: 0 }">
          <y-echart-3></y-echart-3></el-card
      ></el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" class="el-col-chart"
        ><el-card :body-style="{ padding: 0 }"
          ><y-echart-4></y-echart-4> </el-card
      ></el-col>
    </el-row>
  </div>
</template>

<script>
import YEchart1 from "@/components/YEchart1.vue";
import YEchart2 from "@/components/YEchart2.vue";
import YEchart3 from "@/components/YEchart3.vue";
import YEchart4 from "@/components/YEchart4.vue";
export default {
  components: {
    YEchart1,
    YEchart2,
    YEchart3,
    YEchart4,
  },
};
</script>

<style scoped>
.el-row-forCarousel {
  margin-top: 30px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}
.el-carousel__item:nth-child(2n) {
  background-color: #9ebeeb;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #7a9ec7;
}
.el-row-forCard {
  flex-wrap: wrap;
}
.el-card-plus:hover {
  box-shadow: 0 2px 15px 1px rgba(0, 0, 0, 0.2);
}
@media screen and (max-width: 768px) {
  .el-carousel-item--plus {
    height: 200px;
  }
  .el-card-plus {
    height: 300px;
    margin: 20px !important;
  }
}
@media screen and (min-width: 768px) and (max-width: 992px) {
  .el-carousel-item--plus {
    height: 250px;
  }
  .el-card-plus {
    height: 300px;
    margin: 20px !important;
  }
}
@media screen and (min-width: 992px) {
  .el-carousel-item--plus {
    height: 300px;
  }
  .el-card-plus {
    height: 200px;
    margin: 40px !important;
  }
}
.el-col-chart {
  padding: 30px 40px;
}
</style>
