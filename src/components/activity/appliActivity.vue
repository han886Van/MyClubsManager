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
          <span>活动编号：</span>
          <el-input v-model="idInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>社团名字：</span>
          <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>活动名称：</span>
          <el-input v-model="idInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>社团编号：</span>
          <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>活动时间：</span>
          <el-date-picker
            v-model="value1"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
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
          <span>地点</span>
          <span>借用物资</span>
          <span>申请教师</span>
          <span>操作</span>
        </div>
        <ul class="list">
          <li class="societyList" v-for="(item,index) in activityArr"  >
            <span @click="toRouter('/detaileActivity',item.actNum)">{{index+1}}</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" >{{item.actNum}}</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" >{{item.name}}</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" >{{item.societyName}}</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" >{{item.applicant}}</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" >{{item.starTime}}</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" >{{item.starTime}}</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" >{{item.adress}}</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" v-show="item.isMaterials==1" >有</span>
            <span @click="toRouter('/detaileActivity',item.actNum)" v-show="item.isMaterials==2" >无</span>
            <span @click="toRouter('/detaileActivity',item.actNum)">{{item.teacher}}</span>
            <div>
              <span class="delBtn">同意</span>
              <span class="refuseBtn" >拒绝</span>
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
            :total="400">
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
        showNo:false
      }
    },
    methods: {
      createFunc(){
        this.userRole = localStorage.getItem('userRole');
        this.url = this.localhost + 'associationMg/event/teacherGetEventList';
        this.typeId  =localStorage.getItem('typeId');
        this.getTList(1, this.url);
      },
      getTList(val,url,associationId,name){
        this.associationList=[];
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var typeId = this.typeId;
        var json ={
          typeId:typeId,
          teacherCheck:1,
          start:val
        };
        if(associationId){
          json.associationId=associationId
        }
        if(name){
          json.name=name
        }
        this.$http.post(url,json).then(
          (success) => {
          var response = success.data;
          console.log(response);
          if(response.msg==666){
            this.totalNum =parseInt(response.total_num);
            if(response.associationList.length==0){
              this.showNo=true
            }else {
              this.showNo=false
            }
            for(var i =0; i<response.associationList.length;i++){
              this.associationList.push(response.associationList[i]);
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

      },
      toRouter(myRouter,actNum){
        this.$router.push({path: myRouter, query: {'actNum': actNum}})
      },
      /*分页器*/
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },
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
