<template>
  <div class="detailMember">
    <div class="bgc">
      <div class="top" >
        <div><span>成员管理</span>
        <span>	&gt;</span>
        <span class="blue">成员信息</span></div>
        <div><span style="cursor: pointer;" class="blue"  @click="goBack()" >返回</span></div>
      </div>
      <div class="info">
        <div class="left_box">
          <img src="../../assets/img/home1.jpg" alt="">
        </div>
        <div class="right_box">
          <p v-for="(item,index) in user ">
            <span>{{item.name}}：</span>
            <span>{{item.conten}}</span>
          </p>
          <span v-show="false"><i class="iconfont icon-bianji"></i></span>
        </div>
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
        user: [
          {name: '学号', conten: '2014035643001'},
          {name: '年级', conten: '大四'},
          {name: '名字', conten: '陈小黄'},
          {name: '专业', conten: '电商软件'},
          {name: '性别', conten: '女'},
          {name: '学院', conten: '计算机科学学院'},
          {name: '出生日期', conten: '2018.01.01'},
          {name: '邮箱', conten: '80456656665@qq.com'},
          {name: '手机号', conten: '12345678945'},
        ]
      }
    },
    methods:{
      goBack(){
        this.$router.back(-1)
      },
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
    },
    mounted(){

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .detailMember {
    margin-left: 80px;
    margin-top: 50px;
    overflow: hidden;
    width: 1024px;
    background: #fff;
    min-height: 600px;
    border-radius: 8px;
    padding: 20px 40px;
    .bgc {
      border-radius: 8px;
      background-color: #fff;
    }
    .top{
      display: flex;
      justify-content:space-between;
      line-height:50px;
      font-size:16px;
      border-bottom :1px solid #ccc;
      margin-bottom:10px;
    }
    .info {
      display: flex;
      justify-content: flex-start;
    }
    .left_box {
      display: inline-block;
      img {
        width: 140px;
        height: 140px;
      }
    }
    .right_box {
      display: inline-block;
      margin-left: 50px;
      p {
        display: inline-block;
        line-height: 50px;
        font-size: 18px;
        min-width: 408px;
        max-width: 408px;
      }
    }
    .icon-bianji:hover {
      color: #d9a641
    }
  }
</style>
