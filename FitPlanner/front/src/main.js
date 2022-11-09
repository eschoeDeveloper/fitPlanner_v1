import { createApp } from 'vue';
import VueCookies from 'vue-cookies';
import VueMobileDetection from 'vue-mobile-detection';
import Toaster from '@meforma/vue-toaster';
import axios from 'axios';

import 'es6-promise/auto';

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

import App from '@/App.vue';
import AppHeader from "@/components/member/AppHeader";

import router from '@/router/index';
import store from '@/store/storage';

axios.defaults.headers.get["Content-Type"] = "application/json";
axios.defaults.headers.post["Content-Type"] = "application/json";
axios.defaults.headers.put["Content-Type"] = "application/json";

router.beforeEach((to, from, next) => {

});

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
fitPlannerApp.use(Toaster);
fitPlannerApp.use(store);
fitPlannerApp.use(VueMobileDetection);
fitPlannerApp.mount("#app");
