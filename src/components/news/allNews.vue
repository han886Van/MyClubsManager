<template>
  <div class="news">
    <div class="bgc">
      <div class="top">
        <div>
          <span>新闻管理</span>
          <span>	&gt;</span>
          <span class="blue">社团新闻</span>
        </div>
        <span class="blue" @click="goBack()">返回</span>
      </div>
      <div class="search_box">
        <!--  <div>
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
            <span>社团名字：</span>
            <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
          </div>
          <div>
            <span>社团编号：</span>
            <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
          </div>-->
       <!-- <div>
          <span>发布状态：</span>
          <el-select v-model="sendStatus" placeholder="发布状态">
            <el-option label="已发送" value="1"></el-option>
            <el-option label="草稿箱" value="0"></el-option>
            <el-option label="全部分类" value="2"></el-option>
          </el-select>
        </div>-->
        <div>
          <span>新闻编号：</span>
          <el-input v-model="idInput" placeholder="请输入内容" clearable></el-input>
        </div>
        <div>
          <span>新闻标题：</span>
          <el-input v-model="nameInput" placeholder="请输入内容" clearable></el-input>
        </div>
        <div class="searchBtn">
          <el-button @click="searchItem()" type="info" plain>搜索</el-button>
          <!--<el-button v-show="isPresident==1" @click="toRouter('/addNews',0,associationId)" type="primary">添加新闻</el-button>
          -->
        </div>
      </div>
      <div>
        <div class="title">
          <span>序号</span>
          <span>编号</span>
          <span>标题</span>
          <span>发送社团</span>
          <span>发送人</span>
          <span>发送时间</span>
          <span>状态</span>
          <span>操作</span>
        </div>
        <ul class="list">
          <!--association_id:1
              association_name:"校篮球队"
              content:"计科女篮冠军!!!!"
              create_time:1526532007000
              id:2
              publish_time:"2018-05-17"
              role_name:"社长"
              role_name_num:"1"
              state:"1"
              state_name:"已发布"
              state_num:"1"
              title:"计科院新闻"
              type_id:4
              type_name:"体育健身类"
              user_id:2
              user_name:"刘超群"-->
          <li class="societyList" v-for="(item,index) in assoNewsList">
            <span @click="toRouter('/detailNews',item.id)">{{index+1}}</span>
            <span @click="toRouter('/detailNews',item.id)">{{item.id}}</span>
            <span @click="toRouter('/detailNews',item.id)">{{item.title}}</span>
            <span @click="toRouter('/detailNews',item.id)">{{item.association_name}}</span>
            <span @click="toRouter('/detailNews',item.id)">{{item.user_name}}</span>
            <span @click="toRouter('/detailNews',item.id)">{{item.publish_time}}</span>
            <span class="delBtn" @click="toRouter('/detailNews',item.id)"
                  v-show="item.state_num==1">已发送</span>
            <span class="refuseBtn" @click="toRouter('/detailNews',item.id)"
                  v-show="item.state_num==0">草稿箱</span>
            <div>
              <span class="delBtn" @click="toRouter('/detailNews',item.id)">查看</span>
            </div>
          </li>
          <li v-show="showNo" class="noList">暂无新闻</li>
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
        sendStatus: "",
        newsArr: [
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            status: 1
          },
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            status: 2
          },
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            status: 1
          },
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            status: 1
          },
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            status: 1
          },
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            status: 2
          },
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            status: 1
          },
          {
            Numbering: 1254688,
            name: '跆拳道又招新啦',
            starTime: '2018.5.12',
            applicant: '孟山支',
            society: '跆拳道社团',
            status: 1
          },

        ],
        idInput: '',
        nameInput: '',
        sortSociety: '',
        currentPage: 1,
        assoNewsList: [],
        showNo:false,
        listCount:1,
        isPresident:''
      }
    },
    methods: {
      createFunc(){
        this.userRole = localStorage.getItem('userRole');
        this.userId = localStorage.getItem('userId');
        this.associationId = this.$route.query.associationId;
        if (this.userRole == 1) {
          this.url = this.localhost + 'associationMg/news/getAssociationNews';
          this.getList(1, this.userId)
        } else if (this.userRole == 2) {

        }
      },
      goBack(){
        this.$router.back(-1)
      },
      getList(val,userId,id,title){
        this.assoNewsList = [];
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json = {
          associationId: this.associationId,
          start: val,
          userId: userId,
          state:1
        };

        if(id){
          json.id=id
        }
        if(title){
          json.title=title
        }
        this.$http.post(this.url, json).then(
          (success) => {
            var response = success.data;
            console.log(response);
            if (response.msg == 666) {
              this.listCount = parseInt(response.listCount);
              if (response.assoNewsList.length == 0) {
                this.showNo = true
              } else {
                this.showNo = false;
                for (var i = 0; i < response.assoNewsList.length; i++) {
                  if (this.userRole == 1) {
                    this.isPresident =response.isPresident;
                    this.assoNewsList.push(response.assoNewsList[i]);
                  } else {
                    this.assoNewsList.push(response.assoNewsList[i]);
                  }
                }
              }
            } else {
              this.$message.error('错误，请求数据失败');
            }
            setTimeout(() => {
              loading.close();
            }, 500);
          },
          (error) => {
            setTimeout(()=> {
              loading.close();
            },500);
            this.$message.error('错误，请求数据失败');
          }
        )
        ;
      },
      /*分页器*/
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        var id = this.idInput;
        var title = this.nameInput;
        this.getList(val,this.userId,id,title)
      },
      searchItem(){
        var id = this.idInput;
        var title = this.nameInput;
        if(!id&&!title){
          this.$message.error('错误，请输入搜索内容或者选择搜索内容');
          this.createFunc()
        }else {
          this. getList(1, this.userId,id,title)
        }

      },
      /*退出社团*/
      toRouter(myRouter,id,associationId){
        this.$router.push({path: myRouter, query: {'id': id,'associationId':associationId}})
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
  .news {
    margin-left: 80px;
    margin-top: 50px;
    overflow: hidden;
    width: 1024px;
    background: #fff;
    min-height: 600px;
    border-radius: 8px;
    padding: 10px 40px 20px 40px;
    .top {
      line-height: 50px;
      display: flex;
      justify-content: space-between;
      border-bottom: 1px solid #ccc;
      margin-bottom: 20px;
      .editing {
        color: #409eff;
        cursor: pointer;
      }
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
