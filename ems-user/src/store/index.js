import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate'
Vue.use(Vuex);
const config = {
    plugins: [createPersistedState()],
    state: {
        isLogin: false,
        username: '',
        token: ''
    },
    getters: {
        isLogin: state => state.isLogin,
        token: state => state.token,
        username: state => state.username
    },
    mutations: {
        updateLogin(state, payload) {
            state.isLogin = payload;
        },
        updateToken(state, payload) {
            state.token = payload;
        },
        updateUsername(state, payload) {
            state.username = payload;
        }
    },
    actions: {}
}
const store = new Vuex.Store(config);
export default store;