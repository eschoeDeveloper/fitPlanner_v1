import { createApp } from 'vue'

import "bootstrap/dist/css/bootstrap.min.css"

import router from './router/index'

import App from './App.vue'

const fitPlannerApp = createApp(App);

fitPlannerApp.use(router);
fitPlannerApp.mount("#app");
