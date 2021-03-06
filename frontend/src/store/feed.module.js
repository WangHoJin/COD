import { getFollowingCodiList, getPopularCodiList } from '@/api/feed';
import { getFollowList } from '@/api/follow';
import { getCodiLikedList } from '../api/codiLiked';
import { getUserList } from '@/api/user';

export default {
  state: {
    followingCodies: [],
    popularCodies: [],
    followList: [],
    codiLikedList: [],
    userList: [],
    followerList: [],
    followingList: [],
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
    codiLikedList(state) {
      return state.codiLikedList;
    },
    userList(state) {
      return state.userList;
    },
    followerList(state) {
      return state.followerList;
    },
    followingList(state) {
      return state.followingList;
    },
  },
  mutations: {
    setFollowingCodies(state, payload) {
      console.log('following mutation');
      state.followingCodies = payload;
    },
    setPopularCodies(state, payload) {
      console.log('popular mutation');
      state.popularCodies = payload;
    },
    setFollowList(state, payload) {
      console.log('follow mutation');
      state.followList = payload;
    },
    setCodiLikedList(state, payload) {
      console.log('like mutation');
      state.codiLikedList = payload;
    },
    setUserList(state, payload) {
      console.log('user mutation');
      state.userList = payload;
    },
    setFollowerList(state, payload) {
      state.followerList = payload;
    },
    setFollowingList(state, payload) {
      state.followingList = payload;
    },
  },
  actions: {
    getFollowingCodies(context, payload) {
      console.log('vuex action');
      let payload2 = { page: payload.page, size: payload.size };
      getFollowingCodiList(payload2, payload.accessToken)
        .then((res) => {
          console.log('vuex axios');
          context.commit('setFollowingCodies', res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    getPopularCodies(context, payload) {
      getPopularCodiList(payload)
        .then((res) => {
          console.log('popularCodiList axios', res);
          context.commit('setPopularCodies', res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    getFollows(context, payload) {
      getFollowList(payload)
        .then((res) => {
          console.log('action' + JSON.stringify(payload));
          context.commit('setFollowList', res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    getCodiLiked(context, payload) {
      let payload2 = { page: payload.page, size: payload.size };
      getCodiLikedList(payload2, payload.accessToken)
        .then((res) => {
          context.commit('setCodiLikedList', res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    getUsers(context, payload) {
      getUserList(payload)
        .then((res) => {
          context.commit('setUserList', res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    getFollower(context, payload) {
      getFollowList(payload)
        .then((res) => {
          context.commit('setFollowerList', res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    getFollowing(context, payload) {
      getFollowList(payload)
        .then((res) => {
          context.commit('setFollowingList', res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
  },
};
