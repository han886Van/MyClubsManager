<template>
  <div id="app">
    <my-header></my-header>
    <left-nav v-show="isShow" class="my_left_nav"></left-nav>
    <router-view class="my_router" />
  </div>
</template>

<script>
    import myHeader from './components/public/header'
    import leftNav from './components/public/left_nav'
    import {mapGetters, mapMutations} from 'vuex'
    export default {
        name: 'app',
        computed: {
          ...mapGetters({
            isShow: 'showNav'
          })
        },
        components: {
            'my-header': myHeader,
            'left-nav': leftNav,
        },
        data() {
            return {
            }
        },
        mounted:function(){
          if(this.$route.path=='/home'||this.$route.path=='/'||this.$route.path=='/register'){
            this.SET_SHOW_NAV(false);
          }else {
            this.SET_SHOW_NAV(true);
          }
        },
        methods: {
          ...mapMutations([
            'SET_SHOW_NAV',
          ]),
        },
        watch:{
          $route(){
            if(this.$route.path=='/home'||this.$route.path=='/'||this.$route.path=='/register'){
              this.SET_SHOW_NAV(false);
            }else {
              this.SET_SHOW_NAV(true);
            }
          }
        }
    }
</script>
<style scoped lang="stylus" rel="stylesheet/stylus">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  width: 100%;
  height: 100%;
  overflow hidden;
  box-sizing border-box;
  background: rgba(99,99,99,0.1);
  .my_router,.my_left_nav{
    float :left;
  }
    .my_left_nav{
      display: inline-block;
    }
}
</style>
