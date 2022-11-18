import { createStore } from "vuex";

export default createStore({
    state: {
        admin: false,
        user: false,
        apiToken: null
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
            //this.axios.defaults.headers.common["Authorization"] = null;
        }
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