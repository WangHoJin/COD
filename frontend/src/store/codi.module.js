import { getCodi, getCodiList } from "@/api/codi";

export default {
  // 현재 상태들
  state: {
    //현재 목록에 있는 코멘트들
    codies: [],
    codi: "",
  },
  getters: {
    codies(state) {
      return state.codies;
    },
    codi(state) {
      return state.codi;
    },
  },
  mutations: {
    setCodies(state, payload) {
      // console.log("vuex mutation");
      // console.log("데이터 : ");
      // console.log(payload);
      state.codies = payload;
    },
    setCodi(state, payload) {
      // console.log("vuex mutation");
      // console.log("데이터 : " + payload);
      state.codi = payload;
    },
  },
  actions: {
    //현재 띄워줄 코디 리스트 가져오기
    getCodies: ({ commit }, payload) => {
      // console.log("vuex action");
      getCodiList(payload)
        .then((res) => {
          // console.log("vuex axios");
          commit("setCodies", res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    //현재 띄워줄 코디 상세 가져오기
    getCodi: ({ commit }, payload) => {
      // console.log("vuex action");
      getCodi(payload)
        .then((res) => {
          // console.log("vuex axios");
          commit("setCodi", res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
  },
};
