<template>
  <div class="activity">
    <div class="bgc">
      <div class="top" v-show="userRole==1" >
        <span class="blue">活动管理</span>
        <span class="blue" @click="goBack()">返回</span>
      </div>
      <div class="top" v-show="userRole==2" >
        <div><span>活动管理</span>
        <span>	&gt;</span>
        <span  class="blue">申请记录</span></div>
        <span class="blue" @click="goBack()">返回</span>
      </div>
      <div class="search_box">
       <!-- <div>
          <span>社团分类：</span>
          <el-select v-model="sortSociety" placeholder="社团分类">
            <el-option label="专业学术类" value="1"></el-option>
            <el-option label="科技创新类" value="2"></el-option>
            <el-option label="艺术兴趣类" value="2"></el-option>
            <el-option label="体育健身类" value="4"></el-option>
            <el-option label="公益服务类" value="5"></el-option>
          </el-select>
        </div>
        <div>
          <span>社团编号：</span>
          <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>社团名字：</span>
          <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
        </div>-->
        <div>
          <span>申请状态：</span>
          <el-select v-model="actiStatus" placeholder="申请状态">
            <el-option label="待审核" value="0"></el-option>
            <el-option label="通过审核" value="1"></el-option>
            <el-option label="未通过审核" value="2"></el-option>
            <el-option label="全部状态" value="4"></el-option>
          </el-select>
        </div>
        <div>
          <span>活动编号：</span>
          <el-input v-model="idInput" placeholder="请输入内容" clearable></el-input>
        </div>
        <div>
          <span>活动名称：</span>
          <el-input v-model="titleName" placeholder="请输入内容" clearable></el-input>
        </div>
        <div class="searchBtn">
          <el-button @click="searchItem()" type="info" plain>搜索</el-button>
          <el-button @click="toRouter('/addActivity',0,associationId)" type="primary"  v-show="userRole==1&&isPresident==1">申请活动</el-button>
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
          <span>审核老师</span>
          <span>状态</span>
        </div>
        <ul class="list">
          <li class="societyList" v-for="(item,index) in assoEventList"  @click="toRouter('/detaileActivity',item.id)">
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
                role_name:"社长"
                role_name_num:"1"
                state:"1"
                state_name:"同意申请"
                state_num:"1"
                title:"计科篮球赛活动申请1"
                user_id:4
                user_name:"张三
                -->
            <span>{{index+1}}</span>
            <span >{{item.id}}</span>
            <span >{{item.title}}</span>
            <span >{{item.association_name}}</span>
            <span >{{item.user_name}}</span>
            <span >{{item.begin_day}}</span>
            <span >{{item.end_day}}</span>
            <span v-show="item.check_person!='0'">{{item.check_person}}</span>
            <span v-show="item.check_person=='0'">暂无</span>
            <div>
              <span class="delBtn"  v-show="item.state_num==0">待审核</span>
              <span class="delBtn"  v-show="item.state_num==1">通过审核</span>
              <span class="delBtn"  v-show="item.state_num==2">未通过审核</span>
            </div>
          </li>
          <li v-show="showNo" class="noList">暂无活动</li>
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
            :total="totalNum">
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
        actiStatus:'',
        idInput: '',
        nameInput: '',
        sortSociety: '',
        currentPage:1,
        userRole:'',
        associationId:'',
        totalNum:1,
        url:'',
        assoEventList:[],
        showNo:false,
        isPresident:'',
        titleName:'',
      }
    },
    methods: {
      createFunc(){
        this.userRole = localStorage.getItem('userRole');
        this.userId =localStorage.getItem('userId');
        this.associationId = this.$route.query.associationId;
        if(this.userRole==1){
          this.url=this.localhost+'associationMg/event/getAssoEventList';
          this.getList(1)
        }else if(this.userRole==2){

        }
      },
      goBack(){
        this.$router.back(-1)
      },
      searchItem(){
        var state = this.actiStatus;
        var id = this.idInput;
        var title = this.titleName;
        if(!state && !id&& !title){
          this.$message.error('错误，请输入或者选择搜索内容');
        }else {
          if(state==4 && !id&& !title){
            this.getList(1)
          }else if(state==4) {
            this.getList(1,id,'',title)
          }else {
            this.getList(1,id,state,title)
          }
        }

      },
      getList(val,id,state,title){
        this.assoEventList=[];
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json ={
          associationId:this.associationId,
          start:val,
          userId:this.userId,
        };
        if(id){
          json.id =id
        }
        if(state){
          json.state =state
        }
        if(title){
          json.title =title
        }
        this.$http.post(this.url,json).then(
          (success) => {
          var response = success.data;
          console.log(response);
          if(response.msg==666){
            this.isPresident = response.isPresident
            this.totalNum =parseInt(response.listCount);
           if(response.assoEventList.length==0){
              this.showNo=true
            }else {
              this.showNo=false;
             for(var i =0; i<response.assoEventList.length;i++){
               if(this.userRole==1){
                 this.assoEventList.push(response.assoEventList[i]);
               }else{
                 this.assoEventList.push(response.assoEventList[i]);
               }
             }
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
      toRouter(myRouter,id,associationId){
        this.$router.push({path: myRouter, query: {'id': id,'associationId':associationId}})
      },
      /*分页器*/
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        var state = this.actiStatus;
        var id = this.idInput;
        var title = this.titleName;
        this.getList(val,id,state,title)
      },
    },
    created() {
      this.createFunc()
    },
    mounted(){
      this.userRole = localStorage.getItem('userRole');
    }
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
      display: flex;
      justify-content:space-between;
      border-bottom :1px solid #ccc;
      margin-bottom:20px;
      .editing{
        color: #409eff;
        cursor :pointer;
      }
    }
    .bgc {
      border-radius: 8px;
      background-color: #fff;
    }
    .search_box {
      margin-bottom: 20px;
      div {
        display: inline-block;
        margin-right: 10px;
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
