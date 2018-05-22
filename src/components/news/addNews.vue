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
          <span class="editing" @click="goBack()">取消</span>
        </div>
      </div>
      <div class="info">
        <div class="edit_input">
          <p>
            <span class="title_span">新闻分类：</span>
            <el-select v-model="newSociety" placeholder="社团分类">
              <el-option label="专业学术类" value="1"></el-option>
              <el-option label="科技创新类" value="2"></el-option>
              <el-option label="艺术兴趣类" value="3"></el-option>
              <el-option label="体育健身类" value="4"></el-option>
              <el-option label="公益服务类" value="5"></el-option>
            </el-select>
          </p>
          <p><span class="title_span">新闻标题：</span>
            <el-input placeholder="请输入内容" v-model="title" clearable></el-input>
          </p>
          <p><span class="title_span">新闻内容：</span>
            <el-input
              type="textarea"
              resize="none"
              placeholder="请输入内容"
              v-model="content">
            </el-input>
          </p>
          <p ><span class="title_span">社团账号：</span>
            <el-input placeholder="请输入内容" :disabled="true" v-model="associationId"></el-input>
          </p>
          <p ><span class="title_span">申请人账号：</span>
            <el-input placeholder="请输入内容" :disabled="true" v-model="userId"></el-input>
          </p>
          <p v-show="userRole==2"><span class="title_span">教师账号：</span>
            <el-input placeholder="请输入内容"></el-input>
          </p>

          <div class="btn">
            <el-button type="primary" @click="toAdd(1)" >发送新闻</el-button>
            <el-button @click="toAdd(0)" >存为草稿</el-button>
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
        userRole:'',
        userId:'',
        title:'',
        content:'',

      }
    },
    methods: {
      createFunc(){
        this.associationId = this.$route.query.associationId;
        this.userId = localStorage.getItem('userId');
      },
      goBack(){
        this.$router.back(-1)
      },
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
      /*创建请求*/
      toAdd(state){
          if(!this.newSociety){
            this.$message.error('错误，请输选择新闻类型');
          }else if(!this.title){
            this.$message.error('错误，请输入新闻标题');
          }else if(!this.content){
            this.$message.error('错误，请输入新闻内容');
          }else {
              this.addNews(this.title,this.content,this.newSociety,state)
          }
      },
      addNews(title,content,typeId,state)  {
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var word = '';
        if(state){
          word = '新闻已发布成功！'
        }else {
          word = '新闻已存为草稿！'
        }
        var url = this.localhost+'associationMg/news/saveOrUpdate';
        var userId = this.userId;
        var associationId = this.associationId;
        var json={
          userId:userId,
          associationId:associationId,
          title:title,
          content:content,
          typeId:typeId,
          state:state
        };
        this.$http.post(url,json).then(
          (success) => {
            var response = success.data;
            setTimeout(() => {
              loading.close();
            }, 500);
            console.log(response);
            if (response.msg == 666) {
              this.$message({
                message: word,
                type: 'success'
              });
              this.goBack();
            } else {
              this.$message.error('错误，请求数据失败');
            }
          }, (error) => {
            setTimeout(() => {
              loading.close();
            }, 500);
            this.$message.error('错误，请求数据失败');
          });
      },
      disabledDate(date){
        console.log(date);
      }
    },

    mounted(){
      this.userRole = localStorage.getItem('userRole');
    },
    created(){
      this.createFunc()
    },
    watch: {

    },
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
      min-width: 98px;
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
