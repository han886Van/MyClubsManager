<template>
  <div class="society">
    <div class="bgc">

    </div>
  </div>
</template>

<script>
  export default {
    name: '',
    components: {},
    data () {
      return {


      }
    },
    methods: {
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
    },

    mounted(){

    },
    watch: {

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
    margin-bottom :50px;
    padding: 40px;

  }

</style>
