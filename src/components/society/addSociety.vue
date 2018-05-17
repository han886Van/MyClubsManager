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
          <p><span >&nbsp;&nbsp;&nbsp;&nbsp;申请人：</span> <span>{{userId}}</span></p>
          <p>
            <span>社团分类：</span>
            <el-select v-model="sortSociety" placeholder="社团分类">
              <el-option label="专业学术类" value="1"></el-option>
              <el-option label="科技创新类" value="2"></el-option>
              <el-option label="艺术兴趣类" value="2"></el-option>
              <el-option label="体育健身类" value="4"></el-option>
              <el-option label="公益服务类" value="5"></el-option>
            </el-select>
          </p>
          <p><span >社团名称：</span> <el-input v-model="societyName" placeholder="请输入内容"></el-input></p>
          <p><span >专用场地：</span> <el-input v-model="societyPlace" placeholder="请输入内容"></el-input></p>
          <p><span class="title_span">社团简介：</span><el-input
            type="textarea"
            resize="none"
            placeholder="请输入内容"
            v-model="textarea">
          </el-input></p>
          <p><span class="title_span">申请理由：</span><el-input
              type="textarea"
              resize="none"
              placeholder="请输入内容"
              v-model="applyCom">
            </el-input></p>
          <div><el-button type="primary" @click="addSociety()" v-loading.fullscreen.lock="fullscreenLoading">创建</el-button></div>
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
        societyName:'',
        sortSociety:'',
        textarea:'',
        dialogVisible: false,
        fullscreenLoading: false,
        userId:'',
        societyPlace:'',
        applyCom:''
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
        var name = this.societyName;
        var typeId = this.sortSociety;
        var place = this.societyPlace;
        var applyComments = this.applyCom;
        if(typeId==''){
          this.$message.error('请选择社团类型！');
        }else if(name==""){
          this.$message.error('请输入社团名称！');
        }else if(place==""){
          this.$message.error('请输入社团专用场地！');
        }else if(applyComments==""){
          this.$message.error('请输入申请社团理由！');
        }else {
            this.sendPost()
        }
      },
      sendPost() {
        var name = this.societyName;
        var typeId = this.sortSociety;
        var place = this.societyPlace;
        var applyComments = this.applyCom;
        var url = this.localhost+'associationMg/association/saveOrUpdate';
        var json ={
          userId:this.userId,
          name:name,
          typeId:typeId,
          place:place,
          applyComments:applyComments,
        };
        console.log(json);
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
              this.toRouter('/society?myRouter=5')
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
