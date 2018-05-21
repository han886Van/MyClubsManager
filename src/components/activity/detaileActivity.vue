<template>
  <div class="detaileActivity">
    <div class="bgc">
      <div class="top">
        <div>
          <span>活动管理</span>
          <span>	&gt;</span>
          <span class="blue">活动详情</span>
        </div>
        <div>
          <span class="editing" @click="goBack()">返回</span>
        </div>
      </div>
      <div class="info">
        <div class="left_box">
         <!--apply_comments:"123123"
             association_id:1
             association_name:"校篮球队"
             begin_day:"2018-05-15"
             begin_time:1526363414000
             check_person:"季磊"
             check_person_id:7
             content:"计科院各班级比赛，决出名次并可领取相应奖励"
             create_time:1526889643000
             end_day:"2018-05-15"
             end_time:1526363420000
             id:2
             state:"1"
             state_name:"同意申请"
             state_num:"1"
             title:"计科篮球赛活动申请1"
             user_id:4
             user_name:"张三"
-->
          <p>
            <span class="span_title">申请社团：</span>
            <span>{{detailEvent.association_name}}</span>
          </p>
          <p>
            <span class="span_title">社长姓名：</span>
            <span>{{detailEvent.user_name}}</span>
          </p>
          <p>
            <span class="span_title">审核教师：</span>
            <span v-show="detailEvent.check_person!='0'">{{detailEvent.check_person}}</span>
            <span v-show="detailEvent.check_person=='0'">暂无</span>
          </p>
          <p>
            <span class="span_title">申请状态：</span>
            <span class="delBtn"  v-show="detailEvent.state_num==0">待审核</span>
            <span class="delBtn"  v-show="detailEvent.state_num==1">通过审核</span>
            <span class="delBtn"  v-show="detailEvent.state_num==2">未通过审核</span>
          </p>
        </div>
        <div class="right_box">
          <p>
            <span class="span_title">活动名称：</span>
            <span>{{detailEvent.title}}</span>
          </p>
          <p>
            <span class="span_title">申请时间：</span>
            <span>{{detailEvent.begin_day}}</span>
            <span>至</span>
            <span>{{detailEvent.end_day}}</span>
          </p>
          <p>
            <span class="span_title">活动内容：</span>
            <span>{{detailEvent.content}}</span>
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
        id:'',
        detailEvent:''
      }
    },
    methods: {
      createFunc(){
        this.id = this.$route.query.id;
        this.url=this.localhost+'associationMg/event/getEventDetail';
        this.getList()
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
                this.detailEvent = response.detailEvent

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
  .detaileActivity {
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
      padding-left: 140px;
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
