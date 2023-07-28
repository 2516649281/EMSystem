/**
 * 所有有关角色的API接口
 */
import request from "@/utils/request";

/**
 * 获取角色信息
 * @param {*} role  条件查询
 * @returns 数据
 */
export function getRoleInfo(role) {
  return request({
    url: "/role/select",
    method: "get",
    params: role,
  });
}

/**
 * 按ID获取角色信息
 * @param {*} role  角色ID
 * @returns 数据
 */
export function getRoleById(roleId) {
  return request({
    url: `/role/${roleId}`,
    method: "get",
  });
}

/**
 * 添加角色信息
 * @param {*} role  条件查询
 * @returns 数据
 */
export function addRole(role) {
  return request({
    url: "/role",
    method: "post",
    data: role,
  });
}

/**
 * 修改一名角色信息
 *
 * @param {*} role
 * @returns 数据
 */
export function updateRole(role) {
  return request({
    url: "/role",
    method: "put",
    data: role,
  });
}

/**
 * 批量删除角色
 * @param {*} ids ID
 * @returns 数据
 */
export function deleteRole(ids) {
  return request({
    url: "/role",
    method: "delete",
    data: ids,
  });
}

//获取权限绑定
export function getPermissionRole(obj) {
  return request({
    url: "/pr/select",
    method: "get",
    params: obj,
  });
}

/**
 * 设置权限绑定
 * @param {*} obj
 * @returns
 */
export function setPermissionRole(obj) {
  return request({
    url: "/pr",
    method: "post",
    data: obj,
  });
}

/**
 * 删除权限绑定
 * @param {*} obj
 * @returns
 */
export function deletePermissionRole(obj) {
  return request({
    url: "/pr",
    method: "delete",
    data: obj,
  });
}
