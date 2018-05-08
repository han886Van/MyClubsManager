<template>
  <div class="editPass">
    <div class="bgc">
      <div class="top">
        <span>修改密码</span>
        <div>
          <span class="editing" @click="toRouter('/mySpace')">取消</span>
        </div>
      </div>
      <div class="info">
        <div class="edit_input">
          <p><span>原始密码：</span> <el-input type="password" v-model="olderPass" placeholder="请输入内容"></el-input></p>
          <p><span>更改密码：</span> <el-input type="password" v-model="newPass" placeholder="请输入内容"></el-input></p>
          <p><span>确认密码：</span> <el-input type="password" v-model="entenNew" placeholder="请输入内容"></el-input>
          <span style="font-size: 10px;color: red;" v-show="newPass!= entenNew">*两次密码输入不正确</span>
          </p>
          <div><el-button type="primary" @click="toEdit()">确认修改</el-button></div>
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
        olderPass:'',
        newPass:'',
        entenNew:'',
        isSame:true
      }
    },
    methods:{
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
      toEdit(){
          if(this.newPass==this.entenNew){
            this.toRouter('/mySpace')
          }else {

          }
      }
    },
    mounted(){

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .editPass {
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
      font-size:16px;
      border-bottom :1px solid #ccc;
      margin-bottom:20px;
      span{
        color: #666
      }
      .editing{
        color: #409eff;
        cursor :pointer;
      }
    }
    .info {
      display: flex;
      justify-content: center;
      flex-wrap:wrap;
    }
      .edit_input{
        width: 600px;
        p{
          margin-bottom :40px;
        }
      }

  }
</style>
