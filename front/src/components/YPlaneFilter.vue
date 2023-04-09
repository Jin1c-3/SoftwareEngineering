<template>
  <el-row type="flex" justify="center" class="nav">
    <el-col :xs="20" :sm="18" :md="16" :lg="14" :xl="14">
      <el-card
        shadow="always"
        class="filter-el-card"
        :body-style="{ padding: 0 }"
      >
        <el-row
          type="flex"
          justify="start"
          align="middle"
          style="min-height: 50px"
        >
          <el-col :xs="4" :sm="5" :md="2" :lg="2" :xl="2">
            <el-popover
              placement="bottom-start"
              title="标题"
              width="100"
              trigger="click"
              content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。"
            >
              <el-checkbox
                :indeterminate="isIndeterminate"
                v-model="checkAll"
                @change="handleCheckAllChange"
                >全选</el-checkbox
              >
              <div style="margin: 15px 0"></div>
              <el-checkbox-group
                v-model="checkedCities"
                @change="handleCheckedCitiesChange"
              >
                <el-checkbox v-for="city in cities" :label="city" :key="city">{{
                  city
                }}</el-checkbox>
              </el-checkbox-group>
              <el-button slot="reference" size="mini" class="el-button-plus"
                >航空公司</el-button
              >
            </el-popover>
          </el-col>
          <el-col :xs="4" :sm="5" :md="2" :lg="2" :xl="2">
            <el-popover
              placement="bottom-start"
              title="标题"
              width="200"
              trigger="click"
              content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。"
            >
              <el-checkbox
                :indeterminate="isIndeterminate"
                v-model="checkAll"
                @change="handleCheckAllChange"
                >全选</el-checkbox
              >
              <div style="margin: 15px 0"></div>
              <el-checkbox-group
                v-model="checkedCities"
                @change="handleCheckedCitiesChange"
              >
                <el-checkbox v-for="city in cities" :label="city" :key="city">{{
                  city
                }}</el-checkbox>
              </el-checkbox-group>
              <el-button slot="reference" size="mini" class="el-button-plus"
                >起抵时间</el-button
              >
            </el-popover>
          </el-col>
          <el-col :xs="4" :sm="4" :md="2" :lg="2" :xl="2">
            <el-popover
              placement="bottom-start"
              title="标题"
              width="200"
              trigger="click"
              content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。"
            >
              <el-checkbox
                :indeterminate="isIndeterminate"
                v-model="checkAll"
                @change="handleCheckAllChange"
                >全选</el-checkbox
              >
              <div style="margin: 15px 0"></div>
              <el-checkbox-group
                v-model="checkedCities"
                @change="handleCheckedCitiesChange"
              >
                <el-checkbox v-for="city in cities" :label="city" :key="city">{{
                  city
                }}</el-checkbox>
              </el-checkbox-group>
              <el-button slot="reference" size="mini" class="el-button-plus"
                >舱位</el-button
              >
            </el-popover>
          </el-col>
          <el-col :xs="3" :sm="2" :md="2" :lg="2" :xl="2">
            <el-dropdown trigger="click">
              <span class="el-dropdown-link">
                排序<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>黄金糕</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-col>
        </el-row>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
const cityOptions = ["上海", "北京", "广州", "深圳"];
export default {
  data() {
    return {
      checkAll: false,
      checkedCities: ["上海", "北京"],
      cities: cityOptions,
      isIndeterminate: true,
    };
  },
  methods: {
    handleCheckAllChange(val) {
      this.checkedCities = val ? cityOptions : [];
      this.isIndeterminate = false;
    },
    handleCheckedCitiesChange(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.cities.length;
      this.isIndeterminate =
        checkedCount > 0 && checkedCount < this.cities.length;
    },
  },
};
</script>

<style scoped>
.nav {
  margin: 20px 0;
}
.filter-el-card {
  min-height: 50px;
}
.el-button-plus {
  border: none;
  outline: none;
}
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>
