<template>
  <div>
    <v-header class="header"></v-header>
    <router-view></router-view>
  </div>
</template>

<script>
import VHeader from "./components/YHeader.vue";
export default {
  name: "App",
  components: {
    VHeader,
  },
};
</script>

<style>
* {
  padding: 0;
  margin: 0;
}
.header {
  z-index: 3;
}
.el-loading-spinner {
  font-size: 10px !important;
}
</style>
