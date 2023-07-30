import axios from "axios";
import {MessageBox, Message} from "element-ui";
import store from "@/store";
import {getToken} from "@/utils/auth";

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_ORIGN_API, // 代理路径
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000, // request timeout
});

// request interceptor
service.interceptors.request.use(
  (config) => {
    // do something before request is sent

    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers["token"] = getToken();
    }
    return config;
  },
  (error) => {
    // do something with request error
    console.log(error); // for debug
    return Promise.reject(error);
  }
);

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom status
   * Here is just an user
   * You can also judge the status by HTTP Status status
   */
  (response) => {
    //判断响应是否为二进制
    if (response.headers["accept-ranges"] === "bytes") {
      return response;
    }
    const res = response.data;
    // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
    if (res.status === 401 || res.status === 403) {
      // to re-login
      MessageBox.confirm("登录已过期，请重新登录!", {
        confirmButtonText: "重新登录",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        store.dispatch("/user/logout");
      });
    }
    // if the custom status is not 20000, it is judged as an error.
    if (res.status !== 200) {
      Message({
        message: res.message || "请求失败!",
        type: "error",
        duration: 5 * 1000,
        showClose: true,
      });
      return Promise.reject(new Error(res.message || "请求错误!"));
    } else {
      return res;
    }
  },
  (error) => {
    console.log("err" + error); // for debug
    Message({
      message: error.message,
      type: "error",
      duration: 5 * 1000,
      showClose: true,
    });
    return Promise.reject(error);
  }
);

export default service;
