import axios from "@/utils/axios";

// 기웃기옷 팔로우한 유저 코디 리스트 조회 API
async function getFollowingCodiList(condition, accessToken) {
  var url = `/codies/following?page=${condition.page}&size=${condition.size}`;
  try {
    console.log("토큰" + accessToken);
    const { data } = await axios.get(url, {
      headers: {
        "X-ACCESS-TOKEN": accessToken,
      },
    });
    console.log("결과" + data.result);
    return data.result;
  } catch (error) {
    console.error(error.response.data.message);
  }
}
// 기웃기옷 인기 코디 리스트 조회 API
async function getPopularCodiList(condition) {
  var url = `/codies/popular?startDate=${condition.startDate}&endDate=${condition.endDate}&page=${condition.page}&size=${condition.size}`;
  try {
    const { data } = await axios.get(url);
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

export { getFollowingCodiList, getPopularCodiList, createCodiLiked, deleteCodiLiked };
