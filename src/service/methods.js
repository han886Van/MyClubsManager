/**
 * Created by wupeng on 2017/7/21.
 */
import Vue from 'vue'
const vueProto = Vue.prototype

// 控制依赖于登录的方法的执行顺序
vueProto.relyLogin = function (func) {
  let userObj = this.$store.getters.getUserObj
  if (this.debug || userObj.id) {
    if (func) {
      func()
    } else {
      return true
    }
  } else if (userObj.hadCheck) {
    // if(!sessionStorage.getItem('url')){
    //     sessionStorage.setItem('url',url)
    // }
    let thisUrl = window.location.href.split('#/')[1]           // 供绑定后跳转回来
    this.$router.push({path: '/login', query: {url: thisUrl}})
  } else {
    this.$store._mutations.SET_CACHE_FUNC[0](func)
  }
}

vueProto.getCaseCover = function (imgSrc, createTime) {
  if (imgSrc) {
    return '/crowdsourcing/upfile' + imgSrc
  } else {
    let date = new Date(createTime)
    return this.coverArray[date.getTime() % 9]
  }
}

vueProto.getDownloadFile = function (fileSrc) {
  if (fileSrc) {
    return '/crowdsourcing/upfile' + fileSrc
  } else {
    return ''
  }
}

vueProto.formatDate = function (obj) {
  let date = new Date(obj)
  let year = date.getFullYear()
  let month = date.getMonth() + 1
  let day = date.getDate()
  if (month < 10) {
    month = '0' + month
  }
  if (day < 10) {
    day = '0' + day
  }
  return year + '-' + month + '-' + day
}

vueProto.getUserHead = function (imgSrc) {
  if (imgSrc) {
    return '/crowdsourcing/upfile' + imgSrc
  } else {
    return this.defaultHead
  }
}

vueProto.getUserHeadNew = function (imgSrc) {
  if (imgSrc) {
    let path = JSON.parse(imgSrc)[0].path
    return 'crowdsourcing/upfile' + path
  } else {
    return this.defaultHead
  }
}

vueProto.beforeNowTime = function (time) {
  if (!time) {
    return false
  } else {
    let nowtime = (new Date()).getTime()
    let targettime = new Date(Date.parse(time.replace(/-/g, '/'))).getTime()
    if (nowtime < targettime) {
      return false
    } else {
      return true
    }
  }
}

// 识别是否是对象，若是对象，则返回对象数组
vueProto.transToArr = function (objOrArr) {
  if (typeof objOrArr.length !== 'number') {
    return [objOrArr]
  }
  return objOrArr
}
