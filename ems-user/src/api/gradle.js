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
