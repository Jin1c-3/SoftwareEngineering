import Vue from "vue";
import App from "./App.vue";

import VueRouter from "vue-router";
import router from "./router";
import store from "./store";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import * as echarts from "echarts";
import qs from "qs";

Vue.config.productionTip = false;

Vue.use(VueRouter);
Vue.use(ElementUI);
Vue.prototype.$echarts = echarts;
Vue.prototype.$qs = qs;

new Vue({
  render: (h) => h(App),
  store,
  router,
}).$mount("#app");
