<template>
  <div class="addSociety">
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
          <p><span >社长编号：</span><el-input
            placeholder=""
            v-model="userId"
            :disabled="true">
          </el-input></p>
          <p>
            <span>社团分类：</span>
            <el-select v-model="sortSociety" placeholder="社团分类">
              <el-option label="专业学术类" value="1"></el-option>
              <el-option label="科技创新类" value="2"></el-option>
              <el-option label="艺术兴趣类" value="3"></el-option>
              <el-option label="体育健身类" value="4"></el-option>
              <el-option label="公益服务类" value="5"></el-option>
            </el-select>
          </p>
          <p><span >社团名称：</span> <el-input v-model="societyName" placeholder="请输入内容" clearable></el-input></p>
          <p><span >专用场地：</span> <el-input v-model="societyPlace" placeholder="请输入内容" clearable></el-input></p>
          <p><span class="title_span">社团简介：</span><el-input
            type="textarea"
            resize="none"
            placeholder="请输入内容"
            v-model="briefIntroduction">
          </el-input></p>
          <p><span class="title_span">申请理由：</span><el-input
              type="textarea"
              resize="none"
              placeholder="请输入内容"
              v-model="applyCom" >
            </el-input></p>
          <p><span class="title_span">社团头像：</span><el-upload
            class="avatar-uploader"
            action="http://localhost:8080/associationMg/attachment/uploadFile"
            :show-file-list="false"
            :on-success="handleAvatarSuccess">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload></p>
          <p><span class="title_span">社团图片：</span> <el-upload
            action="http://localhost:8080/associationMg/attachment/uploadFile"
            list-type="picture-card"
            :limit="6"
            :on-exceed="handleExceed"
            :on-preview="handlePictureCardPreview"
            :on-success="handlSuccess"
            :on-remove="handleRemove">
            <i class="el-icon-plus"></i>
          </el-upload></p>
          <div><el-button type="primary" @click="addSociety()" v-loading.fullscreen.lock="fullscreenLoading">创建</el-button></div>
        </div>
      </div>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="">
      </el-dialog>
    </div>
  </div>
</template>

<script>
  export default {
    name: '',
    components: {},
    data () {
      return {
        societyName:'',
        sortSociety:'',
        textarea:'',
        dialogVisible: false,
        fullscreenLoading: false,
        userId:'',
        societyPlace:'',
        applyCom:'',
        briefIntroduction:'',
        dialogImageUrl: '',
        imageUrl:'',
        headImg:'',
        headImgArr:[],
        images:''
      }
    },
    methods: {
      goBack(){
          this.$router.back(-1)
        },
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
      /*创建请求*/
      addSociety(){
        this.images = this.headImgArr.join(",");
        var name = this.societyName;
        var typeId = this.sortSociety;
        var place = this.societyPlace;
        var applyComments = this.applyCom;
        var briefIntroduction = this.briefIntroduction;
        if(typeId==''){
          this.$message.error('请选择社团类型！');
        }else if(name==""){
          this.$message.error('请输入社团名称！');
        }else if(place==""){
          this.$message.error('请输入社团专用场地！');
        }else if(briefIntroduction==""){
          this.$message.error('请输入社团简介！');
        }else if(applyComments==""){
          this.$message.error('请输入申请社团理由！');
        }else if(!this.headImg){
          this.$message.error('请输入上传社团头像图片！');
        }else if(!this.images){
          this.$message.error('请输入上传社团图片！');
        }else {
          this.sendPost()
        }
      },
      sendPost() {
        var name = this.societyName;
        var typeId = this.sortSociety;
        var place = this.societyPlace;
        var applyComments = this.applyCom;
        var briefIntroduction = this.briefIntroduction;
        var url = this.localhost+'associationMg/association/saveOrUpdate';
        var json ={
          userId:this.userId,
          name:name,
          typeId:typeId,
          place:place,
          applyComments:applyComments,
          briefIntroduction:briefIntroduction,
        };
        if(this.headImg){
          json.headImg= this.headImg
        }
        if(this.images){
          json.images= this.images
        }
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        this.$http.post(url,json).then(
          (success) => {
            setTimeout(() => {
              loading.close();
            }, 500);
            var response = success.data;
            if(response.msg==666){
              this.$message({
                type: 'success',
                message: '创建社团请求发送成功！'
              });
              this.toRouter('society?myRouter=4')
            }else {
              this.$message.error('错误，创建社团失败！');
            }
            console.log(response);

          }, (error) => {
            setTimeout(() => {
              loading.close();
              this.toRouter('/society?myRouter=5')
            }, 500);
            this.$message.error('错误，创建社团失败！');
          });

      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
        this.headImg = file.response.headImg;
      },
      handleRemove(file, fileList) {
        this.headImgArr=[];
        for(var i =0;i<fileList.length;i++){
          this.headImgArr.push(fileList[i].response.headImg);
        }
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      handlSuccess(response, file, fileList){
        this.headImgArr=[];
        for(var i =0;i<fileList.length;i++){
          this.headImgArr.push(fileList[i].response.headImg);
        }
      },
      handleExceed(){
        this.$message.error('抱歉，最多只能传6张图片');
      },
    },

    mounted(){
      this.userId = localStorage.getItem('userId');
    },
    watch: {

    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .addSociety {
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

  }

</style>
