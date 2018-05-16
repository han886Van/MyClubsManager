<template>
  <div class="addNews">
    <div class="bgc">
      <div class="top">
        <div>
          <span>新闻管理</span>
          <span>	&gt;</span>
          <span class="blue">添加新闻</span>
        </div>
        <div>
          <span class="editing" @click="toRouter('/news')">取消</span>
        </div>
      </div>
      <div class="info">
        <div class="edit_input">
          <p>
            <span>新闻分类：</span>
            <el-select v-model="newSociety" placeholder="社团分类">
              <el-option label="专业学术类" value="1"></el-option>
              <el-option label="科技创新类" value="2"></el-option>
              <el-option label="艺术兴趣类" value="2"></el-option>
              <el-option label="体育健身类" value="4"></el-option>
              <el-option label="公益服务类" value="5"></el-option>
            </el-select>
          </p>
          <p><span class="title_span">新闻标题：</span>
            <el-input placeholder="请输入内容"></el-input>
          </p>
          <p><span class="title_span">新闻内容：</span>
            <el-input
              type="textarea"
              resize="none"
              placeholder="请输入内容"
              v-model="textarea">
            </el-input>
          </p>
          <p v-show="userRole==1"><span class="title_span">社团账号：</span>
            <el-input placeholder="请输入内容"></el-input>
          </p>
          <p v-show="userRole==1"><span class="title_span">社长账号：</span>
            <el-input placeholder="请输入内容"></el-input>
          </p>
          <p v-show="userRole==2"><span class="title_span">教师账号：</span>
            <el-input placeholder="请输入内容"></el-input>
          </p>

          <div class="btn">
            <el-button type="primary" @click="addNews()" v-loading.fullscreen.lock="fullscreenLoading">发送新闻</el-button>
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
        textarea: '',
        newSociety:'',
        dialogVisible: false,
        fullscreenLoading: false,
        value4:'',
        pickerOptions:{
          disabledDate(time){
            return  time.getTime() < Date.now()-(24*60*60*1000)
          }
        },
        userRole:''

      }
    },
    methods: {
      goBack(){
        this.$router.back(-1)
      },
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
      addNews() {
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        setTimeout(() => {
          loading.close();
          this.goBack()
        }, 2000);

      },
      disabledDate(date){
        console.log(date);
      }
    },

    mounted(){
      this.userRole = localStorage.getItem('userRole');
    },
    created(){

    },
    watch: {},
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .addNews {
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
    .edit_input {
      width: 600px;
      p {
        display: flex;
        justify-content: flex-start;
        margin-bottom: 20px;
        align-items: center;

      }
    }
    .title_span {
      min-width: 80px;
    }
    .tipSpan {
      min-width: 30px;
      text-align: center;
    }
      .btn{
        margin-top:60px;
      }
  }

</style>
