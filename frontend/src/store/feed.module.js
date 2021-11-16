import { getFollowingCodiList, getPopularCodiList } from "@/api/feed";
import { getFollowList } from "@/api/follow";

export default {
  state: {
    followingCodies: [],
    popularCodies: [],
    followList: [],
  },
  getters: {
    followingCodies(state) {
      return state.followingCodies;
    },
    popularCodies(state) {
      return state.popularCodies;
    },
    followList(state) {
      return state.followList;
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
    setFollowList(state, payload) {
      console.log("follow mutation");
      state.followList = payload;
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
    getFollows(context, payload) {
      getFollowList(payload)
        .then((res) => {
          context.commit("setFollowList", res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
  },
};
