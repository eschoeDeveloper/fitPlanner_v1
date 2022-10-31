import { createApp } from 'vue'
import VueCookies from 'vue-cookies'

import router from './router/index'
import axios from 'axios';

import App from './App.vue'

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"

import AppHeader from "@/components/AppHeader";

axios.defaults.headers.get["Content-Type"] = "application/json";
axios.defaults.headers.post["Content-Type"] = "application/json";
axios.defaults.headers.put["Content-Type"] = "application/json";

const fitPlannerApp = createApp(App);
fitPlannerApp.component("AppHeader", AppHeader);
fitPlannerApp.config.globalProperties.axios = axios;
fitPlannerApp.use(VueCookies,{
    expireTimes: "60*60*12",
    path: "/",
    domain: "",
    secure: true,
    sameSite: "None"
});
fitPlannerApp.use(router);
fitPlannerApp.mount("#app");
