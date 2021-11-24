import { getCodi, getCodiList } from '@/api/codi';
export default {
  // 현재 상태들
  state: {
    codies: [],
    codi: '',
    usedClothes: [],
  },
  getters: {
    codies(state) {
      return state.codies;
    },
    codi(state) {
      return state.codi;
    },
    usedClothes(state) {
      return state.usedClothes;
    },
  },
  mutations: {
    setCodies(state, payload) {
      state.codies = payload;
    },
    setCodi(state, payload) {
      state.codi = payload;
    },
    setUsedClothes(state, payload) {
      console.log('옷넣기');
      state.usedClothes = [...state.usedClothes, payload];
    },
    removeUsedClothes(state, payload) {
      state.usedClothes = payload;
    },
  },
  actions: {
    //현재 띄워줄 코디 리스트 가져오기
    getCodies: ({ commit }, payload) => {
      getCodiList(payload)
        .then((res) => {
          commit('setCodies', res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    //현재 띄워줄 코디 상세 가져오기
    getCodi: ({ commit }, payload) => {
      getCodi(payload)
        .then((res) => {
          commit('setCodi', res);
        })
        .catch(function (err) {
          console.log(err);
        });
    },

    setCodi: ({ commit }, codi) => {
      commit('setCodi', codi);
    },

    getUsedClothes: ({ commit }, payload) => {
      console.log('옷가져오기');
      commit('setUsedClothes', payload);
    },
    removeUsedClothes: ({ commit }, payload) => {
      console.log('옷 삭제하기');
      commit('removeUsedClothes', payload);
    },
  },
};
