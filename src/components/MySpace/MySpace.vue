<template>
  <div class="mySpace">
    <div class="bgc">
      <div class="top">
        <span>个人信息</span>
        <div>
          <span class="editing" @click="toRouter('/editUerInfo')">编辑</span>
          <span>|</span>
          <span class="editing" @click="toRouter('/editPass')">修改密码</span>
        </div>
      </div>
      <div class="info">
        <div class="left_box">
          <img v-show="getUser.sex==0" src="../../assets/img/0.jpg" alt="">
          <img v-show="getUser.sex==1" src="../../assets/img/1.png" alt="">
        </div>
        <div class="right_box">
          <div class="left_item">
            <p v-show="getUser.student_num"><span>学号：</span><span>{{getUser.student_num}}</span></p>
            <p v-show="getUser.user_name"><span>名字：</span><span>{{getUser.user_name}}</span></p>
            <p><span>性别：</span>
              <span v-show="getUser.sex==0">女</span>
              <span v-show="getUser.sex==1">男</span>
            </p>
            <p ><span>出生日期：</span>
              <span v-show="getUser.birthday_time">{{getUser.birthday_time}}</span>
              <span v-show="!getUser.birthday_time">暂无</span>
            </p>
            <p ><span>电话号码：</span>
              <span v-show="getUser.phone">{{getUser.phone}}</span>
              <span v-show="!getUser.phone">暂无</span>
            </p>
          </div>
          <div class="right_item">
            <p v-show="getUser.type_name"><span>分类：</span><span>{{getUser.type_name}}</span></p>
            <p v-show="getUser.grade"><span>年级：</span><span>{{getUser.grade}}</span></p>
            <p v-show="getUser.major"><span>专业：</span><span>{{getUser.major}}</span></p>
            <p v-show="getUser.college"><span>学院：</span><span>{{getUser.college}}</span></p>
            <p v-show="getUser.email"><span>邮箱：</span>
              <span>{{getUser.email}}</span>
            </p>
          </div>
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
        getUser:''
      }
    },
    methods:{
      createFunc(){
        var userId = localStorage.getItem('userId');
        var url = this.localhost+'associationMg/user/personalCenter';
        var json ={
          userId:userId
        };
        console.log(json);
        this.$http.post(url,json).then(
          (success) => {
          var response = success.data;
            this.getUser=response.getUser;
            console.log(this.getUser);
            console.log(response);

      }, (error) => {
            this.$message.error('错误，请求数据失败');
        });
      },
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
    },
    created() {
      this.createFunc()
    },
    mounted(){

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .mySpace {
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
      line-height:50px;
      display: flex;
      justify-content:space-between;
      border-bottom :1px solid #ccc;
      margin-bottom:20px;
      .editing{
       color: #409eff;
       cursor :pointer;
     }
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
      display: flex;
      justify-content: flex-start;
      margin-left: 50px;
      p {
        display: inline-block;
        line-height: 50px;
        font-size: 18px;
        min-width: 408px;
        max-width: 408px;
        position: relative;
        span{
          display: inline-block;
          min-width:90px;
        }
      }
    }
    .icon-bianji:hover {
      color: #d9a641
    }
  }
</style>
