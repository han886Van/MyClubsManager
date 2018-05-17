import Vue from 'vue'

const vueProto = Vue.prototype;

// vueProto.defaultHead = require('../assets/defaultHead.jpeg')  // 默认头像图片的路径
/*vueProto.coverArray = [require('../assets/cover1.jpg'), require('../assets/cover2.jpg'), require('../assets/cover3.jpg'), require('../assets/cover4.jpg'),
  require('../assets/cover5.jpg'), require('../assets/cover6.jpg'), require('../assets/cover7.jpg'), require('../assets/cover8.jpg'), require('../assets/cover9.jpg')]*/

vueProto.localhost = 'http://localhost:8080/';

/*// 辅助事件处理
let eventHub = new Vue()
vueProto.$eventHub = eventHub*/
