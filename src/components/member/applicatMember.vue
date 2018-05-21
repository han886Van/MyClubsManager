<template>
  <div class="member applicatMember">
    <div class="bgc">
      <div class="top">
        <span>成员管理</span>
        <span>	&gt;</span>
        <span class="blue">申请成员</span>
      </div>
      <div class="search_box">
        <div>
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
       <!--   <el-button @click="toRouter('/addMember')" type="primary">添加成员</el-button>-->
        </div>
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
          <el-button @click="clearSearchItem()" type="info" plain>清空搜索</el-button>
          <el-button @click="searchItem()" type="primary">搜索</el-button>
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
          <span>申请</span>
          <span>操作</span>
        </div>
        <ul class="list">
          <li class="societyList" v-for="(item,index) in assoUserList">
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
            <span v-show="item.user_state_num==0" @click="toRouter('/detailMember',item.user_id)">加入</span>
            <span v-show="item.user_state_num==3" @click="toRouter('/detailMember',item.user_id)">退出</span>
            <!--加入-->
            <div v-show="item.user_state_num==0">
              <span class="blue" @click="deilMember(item.id,1)">同意</span>
              <span class="delBtn" @click="deilMember(item.id)">拒绝</span>
            </div>
            <!--退出-->
            <div v-show="item.user_state_num==3">
              <span class="blue"  @click="deilMember(item.id)">同意</span>
              <span class="delBtn"  @click="deilMember(item.id,1)">拒绝</span>
            </div>
          </li>
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
        hadArr: [
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            memberId: '001',
            memberName: '小可爱',
            societyName: '摄影社团',
            position: '社员',
            grade: '大一',
            sort: 1,
            profession: '电子商务',
            status: 2
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            memberId: '001',
            memberName: '小可爱',
            societyName: '摄影社团',
            position: '社员',
            grade: '大一',
            sort: 2,
            profession: '电子商务',
            status: 1
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            memberId: '001',
            memberName: '小可爱',
            societyName: '摄影社团',
            position: '副社长',
            grade: '大一',
            sort: 3,
            profession: '电子商务',
            status: 2
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            memberId: '001',
            memberName: '小可爱',
            societyName: '摄影社团',
            position: '社员',
            grade: '大一',
            sort: 1,
            profession: '电子商务',
            status: 1
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            memberId: '001',
            memberName: '小可爱',
            societyName: '摄影社团',
            position: '社员',
            grade: '大一',
            sort: 1,
            profession: '电子商务',
            status: 2
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            memberId: '001',
            memberName: '小可爱',
            societyName: '摄影社团',
            position: '社员',
            grade: '大一',
            sort: 1,
            profession: '电子商务',
            status: 1
          },
        ],
        sortSociety: '',
        nameInput: '',
        idInput: '',
        operating: '',
        currentPage: 1,
        url: '',
        userId: '',
        listCount: 1,
        assoUserList: [],
        showNo: false,
        studentName: '',
        grade: '',
        studentNum: '',
      }
    },
    methods: {
      createFunc(){
        /*学生*/
        this.userId = localStorage.getItem('userId');
        this.url = this.localhost + 'associationMg/associationAndUser/studentGeApplytAssoUserList';
        this.getList(1, this.url);
      },
      getList(val, url, name, studentName, grade, studentNum){
        this.assoUserList = [];
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json = {
          start: val,
        };
        if (this.userId) {
          json.userId = this.userId;
        }
        if (name) {
          json.name = name;
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
            console.log(response);
            setTimeout(() => {
              loading.close();
            }, 500);
            if (response.msg == 666) {
              if (response.assoUserList.length == 0) {
                this.showNo = true
              } else {
                this.showNo = false
              }
              this.listCount = response.listCount;
              for (var i = 0; i < response.assoUserList.length; i++) {
                this.assoUserList.push(response.assoUserList[i])
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
      /*分页器*/
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        var name = this.nameInput;
        var studentName = this.studentName;
        var grade = this.grade;
        var studentNum = this.studentNum;
        if(this.sortSociety==''&&this.idInput==''&&this.nameInput==''){
          this.getList(val,this.url)
        }else {
          this.getList(val,this.url, name, studentName, grade, studentNum)
        }
      },
      searchItem(){
        var name = this.nameInput;
        var studentName = this.studentName;
        var grade = this.grade;
        var studentNum = this.studentNum;
        if (name && studentName && grade && studentNum) {
          this.$message.error('错误，请输入或选择搜索条件');
        } else {
          var url = this.url;
          this.getList(1, url, name, studentName, grade, studentNum)
        }
      },
      toRouter(myRouter, memberId){
        this.$router.push({path: myRouter, query: {'memberId': memberId}})
      },
      clearSearchItem(){
        this.nameInput='';
        this.studentName='';
        this.grade='';
        this.studentNum='';
        var url = this.url;
        this. getList(1, url)
      },
      /**
       * 同意申请加入社团、拒绝申请加入社团、同意申请退出社团、拒绝申请退出
       * id(社团及用户关系id)
       * 不传userState  拒绝申请加入社团     同意申请退出社团
       *  userState 1 同意申请加入社团    拒绝申请退出社团为1
       */
      deilMember(id,userState){
          var url = this.localhost +'associationMg/associationAndUser/modifyApplyAssociationStatus';
          var json ={
            id:id
          }
          if(userState){
            json.userState=userState
          }
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        this.$http.post(url, json).then(
          (success) => {
            var response = success.data;
            console.log(response);
            setTimeout(() => {
              loading.close();
            }, 500);
            if (response.msg == 666) {
                this.createFunc()
            } else {
              this.$message.error('错误，请求数据失败');
            }
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
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .applicatMember {
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
    .top {
      line-height: 50px;
      font-size: 16px;
      border-bottom: 1px solid #ccc;
      margin-bottom: 10px;
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
      color: #67c23a;
    }
    .delBtn {
      color: #f56c6c;
    }
  }
</style>
