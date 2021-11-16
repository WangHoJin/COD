import VueAxios from "axios";

export default VueAxios.create({
  baseURL: process.env.VUE_APP_API_URL + "/api",
  headers: {
    "Content-type": "application/json",
    "X-ACCESS-TOKEN":
      "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjgsImlhdCI6MTYzNzAwMTEyOX0.smWGKrZt9Cfby7_cXgWJn4Qcp5_1JgvP8UVaLCdrYMs",
  },
});
