<template>
  <div class="detailMaterials">
    <div class="bgc">
      <div class="top">
        <div>
          <span>物资管理</span>
          <span>	&gt;</span>
          <span class="blue">物资详情</span>
        </div>
        <div>
          <span class="editing" @click="goBack()">返回</span>
        </div>
      </div>
      <div class="info">
        <div class="left_box">
          <p>
            <span>申请社团：</span>
            <span>IT协会</span>
          </p>
          <p>
            <span>社长账号：</span>
            <span>12354648525</span>
          </p>
          <p>
            <span>申请教师：</span>
            <span>孟山支</span>
          </p>
          <p>
            <span>申请状态：</span>
            <span>使用中</span>
          </p>
        </div>
        <div class="right_box">
          <p>
            <span>申请时间：</span>
            <span>2018-05-12</span>
            <span>至</span>
            <span>2018-05-22</span>
          </p>
          <p>
            <span>申请标题：</span>
            <span>IT协会招新活动物资</span>
          </p>
          <p>
            <span>申请内容：</span>
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
  .detailMaterials {
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

  }

</style>
