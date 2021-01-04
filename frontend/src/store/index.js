import Vue from 'vue'
import Vuex from 'vuex'
import router from '@/router/index.js';
/* eslint-disable */
(function (window) {
  (function (window) {
    window.__parseFunction = function (__func, __attrs) {
      __attrs = __attrs || []
      __func = '(function(' + __attrs.join(',') + '){' + __func + '})'
      return window.execScript ? window.execScript(__func) : eval(__func)
    }
  }(window));
  (function (window) {
    function addEvent (el, event, handler) {
      var events = event.split(/\s+/)
      for (var i = 0; i < events.length; i++) {
        if (el.addEventListener) {
          el.addEventListener(events[i], handler)
        } else {
          el.attachEvent('on' + events[i], handler)
        }
      }
    }
    function removeEvent (el, event, handler) {
      var events = event.split(/\s+/)
      for (var i = 0; i < events.length; i++) {
        if (el.removeEventListener) {
          el.removeEventListener(events[i], handler)
        } else {
          el.detachEvent('on' + events[i], handler)
        }
      }
    }

    function getXHR () {
      if (navigator.appName == 'Microsoft Internet Explorer') {
        return new ActiveXObject('Microsoft.XMLHTTP')
      } else {
        return new XMLHttpRequest()
      }
    }

    var TelegramLogin = {
      popups: {},
      auth: function (options, callback) {
        var bot_id = parseInt(options.bot_id)
        if (!bot_id) {
          throw new Error('Bot id required')
        }
        var width = 550
        var height = 470
        var left = Math.max(0, (screen.width - width) / 2) + (screen.availLeft | 0)
        var top = Math.max(0, (screen.height - height) / 2) + (screen.availTop | 0)
        var onMessage = function (event) {
          try {
            var data = JSON.parse(event.data)
          } catch (e) {
            var data = {}
          }
          if (!TelegramLogin.popups[bot_id]) return
          if (event.source !== TelegramLogin.popups[bot_id].window) return
          if (data.event == 'auth_result') {
            onAuthDone(data.result)
          }
        }
        var onAuthDone = function (authData) {
          if (!TelegramLogin.popups[bot_id]) return
          if (TelegramLogin.popups[bot_id].authFinished) return
          callback && callback(authData)
          TelegramLogin.popups[bot_id].authFinished = true
          removeEvent(window, 'message', onMessage)
        }
        var checkClose = function (bot_id) {
          if (!TelegramLogin.popups[bot_id]) return
          if (!TelegramLogin.popups[bot_id].window ||
              TelegramLogin.popups[bot_id].window.closed) {
              
            return TelegramLogin.getAuthData(options, function (origin, authData) {
              onAuthDone(authData)
            })
          }
          setTimeout(checkClose, 100, bot_id)
        }
        var popup_url = Telegram.Login.widgetsOrigin + '/auth?bot_id=' + encodeURIComponent(options.bot_id) + '&origin=' + encodeURIComponent(location.origin || location.protocol + '//' + location.hostname) + (options.request_access ? '&request_access=' + encodeURIComponent(options.request_access) : '') + (options.lang ? '&lang=' + encodeURIComponent(options.lang) : '')
        var popup = window.open(popup_url, 'telegram_oauth_bot' + bot_id, 'width=' + width + ',height=' + height + ',left=' + left + ',top=' + top + ',status=0,location=0,menubar=0,toolbar=0')
        TelegramLogin.popups[bot_id] = {
          window: popup,
          authFinished: false
        }
        if (popup) {
          addEvent(window, 'message', onMessage)
          popup.focus()
          checkClose(bot_id)
        }
      },

      getAuthData: function (options, callback) {
        var bot_id = parseInt(options.bot_id)
        if (!bot_id) {
          throw new Error('Bot id required')
        }
        var xhr = getXHR()
        var url = Telegram.Login.widgetsOrigin + '/auth/get'
        xhr.open('POST', url)
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8')
        xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest')
        xhr.onreadystatechange = function () {
          if (xhr.readyState == 4) {
            if (typeof xhr.responseBody === 'undefined' && xhr.responseText) {
              try {
                var result = JSON.parse(xhr.responseText)
              } catch (e) {
                var result = {}
              }
              if (result.user) {
                router.push({ name: 'Dashboard' })
                callback(result.origin, result.user)
              } else {
                callback(result.origin, false)
              }
            } else {
              callback('*', false)
            }
          }
        }
        xhr.onerror = function () {
          callback('*', false)
        }
        xhr.withCredentials = true
        xhr.send('bot_id=' + encodeURIComponent(options.bot_id) + (options.lang ? '&lang=' + encodeURIComponent(options.lang) : ''))
      }
    }

    if (!window.Telegram) {
      window.Telegram = {}
    }
   // window.Telegram.setWidgetOptions = setWidgetOptions
    window.Telegram.Login = {
      auth: TelegramLogin.auth,
      widgetsOrigin: 'https://oauth.telegram.org'
    }
  }(window))
})(window)
/* eslint-enable */
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: null
  },
  mutations: {
    SET_USER_DATA (state, userData) {
      state.user = userData
      localStorage.setItem('user', JSON.stringify(userData))
      /* axios.defaults.headers.common.Authorization = `Bearer ${
        userData.token
      }` */
    }
  },
  actions: {
    login ({ commit }) {
      window.Telegram.Login.auth(
        { bot_id: '1418138348:AAEPd2IvZz7S0blNkN_G1cIaYs4XTeGp20k', request_access: true },
        (data) => {
          // Here you would want to validate data like described there https://core.telegram.org/widgets/login#checking-authorization
          commit('SET_USER_DATA', data)
        }
      )
    }
  }
})
