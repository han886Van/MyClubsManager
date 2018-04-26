<template>
  <div class="manage">
    <div class="bgc_manage"></div>
    <p>欢迎进入社团管理系统!</p>
  </div>
</template>

<script>
  export default {
    name: '',
    components: {

    },
    data () {
      return {

      }
    },
    mounted(){

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .manage{
    overflow:hidden
    width: 90%
    height: 100%
    .bgc_manage{
      width: 100%
      height: 100%
      position: absolute;
      background: url("../assets/img/bg2.jpg") no-repeat;
      background-position: center;
      background-size: 100% 100%;
      z-index: -11;
    }
      p{
        color: #fff;
        font-size: 40px;
        text-align: center;
        margin-top:200px;
      }
  }
</style>
