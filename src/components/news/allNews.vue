<template>
  <div class="allNews">
    <div class="bgc">
      <div class="top" >
        <span>新闻管理</span>
        <span>	&gt;</span>
        <span class="blue">全部新闻</span>
      </div>
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
          <span>新闻编号：</span>
          <el-input v-model="idInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>社团名字：</span>
          <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>新闻标题：</span>
          <el-input v-model="idInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>社团编号：</span>
          <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>发布状态：</span>
          <el-select v-model="sendStatus" placeholder="发布状态">
            <el-option label="已发送" value="1"></el-option>
            <el-option label="草稿箱" value="2"></el-option>
          </el-select>
        </div>
        <div class="searchBtn">
          <el-button @click="searchItem()" type="info" plain>&nbsp;&nbsp;&nbsp;搜索&nbsp;&nbsp;&nbsp;</el-button>
        </div>
      </div>
      <div>
        <div class="title">
          <span>序号</span>
          <span>编号</span>
          <span>标题</span>
          <span>内容</span>
          <span>发送社团</span>
          <span>发送人</span>
          <span>发送时间</span>
        </div>
        <ul class="list">
          <li class="societyList" v-for="(item,index) in newsArr">
            <span @click="toRouter('/societyDetails',item.societyId)">{{index+1}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.Numbering}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.name}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.content}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.society}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.applicant}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.starTime}}</span>

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
    components: {},
    data () {
      return {
        sendStatus:"",
        newsArr: [
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            content:'寻梦，是每个人心中的那片橄榄叶。然而学生会举行的隆重的招新大会为新生提供了一个服务同学，锻炼自我的平台。学生会招新是为了保证校学生会有新鲜的血液的注入，有足够高素质的后备力量，能更有效地开展学生会工作，实现学生会干部的新老交替，培养和壮大'
          },
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            content:'寻梦，是每个人心中的那片橄榄叶。然而学生会举行的隆重的招新大会为新生提供了一个服务同学，锻炼自我的平台。学生会招新是为了保证校学生会有新鲜的血液的注入，有足够高素质的后备力量，能更有效地开展学生会工作，实现学生会干部的新老交替，培养和壮大'
          },
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            content:'寻梦，是每个人心中的那片橄榄叶。然而学生会举行的隆重的招新大会为新生提供了一个服务同学，锻炼自我的平台。学生会招新是为了保证校学生会有新鲜的血液的注入，有足够高素质的后备力量，能更有效地开展学生会工作，实现学生会干部的新老交替，培养和壮大'
          },
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            content:'寻梦，是每个人心中的那片橄榄叶。然而学生会举行的隆重的招新大会为新生提供了一个服务同学，锻炼自我的平台。学生会招新是为了保证校学生会有新鲜的血液的注入，有足够高素质的后备力量，能更有效地开展学生会工作，实现学生会干部的新老交替，培养和壮大'
          },
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            content:'寻梦，是每个人心中的那片橄榄叶。然而学生会举行的隆重的招新大会为新生提供了一个服务同学，锻炼自我的平台。学生会招新是为了保证校学生会有新鲜的血液的注入，有足够高素质的后备力量，能更有效地开展学生会工作，实现学生会干部的新老交替，培养和壮大'
          },
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            content:'寻梦，是每个人心中的那片橄榄叶。然而学生会举行的隆重的招新大会为新生提供了一个服务同学，锻炼自我的平台。学生会招新是为了保证校学生会有新鲜的血液的注入，有足够高素质的后备力量，能更有效地开展学生会工作，实现学生会干部的新老交替，培养和壮大'
          },
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            content:'寻梦，是每个人心中的那片橄榄叶。然而学生会举行的隆重的招新大会为新生提供了一个服务同学，锻炼自我的平台。学生会招新是为了保证校学生会有新鲜的血液的注入，有足够高素质的后备力量，能更有效地开展学生会工作，实现学生会干部的新老交替，培养和壮大'
          },


        ],
        idInput: '',
        nameInput: '',
        sortSociety: '',
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
  .allNews {
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
      font-size:16px;
      border-bottom :1px solid #ccc;
      margin-bottom:10px;
    }
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
