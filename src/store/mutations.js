import {
  SET_SHOW_NAV,USER_ROULE


} from './mutation-types'

const mutations = {
  [SET_SHOW_NAV](state, data) {
    state.showNav = data
  },
    [USER_ROULE](state, data) {
    state.userRole = data
  },
}

export default mutations
