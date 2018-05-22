<template>
  <div class="member">
    <div class="bgc">
      <div class="top">
       <div>
         <span>成员管理</span>
        <span>	&gt;</span>
        <span class="blue">社团成员</span>
       </div>
        <span class="blue" @click="goBack()">返回</span>
      </div>
      <div class="search_box">
      <!--  <div>
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
            <span>社团编号：</span>
            <el-input v-model="idInput" placeholder="请输入内容" clearable></el-input>
          </div>
          <div>
            <span>社团名字：</span>
            <el-input v-model="nameInput" placeholder="请输入内容" clearable></el-input>
          </div>
          <el-button @click="toRouter('/addMember')" type="primary">添加成员</el-button>
        </div>-->
        <div>
          <span>学生学号：</span>
          <el-input v-model="studentNum" placeholder="请输入内容" clearable></el-input>
        </div>
        <div>
          <span>学生名字：</span>
          <el-input v-model="studentName" placeholder="请输入内容" clearable></el-input>
        </div>
        <div>
          <span>学生年级：</span>
          <el-input v-model="grade" placeholder="请输入内容" clearable></el-input>
        </div>
        <div class="searchBtn">
          <el-button @click="searchItem()" type="primary" >搜索</el-button>
          <el-button @click="clearSearchItem()" type="info" plain>清空搜索</el-button>
        </div>
      </div>
      <div>
        <div class="title">
          <span>序号</span>
          <span>学号</span>
          <span>名字</span>
          <span>专业</span>
          <span>年级</span>
          <span>社团</span>
          <span>分类</span>
          <span>职位</span>
          <span>操作</span>
        </div>
        <ul v-show="this.userRole == 1" class="list">
          <!--社长-->
          <li class="societyList" v-for="(item,index) in lAssociationList">
            <span @click="toRouter('/detailMember',item.user_id)">{{index+1}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.student_num}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.user_name}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.major}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.grade}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.name}}</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==1">专业学术类</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==2">科技创新类</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==3">艺术兴趣类</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==4">体育健身类</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==5">公益服务类</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.user_type_name}}</span>
            <div>
              <span class="blue" v-show="isPresident==1" @click="changeMember('/changeMain',item.association_id)">更换</span>
              <span class="blue" v-show="isPresident!=1">查看</span>
            </div>
          </li>
          <!--社员-->
          <li class="societyList" v-for="(item,index) in associationList">
            <span @click="toRouter('/detailMember',item.user_id)">{{lAssociationList.length+index+1}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.student_num}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.user_name}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.major}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.grade}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.name}}</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==1">专业学术类</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==2">科技创新类</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==3">艺术兴趣类</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==4">体育健身类</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==5">公益服务类</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.user_type_name}}</span>
            <div>
              <span class="blue" v-show="isPresident!=1">查看</span>
              <span class="red_color" v-show="isPresident==1" @click="delMember(item.id)">删除</span>
            </div>
          </li>
          <li v-show="showNo" class="noList">暂无成员</li>
        </ul>
        <ul v-show="this.userRole == 2" class="list">
          <li class="societyList" v-for="(item,index) in lAssociationList">
            <span @click="toRouter('/detailMember',item.user_id)">{{index+1}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.student_num}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.user_name}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.major}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.grade}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.name}}</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==1">专业学术类</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==2">科技创新类</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==3">艺术兴趣类</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==4">体育健身类</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==5">公益服务类</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.user_type_name}}</span>
            <div>
              <span class="red_color" >删除</span>
            </div>
          </li>
          <!--社员-->
          <li v-show="showNo" class="noList">暂无成员</li>
        </ul>
      </div>
      <div class="myPagination">
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
        associationId: '',
        currentPage: 1,
        showNo: false,
        userId: '',
        url: '',
        userRole: '',
        /*社长*/
        lAssociationList: [],
        /*社员*/
        associationList: [],
        listCount: 1,
        studentName:'',
        grade:'',
        studentNum: '',
        userType:'',
        isPresident:'',
        typeId:''
      }
    },
    methods: {
      createFunc(){
        this.userRole = localStorage.getItem('userRole');
        if (this.userRole == 1) {
          /*学生*/
          this.userId = localStorage.getItem('userId');
          this.url = this.localhost + 'associationMg/associationAndUser/studentGetAssoUserList';
          this.associationId = this.$route.query.associationId;
          this.getList(1, this.url);
        } else if (this.userRole == 2) {
          this.url = this.localhost + 'associationMg/association/getAllAssociation';
          this.typeId  =localStorage.getItem('typeId');
          this.getTList(1, this.url);
        }
      },
      goBack(){
        this.$router.back(-1)
      },
      getList(val, url, studentName, grade,studentNum){
        this.lAssociationList=[];
        this.associationList=[];
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json = {
          userId:this.userId,
          start: val,
          associationId: this.associationId,
        };
        if ( this.userType) {
          json.userType = this.userType;
        }
        if (studentName) {
          json.studentName = studentName;
        }
        if (grade) {
          json.grade = grade;
        }
        if (studentNum) {
          json.studentNum = studentNum;
        }
        this.$http.post(url, json).then(
          (success) => {
            var response = success.data;
            setTimeout(() => {
              loading.close();
            }, 500);
            if (response.msg == 666) {
              console.log(response);
              this.listCount = response.listCount;
              if (response.assoUserList.length == 0) {
                this.showNo = true
              } else {
                this.showNo = false
              }
              for (var i = 0; i < response.assoUserList.length; i++) {
                  if(this.userRole == 1){
                    this.isPresident =response.isPresident;
                    if (response.assoUserList[i].user_type == 1) {
                      /*社长*/
                      this.lAssociationList.push(response.assoUserList[i]);
                    } else {
                      this.associationList.push(response.assoUserList[i]);
                    }
                  }else {
                    this.associationList.push(response.assoUserList[i]);
                  }

              }
            } else {
              this.$message.error('错误，请求数据失败');
            }
          }, (error) => {
            setTimeout(() => {
              loading.close();
            }, 500);
            this.$message.error('错误，请求数据失败');
          });
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
      /*分页器*/
      handleSizeChange(val) {
        var name= this.nameInput;
        var studentName= this.studentName;
        var grade= this.grade;
        var studentNum= this.studentNum;
        var url = this.url;
        this.getList(val,url,name,studentName,grade,studentNum)
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        if(this.sortSociety==''&&this.idInput==''&&this.nameInput==''){
          this.getList(val,this.url)
        }else {
          var name= this.nameInput;
          var studentName= this.studentName;
          var grade= this.grade;
          var studentNum= this.studentNum;
          this.getList(val, this.url,name,studentName,grade,studentNum)
        }
      },
      clearSearchItem(){
        this.nameInput='';
        this.studentName='';
        this.grade='';
        this.studentNum='';
        var url = this.url;
        this. getList(1, url)
      },
      searchItem(){
       var studentName = this.studentName;
       var studentNum = this.studentNum;
       var grade = this.grade;
       if(!studentName && !studentNum && !grade){
         this.$message.error('错误，请输入搜索内容');
       }else {
         this.getList(1, this.url, studentName, grade,studentNum)
       }
      },
      toRouter(myRouter, memberId){
        this.$router.push({path: myRouter, query: {'memberId': memberId}})
      },
      /*删除社员*/
      delMember(id) {
        this.$confirm('是否删除该成员?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.delPost(id);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      delPost(id){
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json = {
          id:id
        };
          var url  = this.localhost +'associationMg/associationAndUser/modifyApplyAssociationStatus';
        this.$http.post(url, json).then(
          (success) => {
            var response = success.data;
            setTimeout(() => {
              loading.close();
            }, 500);
            if (response.msg == 666) {
              this.getList(1, this.url);
            } else {
              this.$message.error('错误，请求数据失败');
            }
          }, (error) => {
            setTimeout(() => {
              loading.close();
            }, 500);
            this.$message.error('错误，请求数据失败');
          });
      },
      changeMember(myRouter,associationId){
        this.$router.replace({path: myRouter, query: {'associationId': associationId}})
      }
    },
    mounted(){
    },
    created() {
      this.createFunc()
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .member {
    margin-left: 80px;
    margin-top: 50px;
    overflow: hidden;
    width: 1024px;
    background: #fff;
    min-height: 600px;
    border-radius: 8px;
    padding: 10px 40px 20px 40px;
    .bgc {
      border-radius: 8px;
      background-color: #fff;
    }
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
    .search_box {
      margin-bottom: 20px;
      div {
        margin-top: 10px;
        display: inline-block;
        margin-right: 10px;
      }
    }
    .searchBtn {
      /*float :right;*/
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
    .editBtn {
      color: #409eff;
    }
    .delBtn {
      color: #f56c6c;
    }
  }
</style>
