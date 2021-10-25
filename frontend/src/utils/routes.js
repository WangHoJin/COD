import { createRouter, createWebHistory } from 'vue-router';
import store from '../store/auth/index';

const routes = [
  {
    path: '/',
    component: () => import('../layouts/MainLayout.vue'),
  },
  {
    path: '/:catchAll(.*)*',
    component: () => import('../pages/Error404.vue'),
  },
];
const router = createRouter({
  history: createWebHistory(),
  routes,
});
router.beforeEach((to, from, next) => {
  if (to.meta.auth && !store.getters['auth/isLogin']) {
    alert('로그인이 필요한 페이지입니다.');
    next('/users/login');
  } else {
    next();
  }
});

export default routes;
