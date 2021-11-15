import VueAxios from "axios";

export default VueAxios.create({
  baseURL: process.env.VUE_APP_API_URL + "/api",
  headers: {
    "Content-type": "application/json",
    "X-ACCESS-TOKEN":
      "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjgsImlhdCI6MTYzNjk2MDM4NX0.jlHULTsvsHGk8Pl64nG6lq-5tFkDFOOZBBulWavMFCo",
  },
});
