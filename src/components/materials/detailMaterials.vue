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
            <span>{{detailMateriel.association_name}}</span>
          </p>
          <p>
            <span>社长账号：</span>
            <span>{{detailMateriel.association_id}}</span>
          </p>
          <p>
            <span>审核教师：</span>
            <span v-show="detailMateriel.check_person!='0'">{{detailMateriel.check_person}}</span>
            <span v-show="detailMateriel.check_person=='0'">暂无</span>
          </p>
          <p>
            <span>申请状态：</span>
            <span class="delBtn"  v-show="detailMateriel.state_num==0">待审核</span>
            <span class="delBtn"  v-show="detailMateriel.state_num==1">通过审核</span>
            <span class="delBtn"  v-show="detailMateriel.state_num==2">未通过审核</span>
          </p>
        </div>
        <div class="right_box">
          <p>
            <span>申请时间：</span>
            <span>{{detailMateriel.begin_day}}</span>
            <span>至</span>
            <span>{{detailMateriel.end_day}}</span>
          </p>
          <p>
            <span>申请标题：</span>
            <span>{{detailMateriel.title}}</span>
          </p>
          <p>
            <span>申请内容：</span>
            <span>{{detailMateriel.apply_comments}}</span>
          </p>
          <p>
            <span>申请理由：</span>
            <span>{{detailMateriel.content}}</span>
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
        Numbering:'',
        url:'',
        detailMateriel:''
      }
    },
    methods: {
      createFunc(){
        this.materiId = this.$route.query.id;
        this.url=this.localhost+'associationMg/materiel/getMateriDetail';
        this.getList();
      },
      getList(){
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json ={
          id:this.materiId,
        };
        this.$http.post(this.url,json).then(
          (success) => {
            var response = success.data;
            console.log(response);
            if(response.msg==666){
                this.detailMateriel = response.detailMateriel

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
      goBack(){
        this.$router.back(-1)
      },
      toRouter(myRouter){
        this.$router.push({path: myRouter})
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

  }

</style>
