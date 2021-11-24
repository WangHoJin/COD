import axios from '@/utils/axios';

async function getDiraryList(condition, accessToken) {
  var url = `/diaries?page=${condition.page}&size=${condition.size}`;
  if (condition.userId) url += `&userId=${condition.userId}`;
  try {
    const { data } = await axios.get(url, {
      headers: {
        'X-ACCESS-TOKEN': accessToken,
      },
    });
    return data.result;
  } catch (error) {
    // console.error(error);
    console.error(error.response.data.message);
  }
}
// 옷 상세 조회 API
async function getDirary(recordId, accessToken) {
  var url = `/diaries/${recordId}`;
  // console.log("getDirary API", url);
  try {
    const { data } = await axios.get(url, {
      headers: {
        'X-ACCESS-TOKEN': accessToken,
      },
    });
    return data.result;
  } catch (error) {
    console.error(error);
    if (error.response) console.error(error.response.data.message);
  }
}
async function createDirary(record, accessToken) {
  console.log('createDirary API', record);
  try {
    return axios.post('/diaries', record, {
      headers: {
        'X-ACCESS-TOKEN': accessToken,
      },
    });
  } catch (error) {
    console.log(error);
    if (error.response) console.error(error.response.data.message);
  }
}
// 옷 수정 API
async function updateDirary(record, recordId, accessToken) {
  // console.log("updateDirary API", clothes, clothesId);
  try {
    return axios.patch(`/diaries/${recordId}`, record, {
      headers: {
        'X-ACCESS-TOKEN': accessToken,
      },
    });
  } catch (error) {
    console.log(error);
    // console.error(error.response.data.message);
  }
}
// 옷 삭제 API
async function deleteDirary(recordId, accessToken) {
  // console.log("deleteDirary API", clothesId);
  try {
    return axios.delete(`/diaries/${recordId}`, {
      headers: {
        'X-ACCESS-TOKEN': accessToken,
      },
    });
  } catch (error) {
    console.log(error);
    // console.error(error.response.data.message);
  }
}
export { getDiraryList, getDirary, createDirary, updateDirary, deleteDirary };
