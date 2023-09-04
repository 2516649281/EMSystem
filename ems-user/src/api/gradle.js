/**
 * 所有有关年级的API接口
 */
import request from "../utils/axios";

/**
 * 获取年级信息
 * @param {*} gradle  条件查询
 * @returns 数据
 */
export function getGradleInfo(gradle) {
  return request({
    url: "/gradle/select",
    method: "get",
    params: gradle,
  });
}

/**
 * 按ID获取年级信息
 * @param {*} gradle  年级ID
 * @returns 数据
 */
export function getGradleById(gradleId) {
  return request({
    url: `/gradle/one`,
    method: "get",
    params: gradleId,
  });
}

/**
 * 添加年级信息
 * @param {*} gradle  条件查询
 * @returns 数据
 */
export function addGradle(gradle) {
  return request({
    url: "/gradle",
    method: "post",
    data: gradle,
  });
}

/**
 * 修改一名年级信息
 *
 * @param {*} gradle
 * @returns 数据
 */
export function updateGradle(gradle) {
  return request({
    url: "/gradle",
    method: "put",
    data: gradle,
  });
}

/**
 * 批量删除年级
 * @param {*} ids ID
 * @returns 数据
 */
export function deleteGradle(ids) {
  return request({
    url: "/gradle",
    method: "delete",
    data: ids,
  });
}
