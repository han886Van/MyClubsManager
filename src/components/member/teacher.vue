<template>
  <div class="member">
    <div class="bgc">
      <div class="top" >
        <span>成员管理</span>
        <span>	&gt;</span>
        <span class="blue">教师</span>
      </div>
      <div class="search_box">
        <div>
          <span>教师编号：</span>
          <el-input v-model="idInput" placeholder="请输入内容" clearable></el-input>
        </div>
        <div>
          <span>教师名字：</span>
          <el-input v-model="nameInput" placeholder="请输入内容" clearable></el-input>
        </div>
        <div>
          <span>教师分类：</span>
          <el-select v-model="teacherSort" placeholder="教师分类">
            <el-option label="专业学术类" value="1"></el-option>
            <el-option label="科技创新类" value="2"></el-option>
            <el-option label="艺术兴趣类" value="3"></el-option>
            <el-option label="体育健身类" value="4"></el-option>
            <el-option label="公益服务类" value="5"></el-option>
            <el-option label="全部分类" value="6"></el-option>
          </el-select>
        </div>
        <div class="searchBtn">
          <el-button @click="searchItem()" type="info" plain>搜索</el-button>
          <el-button @click="toRouter('/addTeacher')" type="primary">添加教师</el-button>
        </div>
      </div>
      <div>
        <div class="title">
          <span>序号</span>
          <span>编号</span>
          <span>姓名</span>
          <span>性别</span>
          <span>分类</span>
          <span>操作</span>
        </div>
        <ul class="list">
          <li class="societyList" v-for="(item,index) in userList">
            <span @click="toRouter('/detailMember',item.user_id)">{{index+1}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.user_id}}</span>
            <span @click="toRouter('/detailMember',item.user_id)">{{item.user_name}}</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.sex==0">女</span>
            <span @click="toRouter('/detailMember',item.user_id)" v-show="item.sex==1">男</span>
             <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==1">专业学术类</span>
             <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==2">科技创新类</span>
             <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==3">艺术兴趣类</span>
             <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==4">体育健身类</span>
             <span @click="toRouter('/detailMember',item.user_id)" v-show="item.type_id==5">公益服务类</span>
            <div>
              <span class="editBtn" @click="toRouter('/addTeacher',item.user_id)" >编辑</span>
              <span class="delBtn" @click="delMember(index)">删除</span>
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
            teacherId: '001',
            teacherName: '小可爱',
            sex: 0,
            sort:1,

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            teacherId: '001',
            teacherName: '小可爱',
            sex: 0,
            sort:2,

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            teacherId: '001',
            teacherName: '小可爱',
            sex: 1,
            sort:3,

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            teacherId: '001',
            teacherName: '小可爱',
            sex: 0,
            sort:1,

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            teacherId: '001',
            teacherName: '小可爱',
            sex: 0,
            sort:4,

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            teacherId: '001',
            teacherName: '小可爱',
            sex: 1,
            sort:1,

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            teacherId: '001',
            teacherName: '小可爱',
            sex: 0,
            sort:1,

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            teacherId: '001',
            teacherName: '小可爱',
            sex: 1,
            sort:3,

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            teacherId: '001',
            teacherName: '小可爱',
            sex: 1,
            sort:2,

          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            teacherId: '001',
            teacherName: '小可爱',
            sex: 0,
            sort:1,

          },


        ],
        teacherSort:'',
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
        this.getTeacher(1)
      },
      getTeacher(val,userId,userName,typeId){
        this.userList=[];
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var url = this.localhost+'associationMg/user/getUserList';
        var json ={
          userType:2,
          start:val
        };
        if(userId){
          json.userId=userId
        }
        if(userName){
          json.userName=userName
        }
        if(typeId){
          json.typeId=typeId
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
      /*分页器*/
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        var userId = this.idInput;
        var userName = this.nameInput;
        var typeId = this.teacherSort;
        this.getTeacher(val,userId,userName,typeId)
      },
      searchItem(){
          /*userId(编号userId)、userName(名字 可模糊查询)、typeId(老师类别id)*/
          var userId = this.idInput;
          var userName = this.nameInput;
          var typeId = this.teacherSort;
          if(!userId&&!userName&&!typeId){
            this.$message.error('错误，请输入搜索信息');
          }else if(typeId==6){
            this.getTeacher(1,userId,userName)
          }else {
            this.getTeacher(1,userId,userName,typeId)
          }
      },
      toRouter(myRouter,memberId){
        this.$router.push({path: myRouter, query: {'memberId': memberId}})
      },
      delMember(index) {
        this.$confirm('是否删除该成员?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
    },
    mounted(){

    },
    created() {
      this.createFunc()
    },
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
