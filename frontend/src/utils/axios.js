import VueAxios from 'axios';

export default VueAxios.create({
  baseURL: process.env.VUE_APP_API_URL + '/api',
  headers: {
    'Content-type': 'application/json',
  },
});
