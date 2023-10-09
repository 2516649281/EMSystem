/**
 * @Author: 春风能解释
 * @Date:   2023-09-04 22:12:10
 * @Last Modified by:   春风能解释
 * @Last Modified time: 2023-09-13 14:49:06
 */
import axios from "axios";
import {showDialog} from "vant";
import "vant/lib/index.css";

var messageStatus = false;
//设置公共后台url地址
const server = axios.create({
  // baseURL: "http://47.92.215.94:8000/api",
  baseURL: process.env.VUE_APP_AXIOS_URL,
});

//统一请求处理
server.interceptors.request.use(
  (config) => {
    var token = sessionStorage.getItem("token");
    console.log(`获取token:${token}`);
    if (token != null) {
      console.log("已向请求添加请求头");
      config.headers["token"] = token;
    }
    console.log("======请求体======");
    console.log(config);
    return config;
  },
  (err) => {
    showDialog({ message: err.message });
    console.log(`捕获到异常:${err}`);
      error(err);
    sessionStorage.clear();
  }
);

//统一响应处理
server.interceptors.response.use(
  (config) => {
    console.log("======响应体======");
    console.log(config);
    return config;
  },
  (err) => {
    showDialog({ message: err.message });
    console.log(`捕获到异常:${err}`);
      error(err);
    sessionStorage.clear();
  }
);

//异常处理
function error(err) {
    var data = err.response.data;
    console.log(data);
    if (!data) {
        showDialog({
            message: "客户端未知异常!",
            type: "warning",
        });
        sessionStorage.clear();
        return;
  }
    if (data.status != 200) {
    showDialog({
        message: data.message,
      type: "danger",
    });
    sessionStorage.clear();
        return;
  }
}

export default server;
