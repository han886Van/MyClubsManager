<template>
  <div class="edtiSociety">
    <div class="bgc">
      <div class="top">
        <div>
          <span>社团管理</span>
          <span>	&gt;</span>
          <span class="blue">编辑社团</span>
        </div>
        <div>
          <span class="editing" @click="goBack()">取消</span>
        </div>
      </div>
      <div class="info">
        <div class="edit_input">
          <p>
            <span class="title_span">社团分类：</span>
            <el-select v-model="detailAssociation.type_id" placeholder="社团分类">
              <el-option label="专业学术类" value='1'></el-option>
              <el-option label="科技创新类" value='2'></el-option>
              <el-option label="艺术兴趣类" value='3'></el-option>
              <el-option label="体育健身类" value='4'></el-option>
              <el-option label="公益服务类" value='5'></el-option>
            </el-select>
          </p>
          <p>
            <span class="title_span">社团名称：</span>
            <el-input v-model="detailAssociation.name" placeholder="请输入内容" clearable></el-input></p>
          <p><span class="title_span">社长名字：</span><el-input
            placeholder="请输入内容"
            v-model="userName"
            :disabled="true">
          </el-input>
            <span class="changeMain" @click="toRouter('/changeMain',associationId)">更改社长</span></p>
          <p><span >专用场地：</span> <el-input v-model="detailAssociation.place" placeholder="请输入内容" clearable></el-input></p>
          <p>
            <span class="title_span">社团简介：</span>
            <el-input
            type="textarea"
            resize="none"
            placeholder="请输入内容"
            v-model="detailAssociation.brief_introduction">
          </el-input>
          </p>
          <p class="headImg"><span class="title_span">社团头像：</span>
            <el-upload
              class="avatar-uploader"
              action="http://localhost:8080/associationMg/attachment/uploadFile"
              :show-file-list="false"
              :on-success="handleAvatarSuccess">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </p>
          <p><span class="title_span">社团图片：</span>
            <el-upload
              action="http://localhost:8080/associationMg/attachment/uploadFile"
              list-type="picture-card"
              :limit="6"
              :on-exceed="handleExceed"
              :on-preview="handlePictureCardPreview"
              :on-success="handlSuccess"
              :on-remove="handleRemove">
              <i class="el-icon-plus"></i>
            </el-upload>
          </p>
          <div><el-button type="primary" @click="toEdti()" v-loading.fullscreen.lock="fullscreenLoading">发送修改</el-button></div>
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
        sortSociety:'',
        textarea:'',
        /*头像*/
        imageUrl:'',
        dialogImageUrl: '',
        dialogVisible: false,
        fullscreenLoading: false,
        societyId:'12321654646',
        associationId:'',
        memberId:'',
        url:'',
        userId:'',
        societyPlace:'',
        detailAssociation:{},
        changeUserId:'',
        userName:'',
        headImg:'',
        headImgArr:[],
        images:''
      }
    },
    methods: {
      createFunc(){
        this.userId = localStorage.getItem('userId');
        this.memberId =this.userId;
        this.associationId = this.$route.query.associationId;
        this.url = this.localhost + 'associationMg/association/getAssociationDetail';
        console.log(this.associationId);
        this.getDetails(this.url, this.associationId);
      },
      goBack(){
        this.$router.back(-1)
      },
      toRouter(myRouter,associationId){
        this.$router.replace({path: myRouter, query: {'associationId': associationId}})
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
      /* 编辑社团*/
      toEdti(){
        this.images = this.headImgArr.join(",");
        var name = this.detailAssociation.name;
        var typeId = this.detailAssociation.type_id;
        var place = this.detailAssociation.place;
        var briefIntroduction = this.detailAssociation.brief_introduction;
        if(!typeId){
          this.$message.error('请选择社团类型！');
        }else if(!name){
          this.$message.error('请输入社团名称！');
        }else if(!place){
          this.$message.error('请输入社团专用场地！');
        }else if(!briefIntroduction){
          this.$message.error('请输入社团简介！');
        }else {
          this.edtiSociety()
        }
      },
      edtiSociety() {
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var url = this.localhost+'associationMg/association/saveOrUpdate';
        var userId ='';
        if(this.$route.query.userName){
          userId =this.changeUserId
        }else {
          userId = this.userId
        }
        var json ={
            associationId:this.associationId,
            userId:userId,
            name: this.detailAssociation.name,
            typeId:this.detailAssociation.type_id,
            briefIntroduction:this.detailAssociation.check_comments,
            place:this.detailAssociation.place,
        };
        if(this.headImg){
          json.headImg= this.headImg
        }
        if(this.images){
          json.images= this.images
        }
        this.$http.post(url,json).then(
          (success) => {
            var response = success.data;
            console.log(response);
            if(response.msg==666){
                this.goBack()
            }else {
              this.$message.error('错误，请求数据失败');
              this.goBack()
            }
            setTimeout(() => {
              loading.close();
            }, 500);
          }, (error) => {
            setTimeout(() => {
              loading.close();
            }, 500);
            this.$message.error('错误，请求数据失败');
          });

      },
      getDetails(url, associationId){
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json = {
          associationId: associationId,
        };
        this.$http.post(url, json).then(
          (success) => {
          var response = success.data;
          if (response.msg == 666) {
            this.detailAssociation=response.detailAssociation;
            this.detailAssociation.type_id=this.detailAssociation.type_id+'';
            this.userName=response.detailAssociation.user_name;
            if(this.$route.query.userName){
              this.userName = this.$route.query.userName;
              this.changeUserId = this.$route.query.changeUserId;
            }
            console.log(this.detailAssociation);
            if( this.detailAssociation.headImg!=0){
              this.imageUrl = this.detailAssociation.headimg;
            }
            /*if(this.detailAssociation.imgs!='0'){
              console.log('11111');
              console.log(this.detailAssociation.imgs);
              console.log(this.detailAssociation.imgs.split(","));
              this.headImgArr= [];
              var imgs = this.detailAssociation.imgs.split(",")
               for(var i=0; i<imgs.length;i++){
                var boj={
                    url:imgs[i]
                };
                this.headImgArr.push(boj)
              }
              console.log(this.headImgArr,'headImgArr');

            }*/
          } else {
            this.$message.error('错误，社团详情请求数据失败');
//              this.goBack()
          }
          setTimeout(() => {
            loading.close();
          }, 500);
        }, (error) => {
          setTimeout(() => {
            loading.close();
          }, 500);
          this.$message.error('错误，社团详情请求数据失败');
//            this.goBack()
        });

      },
      handleExceed(){
        this.$message.error('抱歉，最多只能传6张图片');
      },
    },

    mounted(){

    },
    watch: {

    },
    created() {
      this.createFunc()
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
