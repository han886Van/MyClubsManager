<template>
  <div class="materials">
    <div class="bgc">
      <div class="top" v-show="userRole==1" >
        <div>
          <span>物资管理</span>
        <span>	&gt;</span>
        <span class="blue">社团物资</span>
        </div>
        <span class="blue" @click="goBack()">返回</span>
      </div>
      <div class="top" v-show="userRole==2" >
        <span>物资管理</span>
        <span>	&gt;</span>
        <span  class="blue">申请记录</span>
      </div>
      <div class="search_box">
        <!--  <div>
          <span>社团分类：</span>
          <el-select v-model="sortSociety" placeholder="社团分类">
            <el-option label="专业学术类" value="1"></el-option>
            <el-option label="科技创新类" value="2"></el-option>
            <el-option label="艺术兴趣类" value="2"></el-option>
            <el-option label="体育健身类" value="4"></el-option>
            <el-option label="公益服务类" value="5"></el-option>
          </el-select>
        </div>-->
        <!--  <div>
         <span>社团名字：</span>
         <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
       </div>-->
        <!--<div>
          <span>社团编号：</span>
          <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
        </div>-->
        <div>
          <span>申请状态：</span>
          <el-select v-model="materialsStatus" placeholder="申请状态">
            <el-option label="使用中" value="1"></el-option>
            <el-option label="已归还" value="2"></el-option>
            <el-option label="拒绝申请" value="2"></el-option>
            <el-option label="同意申请" value="4"></el-option>
          </el-select>
        </div>
        <div>
          <span>物资编号：</span>
          <el-input v-model="idInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>物资名称：</span>
          <el-input v-model="idInput" placeholder="请输入内容"></el-input>
        </div>
        <div class="searchBtn">
          <el-button @click="searchItem()" type="info" plain>搜索</el-button>
          <el-button @click="toRouter('/addMaterials',0,associationId)" type="primary" v-show="userRole==1">申请物资</el-button>
        </div>
      </div>
      <div>
        <div class="title">
          <span>序号</span>
          <span>编号</span>
          <span>内容</span>
          <span>借用时间</span>
          <span>归还时间</span>
          <span>社团名称</span>
          <span>申请人</span>
          <span>审核老师</span>
          <span>状态</span>
        </div>
        <ul class="list">
          <!--association_id:1
              association_name:"校篮球队"
              content:"测试测试测试"
              create_time:1526821274000
              id:1
              state:"0"
              state_name:"待审核"
              state_num:"0"
              title:"测试"
              user_id:4
              user_name:"张三"
