import { createWebHistory, createRouter } from "vue-router"

import AppLogin from "../components/AppLogin.vue";
import AppSignUp from "../components/AppSignUp.vue";
import AppPwdReset from "../components/AppPwdReset.vue";
import AppMain from "../components/AppMain.vue";
import AppLoginFail from "../components/AppLoginFail.vue";
import AppFitSchedule from "../components/AppFitSchedule.vue";
import AppFitExercise from "../components/AppFitExercise.vue";
import AppFitFoodDiet from "../components/AppFitFoodDiet.vue";
import AppFitHealthGym from "../components/AppFitHealthGym.vue";
import AppMemberEdit from "../components/AppMemberEdit.vue";
import AppSignUpResult from "../components/AppSignUpResult.vue";

const routes = [
    { path: "/", name: "AppLogin", component: AppLogin},
    { path: "/signUp", name: "AppSignUp", component: AppSignUp},
    { path: "/pwdReset", name: "AppPwdReset", component: AppPwdReset},
    { path: "/main", name: "AppMain", component: AppMain},
    { path: "/loginFail", name: "AppLoginFail", component: AppLoginFail},
    { path: "/fitSchedule", name: "AppFitSchedule", component: AppFitSchedule},
    { path: "/fitExercise", name: "AppFitExercise", component: AppFitExercise},
    { path: "/fitFoodDiet", name: "AppFitFoodDiet", component: AppFitFoodDiet},
    { path: "/fitHealthGym", name: "AppFitHealthGym", component: AppFitHealthGym},
    { path: "/fitMemberEdit", name: "AppMemberEdit", component: AppMemberEdit},
    { path: "/signUpResult", name: "AppSignUpResult", component: AppSignUpResult}
]

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;