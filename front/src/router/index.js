import VueRouter from "vue-router";

import YHome from "../page/YHome";
import YPlane from "../page/YPlane";
import YTrain from "../page/YTrain";
import YSelf from "../page/YSelf";
import YSelfInfo from "../page/self/YSelfInfo";
import YSelfOrder from "../page/self/YSelfOrder";
import YSelfPassenger from "../page/self/YSelfPassenger";
import YSelfHelp from "../page/self/YSelfHelp";

export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      redirect: "home",
    },
    {
      path: "/home",
      name: "home",
      component: YHome,
    },
    {
      path: "/plane",
      name: "plane",
      component: YPlane,
    },
    {
      path: "/train",
      name: "train",
      component: YTrain,
    },
    {
      path: "/self",
      name: "self",
      component: YSelf,
      children: [
        {
          path: "info",
          name: "selfInfo",
          component: YSelfInfo,
        },
        {
          path: "order",
          name: "selfOrder",
          component: YSelfOrder,
        },
        {
          path: "passenger",
          name: "selfPassenger",
          component: YSelfPassenger,
        },
        {
          path: "help",
          name: "selfHelp",
          component: YSelfHelp,
        },
      ],
    },
  ],
});
