// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import '@/assets/css/iconfont.css'
import axios from 'axios'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '../static/reset.css'
import '@/assets/css/my_element.css'
import '@/assets/css/public.css'
import store from './store'
// import '@/service'
import './service/index'

axios.defaults.withCredentials = true;
Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.use(ElementUI);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
