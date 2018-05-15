<template>
  <div class="materials">
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
          <span>借用编号：</span>
          <el-input v-model="idInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>社团名字：</span>
          <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>物资名称：</span>
          <el-input v-model="idInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>社团编号：</span>
          <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>申请状态：</span>
          <el-select v-model="materialsStatus" placeholder="申请状态">
            <el-option label="使用中" value="1"></el-option>
            <el-option label="已归还" value="2"></el-option>
            <el-option label="拒绝申请" value="2"></el-option>
            <el-option label="同意申请" value="4"></el-option>
          </el-select>
        </div>
        <div class="searchBtn">
          <el-button @click="searchItem()" type="info" plain>搜索</el-button>
          <el-button @click="toRouter('/addMaterials')" type="primary">申请物质</el-button>
        </div>
      </div>
      <div>
        <div class="title">
          <span>序号</span>
          <span>编号</span>
          <span>名称</span>
          <span>数量</span>
          <span>借用时间</span>
          <span>归还时间</span>
          <span>申请教师</span>
          <span>申请社团</span>
          <span>申请人</span>
          <span>状态</span>
        </div>
        <ul class="list">
          <li class="societyList" v-for="(item,index) in materialsArr">
            <span @click="toRouter('/societyDetails',item.societyId)">{{index+1}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.Numbering}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.name}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.num}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.starTime}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.starTime}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.teacher}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.society}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.applicant}}</span>
            <div>
              <span class="delBtn" @click="toRouter('/societyDetails',item.societyId)"
                    v-show="item.status==1">使用中</span>
              <span class="editBtn" @click="toRouter('/societyDetails',item.societyId)"
                    v-show="item.status==2">已归还</span>
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
            status: 1
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
            status: 2
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
            status: 3
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
            status: 4
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
            status: 2
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
            status: 1
          },
        ]
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
      /*退出社团*/
      toRouter(myRouter, societyId){
        this.$router.push({path: myRouter, query: {'societyId': societyId}})
      },
    },
    mounted(){

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
