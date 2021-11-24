import axios from "@/utils/axios";

// 팔로우/팔로워조회 API
async function getFollowList(condition) {
  var url = `/follows?page=${condition.page}&size=${condition.size}`;
  if (condition.toUserId) url += `&toUserId=${condition.toUserId}`;
  else if (condition.fromUserId) url += `&fromUserId=${condition.fromUserId}`;
  try {
    const { data } = await axios.get(url);
    return data.result;
  } catch (error) {
    console.error(error.response.data.message);
  }
}
// 팔로우 상태 생성 API
async function createFollow(follow, accessToken) {
  try {
    return axios.post("/follows", follow, {
      headers: {
        "X-ACCESS-TOKEN": accessToken,
      },
    });
  } catch (error) {
    console.error(error.response.data.message);
  }
}
// 팔로우 상태 해제 API
async function deleteFollow(userId, accessToken) {
  try {
    return axios.delete(`/follows?toUserId=${userId}`, {
      headers: {
        "X-ACCESS-TOKEN": accessToken,
      },
    });
  } catch (error) {
    console.error(error.response.data.message);
  }
}

export { getFollowList, createFollow, deleteFollow };
