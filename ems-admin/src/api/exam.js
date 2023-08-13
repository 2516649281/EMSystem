/**
 * 所有有关试卷的API接口
 */
import request from "@/utils/request";

/**
 * 条件获取试卷信息
 * @param {*} exam  条件查询
 * @returns 数据
 */
export function getExamInfo(exam) {
  return request({
    url: "/exam/select",
    method: "get",
    params: exam,
  });
}

/**
 * 获取一条试卷信息
 * @param {*} id  ID值
 * @returns 数据
 */
export function getExamById(examId) {
  return request({
    url: "/exam/one",
    method: "get",
    params: examId,
  });
}

/**
 * 添加试卷信息
 * @param {*} exam  条件查询
 * @returns 数据
 */
export function addExam(exam) {
  return request({
    url: "/exam",
    method: "post",
    data: exam,
  });
}

/**
 * 修改一条试卷信息
 *
 * @param {*} exam 待修改的试卷
 * @returns 数据
 */
export function updateExam(exam) {
  return request({
    url: "/exam",
    method: "put",
    data: exam,
  });
}

/**
 * 批量删除试卷
 * @param {*} ids ID
 * @returns 数据
 */
export function deleteExam(ids) {
  return request({
    url: "/exam",
    method: "delete",
    data: ids,
  });
}
