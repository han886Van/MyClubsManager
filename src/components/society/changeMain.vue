<template>
  <div class="changeMain">
    <div class="bgc">
      <div class="top" >
       <div>
         <span>成员管理</span>
        <span>	&gt;</span>
        <span class="blue">社团成员</span>
       </div>
        <span class="blue" @click="goBack()">取消</span>
      </div>
      <div class="search_box">
        <div>
          <span>学生编号：</span>
          <el-input v-model="idInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>学生名字：</span>
          <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>学生年级：</span>
          <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <el-button @click="searchItem()" type="info" plain>搜索</el-button>
        </div>
        <div class="searchBtn">
          <el-button @click="toRouter('/edtiSociety',hadArr[radio2].memberId)" type="primary">确认选择</el-button>
        </div>
      </div>
      <div>
        <div class="title">
          <span>选择</span>
          <span>学号</span>
          <span>名字</span>
          <span>专业</span>
          <span>年级</span>
          <span>社团</span>
          <span>分类</span>
          <span>职位</span>
        </div>
        <ul class="list">
          <li v-for="(item,index) in assoUserList">
           <div class="checkBox"> <el-radio-group v-model="radio2">
              <el-radio :label=index >{{index+1}}</el-radio>
            </el-radio-group></div>
            <!--association_id:1
                grade:"大四"
                major:"软件工程"
                name:"计科女篮"
                student_num:"2014034743019"
                type_id:4
                type_name:"体育健身类"
                user_id:4
                user_name:"哥哥"
                user_type:"1"
                user_type_name:"社长"
                -->
            <span @click="toRouter('/detailMember',item.memberId)">{{item.user_id}}</span>
            <span @click="toRouter('/detailMember',item.memberId)">{{item.user_name}}</span>
            <span @click="toRouter('/detailMember',item.memberId)">{{item.major}}</span>
            <span @click="toRouter('/detailMember',item.memberId)">{{item.grade}}</span>
            <span @click="toRouter('/detailMember',item.memberId)">{{item.name}}</span>
            <span @click="toRouter('/detailMember',item.memberId)" v-show="item.user_type==1">专业学术类</span>
            <span @click="toRouter('/detailMember',item.memberId)" v-show="item.user_type==2">科技创新类</span>
            <span @click="toRouter('/detailMember',item.memberId)" v-show="item.user_type==3">艺术兴趣类</span>
            <span @click="toRouter('/detailMember',item.memberId)" v-show="item.user_type==4">体育健身类</span>
            <span @click="toRouter('/detailMember',item.memberId)" v-show="item.user_type==5">公益服务类</span>
            <span @click="toRouter('/detailMember',item.memberId)">社员</span>
          </li>
        </ul>
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
            memberId: '001',
            memberName: '小可爱',
            societyName: '摄影社团',
            position:'社员',
            grade:'大一',
            sort: 1,
            profession:'电子商务'
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            memberId: '001',
            memberName: '小可爱',
            societyName: '摄影社团',
            position:'社员',
            grade:'大一',
            sort: 2,
            profession:'电子商务'
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            memberId: '001',
            memberName: '小可爱',
            societyName: '摄影社团',
            position:'社员',
            grade:'大一',
            sort: 3,
            profession:'电子商务'
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            memberId: '001',
            memberName: '小可爱',
            societyName: '摄影社团',
            position:'社员',
            grade:'大一',
            sort: 1,
            profession:'电子商务'
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            memberId: '001',
            memberName: '小可爱',
            societyName: '摄影社团',
            position:'社员',
            grade:'大一',
            sort: 1,
            profession:'电子商务'
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            memberId: '001',
            memberName: '小可爱',
            societyName: '摄影社团',
            position:'社员',
            grade:'大一',
            sort: 1,
            profession:'电子商务'
          },
        ],
        sortSociety:'',
        nameInput:'',
        idInput:'',
        radio2:'',
        assoUserList:[],
        associationId:''
      }
    },
    methods: {
      createFunc(){
        this.assoUserList=[];
        this.associationId = this.$route.query.associationId;
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var userId = localStorage.getItem('userId');
        var url = this.localhost+'associationMg/associationAndUser/studentGetAssoUserList';
        var json ={
          userId:userId,
          associationId:this.associationId
        };
        this.$http.post(url,json).then(
          (success) => {
          var response = success.data;
          console.log(response);
          if(response.msg==666){
            for (var i = 0; i < response.assoUserList.length; i++) {
              this.assoUserList.push(response.assoUserList[i])
            }
            console.log(this.assoUserList);
          }else {
              this.goBack();
            this.$message.error('错误，请求数据失败');
          }
          setTimeout(() => {
            loading.close();
          }, 500);
        }, (error) => {
          setTimeout(() => {
            loading.close();
          }, 500);
            this.goBack();
          this.$message.error('错误，请求数据失败');
        });
      },
      goBack(){
        this.$router.back(-1)
      },
      searchItem(){
      },
      getList(val, url, name, studentName, grade,studentNum){
        this.associationList=[];
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json = {
          start: val,
        };
        if ( this.userId) {
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
          setTimeout(() => {
            loading.close();
          }, 500);
       /*   if (response.msg == 666) {
            this.listCount = response.listCount;
            if (response.assoUserList.length == 0) {
              this.showNo = true
            } else {
              this.showNo = false
            }
            for (var i = 0; i < response.assoUserList.length; i++) {
              this.associationList.push(response.assoUserList[i]);
            }
            console.log(this.associationList);
          } else {
            this.$message.error('错误，请求数据失败');
          }*/
        }, (error) => {
          setTimeout(() => {
            loading.close();
          }, 500);
          this.$message.error('错误，请求数据失败');
        });
      },
      toRouter(myRouter,memberId){
        console.log(myRouter,memberId);
        this.$router.push({path: myRouter, query: {'memberId': memberId}})
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
  .changeMain{
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
      .searchBtn{
        margin-top:20px;
      }
    }

    .title {
      color: #000000;
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 20px;
      span {
        min-width: 128px;
        text-align: center;

      }
    }
      .list{
        li{
          span{
            display: inline-block;
            min-width: 128px;
            text-align: center;
          }
        }
      }
        .checkBox{
          overflow :hidden;
          max-width: 146px;
        }
    .editBtn{
      color : #409eff;
    }
    .delBtn{
      color : #f56c6c;
    }
  }
</style>
