import { getFollowingCodiList } from "@/api/feed";
import { getPopularCodiList } from "../api/feed";

export default {
  state: {
    followingCodies: [],
    popularCodies: [],
  },
  getters: {
    followingCodies(state) {
      return state.followingCodies;
    },
    popularCodies(state) {
      return state.popularCodies;
    },
  },
  mutations: {
    setFollowingCodies(state, payload) {
      console.log("following mutation");
      state.followingCodies = payload;
    },
    setPopularCodies(state, payload) {
      console.log("popular mutation");
      state.popularCodies = payload;
    },
  },
  actions: {
    getFollowingCodies(context, payload) {
      console.log("vuex action");
      getFollowingCodiList(payload)
        .then((res) => {
          console.log("vuex axios");
          context.commit("setFollowingCodies", res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    getPopularCodies(context, payload) {
      getPopularCodiList(payload)
        .then((res) => {
          console.log("popularCodiList axios");
          context.commit("setPopularCodies", res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
  },
};
