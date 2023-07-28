/**
 * 所有有关用户的API接口
 */
import request from "@/utils/request";
import qs from "qs";

/**
 * 用户登录
 * @param {*} data 登录数据
 * @returns 数据
 */
export function login(data) {
  return request({
    url: "/user/login",
    method: "post",
    params: data,
  });
}

/**
 * 获取当前用户信息
 * @param {*} user  条件查询
 * @returns 数据
 */
export function getInfo(user) {
  return request({
    url: "/user/select",
    method: "get",
    params: user,
  });
}

/**
 * 修改一名用户信息
 *
 * @param {*} user
 * @returns 数据
 */
export function updateUser(user) {
  return request({
    url: "/user",
    method: "put",
    params: user,
  });
}

/**
 * 批量删除用户
 * @param {*} ids ID
 * @returns 数据
 */
export function deleteUser(ids) {
  return request({
    url: "/user",
    method: "delete",
    params: ids,
    //参数转化
    paramsSerializer: (params) => {
      return qs.stringify(params, {indices: false});
    },
  });
}

/**
 * 用户退出登录
 * @returns 数据
 */
export function logout() {
  return request({
    url: "/user/logout",
    method: "get",
  });
}

//获取头像
export function avatar(userId) {
  return request({
    url: "/user/avatar",
    method: "get",
    params: userId,
    responseType: "blob",
  });
}