-->
          <li  v-show="!showNo" class="societyList" v-for="(item,index) in assoMaterielList" @click="toRouter('/detailMaterials',0,0,item.id)">
            <span >{{index+1}}</span>
            <span>{{item.id}}</span>
            <span>{{item.content}}</span>
            <span>{{item.create_time}}</span>
            <span>{{item.create_time}}</span>
            <span>{{item.association_name}}</span>
            <span>{{item.user_name}}</span>
            <span>{{item.applicant}}</span>
            <div>
              <span class="delBtn"  v-show="item.state_num==0">待审核</span>
              <!--<span class="editBtn" v-show="item.status==2">已归还</span>
              <span class="refuseBtn"  v-show="item.status==3">拒绝申请</span>
              <span class="agreetBtn"  v-show="item.status==4">同意申请</span>-->
            </div>
          </li>
          <li v-show="showNo" class="noList">暂无物资</li>
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
    components: {},
    data () {
      return {
        idInput: '',
        nameInput: '',
        sortSociety: '',
        materialsStatus:'',
        materialsArr: [
          {
            Numbering: 1254688,
            name: '帐篷',
            num: '5',
            starTime: '2018.5.12',
            endTime: '2018.06.16',
            teacher: '孟山支',
            society: '跆拳道社团',
            applicant: '陈小黄',
            status: 1,
            content:'5个帐篷5个帐篷5个帐篷5个帐篷5个帐篷5个帐篷'
          },
          {
            Numbering: 1254688,
            name: '帐篷',
            num: '5',
            starTime: '2018.5.12',
            endTime: '2018.06.16',
            teacher: '孟山支',
            society: '跆拳道社团',
            applicant: '陈小黄',
            status: 2,
            content:'5个帐篷5个帐篷5个帐篷5个帐篷5个帐篷5个帐篷'
          },
          {
            Numbering: 1254688,
            name: '帐篷',
            num: '5',
            starTime: '2018.5.12',
            endTime: '2018.06.16',
            teacher: '孟山支',
            society: '跆拳道社团',
            applicant: '陈小黄',
            status: 3,
            content:'5个帐篷5个帐篷5个帐篷5个帐篷5个帐篷5个帐篷'
          },
          {
            Numbering: 1254688,
            name: '帐篷',
            num: '5',
            starTime: '2018.5.12',
            endTime: '2018.06.16',
            teacher: '孟山支',
            society: '跆拳道社团',
            applicant: '陈小黄',
            status: 4,
            content:'5个帐篷5个帐篷5个帐篷5个帐篷5个帐篷5个帐篷'
          },
          {
            Numbering: 1254688,
            name: '帐篷',
            num: '5',
            starTime: '2018.5.12',
            endTime: '2018.06.16',
            teacher: '孟山支',
            society: '跆拳道社团',
            applicant: '陈小黄',
            status: 2,
            content:'5个帐篷5个帐篷5个帐篷5个帐篷5个帐篷5个帐篷'
          },
          {
            Numbering: 1254688,
            name: '帐篷',
            num: '5',
            starTime: '2018.5.12',
            endTime: '2018.06.16',
            teacher: '孟山支',
            society: '跆拳道社团',
            applicant: '陈小黄',
            status: 1,
            content:'5个帐篷5个帐篷5个帐篷5个帐篷5个帐篷5个帐篷'
          },
        ],
        currentPage:1,
        userRole:'',
        url:'',
        userId:'',
        associationId:'',
        assoMaterielList:[],
        showNo:false

      }
    },
    methods: {
      createFunc(){
        this.userRole = localStorage.getItem('userRole');
        this.userId =localStorage.getItem('userId');
        this.associationId = this.$route.query.associationId;
        if(this.userRole==1){
          this.url=this.localhost+'/associationMg/materiel/getAssoMaterielList';
          this.getList(1)
        }else if(this.userRole==2){

        }
      },
      getList(val){
        this.associationList=[];
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json ={
          associationId:this.associationId,
          start:val
        };
        this.$http.post(this.url,json).then(
          (success) => {
          var response = success.data;
          console.log(response);
          if(response.msg==666){
            this.totalNum =parseInt(response.listCount);
            if(response.assoMaterielList.length==0){
              this.showNo=true
            }else {
              this.showNo=false
            }
            for(var i =0; i<response.assoMaterielList.length;i++){
              if(this.userRole==1){
                this.assoMaterielList.push(response.assoMaterielList[i]);
              }else{
                this.assoMaterielList.push(response.assoMaterielList[i]);
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
      goBack(){
        this.$router.back(-1)
      },
        /*分页器*/
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },
      searchItem(){
        var searchArr = [];
        var lastArr = [];
        var idInput = this.idInput;
        var sortSociety = this.sortSociety;
        var nameInput = this.nameInput;
        if (isNaN(idInput) && idInput != '') {
          this.$message({
            type: 'error',
            message: '社团编号请输入数字!'
          });
        }
        searchArr.push({name: 'sortSociety', value: sortSociety});
        searchArr.push({name: 'nameInput', value: nameInput});
        searchArr.push({name: 'idInput', value: idInput});
        for (var i = 0; i < searchArr.length; i++) {
          if (searchArr[i].value != '') {
            lastArr.push(searchArr[i]);
          }
        }
        console.log(searchArr);
        console.log(lastArr);
        if (lastArr.length > 0) {
          console.log('发送请求');
        } else {
          this.$message({
            type: 'error',
            message: '请输入或选择搜索条件!'
          });
        }
        /*请求*/
        /*   this.$http.post(url).then(
         (success) => {
         this.Indicator.close();
         var response = success.data;
         this.SET_USER_LOGIN(false);
         this.mineObj.mineName = '请登录';
         this.$router.push({path: '/login'})
         },(error) => {
         this.Indicator.close();
         this.Toast({
         message: '总部信息加载失败',
         duration: 2000
         });
         });*/

      },
      toRouter(myRouter, materiId,associationId,id){
        this.$router.replace({path: myRouter, query: {'materiId': materiId,'associationId':associationId,'id':id}})
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
