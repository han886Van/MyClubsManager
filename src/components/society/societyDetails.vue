<template>
  <div class="society">
    <div class="bgc">
      <div class="top">
        <div>
          <span>社团详情</span>
          <span>	&gt;</span>
          <span class="blue">{{detailAssociation.name}}</span>
        </div>
        <div>
          <span @click="goBack()" class="blue">返回</span>
        </div>
      </div>
      <div class="info">
        <div class="left_box">
          <img v-show="detailAssociation.headimg==0" src="../../assets/img/2.jpg" alt="">
          <img v-show="detailAssociation.headimg!=0" :src="detailAssociation.headimg" alt="">
        </div>
        <div class="right_box">
          <div class="left_item">
            <p><span>社团名字：</span><span>{{detailAssociation.name}}</span></p>
            <p ><span>社团ID：</span><span>{{detailAssociation.association_id}}</span></p>
            <p><span>总人数：</span><span>{{detailAssociation.total_person}}</span></p>
          </div>
          <div class="right_item">
            <p><span>社长：</span>
               <span>{{detailAssociation.user_name}}</span>
            </p>
            <p>
              <span>创立时间：</span>
              <span>{{detailAssociation.create_day}}</span>

            </p>
            <p>
              <span>社团分类：</span>
              <span>{{detailAssociation.type_name}}</span>
            </p>
            <p>
              <span>专用场地：</span>
              <span>{{detailAssociation.place}}</span>
            </p>
          </div>
        </div>
      </div>
      <!--<div class="banner">
        <el-carousel trigger="click" height="400px">
          <el-carousel-item v-for="(item,index) in societuItem.imgUrl" :key="index">
            <img :src=item.itemUrl alt="">
          </el-carousel-item>
        </el-carousel>
      </div>-->
      <ul class="conten">
        <li class="intro">
          <span>社团简介：</span>
          <p>{{detailAssociation.brief_introduction}}</p>
        </li>
        <li class="intro" v-show="detailAssociation.imgs!='0'">
          <span>社团图片：</span>
          <div class="intro_img">
            <el-carousel trigger="click" height="400px">
            <el-carousel-item v-for="item in imgs" :key="item">
              <img :src="item" alt="">
            </el-carousel-item>
          </el-carousel>
          </div>
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
        societuItem: {
          ortherInfo: [
            {
              name: '社团简介：',
              value: 'UTALK 友谈协会，下方是一个笑脸，表明我们社团十分有爱，是一个很欢乐的大家庭，同时笑脸里面表明了社团名称和社团创建时间。最后是我们社团的口号，激励大家积极勇敢说英语。'
            },
            {
              name: '社团职能：',
              value: 'UTALK 友谈协会，下方是一个笑脸，表明我们社团十分有爱，是一个很欢乐的大家庭，同时笑脸里面表明了社团名称和社团创建时间。最后是我们社团的口号，激励大家积极勇敢说英语。'
            },
            {
              name: '社团口号：',
              value: 'UTALK 友谈协会，下方是一个笑脸，表明我们社团十分有爱，是一个很欢乐的大家庭，同时笑脸里面表明了社团名称和社团创建时间。最后是我们社团的口号，激励大家积极勇敢说英语。'
            },

          ],
          member: [
            {name: '社长', value: '小可爱'},
            {name: '副社长', value: '小可爱'},
            {name: '外联部', value: '小可爱 小可爱'},
            {name: '活动部', value: '小可爱 小可爱'},
            {name: '组织部', value: '小可爱 小可爱'},

          ],
          imgUrl: [
            {itemUrl: require('../../assets/img/home1.jpg')},
            {itemUrl: require('../../assets/img/home2.jpg')},
            {itemUrl: require('../../assets/img/home3.jpg')},
            {itemUrl: require('../../assets/img/home4.jpg')},
            {itemUrl: require('../../assets/img/home3.jpg')},
          ]
        },
        userId: '',
        associationId: '',
        url: '',
        detailAssociation:'',
        imgs:''

      }
    },
    methods: {
      createFunc(){
        this.userId = localStorage.getItem('userId');
        this.associationId = this.$route.query.associationId;
        this.url = this.localhost + 'associationMg/association/getAssociationDetail';
        this.getDetails(this.url, this.associationId)
      },
      goBack(){
        this.$router.back(-1)
      },
      toRouter(myRouter){
        this.$router.push({path: myRouter})
      },
      getDetails(url, associationId){
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json = {
          associationId: associationId,
        };
        this.$http.post(url, json).then(
          (success) => {
            var response = success.data;
            if (response.msg == 666) {
                this.detailAssociation=response.detailAssociation;
                if(this.detailAssociation.imgs!='0'){
                  this.imgs = this.detailAssociation.imgs.split(",");
                }
            } else {
              this.$message.error('错误，社团详情请求数据失败');
              this.goBack()
          }
            setTimeout(() => {
              loading.close();
            }, 500);
          }, (error) => {
            setTimeout(() => {
              loading.close();
            }, 500);
            this.$message.error('错误，社团详情请求数据失败');
            this.goBack()
          });

      }
    },

    mounted(){


    },
    watch: {
      $route(){
        this.associationId = this.$route.query.associationId;
      }
    },
    created() {
      this.createFunc()
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
    width: 988px;
    background: #fff;
    min-height: 600px;
    border-radius: 8px;
    margin-bottom: 50px;
    padding:10px 40px 20px 40px;
    .bgc {
      border-radius: 8px;
      background-color: #fff;
    }
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
    .info {
      display: flex;
      justify-content: flex-start;
    }
    .left_box {
      display: inline-block;
      img {
        width: 140px;
        height: 140px;
      }
    }
    .right_box {
      display: flex;
      justify-content: flex-start;
      margin-left: 50px;
      p {
        display: inline-block;
        line-height: 50px;
        font-size: 18px;
        min-width: 408px;
        max-width: 408px;
        position: relative;
        span {
          display: inline-block;
          min-width: 90px;
        }
      }
    }
    .banner {
      padding: 20px 50px;
      width: 90%;
      img {
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
        margin-bottom: 40px;
      }
    }
    .intro_img{
        margin-top:20px;
      }
    .intro_title {
      margin-bottom: 20px;
    }
    .el-carousel__item img {
      width: 100%;
      height: 100%;
    }

    .el-carousel__item:nth-child(2n) {
      background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
      background-color: #d3dce6;
    }
  }

</style>
