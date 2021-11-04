import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: "/",
      redirect: "/main",
      component: () => import("@/layouts/MainLayout"),
      children: [
        {
          path: "/main",
          name: "main",
          component: () => import("@/pages/Main/Main.vue"),
        },
        {
          path: "/record-codi",
          name: "record-codi",
          component: () => import("@/pages/Main/RecordCodi.vue"),
        },
      ],
    },
    {
      path: "/codi",
      redirect: "/codi/list",
      component: () => import("@/layouts/CodiLayout"),
      children: [
        {
          path: "create",
          component: () => import("@/pages/codi/CodiCreatePage.vue"),
        },
        {
          path: "list",
          component: () => import("@/pages/codi/CodiListPage.vue"),
        },
        {
          path: "detail/:no",
          component: () => import("@/pages/codi/CodiDetailPage.vue"),
        },
      ],
    },
    {
      path: "/:catchAll(.*)*",
      component: () => import("../pages/Error404.vue"),
    },
  ],

  // #을 제거하기 위해 history 를 모드로 추가한다.
  mode: "history",
});

export default router;
