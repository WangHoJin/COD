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
          path: '/record-codi/select',
          name: 'record-codi-select',
          component: () => import('@/pages/Main/RecordCodi/SelectCodi.vue'),
        },
        {
          path: '/record-coid/coordination',
          name: 'coordination',
          component: () => import('@/pages/Main/RecordCodi/Coordination.vue'),
        },
        {
          path: '/record-coid/regist',
          name: 'record-coid-regist',
          component: () => import('@/pages/Main/RecordCodi/RegistCodi.vue'),
        },
        {
          path: '/record-coid/detail',
          name: 'record-coid-detail',
          component: () => import('@/pages/Main/RecordCodi/CalendarDetail.vue'),
        },
      ],
    },
    {
      path: '/codi',
      name: 'codi',
      redirect: '/codi/list',
      component: () => import('@/layouts/CodiLayout'),
      children: [
        {
          path: 'create',
          component: () => import('@/pages/codi/CodiCreate.vue'),
        },
        {
          path: 'list',
          component: () => import('@/pages/codi/CodiList.vue'),
        },
        {
          path: 'detail/:no',
          component: () => import('@/pages/codi/CodiDetail.vue'),
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
