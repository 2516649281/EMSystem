import {avatar, getInfo, login, logout} from "@/api/user";
import {getToken, removeToken, setToken} from "@/utils/auth";
import {resetRouter} from "@/router";
import jwtDecode from "jwt-decode";

const getDefaultState = () => {
  return {
    token: getToken(),
    name: "",
    avatar: "",
    id: "",
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
      // console.log(id);
      //获取用户信息
      getInfo({id: id})
        .then((response) => {
          const {data} = response;
          if (!data) {
            reject("非法访问");
          }
          const {name, id} = data[0];
          commit("SET_NAME", name);
          commit("SET_ID", id);
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

  //头像回显
  getAvatar({commit}) {
    var id = jwtDecode(state.token).user;
    avatar({userId: id}).then((response) => {
      commit("SET_AVATAR", window.URL.createObjectURL(response.data));
    });
  },

  // user logout
  logout({commit}) {
    return new Promise((resolve, reject) => {
      logout({token: getToken()})
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
