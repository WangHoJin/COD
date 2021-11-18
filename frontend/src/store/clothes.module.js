import { getClothes, getClothesList } from "@/api/clothes";
import store from "@/store";

export default {
  // 현재 상태들
  state: {
    clothesList: [],
    clothes: "",
  },
  getters: {
    clothesList(state) {
      return state.clothesList;
    },
    clothes(state) {
      return state.clothes;
    },
  },
  mutations: {
    setClothesList(state, payload) {
      state.clothesList = payload;
    },
    setClothes(state, payload) {
      state.clothes = payload;
    },
  },
  actions: {
    // 옷 리스트 가져오기
    getClothesList: ({ commit }, payload) => {
      console.log("사이즈", payload.size);
      getClothesList(payload)
        .then((res) => {
          commit("setClothesList", res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    // 옷 상세 정보 가져오기
    getClothes: ({ commit }, payload) => {
      getClothes(payload)
        .then((res) => {
          commit("setClothes", res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
  },
};
