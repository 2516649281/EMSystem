import axios from "axios";
import { showDialog } from "vant";
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
    sessionStorage.clear();
  }
);

//统一响应处理
server.interceptors.response.use(
  (config) => {
    console.log("======响应体======");
    console.log(config);
    error(config);
    return config;
  },
  (err) => {
    showDialog({ message: err.message });
    console.log(`捕获到异常:${err}`);
    sessionStorage.clear();
  }
);

//异常处理
function error(err) {
  console.log(err);
  if (err.data.status === 403) {
    console.log("登陆过期");
    if (!messageStatus) {
      showDialog({
        message: "当前登录信息已过期,请重新登陆!",
        type: "warning",
      });
      sessionStorage.clear();
    }
  }
  if (err.data.status === 401) {
    console.log("非法访问!");
    if (!messageStatus) {
      showDialog({ message: "对不起,你没有权限访问!", type: "warning" });
      sessionStorage.clear();
    }
  }
  if (err.data.status === 500) {
    showDialog({
      message: "服务异常!请稍后重试!",
      type: "danger",
    });
    sessionStorage.clear();
  }
  messageStatus = true;
}

export default server;
