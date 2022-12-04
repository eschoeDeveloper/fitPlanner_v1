import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router';
import store from '@/store/index';

import Main from '@/modules/main/main.vue';
import Login from '@/modules/login/login.vue';
import Register from '@/modules/register/register.vue';

import Dashboard from '@/pages/dashboard/dashboard.vue';

import Admin from '@/pages/management/admin/admin.vue';
import Schedule from '@/pages/management/schedule/schedule.vue';
import Member from '@/pages/management/member/member.vue';
import FoodDiet from '@/pages/management/foodDiet/foodDiet.vue';
import Exercise from '@/pages/management/exercise/exercise.vue';


import Profile from '@/pages/profile/profile.vue';
import ForgotPassword from '@/modules/forgot-password/forgot-password.vue';
import RecoverPassword from '@/modules/recover-password/recover-password.vue';
import PrivacyPolicy from '@/modules/privacy-policy/privacy-policy.vue';
import SubMenu from '@/pages/main-menu/sub-menu/sub-menu.vue';
import Blank from '@/pages/blank/blank.vue';

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'Main',
        component: Main,
        meta: {
            requiresAuth: true
        },
        children: [
            {
                path: 'profile',
                name: 'Profile',
                component: Profile,
                meta: {
                    requiresAuth: true
                }
            },
            {
                path: 'blank',
                name: 'Blank',
                component: Blank,
                meta: {
                    requiresAuth: true
                }
            },
            {
                path: 'sub-menu-1',
                name: 'Sub Menu 1',
                component: SubMenu,
                meta: {
                    requiresAuth: true
                }
            },
            {
                path: 'sub-menu-2',
                name: 'Sub Menu 2',
                component: Blank,
                meta: {
                    requiresAuth: true
                }
            },
            {
                path: '',
                name: 'Dashboard',
                component: Dashboard,
                meta: {
                    requiresAuth: true
                }
            },
            {
                path: '/management/admin',
                name: 'Admin',
                component: Admin,
                meta: {
                    requiresAuth: true
                }
            },
            {
                path: '/management/member',
                name: 'Member',
                component: Member,
                meta: {
                    requiresAuth: true
                }
            },
            {
                path: '/management/schedule',
                name: 'Schedule',
                component: Schedule,
                meta: {
                    requiresAuth: true
                }
            },
            {
                path: '/management/foodDiet',
                name: 'FoodDiet',
                component: FoodDiet,
                meta: {
                    requiresAuth: true
                }
            },
            {
                path: '/management/exercise',
                name: 'Exercise',
                component: Exercise,
                meta: {
                    requiresAuth: true
                }
            }
        ]
    },
    {
        path: '/login',
        name: 'Login',
        component: Login,
        meta: {
            requiresUnauth: true
        }
    },
    {
        path: '/register',
        name: 'Register',
        component: Register,
        meta: {
            requiresUnauth: true
        }
    },
    {
        path: '/forgot-password',
        name: 'ForgotPassword',
        component: ForgotPassword,
        meta: {
            requiresUnauth: true
        }
    },
    {
        path: '/recover-password',
        name: 'RecoverPassword',
        component: RecoverPassword,
        meta: {
            requiresUnauth: true
        }
    },
    {
        path: '/privacy-policy',
        name: 'RecoverPassword',
        component: PrivacyPolicy
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

router.beforeEach((to, from, next) => {
    if (to.meta.requiresAuth && !store.getters['auth/token']) {
        next('/login');
    } else if (to.meta.requiresUnauth && !!store.getters['auth/token']) {
        next('/');
    } else {
        next();
    }
});

export default router;
