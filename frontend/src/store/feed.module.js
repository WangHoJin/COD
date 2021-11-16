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
      console.log("페이로드야" + payload);
      state.followList = payload;
      console.log(state.followList);
    },
  },
  actions: {
    getFollowingCodies(context, payload, accessToken) {
      console.log("vuex action");
      getFollowingCodiList(payload, accessToken)
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
          console.log("action" + JSON.stringify(payload));
          context.commit("setFollowList", payload);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
  },
};
