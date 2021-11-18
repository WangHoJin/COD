import axios from "@/utils/axios";

async function signIn(info) {
  console.log("login API", info);
  try {
    const { data } = await axios.post("/users/signin", info);
    return data;
  } catch (error) {
    // console.error('login API', error.response.data.message);
    throw error;
  }
}

async function signUp(info) {
  console.log("signUp API", info);
  try {
    const { data } = await axios.post("/users/signup", info);
    return data;
  } catch (error) {
    // console.error('signUp API', error.response.data.message);
    throw error;
  }
}

async function getProfile(accessToken) {
  console.log("get login user Profile API");
  try {
    const { data } = await axios.get("/users/profile", {
      headers: {
        "X-ACCESS-TOKEN": accessToken,
      },
    });
    return data;
  } catch (error) {
    // console.error('user.js', error.response.data.message);
    throw error;
  }
}

async function getUserList(condition) {
  try {
    const { data } = await axios.get(
      `users?nickname=${condition.nickname}&page=${condition.page}&size=${condition.size}`
    );
    return data;
  } catch (error) {
    console.error(error.response.data.message);
  }
}
export { signIn, getProfile, signUp, getUserList };