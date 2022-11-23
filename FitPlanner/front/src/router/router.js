import { createWebHistory, createRouter } from "vue-router"

import information from "@/components/information";
import index from "@/components/index";

import AppLogin from "@/components/app/AppLogin";
import AppSignUp from "@/components/app/AppSignUp";
import AppPwdReset from "@/components/app/AppPwdReset";
import AppMain from "@/components/app/AppMain";
import AppLoginFail from "@/components/app/AppLoginFail";
import AppFitSchedule from "@/components/app/AppFitSchedule";
import AppFitExercise from "@/components/app/AppFitExercise";
import AppFitFoodDiet from "@/components/app/AppFitFoodDiet";
import AppFitHealthGym from "@/components/app/AppFitHealthGym";
import AppMemberEdit from "@/components/app/AppMemberEdit";
import AppSignUpResult from "@/components/app/AppSignUpResult";
import AppFitExerciseView from "@/components/app/AppFitExerciseView";

const routes = [

    { path: "/", name: "information", component: information},
    { path: "/index", name: "index", component: index},

    { path: "/app/login", name: "AppLogin", component: AppLogin},
    { path: "/app/signUp", name: "AppSignUp", component: AppSignUp},
    { path: "/app/pwdReset", name: "AppPwdReset", component: AppPwdReset},
    { path: "/app/main", name: "AppMain", component: AppMain},
    { path: "/app/loginFail", name: "AppLoginFail", component: AppLoginFail},
    { path: "/app/fitSchedule", name: "AppFitSchedule", component: AppFitSchedule},
    { path: "/app/fitExercise", name: "AppFitExercise", component: AppFitExercise},
    { path: "/app/fitExerciseView", name: "AppFitExerciseView", component: AppFitExerciseView},
    { path: "/app/fitFoodDiet", name: "AppFitFoodDiet", component: AppFitFoodDiet},
    { path: "/app/fitHealthGym", name: "AppFitHealthGym", component: AppFitHealthGym},
    { path: "/app/fitMemberEdit", name: "AppMemberEdit", component: AppMemberEdit},
    { path: "/app/signUpResult", name: "AppSignUpResult", component: AppSignUpResult},

]

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;