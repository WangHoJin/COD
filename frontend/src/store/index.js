import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import comment from './comment.module';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    comment,
  },
  plugins: [createPersistedState()],
});
