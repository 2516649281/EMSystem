/**
 * 所有有关路由的API接口
 */
import request from "@/utils/request";

/**
 * 条件获取路由信息
 * @param {*} router  条件查询
 * @returns 数据
 */
export function getRouterInfo(router) {
  return request({
    url: "/router/select",
    method: "get",
    params: router,
  });
}

/**
 * 获取一条路由信息
 * @param {*} id  ID值
 * @returns 数据
 */
export function getRouterById(routerId) {
  return request({
    url: "/router/one",
    method: "get",
    params: routerId,
  });
}

/**
 * 添加路由信息
 * @param {*} router  条件查询
 * @returns 数据
 */
export function addRouter(router) {
  return request({
    url: "/router",
    method: "post",
    data: router,
  });
}

/**
 * 修改一名路由信息
 *
 * @param {*} router 待修改的路由
 * @returns 数据
 */
export function updateRouter(router) {
  return request({
    url: "/router",
    method: "put",
    data: router,
  });
}

/**
 * 批量删除路由
 * @param {*} ids ID
 * @returns 数据
 */
export function deleteRouter(ids) {
  return request({
    url: "/router",
    method: "delete",
    data: ids,
  });
}
