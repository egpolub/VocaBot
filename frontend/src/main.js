import Vue from 'vue'
import VueKinesis from 'vue-kinesis'

import App from './App.vue'
import router from './router'
import store from './store'
import { library } from '@fortawesome/fontawesome-svg-core'
// import { faUserSecret } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faTelegramPlane } from '@fortawesome/free-brands-svg-icons'
import { faGamepad, faBars, faCog } from '@fortawesome/free-solid-svg-icons'

Vue.use(VueKinesis)
library.add(faTelegramPlane, faGamepad, faBars, faCog)
// library.add(FontAwesomeBrandsIcon)
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
