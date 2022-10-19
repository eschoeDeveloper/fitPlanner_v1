import { createWebHistory, createRouter } from "vue-router"

import AppMain from "../components/main/AppMain.vue";

const routes = [
    {
        path: "/",
        name: "AppMain",
        component: AppMain
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;