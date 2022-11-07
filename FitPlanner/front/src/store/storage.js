import { createStore } from "vuex";

export default createStore({
    state: {
        admin: false,
        manager: false,
        user: false,
    },
    getters: {
        getStateValue(state, column) {
            return state[column];
        }
    },
    mutations: {
        setAuthorization(state, column, value) {
            state[column] = value;
        }
    }
});