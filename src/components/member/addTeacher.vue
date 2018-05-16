<template>
  <div class="addStudent">
    <div class="bgc">
      <div class="top">
        <div>
          <span>成员管理</span>
          <span>	&gt;</span>
          <span class="blue">添加教师</span>
        </div>
        <div>
          <span class="editing" @click="goBack()">取消</span>
        </div>
      </div>
      <div class="info">
        <div class="left_box">
          <p>
            <span class="span_title">姓名：</span>
            <el-input v-model="teacherName" placeholder="请输入姓名"></el-input>
          </p>
          <p>
            <span class="span_title">性别：</span>
            <el-select v-model="sex" placeholder="选择性别">
              <el-option label="男" value="1"></el-option>
              <el-option label="女" value="2"></el-option>
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
            <span class="span_title">分类：</span>
            <el-select v-model="teacherSort" placeholder="教师分类">
              <el-option label="专业学术类" value="1"></el-option>
              <el-option label="科技创新类" value="2"></el-option>
              <el-option label="艺术兴趣类" value="2"></el-option>
              <el-option label="体育健身类" value="4"></el-option>
              <el-option label="公益服务类" value="5"></el-option>
            </el-select>
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
        teacherName:'',
        birth:'',
        sex:'',
        teacherSort:'',
        email:'',
        phoneNum:'',
        teacherId:''
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
      this.teacherId = this.$route.query.teacherId;
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
