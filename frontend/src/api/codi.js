import axios from "@/utils/axios";

// 코디 리스트 조회 API
async function getCodiList(condition) {
  var url = `/codies?page=${condition.page}&size=${condition.size}`;
  if (condition.userId) url += `&userId=${condition.userId}`;
  if (condition.tag) url += `&tag=${condition.tag}`;
  // console.log("getCodiList API", url);
  try {
    const { data } = await axios.get(url);
    return data.result;
  } catch (error) {
    console.error(error);
  }
}
// 코디 상세 조회 API
async function getCodi(codiId) {
  var url = `/codies/${codiId}`;
  // console.log("getCodiDetail API", url);
  try {
    const { data } = await axios.get(url);
    return data.result;
  } catch (error) {
    console.error(error);
  }
}
// 코디 작성 API
async function createCodi(codi, accessToken) {
  // console.log("createCodi API", codi);
  try {
    return axios.post("/codies", codi, {
      headers: {
        "X-ACCESS-TOKEN": accessToken,
      },
    });
  } catch (error) {
    console.error(error.response.data.message);
  }
}
// 코디 수정 API
async function updateCodi(codi, codiId) {
  // console.log("updateCodi API", codi, codiId);
  try {
    return axios.patch(`/codies/${codiId}`, codi);
  } catch (error) {
    console.error(error.response.data.message);
  }
}
// 코디 삭제 API
async function deleteCodi(codiId, accessToken) {
  // console.log("deleteCodi API", codiId);
  try {
    return axios.delete(`/codies/${codiId}`, {
      headers: {
        "X-ACCESS-TOKEN": accessToken,
      },
    });
  } catch (error) {
    console.log(error);
    console.error(error.response.data.message);
  }
}
export { getCodiList, getCodi, createCodi, updateCodi, deleteCodi };
