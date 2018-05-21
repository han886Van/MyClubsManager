<template>
  <div class="addMaterials">
    <div class="bgc">
      <div class="top">
        <div>
          <span>物资管理</span>
          <span>	&gt;</span>
          <span class="blue">申请物资</span>
        </div>
        <div>
          <span class="editing" @click="goBack()">取消</span>
        </div>
      </div>
      <div class="info">
        <div class="edit_input">
          <p><span class="title_span">申请时间：</span>
            <el-date-picker
              v-model="value4"
              type="daterange"
              :picker-options="pickerOptions"
              format="yyyy 年 MM 月 dd 日"
              value-format="yyyy-MM-dd"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </p>
          <p><span class="title_span">申请标题：</span>
            <el-input placeholder="请输入内容" v-model="title"></el-input>
          </p>
          <p><span class="title_span">申请内容：</span>
            <el-input
              type="textarea"
              resize="none"
              placeholder="请输入内容"
              v-model="content">
            </el-input>
          </p>
          <p><span class="title_span">申请理由：</span>
            <el-input
              type="textarea"
              resize="none"
              placeholder="请输入内容"
              v-model="applyComments">
            </el-input>
          </p>
          <p><span class="title_span">社团账号：</span>
            <el-input placeholder="请输入内容" :disabled="true" v-model="associationId"></el-input>
          </p>
          <p><span class="title_span">社长账号：</span>
            <el-input placeholder="请输入内容" :disabled="true" v-model="userId"></el-input>
          </p>
          <div>
            <el-button type="primary" @click="toAdd()" >发送申请</el-button>
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
        dialogVisible: false,
        fullscreenLoading: false,
        value4:'',
        pickerOptions:{
          disabledDate(time){
            return  time.getTime() < Date.now()-(24*60*60*1000)
          }
        },
        associationId:'',
        userRole:'',
        userId:'',
        title:'',
        content:'',
        applyComments:'',
      }
    },
    methods: {
      createFunc(){
        this.userRole = localStorage.getItem('userRole');
        this.userId = localStorage.getItem('userId');
        this.associationId = this.$route.query.associationId;
      },
      goBack(){
        this.$router.back(-1)
      },
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
      /*创建请求*/
      toAdd(){
        if(!this.title){
          this.$message.error('错误，请输入标题');
          console.log(this.title);
        }else if(!this.content){
          this.$message.error('错误，请求输入内容');
        }else if(!this.value4){
          this.$message.error('错误，请选择时间');
        }else if(!this.applyComments){
          this.$message.error('错误，请输入申请理由');
        }else {
          this.addMaterials()
        }
      },
      addMaterials() {
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var url = this.localhost+'associationMg/materiel/saveOrUpdate';
        var userId = this.userId;
        var associationId = this.associationId;
        var json={
          userId:userId,
          associationId:associationId,
          title:this.title,
          content:this.content,
          apply_comments:this.applyComments,
          beginTime:this.value4[0],
          endTime:this.value4[1],
          state:0,
        };
         this.$http.post(url, json).then(
          (success) => {
            var response = success.data;
            setTimeout(() => {
              loading.close();
            }, 500);
            console.log(response);
            if (response.msg == 666) {
              this.$message({
                message: '恭喜你，已成功申请物资！',
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

    },

    mounted(){

    },
    created() {
      this.createFunc()
    },
    watch: {},
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .addMaterials {
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
  }

</style>
