import {login, logout, getInfo, avatar} from "@/api/user";
import {getToken, setToken, removeToken} from "@/utils/auth";
import {resetRouter} from "@/router";
import jwtDecode from "jwt-decode";
import {status} from "nprogress";

const getDefaultState = () => {
  return {
    token: getToken(),
    name: "",
    avatar: "",
    id: "",
    status: "",
  };
};

const state = getDefaultState();

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState());
  },
  SET_ID: (state, id) => {
    state.id = id;
  },
  SET_TOKEN: (state, token) => {
    state.token = token;
  },
  SET_NAME: (state, name) => {
    state.name = name;
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar;
  },
  SET_STATUS: (state, status) => {
    state.status = status;
  },
};

const actions = {
  // user login
  login({commit}, userInfo) {
    const {username, password} = userInfo;
    return new Promise((resolve, reject) => {
      login({username: username.trim(), password: password})
        .then((response) => {
          const {data} = response;
          commit("SET_TOKEN", data);
          setToken(data);
          resolve();
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // get user info
  getInfo({commit, state}) {
    return new Promise((resolve, reject) => {
      var id = jwtDecode(state.token).user;
      commit("SET_ID", id);
      // console.log(id);
      //获取用户信息
      getInfo({id: id})
        .then((response) => {
          const {data} = response;
          if (!data) {
            reject("非法访问");
          }
          const {name, status} = data;
          commit("SET_STATUS", status);
          commit("SET_NAME", name);
          avatar({userId: id}).then((response) => {
            commit("SET_AVATAR", window.URL.createObjectURL(response.data));
          });
          resolve(data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // user logout
  logout({commit}) {
    return new Promise((resolve, reject) => {
      logout()
        .then(() => {
          removeToken(); // must remove  token  first
          resetRouter();
          commit("RESET_STATE");
          resolve();
        })
        .catch((error) => {
          reject(error);
        });
    });
  },

  // remove token
  resetToken({commit}) {
    return new Promise((resolve) => {
      removeToken(); // must remove  token  first
      commit("RESET_STATE");
      resolve();
    });
  },
};

export default {
  namespaced: true,
  state,
  mutations,
  actions,
};
