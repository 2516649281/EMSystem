import { createRouter, createWebHashHistory } from "vue-router";

const routes = [
  {
    path: "/login",
    name: "login",
    component: () => import("../views/login.vue"),
    meta: {
      requireAuth: true,
      title: "用户登录",
    },
  },
  {
    path: "/",
    name: "page",
    component: () => import("../views/page.vue"),
    redirect: "/index",
    children: [
      {
        path: "/index",
        name: "index",
        component: () => import("../views/index.vue"),
        meta: { title: "主页" },
      },
      {
        path: "/user",
        name: "user",
        component: () => import("../views/user.vue"),
        meta: { title: "用户设置" },
      },
    ],
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

//登录拦截
router.beforeEach((to, from, next) => {
  window.document.title =
    to.meta.title == undefined
      ? "考试管理系统-用户端"
      : "考试管理系统-" + to.meta.title;
  if (!to.meta.requireAuth) {
    var token = sessionStorage.getItem("token");
    if (token != null) {
      next();
    } else {
      next({
        name: "login",
      });
    }
  } else {
    next();
  }
});

export default router;
