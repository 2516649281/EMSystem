import request from "@/utils/request";

//用户
export function getUsers(params) {
  return request({
    url: "/user",
    method: "get",
    params,
  });
}

//角色
export function getRoles(params) {
  return request({
    url: "/role",
    method: "get",
    params,
  });
}
