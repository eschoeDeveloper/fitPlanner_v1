import { createWebHistory, createRouter } from "vue-router"

import information from "@/components/information";
import index from "@/components/index";

import AppLogin from "@/components/member/AppLogin";
import AppSignUp from "@/components/member/AppSignUp";
import AppPwdReset from "@/components/member/AppPwdReset";
import AppMain from "@/components/member/AppMain";
import AppLoginFail from "@/components/member/AppLoginFail";
import AppFitSchedule from "@/components/member/AppFitSchedule";
import AppFitExercise from "@/components/member/AppFitExercise";
import AppFitFoodDiet from "@/components/member/AppFitFoodDiet";
import AppFitHealthGym from "@/components/member/AppFitHealthGym";
import AppMemberEdit from "@/components/member/AppMemberEdit";
import AppSignUpResult from "@/components/member/AppSignUpResult";
import AppFitExerciseView from "@/components/member/AppFitExerciseView";

const routes = [

    { path: "/", name: "information", component: information},
    { path: "/index", name: "index", component: index},

    { path: "/member/app/login", name: "AppLogin", component: AppLogin},
    { path: "/member/app/signUp", name: "AppSignUp", component: AppSignUp},
    { path: "/member/app/pwdReset", name: "AppPwdReset", component: AppPwdReset},
    { path: "/member/app/main", name: "AppMain", component: AppMain},
    { path: "/member/app/loginFail", name: "AppLoginFail", component: AppLoginFail},
    { path: "/member/app/fitSchedule", name: "AppFitSchedule", component: AppFitSchedule},
    { path: "/member/app/fitExercise", name: "AppFitExercise", component: AppFitExercise},
    { path: "/member/app/fitExerciseView", name: "AppFitExerciseView", component: AppFitExerciseView},
    { path: "/member/app/fitFoodDiet", name: "AppFitFoodDiet", component: AppFitFoodDiet},
    { path: "/member/app/fitHealthGym", name: "AppFitHealthGym", component: AppFitHealthGym},
    { path: "/member/app/fitMemberEdit", name: "AppMemberEdit", component: AppMemberEdit},
    { path: "/member/app/signUpResult", name: "AppSignUpResult", component: AppSignUpResult},

]

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;