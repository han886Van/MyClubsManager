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
          <span class="editing" @click="toRouter('/society')">取消</span>
        </div>
      </div>
      <div class="info">
        <div class="edit_input">
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
          <p><span>社团名称：</span> <el-input  placeholder="请输入内容"></el-input></p>
            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="dialogImageUrl" alt="">
            </el-dialog></p>
          <p><span>&nbsp;&nbsp;&nbsp;申请人：</span> <el-input  placeholder="请输入内容"></el-input></p>
          <p><span>社团简介：</span>
            <el-input
            type="textarea"
            resize="none"
            placeholder="请输入内容"
            v-model="textarea">
          </el-input></p>
          <p><span>申请理由：</span>
            <el-input
              type="textarea"
              resize="none"
              placeholder="请输入内容"
              v-model="textarea">
            </el-input></p>
          <p><span>社团图片：</span> <el-upload
            action="https://jsonplaceholder.typicode.com/posts/"
            list-type="picture-card"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove">
            <i class="el-icon-plus"></i>
          </el-upload>
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
        sortSociety:'',
        textarea:'',
        dialogImageUrl: '',
        dialogVisible: false,
        fullscreenLoading: false
      }
    },
    methods: {
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      /*创建请求*/
      addSociety() {
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        setTimeout(() => {
          loading.close();
          this.toRouter('/society')
        }, 2000);

      },
    },

    mounted(){

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

  }

</style>
