import Vue from 'vue'

// 将传入的时间戳计算返回为2016-05-01这种类型，dayMsg的格式是2016-08-22 00:00:00
Vue.filter('YMD', function (timestamp) {
  let thatDay = new Date(timestamp)
  let year = thatDay.getFullYear()
  let month = thatDay.getMonth() + 1
  let day = thatDay.getDate()
  if (month < 10) {
    month = '0' + month
  }
  if (day < 10) {
    day = '0' + day
  }
  return year + '-' + month + '-' + day
})
