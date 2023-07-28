/**
 * 所有有关权限的API接口
 */
import request from "@/utils/request";
import qs from "qs";

/**
 * 条件获取权限信息
 * @param {*} per  条件查询
 * @returns 数据
 */
export function getPermissionInfo(per) {
  return request({
    url: "/per/select",
    method: "get",
    params: per,
  });
}

/**
 * 添加权限信息
 * @param {*} per  条件查询
 * @returns 数据
 */
export function addPermission(per) {
  return request({
    url: "/per/select",
    method: "get",
    data: per,
  });
}

/**
 * 修改一名权限信息
 *
 * @param {*} per 待修改的权限
 * @returns 数据
 */
export function updatePermission(per) {
  return request({
    url: "/per",
    method: "put",
    data: per,
  });
}

/**
 * 批量删除权限
 * @param {*} ids ID
 * @returns 数据
 */
export function deletePermission(ids) {
  return request({
    url: "/per",
    method: "delete",
    data: ids,
  });
}
