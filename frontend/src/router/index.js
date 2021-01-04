import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Voca from '../views/Voca.vue'
import Dashboard from '../views/Dashboard.vue'
import Error from '../views/Error.vue'
import Game from '../views/Game.vue'
import Settings from '../views/Settings.vue'
Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/voca',
      name: 'Voca',
      component: Voca,
      children: [{
        name: 'Dashboard',
        path: '/dashboard',
        component: Dashboard
      }, {
        name: 'Game',
        path: '/game',
        component: Game
      }, {
        name: 'Settings',
        path: '/settings',
        component: Settings
      }],
      meta: { requiresAuth: true }
    },
    {
      path: '/404',
      name: 'NotFound',
      component: Error
    },
    {
      path: '*',
      redirect: '/404'
    }
  ]
})

router.beforeEach((to, from, next) => {
  const loggedIn = localStorage.getItem('user')
  if (to.matched.some(record => record.meta.requiresAuth) && !loggedIn) {
    next({ name: 'Login' })
  }
  next()
})

export default router
