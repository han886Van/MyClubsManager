<template>
  <div class="materials">
    <div class="bgc">
      <div class="top" >
        <span >物资管理</span>
        <span>	&gt;</span>
        <span class="blue">物资申请</span>
      </div>
      <div class="search_box">
        <div>
          <span>社团名字：</span>
          <el-input v-model="nameInput" placeholder="请输入内容" clearable></el-input>
        </div>
        <div>
          <span>社团编号：</span>
          <el-input v-model="idInput" placeholder="请输入内容" clearable></el-input>
        </div>
        <div class="searchBtn">
          <el-button @click="searchItem()" type="primary" >搜索</el-button>
          <el-button @click="clearSearch()">清空搜索</el-button>
         <!-- <el-button @click="toRouter('/addMaterials')" type="primary" v-show="userRole==1">申请物质</el-button>-->
        </div>
      </div>
      <div>
        <div class="title">
          <span>序号</span>
          <span>编号</span>
          <span>内容</span>
          <span>借用时间</span>
          <span>归还时间</span>
          <span>申请社团</span>
          <span>申请人</span>
          <span>操作</span>
        </div>
        <ul class="list">
          <li class="societyList" v-for="(item,index) in assoMaterielList">
            <span  @click="toRouter('/detailMaterials',item.id)">{{index+1}}</span>
            <span  @click="toRouter('/detailMaterials',item.id)">{{item.id}}</span>
            <span  @click="toRouter('/detailMaterials',item.id)">{{item.content}}</span>
            <span  @click="toRouter('/detailMaterials',item.id)">{{item.begin_day}}</span>
            <span  @click="toRouter('/detailMaterials',item.id)">{{item.end_day}}</span>
            <span  @click="toRouter('/detailMaterials',item.id)">{{item.association_name}}</span>
            <span  @click="toRouter('/detailMaterials',item.id)">{{item.user_name}}</span>
            <div>
              <span class="delBtn" @click="deilAppli(item.id,1)">同意</span>
              <span class="refuseBtn" @click="deilAppli(item.id)">拒绝</span>
            </div>
          </li>
          <li v-show="showNo" class="noList">暂无申请</li>
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
    components: {},
    data () {
      return {
        idInput: '',
        nameInput: '',
        currentPage:1,
        userRole:'',
        typeId:'',
        url:'',
        showNo:false,
        assoMaterielList:[],
        listCount:1,
        userId:''
      }
    },
    methods: {
      createFunc(){
        this.userRole = localStorage.getItem('userRole');
        this.userId = localStorage.getItem('userId');
        if (this.userRole == 2) {
          this.url = this.localhost + 'associationMg/materiel/teacherGetMateriel';
          this.typeId  =localStorage.getItem('typeId');
          this.getTList(1, this.url);
        }
      },
      /*分页器*/
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        var associationId =this.idInput;
        var name =this.nameInput;
        this.getTList(val,this.url,associationId,name)
      },
      getTList(val,url,associationId,name){
        this.assoMaterielList=[];
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
              this.listCount =parseInt(response.listCount);
              if(response.assoMaterielList.length==0){
                this.showNo=true
              }else {
                this.showNo=false
              }
              for(var i =0; i<response.assoMaterielList.length;i++){
                this.assoMaterielList.push(response.assoMaterielList[i]);
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
      clearSearch(){
        this.idInput='';
        this.nameInput='';
        this.getTList(1,this.url,this.idInput,this.nameInput)
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
      /*退出社团*/
      toRouter(myRouter, id){
        this.$router.push({path: myRouter, query: {'id': id}})
      },
      deilAppli(id,state){
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
          var url = this.localhost + 'associationMg/materiel/modifyMaterielStatus';
          var json={
            id:id,
            userId:this.userId
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
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .materials {
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
