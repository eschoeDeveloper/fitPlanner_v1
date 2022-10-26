import { createApp } from 'vue'
import { VueCookies } from 'vue-cookies'

import router from './router/index'
import axios from 'axios';

import App from './App.vue'

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"

axios.defaults.headers.get["Content-Type"] = "application/json";
axios.defaults.headers.post["Content-Type"] = "application/json";
axios.defaults.headers.put["Content-Type"] = "application/json";

const fitPlannerApp = createApp(App);
fitPlannerApp.config.globalProperties.axios = axios;
fitPlannerApp.use(VueCookies,{
    expireTimes: "60*60*12",
    secure: true
})
fitPlannerApp.use(router);
fitPlannerApp.mount("#app");
