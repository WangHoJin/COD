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
          component: () => import("@/pages/Main.vue"),
        },
      ],
    },
    {
      path: "/feed",
      redirect: "/feed/main",
      component: () => import("@/layouts/FeedLayout.vue"),
      children: [
        {
          path: "main",
          component: () => import("@/pages/feed/FeedMain.vue"),
        },
        {
          path: "search",
          component: () => import("@/pages/feed/FeedSearch.vue"),
        },
      ],
    },
    // {
    //   path: "/feed/search",

    // }
    {
      path: "/:catchAll(.*)*",
      component: () => import("../pages/Error404.vue"),
    },
  ],

  // #을 제거하기 위해 history 를 모드로 추가한다.
  mode: "history",
});

export default router;
