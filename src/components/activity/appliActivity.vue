<template>
  <div class="activity">
    <div class="bgc">
      <div class="top" v-show="userRole==2" >
        <span>活动管理</span>
        <span>	&gt;</span>
        <span  class="blue">活动申请</span>
      </div>
      <div class="search_box">
        <div>
          <span>社团名字：</span>
          <el-input v-model="idInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>社团编号：</span>
          <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
        </div>
        <div class="searchBtn">
          <el-button @click="searchItem()" type="info" plain>搜索</el-button>
          <el-button @click="toRouter('/addActivity')" type="primary"  v-show="userRole==1">申请活动</el-button>
        </div>
      </div>
      <div>
        <div class="title">
          <span>序号</span>
          <span>编号</span>
          <span>名称</span>
          <span>申请社团</span>
          <span>申请人</span>
          <span>开始时间</span>
          <span>结束时间</span>
          <span>操作</span>
        </div>
        <ul class="list">
          <li class="societyList" v-for="(item,index) in assoEventList"  >
            <span @click="toRouter('/detaileActivity',item.actNum)">{{index+1}}</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" >{{item.id}}</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" >{{item.title}}</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" >{{item.association_name}}</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" >{{item.user_name}}</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" >{{item.begin_day}}</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" >{{item.end_day}}</span>
            <div>
              <span class="delBtn" @click="deilAppli(item.id,1)">同意</span>
              <span class="refuseBtn" @click="deilAppli(item.id)">拒绝</span>
            </div>
          </li>
          <li v-show="showNo" class="noList">暂无活动申请</li>
        </ul>
      </div>
      <div  class="myPagination">
        <div>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="10"
            layout="total, prev, pager, next, jumper"
            :total="listCount">
          </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: '',
    components: {

    },
    data () {
      return {
        value1:'',
        activityArr:[
          {actNum:'145662',
            name:'招新活动',
            societyName:'跆拳道协会',
            starTime: '2018.5.12',
            endTime: '2018.06.16',
            teacher: '孟山支',
            applicant: '陈小黄',
            adress:'综合馆',
            isMaterials:1,
            status: 1},
          {actNum:'145662',
            name:'招新活动',
            societyName:'跆拳道协会',
            starTime: '2018.5.12',
            endTime: '2018.06.16',
            teacher: '孟山支',
            applicant: '陈小黄',
            adress:'综合馆',
            isMaterials:2,
            status: 2},
          {actNum:'145662',
            name:'招新活动',
            societyName:'跆拳道协会',
            starTime: '2018.5.12',
            endTime: '2018.06.16',
            teacher: '孟山支',
            applicant: '陈小黄',
            adress:'综合馆',
            isMaterials:1,
            status: 3},
          {actNum:'145662',
            name:'招新活动',
            societyName:'跆拳道协会',
            starTime: '2018.5.12',
            endTime: '2018.06.16',
            teacher: '孟山支',
            applicant: '陈小黄',
            adress:'综合馆',
            isMaterials:1,
            status: 4},

        ],
        actiStatus:'',
        idInput: '',
        nameInput: '',
        sortSociety: '',
        currentPage:1,
        userRole:'',
        url:'',
        typeId:'',
        associationList:'',
        listCount:1,
        showNo:false,
        assoEventList:[],
        userId:''
      }
    },
    methods: {
      createFunc(){
        this.userRole = localStorage.getItem('userRole');
        this.userId = localStorage.getItem('userId');
        this.url = this.localhost + 'associationMg/event/teacherGetEventList';
        this.typeId  =localStorage.getItem('typeId');
        this.getTList(1, this.url);
      },
      getTList(val,url,associationId,name){
        this.assoEventList=[];
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var typeId = this.typeId;
        var json ={
          typeId:typeId,
          start:val
        };
        if(associationId){
          json.associationId =associationId
        }
        if(name){
          json.name =name
        }
        this.$http.post(url,json).then(
          (success) => {
          var response = success.data;
          console.log(response);
          if(response.msg==666){
            this.listCount =parseInt(response.listCount);
            if(response.assoEventList.length==0){
              this.showNo=true
            }else {
              this.showNo=false
            }
            for(var i =0; i<response.assoEventList.length;i++){
              this.assoEventList.push(response.assoEventList[i]);
            }
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
      searchItem(){
          if(this.idInput==''&&this.nameInput==''){
          this.$message({
            type: 'error',
            message: '请输入或选择搜索条件!'
          });
          this. getTList(1,this.url);
        }else {
          var associationId =this.idInput;
          var name =this.nameInput;
          this.getTList(1,this.url,associationId,name)
        }
      },
      toRouter(myRouter,actNum){
        this.$router.push({path: myRouter, query: {'actNum': actNum}})
      },
      /*分页器*/
      handleSizeChange(val) {
        var associationId =this.idInput;
        var name =this.nameInput;
        this.getTList(val,this.url,associationId,name)
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        console.log(`当前页: ${val}`);
      },
      deilAppli(id,state){
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var url = this.localhost + 'associationMg/event/modifyEventStatus';
        var json={
          id:id,
          checkPersonId:this.userId
        }
        if(state){
          json.state=state
        }
        this.$http.post(url,json).then(
          (success) => {
          var response = success.data;
          console.log(response);
          if(response.msg==666){
            this.$message({
              showClose: true,
              message: '请求成功！',
              type: 'success'
            });
            this.createFunc();
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
      }
    },
    mounted(){
      this.userRole = localStorage.getItem('userRole');
    },
    created() {
      this.createFunc()
    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .activity {
    margin-left: 80px;
    margin-top: 50px;
    overflow: hidden;
    width: 1024px;
    background: #fff;
    min-height: 600px;
    border-radius: 8px;
    padding: 10px 40px 20px 40px;
    .top{
      line-height:50px;
      font-size:16px;
      border-bottom :1px solid #ccc;
      margin-bottom:10px;
    }
    .bgc {
      border-radius: 8px;
      background-color: #fff;
    }
    .search_box {
      margin-bottom: 20px;
      div {
        margin-top: 10px;
        display: inline-block;
        margin-right: 10px;
        min-width: 280px;
      }
      .searchBtn {
        min-width: 200px;
        text-align: right;
      }
    }
    .title {
      color: #000000;
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 20px;
      span {
        text-align: center;
        width: 146px;
      }
    }
    .delBtn {
      color: #409eff;
    }
    .refuseBtn {
      color: #f56c6c;
    }
    .agreetBtn {
      color: #67c23a;
    }
  }
</style>
