import { createApp } from 'vue';
import VueCookies from 'vue-cookies';
import VueMobileDetection from 'vue-mobile-detection';
import Toaster from '@meforma/vue-toaster';
import axios from "@/plugins/axios";
import App from '@/App.vue';
import AppHeader from "@/components/app/AppHeader";
import router from '@/router/router';
import store from '@/store/store';
import {BootstrapVue3} from "bootstrap-vue-3";

import 'es6-promise/auto';
import "bootstrap"
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'

const fitPlannerApp = createApp(App);

fitPlannerApp.config.productionTip = false;

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
fitPlannerApp.use(BootstrapVue3);
fitPlannerApp.mount('#app')
