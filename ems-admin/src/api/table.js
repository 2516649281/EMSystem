import request from "@/utils/request";

export function getUsers(params) {
  return request({
    url: "/user",
    method: "get",
    params,
  });
}
