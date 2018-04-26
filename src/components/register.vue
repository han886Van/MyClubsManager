<template>
  <div class="home register">
    <div class="bgc"></div>
    <div class="login">
      <p>注册</p>
      <el-radio-group v-model="formRegister.radio" @change="chooseRule()">
        <el-radio :label="1">社员</el-radio>
        <el-radio :label="2">社团管理员</el-radio>
        <el-radio :label="3">教师</el-radio>
      </el-radio-group>
      <div class="my_form">
        <el-form :label-position="labelPosition" label-width="80px" :model="formRegister">
          <el-form-item label="账号">
            <el-input v-model="formRegister.name"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input type="password" v-model="formRegister.passWord"></el-input>
          </el-form-item>
          <el-button type="primary" @click="toLogin" >立即注册</el-button>
        </el-form>
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
        labelPosition: 'right',
        formRegister: {
          name: '',
          passWord: '',
          radio: 3
        }
      }
    },
    methods: {
      chooseRule(){
        console.log(this.formRegister.radio);
      },
      toLogin(){
        console.log(this.formRegister);
        /*成功注册*/
        this.$message({
          message: '恭喜你，成功注册',
          type: 'success'
        });
        this.$router.push({path: '/home'})
      }
    },
    mounted(){

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .register {
    overflow: hidden
    width: 100%
    height: 100%
    box-sizing: border-box;
    .bgc {
      width: 100%
      height: 93%
      position: absolute;
      background: url("../assets/img/bg5.jpg") no-repeat;
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
        margin-bottom :30px;
      }
      .my_form{

      }

    }
  }
</style>
