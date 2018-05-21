<template>
  <div class="home">
    <div class="bgc"></div>
    <div class="login">
      <p>登录</p>
      <el-radio-group v-model="formLabelAlign.radio" @change="chooseRule()">
        <el-radio :label="3">学生</el-radio>
        <el-radio :label="2">教师</el-radio>
        <el-radio :label="1">管理员</el-radio>
      </el-radio-group>
      <div class="my_form">
        <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
          <el-form-item label="账号">
            <el-input v-model="formLabelAlign.name" clearable></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input type="password" v-model="formLabelAlign.passWord" clearable></el-input>
          </el-form-item>
          <el-button type="primary" @click="goLogin">立即登录</el-button>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
  import {mapGetters, mapMutations} from 'vuex'
  export default {
    name: '',
    components: {},
    data () {
      return {
        labelPosition: 'right',
        formLabelAlign: {
          name: '',
          passWord: '',
          radio: 3,
        },
        userRole: '',
        typeId:''
      }
    },
    methods: {
      ...mapMutations([
        'USER_ROULE',
      ]),
      chooseRule(){

      },
      goLogin(){
          var userName=this.formLabelAlign.name;
          var passWord=this.formLabelAlign.passWord;
        if( userName ==''&& passWord==''){
          this.$message.error('错误，账号和密码不能为空');
        }else if( userName ==''){
          this.$message.error('错误，账号不能为空 ');
        } else if(passWord==''){
          this.$message.error('错误，密码不能为空');
        }else {
          this.Login(userName,passWord)
        }

      },
      Login(userName,passWord){
        var json = {
          account: userName,
          password: passWord,
          userType: this.formLabelAlign.radio
        };
        var url = this.localhost+'associationMg/user/login';
        this.$http.post(url, json).then(
          (success) => {
            var response = success.data;
            if(response.msg==555){
              this.$message.error('错误，账号密码错误');
            }else if(response.msg==666){
              /*radio 1管理员 2教师 3学生*/
              /*userRole 1 学生 2教师 3管理员*/
              console.log(response);
              if (this.formLabelAlign.radio == 1) {
                this.userRole = 3
              } else if (this.formLabelAlign.radio == 2) {
                this.userRole = 2
              } else {
                this.userRole = 1
              }
              localStorage.setItem("userRole", this.userRole);
              localStorage.setItem("userId", response.loginUser.user_id);
              localStorage.setItem("account", response.loginUser.account);
              /*老师分类*/
              localStorage.setItem("typeId", response.loginUser.type_id);
              this.USER_ROULE(localStorage.getItem('userRole'));
              this.$router.push({path: '/manage'});
            }
          }, (error) => {
            this.$message.error('请求错误，请稍后再试');
          });

      }
    },
    mounted(){

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .home {
    overflow: hidden
    width: 100%
    height: 100%
    box-sizing: border-box;
    .bgc {
      width: 100%
      height: 93%
      position: absolute;
      background: url("../assets/img/bg6.jpg") no-repeat;
      background-position: center;
      background-size: 100% 100%;
      z-index: -22;
    }
    .login {
      margin-top: 20px;
      color: #fff;
      width: 100%;
      height: 100%;
      position: relative;
      p {
        text-align: center;
        font-size: 30px;
        margin-bottom: 30px;
      }
      .my_form {

      }

    }
  }
</style>
