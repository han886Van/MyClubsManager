<template>
  <div class="society">
    <div class="bgc">
      <div class="top">
        <div class="title">社团详情</div>
        <div class="title_show">
          <img src="../../assets/img/bg6.jpg" alt="">
          <div>
            <p v-for="(item,index) in societuItem.basicInfo">
              <span>{{item.name}}</span>
              <span>{{item.value}}</span>
            </p>
          </div>
          <div>
            <p v-for="item in societuItem.member">
              <span>{{item.name}}：</span>
              <span>{{item.value}}</span>
            </p>
          </div>
        </div>
      </div>
      <div class="banner">
        <el-carousel trigger="click" height="400px">
          <el-carousel-item v-for="(item,index) in societuItem.imgUrl" :key="index">
            <img :src=item.itemUrl alt="">
          </el-carousel-item>
        </el-carousel>
      </div>
      <ul class="conten">
        <li class="intro" v-for="(item,index) in societuItem.ortherInfo">
            <span>{{item.name}}</span>
            <p>{{item.value}}</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: '',
    components: {},
    data () {
      return {
        societyId: '',
        societuItem: {
          basicInfo: [{name: '社团名字：', value: '跆拳道社团'},
            {name: '社团ID：', value: '0001'},
            {name: '社长：', value: '陈小黄'},
            {name: '创立时间：', value: '2018-11-8'},
            {name: '社团分类：', value: '体育健身类'},
            {name: '专用场地：', value: '地下室'},
            {name: '总人数：', value: '跆拳道社团'},
          ],
          ortherInfo:[
              {name:'社团简介：',value:'UTALK 友谈协会，下方是一个笑脸，表明我们社团十分有爱，是一个很欢乐的大家庭，同时笑脸里面表明了社团名称和社团创建时间。最后是我们社团的口号，激励大家积极勇敢说英语。'},
              {name:'社团职能：',value:'UTALK 友谈协会，下方是一个笑脸，表明我们社团十分有爱，是一个很欢乐的大家庭，同时笑脸里面表明了社团名称和社团创建时间。最后是我们社团的口号，激励大家积极勇敢说英语。'},
            {name:'社团口号：',value:'UTALK 友谈协会，下方是一个笑脸，表明我们社团十分有爱，是一个很欢乐的大家庭，同时笑脸里面表明了社团名称和社团创建时间。最后是我们社团的口号，激励大家积极勇敢说英语。'},

              ],
          member:[
            {name:'社长',value:'小可爱'},
            {name:'副社长',value:'小可爱'},
            {name:'外联部',value:'小可爱 小可爱'},
            {name:'活动部',value:'小可爱 小可爱'},
            {name:'组织部',value:'小可爱 小可爱'},

          ],
          imgUrl:[
            {itemUrl:require('../../assets/img/home1.jpg')},
            {itemUrl:require('../../assets/img/home2.jpg')},
            {itemUrl:require('../../assets/img/home3.jpg')},
            {itemUrl:require('../../assets/img/home4.jpg')},
            {itemUrl:require('../../assets/img/home3.jpg')},
          ]
        },

      }
    },
    methods: {
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
    },

    mounted(){
      this.societyId = this.$route.query.societyId;

    },
    watch: {
      $route(){
        this.societyId = this.$route.query.societyId;
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
    margin-bottom :50px;
    /*padding: 40px;*/
    .bgc {
      border-radius: 8px;
      background-color: #fff;
    }
    .top {
      padding: 40px;
      background-color: #f2f5f4
    }
    .title {
      font-size: 24px;
      margin-bottom: 40px;
    }
    .title_show {
      display: flex;
      justify-content: flex-start;
      align-items: flex-start;
      img {
        width: 200px;
        height: 200px;
      }
      div {
        margin-left: 60px;
        margin-top: 10px;
        display: inline-block;
        font-size: 18px;
        p {
          margin-top: 10px;
          span{
            display: inline-block;
            min-width :90px;
          }
        }
      }
    }
      .banner{
        padding: 20px 50px;
        width: 90%;
        img{
          width: 100%;
          height: 100%;
        }
      }
    .conten {
      padding: 40px;
    }
    .intro {
      p {
        margin-top: 20px;
        margin-bottom :40px;
      }
    }
    .intro_title{
        margin-bottom:20px;
      }
  }

</style>
