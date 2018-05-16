<template>
  <div class="addStudent">
    <div class="bgc">
      <div class="top">
        <div>
          <span>成员管理</span>
          <span>	&gt;</span>
          <span class="blue">添加学生</span>
        </div>
        <div>
          <span class="editing" @click="goBack()">取消</span>
        </div>
      </div>
      <div class="info">
        <div class="left_box">
          <!--<span>名称</span>
          <span>申请社团</span>
          <span>申请人</span>
          <span>开始时间</span>
          <span>结束时间</span>
          <span>地点</span>
          <span>借用物资</span>
          <span>申请教师</span>
          <span>状态</span>-->
          <p>
            <span class="span_title">姓名：</span>
            <el-input v-model="studentName" placeholder="请输入姓名"></el-input>
          </p>
          <p>
            <span class="span_title">性别：</span>
            <el-select v-model="sex" placeholder="选择性别">
              <el-option label="男" value="1"></el-option>
              <el-option label="女" value="2"></el-option>
            </el-select>
          </p>
          <p>
            <span class="span_title">年级：</span>
            <el-select v-model="grade" placeholder="选择年级">
              <el-option label="大一" value="1"></el-option>
              <el-option label="大二" value="2"></el-option>
              <el-option label="大三" value="3"></el-option>
              <el-option label="大四" value="4"></el-option>
            </el-select>
          </p>
          <p>
            <span class="span_title">出生日期：</span>
            <el-date-picker
              v-model="birth"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </p>
        </div>
        <div class="right_box">
          <p>
            <span class="span_title">专业：</span>
            <el-input v-model="series" placeholder="请输入专业"></el-input>
          </p>
          <p>
            <span class="span_title">学院：</span>
            <el-input v-model="college" placeholder="请输入学院"></el-input>
          </p>
          <p>
            <span class="span_title">邮箱：</span>
            <el-input v-model="email" placeholder="请输入邮箱"></el-input>
          </p>
          <p>
            <span class="span_title">手机号码：</span>
            <el-input v-model="phoneNum" placeholder="请输入手机号码"></el-input>
          </p>
        </div>
      </div>
      <div class="btn">
        <el-button type="primary">立即提交</el-button>
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
        studentName:'',
        birth:'',
        sex:'',
        grade:'',
        series:'',
        college:'',
        email:'',
        phoneNum:'',
        studentId:'',


      }
    },
    methods: {
      goBack(){
        this.$router.back(-1)
      },
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
    },

    mounted(){
      this.studentId = this.$route.query.studentId;
    },
    created(){

    },
    watch: {},
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .addStudent {
    /*box-sizing border-box;*/
    /*padding: 80px 100px;*/
    margin-left: 80px;
    margin-top: 50px;
    overflow: hidden;
    width: 1024px;
    background: #fff;
    min-height: 600px;
    border-radius: 8px;
    margin-bottom: 50px;
    padding: 10px 40px 20px 40px;
    .top {
      line-height: 50px;
      display: flex;
      justify-content: space-between;
      border-bottom: 1px solid #ccc;
      margin-bottom: 20px;
      .editing {
        color: #409eff;
        cursor: pointer;
      }
    }
    .info {
      display: flex;
      justify-content: center;
      flex-wrap: wrap;
    }
    .right_box {
      display: inline-block;
      min-width: 408px;
      max-width: 408px;
      p {
        margin-bottom :10px;
        line-height: 50px;
        font-size: 18px;
        min-width: 408px;
        max-width: 408px;
      }

    }
    .left_box {
      display: inline-block;
      min-width: 408px;
      max-width: 408px;
      p {
        margin-bottom :10px;
        line-height: 50px;
        font-size: 18px;
        min-width: 408px;
        max-width: 408px;
      }

    }

    .span_title{
      display: inline-block;
      min-width :90px;
      text-align: right;
    }
      .btn{
        margin-top: 80px;
        display: flex;
        justify-content:center;
      }
  }

</style>
