import request from "@/utils/request";

//用户
export function getUsers() {
  return request({
    url: "/user",
    method: "get",
  });
}

//角色
export function getRoles() {
  return request({
    url: "/role",
    method: "get",
  });
}

//角色
export function getPermissions() {
  return request({
    url: "/per",
    method: "get",
  });
}

//科目
export function getSubjects() {
  return request({
    url: "/subject",
    method: "get",
  });
}
