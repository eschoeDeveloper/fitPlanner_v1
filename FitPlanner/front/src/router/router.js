import { createWebHistory, createRouter } from "vue-router"

import information from "@/components/information";
import index from "@/components/index";

import AppLogin from "@/components/app/AppLogin";
import AppSignUp from "@/components/app/AppSignUp";
import AppSignOut from "@/components/app/AppSignOut";
import AppPwdReset from "@/components/app/AppPwdReset";
import AppPwdResetAuth from "@/components/app/AppPwdResetAuth";
import AppPwdResetExecute from "@/components/app/AppPwdResetExecute";
import AppMain from "@/components/app/AppMain";
import AppLoginFail from "@/components/app/AppLoginFail";
import AppFitSchedule from "@/components/app/AppFitSchedule";
import AppFitScheduleForm from "@/components/app/AppFitScheduleForm";
import AppFitScheduleView from "@/components/app/AppFitScheduleView";
import AppFitExercise from "@/components/app/AppFitExercise";
import AppFitExerciseView from "@/components/app/AppFitExerciseView";
import AppFitFoodDiet from "@/components/app/AppFitFoodDiet";
import AppFitHealthGym from "@/components/app/AppFitHealthGym";
import AppMemberEdit from "@/components/app/AppMemberEdit";
import AppSignUpResult from "@/components/app/AppSignUpResult";

const routes = [

    { path: "/", name: "information", component: information},
    { path: "/index", name: "index", component: index},

    { path: "/app/login", name: "AppLogin", component: AppLogin},
    { path: "/app/signUp", name: "AppSignUp", component: AppSignUp},
    { path: "/app/signOut", name: "AppSignOut", component: AppSignOut},
    { path: "/app/pwdReset", name: "AppPwdReset", component: AppPwdReset},
    { path: "/app/pwdResetAuth", name: "AppPwdResetAuth", component: AppPwdResetAuth},
    { path: "/app/pwdResetExecute", name: "AppPwdResetExecute", component: AppPwdResetExecute},
    { path: "/app/main", name: "AppMain", component: AppMain},
    { path: "/app/loginFail", name: "AppLoginFail", component: AppLoginFail},
    { path: "/app/fitSchedule", name: "AppFitSchedule", component: AppFitSchedule},
    { path: "/app/fitScheduleForm", name: "AppFitScheduleForm", component: AppFitScheduleForm},
    { path: "/app/fitScheduleView", name: "AppFitScheduleView", component: AppFitScheduleView},
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