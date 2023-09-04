/**
 * 所有有关题目的API接口
 */
import request from "../utils/axios";

/**
 * 获取当前题目信息
 * @param {*} problem  条件查询
 * @returns 数据
 */
export function getInfo(problem) {
  return request({
    url: "/pro/select",
    method: "get",
    params: problem,
  });
}

/**
 * 添加一条题目信息
 * @param {*} problem  条件查询
 * @returns 数据
 */
export function addProblem(problem) {
  return request({
    url: "/pro",
    method: "post",
    data: problem,
  });
}

/**
 * 修改一条题目信息
 *
 * @param {*} problem
 * @returns 数据
 */
export function updateProblem(problem) {
  return request({
    url: "/pro",
    method: "put",
    data: problem,
  });
}

/**
 * 批量删除题目
 * @param {*} ids ID
 * @returns 数据
 */
export function deleteProblem(ids) {
  return request({
    url: "/pro",
    method: "delete",
    data: ids,
  });
}
