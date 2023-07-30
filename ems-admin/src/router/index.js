import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

/* Layout */
import Layout from "@/layout";

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/user/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: "/login",
    component: () => import("@/views/login/index"),
    hidden: true,
  },

  {
    path: "/404",
    component: () => import("@/views/404"),
    hidden: true,
  },

  {
    path: "/",
    component: Layout,
    redirect: "/index",
    children: [
      {
        path: "index",
        name: "index",
        component: () => import("@/views/index/index"),
        meta: {title: "主页", icon: "el-icon-s-home"},
      },
    ],
  },

  {
    path: "/system",
    component: Layout,
    redirect: "/system/user",
    name: "system",
    meta: {title: "系统管理", icon: "el-icon-setting"},
    children: [
      {
        path: "/user",
        name: "user",
        component: () => import("@/views/system/user/user"),
        meta: {title: "管理用户信息", icon: "el-icon-user"},
      },
      {
        path: "/role",
        name: "role",
        component: () => import("@/views/system/role/role"),
        meta: {title: "管理角色信息", icon: "el-icon-star-off"},
      },
      {
        path: "/per",
        name: "permission",
        component: () => import("@/views/system/permission/permission"),
        meta: {title: "管理权限信息", icon: "el-icon-medal"},
      },
    ],
  },
  {
    path: "/op",
    component: Layout,
    redirect: "/op/subject",
    name: "op",
    meta: {title: "教学管理", icon: "el-icon-setting"},
    children: [
      {
        path: "/op",
        name: "op",
        component: () => import("@/views/op/subject/subject"),
        meta: {title: "管理科目信息", icon: "el-icon-user"},
      },
    ],
  },
  // 404 page must be placed at the end !!!
  {path: "*", redirect: "/404", hidden: true},
];

const createRouter = () =>
  new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({y: 0}),
    routes: constantRoutes,
  });

const router = createRouter();

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher; // reset router
}

export default router;
