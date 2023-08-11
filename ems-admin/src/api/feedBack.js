/**
 * 所有有关科目的API接口
 */
import request from "@/utils/request";

/**
 * 获取科目信息
 * @param {*} feed  条件查询
 * @returns 数据
 */
export function getFeedBackInfo(feed) {
  return request({
    url: "/feed/select",
    method: "get",
    params: feed,
  });
}

/**
 * 添加科目信息
 * @param {*} feed  条件查询
 * @returns 数据
 */
export function addFeedBack(feed) {
  return request({
    url: "/feed",
    method: "post",
    data: feed,
  });
}

/**
 * 修改一名科目信息
 *
 * @param {*} feed
 * @returns 数据
 */
export function updateFeedBack(feed) {
  return request({
    url: "/feed",
    method: "put",
    data: feed,
  });
}

/**
 * 批量删除科目
 * @param {*} ids ID
 * @returns 数据
 */
export function deleteFeedBack(ids) {
  return request({
    url: "/feed",
    method: "delete",
    data: ids,
  });
}
