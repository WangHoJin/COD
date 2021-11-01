import Vue from 'vue';
import App from './App.vue';
import router from './router';
import vuetify from './plugins/vuetify';
import axios from './utils/axios';
import store from './store';

Vue.config.productionTip = false;
Vue.prototype.$axios = axios;

new Vue({
  axios,
  vuetify,
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
