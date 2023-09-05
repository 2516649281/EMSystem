import axios from "axios";
import { showDialog, Notify } from "vant";
import "vant/lib/index.css";
import router from "@/router/index";

var messageStarus = false;
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
    return Promise.reject(err);
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
    console.log(`捕获到异常:${err.response}`);
    error(err);
    return Promise.reject(err);
  }
);

function error(err) {
  console.log(err);
  if (err.response.status === 401) {
    console.log("登陆过期");
    if (!messageStarus) {
      showDialog({ message: "当前登录信息已过期,请重新登陆!" });
      router.push({ name: "login" });
    }
  }
  if (err.response.status !== 500) {
    Notify({
      message: "服务异常!请稍后重试!",
      type: "danger",
    });
  }
  sessionStorage.clear();
  messageStarus = true;
}

export default server;
