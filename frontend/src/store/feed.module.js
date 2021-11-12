import { getFollowingCodiList } from "@/api/feed.js";
import { getPopularCodiList } from "@/api/feed.js";

export default {
  state: {
    codies: [],
  },
  getters: {
    codies(state) {
      return state.codies;
    },
  },
  mutations: {
    setCodies(state, payload) {
      console.log("vuex mutation");
      state.codies = payload;
    },
  },
  actions: {
    getCodies(context, payload) {
      console.log("vuex action");
      getFollowingCodiList(payload).then((res) => {
        console.log("vuex axios");
        context.commit("setCodies", res);
      });
    },
  },
};
