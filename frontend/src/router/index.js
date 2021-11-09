import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '/',
      redirect: '/main',
      component: () => import('@/layouts/MainLayout'),
      children: [
        {
          path: '/main',
          name: 'main',
          component: () => import('@/pages/Main/Main.vue'),
        },
        {
          path: '/record-codi',
          name: 'record-codi',
          component: () => import('@/pages/Main/RecordCodi/SelectCodi.vue'),
        },
        {
          path: '/coordination',
          name: 'coordination',
          component: () => import('@/pages/Main/RecordCodi/Coordination.vue'),
        },
      ],
    },
    {
      path: '/:catchAll(.*)*',
      component: () => import('../pages/Error404.vue'),
    },
  ],

  // #을 제거하기 위해 history 를 모드로 추가한다.
  mode: 'history',
});

export default router;
