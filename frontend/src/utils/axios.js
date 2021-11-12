import VueAxios from "axios";

export default VueAxios.create({
  baseURL: process.env.VUE_APP_API_URL + "/api",
  headers: {
    "Content-type": "application/json",
    "X-ACCESS-TOKEN":
      "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjgsImlhdCI6MTYzNjQ4MzkxMX0.7ZWZ-ThxN4b5_rX0lHvJP7Aj38XQp2gUdpkLNbch4Ec",
  },
});
