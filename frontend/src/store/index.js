import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import codi from './codi.module';
import comment from './comment.module';
import auth from './auth.module';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    codi,
    comment,
    auth,
  },
  plugins: [createPersistedState()],
});
