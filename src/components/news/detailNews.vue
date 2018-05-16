<template>
  <div class="detailNews">
    <div class="bgc">
      <div class="top">
        <div>
          <span>新闻管理</span>
          <span>	&gt;</span>
          <span class="blue">新闻详情</span>
        </div>
        <div>
          <span class="editing" @click="goBack()">返回</span>
        </div>
      </div>
      <div class="info">
        <div class="left_box">
          <!-- <span>编号</span>
          <span>标题</span>
          <span>发送社团</span>
          <span>发送人</span>
          <span>发送时间</span>
          <span>状态</span>
          <span>操作</span>-->
          <p>
            <span class="span_title">发送社团：</span>
            <span>IT协会</span>
          </p>
          <p>
            <span class="span_title">社长账号：</span>
            <span>12354648525</span>
          </p>
          <p>
            <span class="span_title">社长名称：</span>
            <span>啦啦啦</span>
          </p>
          <p>
            <span class="span_title">发送状态：</span>
            <span>已发送</span>
          </p>
        </div>
        <div class="right_box">
          <p>
            <span class="span_title">新闻标题：</span>
            <span>IT协会招新活动</span>
          </p>
          <p>
            <span class="span_title">发送时间：</span>
            <span>2018-05-12</span>
          </p>
          <p>
            <span class="span_title">新闻内容：</span>
            <span>我要的撒苦尽甘来卡萨丁华工科技安徽伟固化的空间那块地方规划局领款人开通为人体后</span>
          </p>
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
        Numbering:''

      }
    },
    methods: {
      goBack(){
        this.$router.back(-1)
      },
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
    },

    mounted(){
      this.Numbering = this.$route.query.Numbering;
    },
    created(){

    },
    watch: {},
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .detailNews {
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
      justify-content: flex-start;
      flex-wrap: wrap;
    }
    .right_box {
      display: inline-block;
      min-width: 408px;
      max-width: 408px;
      p {
        line-height: 50px;
        font-size: 18px;
        min-width: 408px;
        max-width: 408px;
      }

    }
    .left_box {
      display: inline-block;
      min-width: 408px;
      max-width: 408px;
      p {
        line-height: 50px;
        font-size: 18px;
        min-width: 408px;
        max-width: 408px;

      }

    }

    .span_title{
      display: inline-block;
      min-width :90px;
      text-align: right;
    }
  }

</style>
