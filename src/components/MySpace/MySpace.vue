<template>
  <div class="mySpace">
    <div class="conten">
      <div class="left_box">
        <img src="../../assets/img/home1.jpg" alt="">
      </div>
      <div class="right_box">
        <ul class="inner_box">
          <li><span>姓名：</span><span class="right_span">{{user.name}}</span></li>
          <li><span>年级：</span><span class="right_span">{{user.grade}}</span></li>
          <li><span>班级：</span><span class="right_span">{{user.classN}}</span></li>
          <li><span>性别：</span><span class="right_span">{{user.sex}}</span></li>
          <li><span>出生日期：</span><span class="right_span">{{user.birthday}}</span></li>
          <li><span>学号：</span><span class="right_span">{{user.idNum}}</span></li>
          <li><span>学校：</span><span class="right_span">{{user.schoolName}}</span></li>
          <li><span>二级学院：</span><span class="right_span">{{user.branc}}</span></li>
          <li><span>专业：</span><span class="right_span">{{user.profession}}</span></li>
          <li v-show="false"><i class="iconfont icon-bianji"></i></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: '',
    components: {},
    data () {
      return {
        user: {
          name: '小仙女', sex: '女', birthday: '1995.11.08', idNum: '2014035643000', schoolName: '广东技术师范学院',
          profession: '电子商务', branc: '计算机科学学院',
          grade: '大四', classN: '14商本'
        }
      }
    },
    mounted(){

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .mySpace {
    padding-top: 30px;
    height: 100%
    max-width: 90%;
    min-width: 90%;
    overflow hidden;
    display: flex;
    justify-content: center;
    align-items: center;
    .conten {
      background-color: #fff
      width: 912px;
      height: 574px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      img {
        width: 150px;
        height: 140px;
      }
    }
    .left_box {
      width: 210px;
      height: 100%;
      display: flex;
      justify-content: center;
      align-items: center;

    }

    .right_box {
      display: inline-block;
      width: 700px
      height: 100%;
      box-sizing: border-box;
      padding: 50px 0;

    }
    .inner_box {
      height: 100%;
      box-sizing: border-box;
      border-left: 1px solid #999;
      padding: 50px;
      li {
        font-size: 18px;
        margin-bottom: 30px;
        span {
          color: #999
        }
      }
      .right_span {
        display: inline-block;
        padding-left: 20px;
        color: #666
      }
    }
    .icon-bianji {
      font-size: 30px;
      float: right;
    }
    .icon-bianji:hover {
      color: #d9a641
    }
  }
</style>
