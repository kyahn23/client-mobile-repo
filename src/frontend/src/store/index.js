import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default function() {
  const store = new Vuex.Store({
    state: {
      isLoading: false,
      isAuth: false,
      currentUser: "",
      newSocialUser: {
        service: "",
        id: "",
        email: "",
        name: ""
      }
    },
    getters: {
      isLoading: state => state.isLoading,
      isAuth: state => state.isAuth,
      currentUser: state => state.currentUser,
      newSocialUser: state => state.newSocialUser
    },
    mutations: {
      setLoading(state, payload) {
        state.isLoading = payload.isLoading;
      },
      setAuth(state, payload) {
        state.isAuth = payload ? payload.isAuth : !state.isAuth;
      },
      setCurrentUser(state, payload) {
        state.currentUser = payload.currentUser;
      },
      setNewSocialUser(state, payload) {
        state.newSocialUser.service = payload.service;
        state.newSocialUser.id = payload.id;
        state.newSocialUser.email = payload.email;
        state.newSocialUser.name = payload.name;
      }
    }
  });

  return store;
}
