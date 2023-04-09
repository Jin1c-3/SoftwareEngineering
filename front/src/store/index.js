import Vue from "vue";

import Vuex from "vuex";
import axios from "axios";
import webConfig from "@/data/config";

Vue.use(Vuex);

const user = {
  namespaced: true,
  state: {
    userForm: {
      id: "",
      account: "",
      password: "",
      email: "",
      phone: "",
      avatar: "",
    },
  },
};

const register = {
  namespaced: true,
  state: {
    rDV: false,
    address: "",
    registerState: false,
  },
  mutations: {
    changeRegisterDialog(state) {
      state.rDV = !state.rDV;
    },
    checkRegister(state, value) {
      if (value.data.code == 200) {
        state.registerState = true;
      }
    },
  },
  actions: {
    checkRegister(context, { account, password, phone, email }) {
      axios({
        method: "post",
        url: `${webConfig.url}/usr/registry`,
        data: {
          usrAccount: account,
          usrEmail: email,
          usrPhone: phone,
          usrPwd: password,
        },
        timeout: 30000,
      })
        .then((value) => {
          context.commit("checkRegister", value);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  getters: {},
};

const login = {
  namespaced: true,
  state: {
    lDV: false,
    fDV: false,
    address: "",
    loginState: false,
  },
  mutations: {
    changeLoginDialog(state) {
      setTimeout(() => {
        state.lDV = !state.lDV;
      }, 300);
    },
    changeFoundDialog(state) {
      setTimeout(() => {
        state.fDV = !state.fDV;
      }, 300);
    },
    checkLogin(state, value) {
      console.log(state);
      if (value.data.code == 200) {
        state.loginState = true;
      }
    },
  },
  actions: {
    checkLogin(context, { account, password }) {
      axios({
        url: `${webConfig.url}/usr/login`,
        params: { usrAccount: account, usrPwd: password },
        timeout: 30000,
      })
        .then((value) => {
          context.commit("checkLogin", value);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  getters: {},
};
const plane = {
  namespaced: true,
  state: {},
};
export default new Vuex.Store({
  modules: {
    user,
    register,
    login,
    plane,
  },
});
