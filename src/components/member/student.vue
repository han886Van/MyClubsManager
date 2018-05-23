<template>
  <div class="member">
    <div class="bgc">
      <div class="top" >
        <span>成员管理</span>
        <span>	&gt;</span>
        <span class="blue">学生</span>
      </div>
      <div class="search_box">
        <div>
          <span>学生账号：</span>
          <el-input v-model="idInput" placeholder="请输入内容" clearable></el-input>
        </div>
        <div>
          <span>学生名字：</span>
          <el-input v-model="nameInput" placeholder="请输入内容" clearable></el-input>
        </div>
        <div>
          <span>学生年级：</span>
          <el-input v-model="grade" placeholder="请输入内容" clearable></el-input>
        </div>
        <div class="searchBtn">
          <el-button @click="searchItem()" type="info" plain>搜索</el-button>
          <el-button @click="toRouter('/addStudent')" type="primary">添加学生</el-button>
        </div>
      </div>
      <div>
        <div class="title">
          <span>序号</span>
          <span>编号</span>
          <span>学号</span>
          <span>姓名</span>
          <span>性别</span>
          <span>年级</span>
          <span>专业</span>
          <span>学院</span>
          <span>操作</span>
        </div>
        <ul class="list">
          <li class="societyList" v-for="(item,index) in userList">
            <span @click="toRouter('/detailMember',item.user_id)">{{index+1}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.user_id}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.student_num}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.user_name}}</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.sex==0">女</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.sex==1">男</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.grade}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.major}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.college}}</span>
            <div>
              <span class="editBtn" @click="toRouter('/addStudent',item.user_id,1)">编辑</span>
              <span class="delBtn" @click="delMember(item.user_id)">删除</span>
            </div>
          </li>
          <li v-show="showNo" class="noList">暂无学生成员</li>
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
        hadArr: [
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            studentId: '001',
            studentName: '小可爱',
            sex: 0,
            grade:'大一',
            profession:'电子商务',
            college: '计算机科学学院',

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            studentId: '001',
            studentName: '小可爱',
            sex: 0,
            grade:'大一',
            profession:'电子商务',
            college: '计算机科学学院',

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            studentId: '001',
            studentName: '小可爱',
            sex: 1,
            grade:'大一',
            profession:'电子商务',
            college: '计算机科学学院',

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            studentId: '001',
            studentName: '小可爱',
            sex: 0,
            grade:'大一',
            profession:'电子商务',
            college: '计算机科学学院',

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            studentId: '001',
            studentName: '小可爱',
            sex: 1,
            grade:'大一',
            profession:'电子商务',
            college: '计算机科学学院',

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            studentId: '001',
            studentName: '小可爱',
            sex: 0,
            grade:'大一',
            profession:'电子商务',
            college: '计算机科学学院',

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            studentId: '001',
            studentName: '小可爱',
            sex: 0,
            grade:'大一',
            profession:'电子商务',
            college: '计算机科学学院',

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            studentId: '001',
            studentName: '小可爱',
            sex: 1,
            grade:'大一',
            profession:'电子商务',
            college: '计算机科学学院',

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            studentId: '001',
            studentName: '小可爱',
            sex: 0,
            grade:'大一',
            profession:'电子商务',
            college: '计算机科学学院',

          },

        ],
        grade:'',
        nameInput:'',
        idInput:'',
        currentPage:1,
        totalNum:1,
        userList:[],
        showNo:false
      }
    },
    methods: {
      createFunc(){
          this.getStudent(1)
      },
      getStudent(val,userId,userName,grade){
        this.userList=[];
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var url = this.localhost+'associationMg/user/getUserList';
        var json ={
          userType:3,
          start:val
        };
        if(userId){
          json.userId=userId
        }
        if(userName){
          json.userName=userName
        }
        if(grade){
          json.grade=grade
        }
        this.$http.post(url,json).then(
          (success) => {
            var response = success.data;
            console.log(response);
            if(response.msg==666){
                this.totalNum=response.total_num;
                if(response.userList.length>0){
                    this.showNo=false;
                  for(var i=0;i<response.userList.length;i++){
                    this.userList.push(response.userList[i])
                  }
                }else {
                  this.showNo=true;
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
        var userId = this.idInput;
        var userName = this.nameInput;
        var grade = this.grade;
        if(!userId&&!userName&&!grade){
          this.$message.error('错误，请输入搜索信息');
          this.getStudent(1)
        }else {
          this.getStudent(1,userId,userName,grade)
        }
      },
      /*分页器*/
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        console.log(this.currentPage);
        var userId = this.idInput;
        var userName = this.nameInput;
        var grade = this.grade;
        this.getStudent(val,userId,userName,grade)
      },
      toRouter(myRouter,memberId,role){
        this.$router.push({path: myRouter, query: {'memberId': memberId,'role':role}})
      },
      delMember(userId) {
        this.$confirm('是否删除该成员?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this. postDel(userId)
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      postDel(userId){
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var url = this.localhost+'associationMg/user/deleteUser';
        var json ={
          userId:userId,
        };
        this.$http.post(url,json).then(
          (success) => {
            var response = success.data;
            console.log(response);
            if(response.msg==666){
              this.$message({
                message: '成功删除学生！',
                type: 'success'
              });
              this.currentPage = 1;
              this.createFunc()
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

    },
    created() {
      this.createFunc()
    },
    watch: {
      currentPage: function () {
        console.log('监听到currentPage发生变化');
        console.log(this.currentPage);
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .member{
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
      font-size:16px;
      border-bottom :1px solid #ccc;
      margin-bottom:10px;
    }
    .search_box {
      margin-bottom: 20px;
      div {
        margin-top: 10px;
        display: inline-block;
        margin-right: 10px;
      }
    }
    .searchBtn{
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
    .editBtn{
      color : #409eff;
    }
    .delBtn{
      color : #f56c6c;
    }
  }
</style>
