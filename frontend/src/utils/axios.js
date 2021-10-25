import VueAxios from 'vue-axios';
import axios from 'axios';
// import config from '../config'

const BASE_URL = process.env.BASE_URL + '/api';
const DEFAULT_ACCEPT_TYPE = 'application/json';

axios.defaults.baseURL = BASE_URL;
axios.defaults.headers['Content-Type'] = DEFAULT_ACCEPT_TYPE;

export default { VueAxios, axios };
