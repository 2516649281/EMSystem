/**
 * 所有有关科目的API接口
 */
import request from "@/utils/request";

/**
 * 获取科目信息
 * @param {*} subject  条件查询
 * @returns 数据
 */
export function getSubjectInfo(subject) {
  return request({
    url: "/subject/select",
    method: "get",
    params: subject,
  });
}

/**
 * 添加科目信息
 * @param {*} subject  条件查询
 * @returns 数据
 */
export function addSubject(subject) {
  return request({
    url: "/subject",
    method: "post",
    data: subject,
  });
}

/**
 * 修改一名科目信息
 *
 * @param {*} subject
 * @returns 数据
 */
export function updateSubject(subject) {
  return request({
    url: "/subject",
    method: "put",
    data: subject,
  });
}

/**
 * 批量删除科目
 * @param {*} ids ID
 * @returns 数据
 */
export function deleteSubject(ids) {
  return request({
    url: "/subject",
    method: "delete",
    data: ids,
  });
}
