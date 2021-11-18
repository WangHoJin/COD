import axios from "@/utils/axios";
import store from "@/store";

// 옷 리스트 조회 API
async function getClothesList(condition, accessToken) {
  var url = `/clothes?page=${condition.page}&size=${condition.size}`;
  if (condition.userId) url += `&userId=${condition.userId}`;
  try {
    const { data } = await axios.get(url, {
      headers: {
        "X-ACCESS-TOKEN": store.state.auth.accessToken,
      },
    });
    return data.result;
  } catch (error) {
    // console.error(error);
    console.error(error.response.data.message);
  }
}
// 옷 상세 조회 API
async function getClothes(clothesId) {
  var url = `/clothes/${clothesId}`;
  // console.log("getClothesDetail API", url);
  try {
    const { data } = await axios.get(url);
    return data.result;
  } catch (error) {
    console.error(error);
  }
}
// 옷 등록 API
async function createClothes(clothes) {
  // console.log("createClothes API", clothes);
  try {
    return axios.post('/clothes', clothes);
  } catch (error) {
    console.error(error.response.data.message);
  }
}
// 옷 수정 API
async function updateClothes(clothes, clothesId) {
  // console.log("updateClothes API", clothes, clothesId);
  try {
    return axios.patch(`/clothes/${clothesId}`, clothes);
  } catch (error) {
    console.error(error.response.data.message);
  }
}
// 옷 삭제 API
async function deleteClothes(clothesId) {
  // console.log("deleteClothes API", clothesId);
  try {
    return axios.delete(`/clothes/${clothesId}`);
  } catch (error) {
    console.log(error);
    console.error(error.response.data.message);
  }
}
export { getClothesList, getClothes, createClothes, updateClothes, deleteClothes };
