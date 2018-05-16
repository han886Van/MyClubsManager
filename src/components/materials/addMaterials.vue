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
          <p><span class="title_span">申请标题：</span>
            <el-input placeholder="请输入内容"></el-input>
          </p>
          <p><span class="title_span">申请内容：</span>
            <el-input
              type="textarea"
              resize="none"
              placeholder="请输入内容"
              v-model="textarea">
            </el-input>
          </p>
          <p><span class="title_span">社团账号：</span>
            <el-input placeholder="请输入内容"></el-input>
          </p>
          <p><span class="title_span">社长账号：</span>
            <el-input placeholder="请输入内容"></el-input>
          </p>
          <p><span class="title_span">申请时间：</span>
            <el-date-picker
              v-model="value4"
              :disabledDate="disabledDate"
              type="datetimerange"
              :picker-options="pickerOptions"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </p>
          <div>
            <el-button type="primary" @click="addMaterials()" v-loading.fullscreen.lock="fullscreenLoading">发送申请</el-button>
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
      addMaterials() {
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        setTimeout(() => {
          loading.close();
          this.toRouter('/materials')
        }, 2000);

      },
      disabledDate(date){
        console.log(date);
      }
    },

    mounted(){

    },
    created(){

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
