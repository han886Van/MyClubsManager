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
        <div>
          <span>新闻编号：</span>
          <el-input v-model="idInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>新闻标题：</span>
          <el-input v-model="idInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>发布状态：</span>
          <el-select v-model="sendStatus" placeholder="发布状态">
            <el-option label="已发送" value="1"></el-option>
            <el-option label="草稿箱" value="2"></el-option>
          </el-select>
        </div>
        <div class="searchBtn">
          <el-button @click="searchItem()" type="info" plain>搜索</el-button>
          <el-button @click="toRouter('/addNews')" type="primary">添加新闻</el-button>
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
          <li class="societyList" v-for="(item,index) in newsArr">
            <span @click="toRouter('/detailNews',item.id)">{{index+1}}</span>
            <span @click="toRouter('/detailNews',item.id)">{{item.Numbering}}</span>
            <span @click="toRouter('/detailNews',item.id)">{{item.name}}</span>
            <span @click="toRouter('/detailNews',item.id)">{{item.society}}</span>
            <span @click="toRouter('/detailNews',item.id)">{{item.applicant}}</span>
            <span @click="toRouter('/detailNews',item.id)">{{item.starTime}}</span>
            <span class="agreetBtn" @click="toRouter('/detailNews',item.id)"
                  v-show="item.status==1">已发送</span>
            <span class="refuseBtn" @click="toRouter('/detailNews',item.id)"
                  v-show="item.status==2">草稿箱</span>
            <div>
             <span class="refuseBtn" @click="delNew(index)"
                   v-show="item.status==1">删除</span>
              <span class="delBtn" @click="sendAgainNew(index)"
                    v-show="item.status==2">重发</span>
            </div>
          </li>
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
        associationList: []
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
      getList(val, userId){
        this.associationList = [];
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json = {
          associationId: this.associationId,
          start: val,
          userId: userId
        };
        this.$http.post(this.url, json).then(
          (success) => {
            var response = success.data;
           console.log(response);
        if (response.msg == 666) {
          this.totalNum = parseInt(response.listCount);
          if (response.assoEventList.length == 0) {
            this.showNo = true
          } else {
            this.showNo = false;
            for (var i = 0; i < response.assoEventList.length; i++) {
              if (this.userRole == 1) {
                this.assoEventList.push(response.assoEventList[i]);
              } else {
                this.assoEventList.push(response.assoEventList[i]);
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
        console.log(`当前页: ${val}`);
      },
      searchItem(){


      },
      /*退出社团*/
      toRouter(myRouter, id){
        this.$router.push({path: myRouter, query: {'id': id}})
      },
      delNew(index) {
        this.$confirm('是否删除该新闻?', '提示', {
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
      sendAgainNew(index) {
        this.$confirm('是否需要编辑再重新发送?', '提示', {
          confirmButtonText: '直接发送',
          cancelButtonText: '编辑',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.toRouter('/addNews', '14145')
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
