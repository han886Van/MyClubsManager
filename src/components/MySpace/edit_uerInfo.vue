<template>
  <div class="mySpace">
    <div class="bgc">
      <div class="top">
        <span>编辑个人信息</span>
        <div>
          <span class="editing" @click="goBack()">返回</span>
        </div>
      </div>
      <div class="info" v-show="userRole!=3">
        <div class="left_box">
          <div class="avatar-uploader">
            <img :src="imageUrl" class="avatar">
            <input type="file" name="file" accept="image/gif,image/jpeg,image/jpg,image/png" @change="postFile">
          </div>
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
            <p >
              <span>电话号码：</span>
              <el-input v-model="getUser.phone" placeholder="请输入内容" clearable></el-input>
              <span  class="tip"  v-show="!phoneRegex.test(getUser.phone)">*请输入正确手机号码格式</span>
            </p>
          </div>
          <div class="right_item">
            <p v-show="getUser.type_name"><span>分类：</span><span>{{getUser.type_name}}</span></p>
            <p v-show="getUser.grade"><span>年级：</span><span>{{getUser.grade}}</span></p>
            <p v-show="getUser.major"><span>专业：</span><span>{{getUser.major}}</span></p>
            <p v-show="getUser.college"><span>学院：</span><span>{{getUser.college}}</span></p>
            <p v-show="getUser.email">
              <span>邮箱：</span>
              <el-input v-model="getUser.email" placeholder="请输入内容" clearable></el-input>
              <span class="tip" v-show="!emailRegex.test(getUser.email)">*请输入正确邮箱格式</span>
            </p>
          </div>
        </div>
      </div>
      <div class="info" v-show="userRole==3">
        <div class="left_box">
          <div class="avatar-uploader">
            <img :src="imageUrl" class="avatar">
            <input type="file" name="file" accept="image/gif,image/jpeg,image/jpg,image/png" @change="postFile">
          </div>
        </div>
        <div class="right_box">
          <div class="left_item">
            <p v-show="getUser.student_num">
              <span>学号：</span>
              <span>{{getUser.student_num}}</span>
            </p>
            <p v-show="getUser.user_name">
              <span>名字：</span>
              <el-input v-model="getUser.user_name" placeholder="请输入内容" clearable></el-input>
            </p>
            <p><span>性别：</span>
              <el-select v-model="getUser.sex" placeholder="社团分类">
                <el-option label="女" value="0"></el-option>
                <el-option label="男" value="1"></el-option>
              </el-select>
            </p>
            <p ><span>出生日期：</span>
              <el-date-picker
                v-model="getUser.birthday_time"
                format="yyyy - MM - dd "
                value-format="yyyy-MM-dd"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </p>
            <p >
              <span>电话号码：</span>
              <el-input v-model="getUser.phone" placeholder="请输入内容" clearable></el-input>
              <span  class="tip"  v-show="!phoneRegex.test(getUser.phone)">*请输入正确手机号码格式</span>
            </p>
          </div>
          <div class="right_item">
            <p v-show="getUser.type_name"><span>分类：</span><span>{{getUser.type_name}}</span></p>
            <p v-show="getUser.grade"><span>年级：</span><span>{{getUser.grade}}</span></p>
            <p v-show="getUser.major"><span>专业：</span><span>{{getUser.major}}</span></p>
            <p v-show="getUser.college"><span>学院：</span><span>{{getUser.college}}</span></p>
            <p v-show="getUser.email">
              <span>邮箱：</span>
              <el-input v-model="getUser.email" placeholder="请输入内容" clearable></el-input>
              <span class="tip" v-show="!emailRegex.test(getUser.email)">*请输入正确邮箱格式</span>
            </p>
          </div>
        </div>
      </div>
      <div class="bottom" v-show="userRole!=3"><el-button type="primary" @click="toEdit()">确认修改</el-button></div>
      <div class="bottom" v-show="userRole==3"><el-button type="primary" @click="allEdit()">确认修改</el-button></div>
    </div>
  </div>
</template>

