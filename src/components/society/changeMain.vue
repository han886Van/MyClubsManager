<template>
  <div class="changeMain">
    <div class="bgc">
      <div class="top" >
        <span>成员管理</span>
        <span>	&gt;</span>
        <span class="blue">社团成员</span>
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
          <li v-for="(item,index) in hadArr">
           <div class="checkBox"> <el-radio-group v-model="radio2">
              <el-radio :label=index >{{index+1}}</el-radio>
            </el-radio-group></div>
            <span @click="toRouter('/detailMember',item.memberId)">{{item.memberId}}</span>
            <span @click="toRouter('/detailMember',item.memberId)">{{item.memberName}}</span>
            <span @click="toRouter('/detailMember',item.memberId)">{{item.profession}}</span>
            <span @click="toRouter('/detailMember',item.memberId)">{{item.grade}}</span>
            <span @click="toRouter('/detailMember',item.memberId)">{{item.societyName}}</span>
            <span @click="toRouter('/detailMember',item.memberId)" v-show="item.sort==1">专业学术类</span>
            <span @click="toRouter('/detailMember',item.memberId)" v-show="item.sort==2">科技创新类</span>
            <span @click="toRouter('/detailMember',item.memberId)" v-show="item.sort==3">艺术兴趣类</span>
            <span @click="toRouter('/detailMember',item.memberId)" v-show="item.sort==4">体育健身类</span>
            <span @click="toRouter('/detailMember',item.memberId)" v-show="item.sort==5">公益服务类</span>
            <span @click="toRouter('/detailMember',item.memberId)">{{item.position}}</span>
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
        radio2:''
      }
    },
    methods: {
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
      toRouter(myRouter,memberId){
        console.log(myRouter,memberId);
        this.$router.push({path: myRouter, query: {'memberId': memberId}})
      },
    },
    mounted(){

    }
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
