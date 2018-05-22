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
      <!--association_id:1
          association_name:"校篮球队"
          content:"计科女篮冠军!!!!"
          create_time:1526532007000
          id:2
          publish_time:"2018-05-17"
          state:"1"
          state_name:"已发布"
          state_num:"1"
          title:"计科院新闻"
          type_id:4
          type_name:"体育健身类"
          user_id:2
          user_name:"刘超群"-->
     <div class="info_deatil">
       <h1 class="title_line">
        {{detailNews.title}}
      </h1>
      <p class="time">
        <span>  {{detailNews.publish_time}}</span>
        <span>  {{detailNews.association_name}}</span>
        <span>  {{detailNews.user_name}}</span>
      </p>
       <p class="conter">
         &nbsp;&nbsp;&nbsp;&nbsp;
         {{detailNews.content}}
       </p>
       <p>
         <!--<img src="../../assets/img/home1.jpg" alt="">-->
       </p>
     </div>
     <!-- <div class="info">

        <div class="left_box">
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
      </div>-->
    </div>
  </div>
</template>

<script>
  export default {
    name: '',
    components: {},
    data () {
      return {
        id:'',
        url:'',
        detailNews:''

      }
    },
    methods: {
      createFunc(){
        this.id = this.$route.query.id;
        this.url=this.localhost+'associationMg/news/getNewsDetail';
        this.getList();
      },
      goBack(){
        this.$router.back(-1)
      },
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
      getList(){
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json ={
          id:this.id,
        };
        this.$http.post(this.url,json).then(
          (success) => {
            var response = success.data;
            console.log(response);
            if(response.msg==666){
              this.detailNews = response.detailNews

            }else {
              this.$message.error('错误，请求数据失败');
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
    },

    mounted(){
    },
    created(){
        this.createFunc()
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
    .info_deatil{
        font-size: 18px;
        line-height:26px;
        text-align: left;
        img{
          width: 1000px
          height:400px;
        }
      }
    .title_line{
        text-align: center;
        font-size:22px;
        margin-bottom:10px;
      }
    .time{
          text-align: center;
          font-size:14px;

        }
      .conter{
        text-align: center;
        margin-top:40px;
      }
  }

</style>
