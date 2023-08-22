import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import axios from 'axios'

Vue.prototype.$http = axios

axios.defaults.baseURL = 'https://twhuang.top/api/breakblog'
// axios.defaults.baseURL = 'http://localhost:9000/api'

// http request 拦截器
axios.interceptors.request.use(
  (config) => {
    // config.headers["Content-Type"] = "application/x-www-from-urlencoded";
    const token = sessionStorage.getItem('token')
    if (token) {
      // 判断是否存在token，如果存在的话，则每个http header都加上token
      config.headers.Token = token
    }
    return config
  },
  (err) => {
    return Promise.reject(err)
  }
)
Vue.config.productionTip = false

new Vue({
  router,
  render: (h) => h(App)
}).$mount('#app')
