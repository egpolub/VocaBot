import Vue from 'vue'
import VueKinesis from 'vue-kinesis'
import axios from 'axios'
import VueAxios from 'vue-axios'
import App from './App.vue'
import router from './router'
import store from './store'
import { library } from '@fortawesome/fontawesome-svg-core'
// import { faUserSecret } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faTelegramPlane } from '@fortawesome/free-brands-svg-icons'
import { faGamepad, faArrowDown, faArrowRight, faBars, faCog, faMoon, faLightbulb, faThumbtack, faSignOutAlt, faPencilAlt, faTimes, faTrashAlt } from '@fortawesome/free-solid-svg-icons'
import VueMobileDetection from 'vue-mobile-detection'
Vue.use(VueMobileDetection)
Vue.use(VueAxios, axios)
Vue.use(VueKinesis)
library.add(faTelegramPlane, faGamepad, faArrowRight, faArrowDown, faBars, faCog, faMoon, faLightbulb, faThumbtack, faSignOutAlt, faPencilAlt, faTimes, faTrashAlt)
// library.add(FontAwesomeBrandsIcon)
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  created () {
    const userString = localStorage.getItem('user')

    if (userString !== 'null' || userString !== null) {
      const userData = JSON.parse(userString)
      /* if (userData === false) {
        this.$store.commit('SET_USER_DATA', null)
        return
      } */
      this.$store.commit('SET_USER_DATA', userData)
    }
  },
  render: h => h(App)
}).$mount('#app')
