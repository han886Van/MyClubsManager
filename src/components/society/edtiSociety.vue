<template>
  <div class="edtiSociety">
    <div class="bgc">
      <div class="top">
        <div>
          <span>社团管理</span>
          <span>	&gt;</span>
          <span class="blue">创建社团</span>
        </div>
        <div>
          <span class="editing" @click="goBack()">取消</span>
        </div>
      </div>
      <div class="info">
        <div class="edit_input">
          <p>
            <span class="title_span">社团分类：</span>
            <el-select v-model="sortSociety" placeholder="社团分类">
              <el-option label="专业学术类" value="1"></el-option>
              <el-option label="科技创新类" value="2"></el-option>
              <el-option label="艺术兴趣类" value="2"></el-option>
              <el-option label="体育健身类" value="4"></el-option>
              <el-option label="公益服务类" value="5"></el-option>
            </el-select>
          </p>
          <p><span class="title_span">社团名称：</span> <el-input  placeholder="请输入内容"></el-input></p>
          <p><span class="title_span">社长账号：</span><el-input
            placeholder="请输入内容"
            v-model="memberId"
            :disabled="true">
          </el-input>
            <span class="changeMain" @click="toRouter('/changeMain','001')">更改社长</span></p>
          <p><span class="title_span">社团简介：</span><el-input
            type="textarea"
            resize="none"
            placeholder="请输入内容"
            v-model="textarea">
          </el-input></p>
          <p class="headImg"><span class="title_span">社团头像：</span>
            <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>

          </p>
          <p><span class="title_span">社团图片：</span>
            <el-upload
              action="https://jsonplaceholder.typicode.com/posts/"
              list-type="picture-card"
              :on-preview="handlePictureCardPreview"
              :on-remove="handleRemove">
              <i class="el-icon-plus"></i>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="dialogImageUrl" alt="">
            </el-dialog>
          </p>
          <div><el-button type="primary" @click="edtiSociety()" v-loading.fullscreen.lock="fullscreenLoading">发送修改</el-button></div>
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
        sortSociety:'',
        textarea:'',
        /*头像*/
        imageUrl:'',
        dialogImageUrl: '',
        dialogVisible: false,
        fullscreenLoading: false,
        societyId:'12321654646',
        memberId:''
      }
    },
    methods: {
      goBack(){
        this.$router.back(-1)
      },
      toRouter(myRouter,societyId){
        this.$router.push({path: myRouter, query: {'societyId': societyId}})
      },
      /*头像*/
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
      },
      /*照片墙*/
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      /*创建请求*/
      edtiSociety() {
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        setTimeout(() => {
          loading.close();
          this.toRouter('/society?myRouter=5')
        }, 2000);

      },
    },

    mounted(){
      this.societyId = this.$route.query.societyId;
      this.memberId = this.$route.query.memberId;
    },
    watch: {

    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .edtiSociety{
    /*box-sizing border-box;*/
    /*padding: 80px 100px;*/
    margin-left: 80px;
    margin-top: 50px;
    overflow: hidden;
    width: 1024px;
    background: #fff;
    min-height: 600px;
    border-radius: 8px;
    margin-bottom :50px;
    padding:10px 40px 20px 40px;
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
      justify-content: center;
      flex-wrap:wrap;
    }
    .edit_input{
      width: 600px;
      p{
        display: flex;
        justify-content: flex-start;
        margin-bottom :20px;
        align-items:center;

      }
    }
    .title_span{
      min-width:80px;
    }

      .mainNum{
        min-width:310px;
        max-width:310px;
      }
      .changeMain{
        cursor: pointer;
        margin-left: 20px;
        color: #409EFF;
      }

  }

</style>
