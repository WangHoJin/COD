import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import feed from "./feed.module";
import codi from "./codi.module";
import comment from "./comment.module";
import auth from "./auth.module";
import clothes from "./clothes.module";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    codi,
    comment,
    feed,
    auth,
    clothes,
  },
  plugins: [createPersistedState()],
});
