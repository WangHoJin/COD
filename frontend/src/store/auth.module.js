import { signIn, getProfile, getUserInfo } from "@/api/user.js";

export default {
  // 현재 상태들
  state: {
    loginUser: null,
    accessToken: null,
    userInfo: null,
  },
  getters: {
    loginUser(state) {
      return state.loginUser;
    },
    accessToken(state) {
      return state.accessToken;
    },
    userInfo(state) {
      return state.userInfo;
    },
  },
  mutations: {
    setLoginUser(state, payload) {
      state.loginUser = payload;
    },
    setAccessToken(state, payload) {
      state.accessToken = payload;
    },
    setUserInfo(state, payload) {
      state.userInfo = payload;
    },
  },
  actions: {
    login: ({ commit }, payload) => {
      return new Promise((resolve, reject) => {
        signIn(payload)
          .then((res) => {
            //엑세스 토큰 셋팅
            let accessToken = res.result.accessToken;
            console.log(accessToken);
            commit("setAccessToken", accessToken);
            //로그인 유저 셋팅
            getProfile(accessToken).then((res) => {
              commit("setLoginUser", res.result);
              resolve();
            });
          })
          .catch((error) => {
            // console.error('auth.module.js', error.response.data.message);
            reject(error);
          });
      });
    },
    logout: ({ commit }) => {
      console.log("로그아웃 시도");
      commit("setAccessToken", null);
      commit("setLoginUser", null);
    },
    getUser: ({ commit }, payload) => {
      getProfile(payload)
        .then((res) => {
          commit("setLoginUser", res.result);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    getInfo: ({ commit }, payload) => {
      getUserInfo(payload)
        .then((res) => {
          console.log("res", res);
          commit("setUserInfo", res.result);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
  },
};
