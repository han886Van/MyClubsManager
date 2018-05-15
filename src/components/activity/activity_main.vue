<template>
  <div class="activity">
    <div class="bgc">
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
          <span>申请状态：</span>
          <el-select v-model="actiStatus" placeholder="申请状态">
            <el-option label="举办中" value="1"></el-option>
            <el-option label="已结束" value="2"></el-option>
            <el-option label="拒绝申请" value="2"></el-option>
            <el-option label="同意申请" value="4"></el-option>
          </el-select>
        </div>
        <div class="searchBtn">
          <el-button @click="searchItem()" type="info" plain>搜索</el-button>
          <el-button @click="toRouter('/addActivity')" type="primary">申请活动</el-button>
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
          <span>状态</span>
        </div>
        <ul class="list">
          <li class="societyList" v-for="(item,index) in activityArr">
            <span @click="toRouter('/societyDetails',item.societyId)">{{index+1}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.actNum}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.name}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.societyName}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.applicant}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.starTime}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.starTime}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.adress}}</span>
            <span v-show="item.isMaterials==1" @click="toRouter('/societyDetails',item.societyId)">有</span>
            <span v-show="item.isMaterials==2" @click="toRouter('/societyDetails',item.societyId)">无</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.teacher}}</span>
            <div>
              <span class="delBtn" @click="toRouter('/societyDetails',item.societyId)"
                    v-show="item.status==1">举办中</span>
              <span class="editBtn" @click="toRouter('/societyDetails',item.societyId)"
                    v-show="item.status==2">已结束</span>
              <span class="refuseBtn" @click="toRouter('/societyDetails',item.societyId)"
                    v-show="item.status==3">拒绝申请</span>
              <span class="agreetBtn" @click="toRouter('/societyDetails',item.societyId)"
                    v-show="item.status==4">同意申请</span>
            </div>
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
        sortSociety: ''
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
      toRouter(myRouter,societyId){
        this.$router.push({path: myRouter, query: {'societyId': societyId}})
      },
    },
    mounted(){

    }
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
    padding: 20px 40px;
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
