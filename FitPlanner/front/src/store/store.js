import { createStore } from "vuex";

export default createStore({
    state: {
        admin: false,
        user: false,
        apiToken: null,
        barColor: 'rgba(0, 0, 0, .8), rgba(0, 0, 0, .8)',
        barImage: 'https://demos.creative-tim.com/material-dashboard/assets/img/sidebar-1.jpg',
        drawer: null
    },
    getters: {
        getStateValue(state, column) {
            return state[column];
        }
    },
    mutations: {
        setAuthorization(state, column, value) {
            state[column] = value;
        },
        setApiToken(state, _apiToken) {
            state.apiToken = _apiToken;
        },
        resetApiToken(state) {
            state.apiToken = null;
        },
        SET_BAR_IMAGE (state, payload) {
            state.barImage = payload
        },
        SET_DRAWER (state, payload) {
            state.drawer = payload
        },
    },
    actions: { // dispatch 로 부를 수 있다.
        setApiToken:({commit} , _apiToken) => {
            commit('setApiToken' , _apiToken);
        },
        resetApiToken({commit}) {
            commit("resetApiToken");
        }
    }
});