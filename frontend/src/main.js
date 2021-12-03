import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "./plugins/vuetify";
import axios from "./utils/axios";
import store from "./store";
import VueDraggableResizable from "vue-draggable-resizable";
import "vue-draggable-resizable/dist/VueDraggableResizable.css";
Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
Vue.component("vue-draggable-resizable", VueDraggableResizable);

new Vue({
  axios,
  vuetify,
  router,
  store,

  render: (h) => h(App),
}).$mount("#app");
