import Vue from "vue";
import Router from "vue-router";
import store from "@/store";

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
          path: "/record-codi/select",
          name: "record-codi-select",
          component: () => import("@/pages/Main/RecordCodi/SelectCodi.vue"),
        },
        {
          path: "/record-coid/coordination",
          name: "coordination",
          component: () => import("@/pages/Main/RecordCodi/Coordination.vue"),
        },
        {
          path: "/record-coid/regist",
          name: "record-coid-regist",
          component: () => import("@/pages/Main/RecordCodi/RegistCodi.vue"),
        },
        {
          path: "/record-coid/detail",
          name: "record-coid-detail",
          component: () => import("@/pages/Main/RecordCodi/CalendarDetail.vue"),
        },
      ],
    },
    {
      path: "/sign-in",
      name: "sign-in",
      component: () => import("@/pages/sign/SignIn.vue"),
    },
    {
      path: "/sign-up",
      name: "sign-up",
      component: () => import("@/pages/sign/SignUp.vue"),
    },
    {
      path: "/codi",
      name: "codi",
      redirect: "/codi/list",
      component: () => import("@/layouts/CodiLayout"),
      children: [
        {
          name: "codiCreate",
          path: "create",
          component: () => import("@/pages/codi/CodiCreate.vue"),
        },
        {
          name: "codiList",
          path: "list",
          component: () => import("@/pages/codi/CodiList.vue"),
        },
        {
          name: "codiDetail",
          path: "detail/:no",
          component: () => import("@/pages/codi/CodiDetail.vue"),
        },
      ],
    },
    {
      path: "/mypage",
      name: "mypage",
      redirect: "/mypage/:no",
      component: () => import("@/layouts/MypageLayout"),
      children: [
        {
          name: "mypageMain",
          path: ":no",
          component: () => import("@/pages/mypage/MypageMain.vue"),
        },
        {
          name: "mypageFollow",
          path: ":no/follow",
          component: () => import("@/pages/mypage/MypageFollow.vue"),
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
    {
      path: "/clothes",
      name: "clothes",
      redirect: "/clothes/list",
      component: () => import("@/layouts/ClothesLayout"),
      children: [
        {
          name: "clothesCreate",
          path: "create",
          component: () => import("@/pages/clothes/ClothesCreate.vue"),
        },
        {
          name: "clothesList",
          path: "list",
          component: () => import("@/pages/clothes/ClothesList.vue"),
        },
        {
          name: "clothesDetail",
          path: "detail/:no",
          component: () => import("@/pages/clothes/ClothesDetail.vue"),
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

router.beforeEach((to, from, next) => {
  let loginUser = store.getters.loginUser;
  console.log("loginUser", loginUser);
  if (loginUser || to.name == "sign-in" || to.name == "sign-up") {
    next();
  } else {
    // alert('로그인이 필요한 페이지입니다.');
    next("/sign-in");
  }
});
