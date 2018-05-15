<template>
  <div class="society">
    <div class="bgc">
      <!--学生-->
      <div class="top" v-show="userRole==1">
        <span>社团管理</span>
        <span>	&gt;</span>
        <span v-show="showAll==2" class="blue">全部社团</span>
        <span v-show="showAll==5" class="blue">全部社团</span>
        <span v-show="showAll==1" class="blue">已进社团</span>
        <span v-show="showAll==3" class="blue">管理社团</span>
        <span v-show="showAll==4" class="blue">申请记录</span>
      </div>
      <div class="top" v-show="userRole==2">
        <span>社团管理</span>
        <span>	&gt;</span>
        <span v-show="showAll==1" class="blue">全部社团</span>
        <span v-show="showAll==3" class="blue">全部社团</span>
        <span v-show="showAll==2" class="blue">申请社团</span>
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
          <span>社团编号：</span>
          <el-input v-model="idInput" placeholder="请输入内容"></el-input>
        </div>
        <div>
          <span>社团名字：</span>
          <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
        </div>
        <el-button @click="searchItem()" type="info" plain>搜索</el-button>
        <el-button @click="toRouter('/addSociety')" type="primary">创建社团</el-button>
      </div>
      <!--学生-->
      <div class="member" v-show="userRole==1">
        <div class="title">
          <span>序号</span>
          <span>编号</span>
          <span>分类</span>
          <span>名字</span>
          <span>社长</span>
          <span>地点</span>
          <span>人数</span>
          <span>操作</span>
        </div>
        <ul class="list">
          <li class="societyList" v-for="(item,index) in hadArr">
            <span @click="toRouter('/societyDetails',item.societyId)">{{index+1}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.societyId}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==1">专业学术类</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==2">科技创新类</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==3">艺术兴趣类</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==4">体育健身类</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==5">公益服务类</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.societyName}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.societyManage}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.adress}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.num}}</span>
            <div v-show="showAll==1">
              <span @click="outSociety(index)" class="red_color">退出</span>
              <!--<el-button  type="danger">退出</el-button>-->
            </div>
            <div v-show="showAll==2">
              <span @click="outSociety(index)" v-show="item.status==1" class="red_color">退出</span>
              <span  @click="enterSociety(index)" v-show="item.status==2" class="blue">加入</span>
            </div>
            <div v-show="showAll==5">
              <span @click="outSociety(index)" v-show="item.status==1" class="red_color">退出</span>
              <span  @click="enterSociety(index)" v-show="item.status==2" class="blue">加入</span>
            </div>
            <div v-show="showAll==3">
              <span  @click="editSociety(index)" class="green_color">编辑</span>
            </div>
            <div v-show="showAll==4">
              <span  @click="cancelOperating(1,index)" v-show="item.status==1">取消加入</span>
              <span @click="cancelOperating(2,index)" v-show="item.status==2" class="green_color">取消退出</span>
              <!--<el-button  type="success" plain>取消退出</el-button>-->
            </div>
          </li>
        </ul>
      </div>
      <!--教师-->
      <div class="manage_menber" v-show="userRole==2">
        <div class="title">
          <span>序号</span>
          <span>编号</span>
          <span>分类</span>
          <span>名字</span>
          <span>社长</span>
          <span>地点</span>
          <span>人数</span>
          <span>操作</span>
        </div>
        <ul class="list">
          <li class="societyList" v-for="(item,index) in hadArr">
            <span @click="toRouter('/societyDetails',item.societyId)">{{index+1}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.societyId}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==1">专业学术类</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==2">科技创新类</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==3">艺术兴趣类</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==4">体育健身类</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==5">公益服务类</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.societyName}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.societyManage}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.adress}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.num}}</span>
            <div v-show="showAll==1">
              <span class="blue">编辑</span>
              <span class="red_color">删除</span>
            </div>
            <div class="red_color" v-show="showAll==3">
              <span class="blue">编辑</span>
              <span class="red_color">删除</span>
            </div>
            <div v-show="showAll==2">
              <el-button @click="outSociety(index)" v-show="item.status==1" type="danger">退出</el-button>
              <el-button @click="enterSociety(index)" v-show="item.status==2" type="primary">加入</el-button>
            </div>
          </li>
        </ul>
      </div>
      <!--管理员-->
      <div class="manage_menber" v-show="userRole==3">
        管理员
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
        userRole: '',
        showAll: '1',
        /*已加入社团*/
        hadArr: [
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            societyId: '001',
            societyManage: '小可爱',
            societyName: '摄影社团',
            adress: '操场',
            num: 55,
            sort: 1,
            status: 1
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            societyId: '001',
            societyManage: '小可爱',
            societyName: '摄影社团',
            adress: '操场',
            num: 55,
            sort: 2,
            status: 1
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            societyId: '001',
            societyManage: '小可爱',
            societyName: '摄影社团',
            adress: '操场',
            num: 55,
            sort: 3,
            status: 2
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            societyId: '001',
            societyManage: '小可爱',
            societyName: '摄影社团',
            adress: '操场',
            num: 55,
            sort: 4,
            status: 1
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            societyId: '001',
            societyManage: '小可爱',
            societyName: '摄影社团',
            adress: '操场',
            num: 55,
            sort: 5,
            status: 2
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            societyId: '001',
            societyManage: '小可爱',
            societyName: '摄影社团',
            adress: '操场',
            num: 55,
            sort: 1,
            status: 1
          },
        ],
        myRouter: '',
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
      /*退出社团*/
      outSociety(index) {
        this.$prompt('请输退出理由', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          center: true,
          /*          inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,*/
          inputErrorMessage: '邮箱格式不正确'
        }).then(({ value }) => {
          console.log(value);
          this.$message({
            type: 'success',
            message: '已发送申请'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
      },
      /*加入社团*/
      enterSociety(index){
        this.$prompt('请输加入理由', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          center: true,
/*          inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,*/
          inputErrorMessage: '邮箱格式不正确'
        }).then(({ value }) => {
          console.log(value);
          this.$message({
            type: 'success',
            message: '已发送申请'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
        console.log('加入请求' + index);
/*        this.$confirm('是否确定加入社团?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '请求发送成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '请求发送失败'
          });
        });*/
      },
      /*编辑社团*/
      editSociety(index){
        console.log('管理社团' + index);
      },
      /*取消操作*/
      /*operating 0 取消加入 1取消退出*/
      cancelOperating(operating, index){
        console.log(operating, index);
        this.$confirm('是否取消操作?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '请求发送成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '请求发送失败'
          });
        });
      },
      toRouter(myRouter,societyId){
        this.$router.push({path: myRouter, query: {'societyId': societyId}})
      },
    },

    mounted(){
      /*社员 ：1 已加入社团 2 全部社团 3管理社团 4记录 管理员：  */
      this.showAll = this.$route.query.myRouter;
      this.userRole = localStorage.getItem('userRole');
    },
    watch: {
      $route(){
        this.sortSociety = '';
        this.showAll = this.$route.query.myRouter;
      }
    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .society {
    /*box-sizing border-box;*/
    /*padding: 80px 100px;*/
    margin-left: 80px;
    margin-top: 50px;
    overflow: hidden;
    width: 1024px;
    background: #fff;
    min-height: 600px;
    border-radius: 8px;
    padding:10px 40px 20px 40px;
    .bgc {
      border-radius: 8px;
      background-color: #fff;
    }
    .top{
        line-height:50px;
        font-size:16px;
        border-bottom :1px solid #ccc;
        margin-bottom:40px;
      }
    /*.list {
      border-radius: 8px;
      padding: 20px 50px 40px;
      box-sizing border-box;
      width: 100%;
      background-color: #fff;
      min-height: 600px;

    }*/
    .search_box {
      margin-bottom: 20px;
      div {
        display: inline-block;
        margin-right: 10px;
      }
    }
    .title {
      color: #333;
      display: flex;
      justify-content: space-between;
      align-items: center;
      line-height:50px;
      border-radius :8px 8px 0 0;
  span {
        text-align: center;
        width: 146px;
      }
    }
  }

</style>
