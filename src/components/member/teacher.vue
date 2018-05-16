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
          <el-input v-model="idInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>教师名字：</span>
          <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>教师分类：</span>
          <el-select v-model="teacherSort" placeholder="教师分类">
            <el-option label="专业学术类" value="1"></el-option>
            <el-option label="科技创新类" value="2"></el-option>
            <el-option label="艺术兴趣类" value="2"></el-option>
            <el-option label="体育健身类" value="4"></el-option>
            <el-option label="公益服务类" value="5"></el-option>
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
          <li class="societyList" v-for="(item,index) in hadArr">
            <span @click="toRouter('/detailMember',item.memberId)">{{index+1}}</span>
            <span @click="toRouter('/detailMember',item.memberId)">{{item.teacherId}}</span>
            <span @click="toRouter('/detailMember',item.memberId)">{{item.teacherName}}</span>
            <span @click="toRouter('/detailMember',item.memberId)" v-show="item.sex==0">女</span>
            <span @click="toRouter('/detailMember',item.memberId)" v-show="item.sex==1">男</span>
             <span @click="toRouter('/detailMember',item.memberId)" v-show="item.sort==1">专业学术类</span>
             <span @click="toRouter('/detailMember',item.memberId)" v-show="item.sort==2">科技创新类</span>
             <span @click="toRouter('/detailMember',item.memberId)" v-show="item.sort==3">艺术兴趣类</span>
             <span @click="toRouter('/detailMember',item.memberId)" v-show="item.sort==4">体育健身类</span>
             <span @click="toRouter('/detailMember',item.memberId)" v-show="item.sort==5">公益服务类</span>
            <div>
              <span class="editBtn" @click="toRouter('/addTeacher',item.teacherId)" >编辑</span>
              <span class="delBtn" @click="delMember(index)">删除</span>
            </div>
          </li>
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
        currentPage:1
      }
    },
    methods: {
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
      toRouter(myRouter,teacherId){
        this.$router.push({path: myRouter, query: {'teacherId': teacherId}})
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
