<template>
  <div class="mySpace">
    <div class="bgc">
      <div class="top">
        <span>编辑个人信息</span>
        <div>
          <span class="editing" @click="toRouter('/mySpace')">保存</span>
        </div>
      </div>
      <div class="info">
        <div class="left_box">
          <el-upload
            class="avatar-uploader"
            action="https://jsonplaceholder.typicode.com/posts/"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
        <div class="right_box">
          <div>
            <p><span>学号：</span><span>{{user.userId}}</span></p>
            <p><span>年级：</span><span>{{user.grade}}</span></p>
          </div>
          <div>
            <p><span>名字：</span><span>{{user.name}}</span></p>
            <p><span>专业：</span><span>{{user.profession}}</span></p>
          </div>
          <div>
            <p><span>性别：</span>
              <span v-show="user.sex==0">女</span>
              <span v-show="user.sex==1">男</span>
            </p>
            <p><span>专业：</span><span>{{user.College}}</span></p>
          </div>
          <div>
            <p><span>出生日期：</span><span>{{user.birth}}</span></p>
            <p><span>邮箱：</span>
              <el-input v-model="user.email" placeholder="请输入内容"></el-input></p>
          </div>
          <div>
            <p><span>电话号码：</span>
              <el-input v-model="user.phoneNum" placeholder="请输入内容"></el-input></p>
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
        user:{userId:'2014035643001',grade:'大四',name:'陈小黄',profession:'电商软件',sex:0,College:'计算机科学学院',birth:'2018.01.01',email:'80456656665@qq.com',phoneNum:'12345678945'},
        imageUrl:require('../../assets/img/0.jpg'),
      }
    },
    methods:{
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
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
