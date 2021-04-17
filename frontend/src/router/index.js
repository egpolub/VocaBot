import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Sidebar from '../components/Sidebar.vue'
import Dashboard from '../views/Dashboard.vue'
import Error from '../views/Error.vue'
import Game from '../views/Game.vue'
import Settings from '../views/Settings.vue'
import Main from '../views/Main.vue'
Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/voca',
      name: 'Sidebar',
      component: Sidebar,
      redirect: { name: 'Dashboard' },
      children: [{
        name: 'Dashboard',
        path: '/dashboard',
        component: Dashboard,
        meta: { requiresAuth: true }
      }, {
        name: 'Game',
        path: '/game',
        component: Game,
        meta: { requiresAuth: true }
      }, {
        name: 'Settings',
        path: '/settings',
        component: Settings,
        meta: { requiresAuth: true }
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
  if (to.matched.some(record => record.meta.requiresAuth) && (loggedIn === 'null' || loggedIn === null)) {
    // next({ name: 'Login' })
    next()
  }
  next()
})

export default router
