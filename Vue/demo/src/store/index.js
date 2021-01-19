import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    numbers: [1,3,5,7,9]
  },
  mutations: {
    ADD_NUMBER(state,payload){
      state.numbers.push(payload)
    }
  },
  actions: {
    addNumber({commit},number){
      commit("ADD_NUMBER", number)
    }
  },
  modules: {
  }
})
