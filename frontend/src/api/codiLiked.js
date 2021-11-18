import axios from "@/utils/axios";

// 좋아요 누른 코디 조회 API
async function getCodiLikedList(condition, accessToken) {
  var url = `/codi-liked?&page=${condition.page}&size=${condition.size}`;
  try {
    const { data } = await axios.get(url, {
      headers: {
        "X-ACCESS-TOKEN": accessToken,
      },
    });
    return data.result;
  } catch (error) {
    console.error(error.response.data.message);
  }
}
// 코디 좋아요 등록 API
async function createCodiLiked(codiLiked, accessToken) {
  try {
    return axios.post("/codi-liked", codiLiked, {
      headers: {
        "X-ACCESS-TOKEN": accessToken,
      },
    });
  } catch (error) {
    console.error(error.response.data.message);
  }
}
// 코디 좋아요 취소 API
async function deleteCodiLiked(codiId, accessToken) {
  try {
    return axios.delete(`/codi-liked?codiId=${codiId}`, {
      headers: {
        "X-ACCESS-TOKEN": accessToken,
      },
    });
  } catch (error) {
    console.error(error.response.data.message);
  }
}

export { getCodiLikedList, createCodiLiked, deleteCodiLiked };
