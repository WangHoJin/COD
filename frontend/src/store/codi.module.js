import { getCodi, getCodiList } from "@/api/codi";

export default {
  // 현재 상태들
  state: {
    codies: [],
    codi: "",
    clothes: [],
  },
  getters: {
    codies(state) {
      return state.codies;
    },
    codi(state) {
      return state.codi;
    },
    clothes(state) {
      return state.clothes;
    },
  },
  mutations: {
    setCodies(state, payload) {
      state.codies = payload;
    },
    setCodi(state, payload) {
      state.codi = payload;
    },
    setClothes(state, payload) {
      console.log("옷넣기");
      state.clothes = [...state.clothes, payload];
    },
    removeClothes(state, payload) {
      state.clothes = payload;
    },
  },
  actions: {
    //현재 띄워줄 코디 리스트 가져오기
    getCodies: ({ commit }, payload) => {
      getCodiList(payload)
        .then((res) => {
          commit("setCodies", res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    //현재 띄워줄 코디 상세 가져오기
    getCodi: ({ commit }, payload) => {
      getCodi(payload)
        .then((res) => {
          commit("setCodi", res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    getClothes: ({ commit }, payload) => {
      console.log("옷가져오기");
      commit("setClothes", payload);
    },
    removeClothes: ({ commit }, payload) => {
      console.log("옷 삭제하기");
      commit("removeClothes", payload);
    },
  },
};
