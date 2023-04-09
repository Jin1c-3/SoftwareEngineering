<template>
  <el-row type="flex" justify="center" class="nav">
    <el-col :xs="20" :sm="18" :md="16" :lg="12" :xl="12">
      <el-card shadow="always" class="nav-el-card" :body-style="{ padding: 0 }">
        <el-row type="flex" justify="space-around">
          <el-col :xs="12" :sm="8" :md="5" :lg="5" :xl="5">
            <el-cascader
              v-model="from"
              placeholder="出发地"
              :options="option"
              filterable
              :props="pro"
              :clearable="true"
              :show-all-levels="false"
            ></el-cascader>
          </el-col>
          <el-col :xs="12" :sm="8" :md="5" :lg="5" :xl="5">
            <el-cascader
              v-model="to"
              placeholder="目的地"
              :options="option"
              filterable
              :props="pro"
              :clearable="true"
              :show-all-levels="false"
            ></el-cascader>
          </el-col>
          <el-col :xs="12" :sm="8" :md="5" :lg="5" :xl="5">
            <el-date-picker
              v-model="date"
              type="date"
              placeholder="选择日期"
              editable
              style="width: 100%"
              :clearable="true"
            >
            </el-date-picker>
          </el-col>
          <el-col :xs="1" :sm="1" :md="1" :lg="1" :xl="1">
            <el-button
              icon="el-icon-search"
              circle
              @click="easySearch"
            ></el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
export default {
  props: ["option"],
  data() {
    return {
      from: "",
      to: "",
      date: "",
      pro: {
        expandTrigger: "hover",
      },
    };
  },
  methods: {
    easySearch() {
      console.log(this.from, this.to, this.date);
    },
  },
};
</script>

<style scoped>
.nav {
  margin: 30px 0;
}
.nav-el-card {
  border-radius: 25px;
}
.nav-el-card:hover {
  box-shadow: 0 2px 15px 1px rgba(0, 0, 0, 0.2);
}
</style>