<script>
  export default {
    name: '',
    components: {},
    data () {
      return {
        imageUrl:'',
        phoneRegex :/^[1][3,4,5,7,8][0-9]{9}$/,
        emailRegex :/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/,
        getUser:'',
        file:'',
        userRole:''
      }
    },
    methods:{
      createFunc(){
        this.userRole = localStorage.getItem('userRole');
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var userId = localStorage.getItem('userId');
        var url = this.localhost+'associationMg/user/personalCenter';
        var json ={
          userId:userId
        };
        console.log(json);
        this.$http.post(url,json).then(
          (success) => {
          setTimeout(() => {
          loading.close();
        }, 500);
          var response = success.data;
          this.getUser=response.getUser;
          if(this.getUser.headimg=='0'){
            if(this.getUser.sex==0){
              this.imageUrl= require('../../assets/img/0.jpg')
            }else {
              this.imageUrl= require('../../assets/img/1.png')
            }
          }else {
            this.imageUrl = this.getUser.headimg;
          }
        }, (error) => {
          setTimeout(() => {
            loading.close();
        }, 500);
          this.$message.error('错误，请求数据失败');
        });
      },
      goBack(){
        this.$router.back(-1)
      },
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
        console.log(file);
      },
      beforeAvatarUpload(file) {
          this.file=file;
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        return isJPG && isLt2M;
        /*       /!* const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;*!/
        if (file.type != "image/png" || file.type != "image/jpeg" || file.type != "image/bmp" || file.type != "image/jpg") {
          this.$message.error('上传正确头像图片!');
        }
        /!*if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;*!/*/
      },
      toEdit(){
        var userId = localStorage.getItem('userId');
//          this.postFile(this.file,'HEADPIC',userId,userId);
          if(!this.phoneRegex.test(this.getUser.phone)){
            this.$message.error('错误，手机号码格式错误！');
          }else if(!this.emailRegex.test(this.getUser.email)){
            this.$message.error('错误，邮箱格式错误！');
          }else{
              var url = this.localhost +'associationMg/user/saveOrUpdateUser';
              var json={
                userId:userId,
                email:this.getUser.email,
                phone:this.getUser.phone,
                headImg:this.imageUrl,
              };
            this.$http.post(url,json).then(
              (success) => {
                var response = success.data;
                console.log(response);
                if (response.msg==666){
                  this.$message({
                    message: '修改个人信息成功',
                    type: 'success'
                  });
                  this.goBack()
                }else {

                  this.$message.error('编辑信息失败哦！');
                }
              }, (error) => {

                this.$message.error('错误，请求数据失败');
              });
          }

      },
      allEdit(){
        var userId = localStorage.getItem('userId');
        var headImg = this.imageUrl;
        var userName = this.getUser.user_name;
        var sex =this.getUser.sex;
        var birthday =this.getUser.birthday_time;
        var phone = this.getUser.phone;
        var email = this.getUser.email;
        if(!userName){
          this.$message.error('用户姓名不能为空！');
        }else if(!userName) {
          this.$message.error('用户姓名不能为空！');
        }else if(!this.phoneRegex.test(this.getUser.phone)){
          this.$message.error('错误，手机号码格式错误！');
        }else if(!this.emailRegex.test(this.getUser.email)){
          this.$message.error('错误，邮箱格式错误！');
        }else {
          var url = this.localhost +'associationMg/user/saveOrUpdateUser';
          var json={
            userId:userId,
            headImg :this.imageUrl,
            userName: this.getUser.user_name,
            sex :this.getUser.sex,
            birthday :this.getUser.birthday_time,
            phone :this.getUser.phone,
            email : this.getUser.email,
          };
          this.$http.post(url,json).then(
            (success) => {
            var response = success.data;
          console.log(response);
          if (response.msg==666){
            this.$message({
              message: '修改个人信息成功',
              type: 'success'
            });
            this.goBack()
          }else {

            this.$message.error('编辑信息失败哦！');
          }
        }, (error) => {

            this.$message.error('错误，请求数据失败');
          });

      }

      },
      postFile(e){
        var files = e.target.files || e.dataTransfer.files;
        if (!files.length) return;
        var file = files[0];
        console.log(file);
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var url =this.localhost+'associationMg/attachment/uploadFile';
        var formData = new FormData();
        formData.append('file', file);
        formData.append('state', '1');
        formData.append('attachmentType', 'HEADPIC');
        var data={
          state:1 ,
          attachmentType:'HEADPIC',
        };
//        formData.append('attachmentId', attachmentId);
        formData.append('userId', localStorage.getItem('userId'));
        this.$http.post(url,formData).then(
          (success) => {
            setTimeout(() => {
              loading.close();
            }, 500);
            var response = success.data;
            console.log(response);
         this.imageUrl = response.headImg
          }, (error) => {
            setTimeout(() => {
              loading.close();
            }, 500);
            this.$message.error('错误，上传头像错误');
          });
      }
    },
    mounted(){

    },
    created() {
      this.createFunc()
    },
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
      .tip{
        font-size: 12px;
        color: red;
        position: absolute;
        top: 36px;
        left: 94px;
      }
        .bottom{
          margin-top: 80px;
          display: flex;
          justify-content:center;
        }
    .avatar-uploader {
      margin-right:10px;
      width: 140px;
      height: 140px;
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }
    .avatar-uploader:hover {
      border-color: #20a0ff;
    }
    .avatar-uploader img{
      width: 100%;
      height: 100%;
    }

  }
</style>
