// import Vue from 'vue';
// import App from './App.vue';
// import router from './utils/routes';
// import { VueAxios, axios } from './utils/axios';
// import store from './utils/store';

// Vue.use(router);
// Vue.use(VueAxios, axios);
// Vue.use(store);

// Vue.config.productionTip = false;

// new Vue({
  //   render: (h) => h(App),
  // }).$mount('#app');
  
  import { createApp } from 'vue';
  import App from './App.vue';
  import router from './utils/routes';
  import { VueAxios, axios } from './utils/axios';
  import store from './utils/store';
  createApp(App).mount('#app');
  App.use(router);
  App.use(VueAxios, axios);
  App.use(store);