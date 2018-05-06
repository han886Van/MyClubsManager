<template>
  <div class="left_nav">
    <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      router=true
      background-color="#233445"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="mySpace">
        <i class="iconfont icon-geren"></i>
        <span slot="title" class="i_word">个人中心</span>
      </el-menu-item>
      <!--多选-->
      <el-submenu v-show="myRole==1"  index="society?myRouter=2">
        <template slot="title">
          <i class="iconfont icon-shangwuzhantubiaoku__jishutuandui"></i>
          <span class="i_word">社团管理</span>
        </template>
        <el-menu-item index="society?myRouter=1" class="li_son">
          <span slot="title" class="i_word">已进社团</span>
        </el-menu-item>
        <el-menu-item index="society?myRouter=2" class="li_son">
          <span slot="title" class="i_word">全部社团</span>
        </el-menu-item>
        <el-menu-item  index="society?myRouter=3" class="li_son">
          <span slot="title" class="i_word">管理社团</span>
        </el-menu-item>
        <el-menu-item  index="society?myRouter=4" class="li_son">
          <span slot="title" class="i_word">申请记录</span>
        </el-menu-item>
      </el-submenu>
      <!--教师-->
      <el-submenu v-show="myRole==2"  index="society?myRouter=7">
        <template slot="title">
          <i class="iconfont icon-shangwuzhantubiaoku__jishutuandui"></i>
          <span class="i_word">社团管理</span>
        </template>
        <el-menu-item index="society?myRouter=4" class="li_son">
          <span slot="title" class="i_word">已加入社团</span>
        </el-menu-item>
        <el-menu-item index="society?myRouter=5" class="li_son">
          <span slot="title" class="i_word">已加入社团</span>
        </el-menu-item>
      </el-submenu>
      <el-submenu v-show="myRole==3"  index="society?myRouter=8">
        <template slot="title">
          <i class="iconfont icon-shangwuzhantubiaoku__jishutuandui"></i>
          <span class="i_word">社团管理</span>
        </template>
        <el-menu-item index="society?myRouter=4" class="li_son">
          <span slot="title" class="i_word">已加入社团</span>
        </el-menu-item>
        <el-menu-item index="society?myRouter=5" class="li_son">
          <span slot="title" class="i_word">已加入社团</span>
        </el-menu-item>
      </el-submenu>
      <el-menu-item index="member">
        <i class="iconfont icon-yuangongliebiao"></i>
        <span slot="title" class="i_word">成员管理</span>
      </el-menu-item>
      <el-menu-item index="materials">
        <i class="iconfont icon-cailiaozhengli"></i>
        <span slot="title" class="i_word">物质管理</span>
      </el-menu-item>
      <el-menu-item index="activity">
        <i class="iconfont icon-huodong"></i>
        <span slot="title" class="i_word">活动管理</span>
      </el-menu-item>
      <el-menu-item index="news">
        <i class="iconfont icon-shangwutubiao-"></i>
        <span slot="title" class="i_word">新闻管理</span>
      </el-menu-item>
      <el-menu-item index="discussion">
        <i class="iconfont icon-icon"></i>
        <span slot="title" class="i_word_sp">论坛</span>
      </el-menu-item>
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
