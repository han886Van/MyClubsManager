<template>
  <div class="left_nav">
    <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      @select="handleSelect"
      @open="handleOpen"
      @close="handleClose"
      router:true
      background-color="#233445"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="mySpace">
        <i class="iconfont icon-geren"></i>
        <span slot="title" class="i_word">个人中心</span>
      </el-menu-item>
      <!--社团管理-->
      <el-submenu v-show="myRole==1"  index="society?myRouter=5">
        <template slot="title">
          <i class="iconfont icon-shangwuzhantubiaoku__jishutuandui"></i>
          <span class="i_word">社团管理</span>
        </template>
        <el-menu-item index="society?myRouter=2" class="li_son">
          <span slot="title" class="i_word">全部社团</span>
        </el-menu-item>
        <el-menu-item index="society?myRouter=1" class="li_son">
          <span slot="title" class="i_word">已进社团</span>
        </el-menu-item>
       <!-- <el-menu-item  index="society?myRouter=3" class="li_son">
          <span slot="title" class="i_word">管理社团</span>
        </el-menu-item>-->
        <el-menu-item  index="society?myRouter=4" class="li_son">
          <span slot="title" class="i_word">申请记录</span>
        </el-menu-item>
      </el-submenu>
      <!--教师-->
      <el-submenu v-show="myRole==2"  index="society?myRouter=1">
        <template slot="title">
          <i class="iconfont icon-shangwuzhantubiaoku__jishutuandui"></i>
          <span class="i_word">社团管理</span>
        </template>
        <el-menu-item index="society?myRouter=3" class="li_son">
          <span slot="title" class="i_word">全部社团</span>
        </el-menu-item>
        <el-menu-item index="society?myRouter=2" class="li_son">
          <span slot="title" class="i_word">社团申请</span>
        </el-menu-item>
        <el-menu-item index="society?myRouter=4" class="li_son">
          <span slot="title" class="i_word">申请记录</span>
        </el-menu-item>
      </el-submenu>
      <!--管理员-->
      <el-menu-item v-show="myRole==3" index="society">
        <i class="iconfont icon-shangwuzhantubiaoku__jishutuandui"></i>
        <span slot="title" class="i_word">社团管理</span>
      </el-menu-item>
      <!--成员管理-->
      <el-submenu index="inSociety" v-show="myRole!=3">
        <template slot="title">
          <i class="iconfont icon-yuangongliebiao"></i>
          <span class="i_word">成员管理</span>
        </template>
        <el-menu-item index="inSociety?myRouter=1" class="li_son">
          <span slot="title" class="i_word">社团成员</span>
        </el-menu-item>
        <el-menu-item index="applicatMember" class="li_son" v-show="myRole==1" >
          <span slot="title" class="i_word">申请社员</span>
        </el-menu-item>
      </el-submenu>
      <el-submenu index="student"  v-show="myRole==3">
        <template slot="title">
          <i class="iconfont icon-yuangongliebiao"></i>
          <span class="i_word">成员管理</span>
        </template>
        <el-menu-item index="student?myRouter=1" class="li_son">
          <span slot="title" class="i_word">学生</span>
        </el-menu-item>
        <el-menu-item index="teacher" class="li_son"  >
          <span slot="title" class="i_word">教师</span>
        </el-menu-item>
      </el-submenu>
      <!--物资管理-->
      <el-menu-item index="mSociety" v-show="myRole==1">
        <i class="iconfont icon-cailiaozhengli"></i>
        <span slot="title" class="i_word">物资管理</span>
      </el-menu-item>
      <el-submenu   index="materials?myRouter=1"  v-show="myRole==2">
        <template slot="title">
          <i class="iconfont icon-cailiaozhengli"></i>
          <span class="i_word">物质管理</span>
        </template>
        <el-menu-item index="materials" class="li_son">
          <span slot="title" class="i_word">申请记录</span>
        </el-menu-item>
        <el-menu-item index="appliMaterials" class="li_son">
          <span slot="title" class="i_word">物资申请</span>
        </el-menu-item>
      </el-submenu>
      <!--活动管理-->
      <el-menu-item index="inActivity" v-show="myRole==1">
        <i class="iconfont icon-huodong"></i>
        <span slot="title" class="i_word">活动管理</span>
      </el-menu-item>
      <el-submenu   index="activity?myRouter=1"  v-show="myRole==2">
        <template slot="title">
          <i class="iconfont icon-huodong"></i>
          <span class="i_word">活动管理</span>
        </template>
        <el-menu-item index="activity" class="li_son">
          <span slot="title" class="i_word">申请记录</span>
        </el-menu-item>
        <el-menu-item index="appliActivity" class="li_son">
          <span slot="title" class="i_word">活动申请</span>
        </el-menu-item>
      </el-submenu>
      <!--新闻-->
      <el-submenu  index="news">
        <template slot="title">
          <i class="iconfont icon-shangwutubiao-"></i>
          <span class="i_word">新闻管理</span>
        </template>
        <el-menu-item index="news?myRouter=1" class="li_son">
          <span slot="title" class="i_word">发送记录</span>
        </el-menu-item>
        <el-menu-item index="allNews" class="li_son">
          <span slot="title" class="i_word">全部新闻</span>
        </el-menu-item>
      </el-submenu>
      <el-submenu  index="discussion">
        <template slot="title">
          <i class="iconfont icon-icon"></i>
          <span class="i_word_sp">论坛</span>
        </template>
        <el-menu-item index="owerDis" class="li_son">
          <span slot="title" class="i_word">管理帖子</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
  import {mapGetters, mapMutations} from 'vuex'
  export default {
    name: '',
    data() {
      return {
        /*myRole:'',*/
        isHoverClass: false,
        hoverIndex: -1,
        leftList: [
          {className: 'icon-geren', linkRouter: '/mySpace', name: '个人中心'},
          {className: 'icon-shangwuzhantubiaoku__jishutuandui', linkRouter: '/society', name: '社团管理'},
          {className: 'icon-yuangongliebiao', linkRouter: '/member', name: '成员管理'},
          { className: 'icon-cailiaozhengli',linkRouter: '/materials',name: '物质管理' },
          {className: 'icon-huodong', linkRouter: '/activity', name: '活动管理'},
          {className: 'icon-shangwutubiao-', linkRouter: '/news', name: '新闻管理'},
          {className: 'icon-icon', linkRouter: '/discussion', name: '论坛'},
        ],
      }
    },
    watch: {

    },
    computed: {
      ...mapGetters({
        myRole: 'userRole'
      })
    },
    methods: {
        ...mapMutations([
            'USER_ROULE',
        ]),
      createFunc() {

      },
      goback(){
        window.history.go(-1);
      },
      handleSelect(key, keyPath){
        this.toRouter(key)
      },
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
      handleOpen(key, keyPath) {
        this.$router.push({path: key})
      },
      handleClose(key, keyPath) {
      }
    },
    mounted() {
        this.USER_ROULE(localStorage.getItem('userRole'));
     /* this.myRole = localStorage.getItem('userRole')
      console.log(localStorage.getItem('userRole')+'侧边栏的角色');*/
    },
    created() {
      this.createFunc()
    }
  }
</script>

<style scoped lang='stylus' rel='stylesheet/stylus'>
  .left_nav {
    width: 120px;
    height: 100%;
    background-color: #233445
    border-radius: 0 0 8px 8px;
    display: inline-block;
    min-height:600px;
    box-sizing: border-box;
    .iconfont {
      font-size: 28px;
      margin-top: 6px;
      display: block;
      margin-left: 26px;
    }
    .i_word {
      margin-left: 8px;
    }
    .i_word_sp{
        margin-left: 26px;
      }
    .li_son{
          padding:4px 20px !important;
    }
  }
</style>
