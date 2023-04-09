<template>
  <div>
    <y-search :option="options"></y-search>
    <y-plane-filter v-show="isShow"></y-plane-filter>
    <y-plane-info
      v-show="isShow"
      v-for="index in 9"
      :key="index"
    ></y-plane-info>
  </div>
</template>

<script>
import constData from "../data/data";
import YSearch from "@/components/YSearch.vue";
import YPlaneFilter from "@/components/YPlaneFilter.vue";
import YPlaneInfo from "@/components/YPlaneInfo.vue";
export default {
  components: {
    YSearch,
    YPlaneFilter,
    YPlaneInfo,
  },
  data() {
    return {
      options: constData.options,
      isShow: false,
    };
  },
};
</script>

<style scoped></style>
