<template>
  <div class="society">
    <div class="bgc">
      <!--社团成员-->
      <div class="member" v-show="userRole==1">
        <div class="btn">
          <el-button type="success" @click="showAll=1">&nbsp;全部社团&nbsp;</el-button>
          <el-button type="primary" @click="showAll=2">已加入社团</el-button>
          <el-button type="warning" @click="showAll=3">申请加入中</el-button>
          <el-button type="danger"  @click="showAll=4">申请退出中</el-button>
        </div>
        <ul class="list">
          <li>
            <div>社团图片</div>
            <span>社团编号</span>
            <span>社团名</span>
            <span>管理员</span>
            <span>操作</span>
          </li>
          <li v-show="showAll==1" class="societyList" v-for="(item,index) in hadArr">
            <div><img :src=item.imgUrl alt=""></div>
            <span>{{item.societyId}}</span>
            <span>{{item.societyName}}</span>
            <span>{{item.societyManage}}</span>
            <span @click="outSociety(1,index)" class="out_span">退出</span>
          </li>
          <li v-show="showAll==2" class="societyList" v-for="(item,index) in allArr">
            <div><img :src=item.imgUrl alt=""></div>
            <span>{{item.societyId}}</span>
            <span>{{item.societyName}}</span>
            <span>{{item.societyManage}}</span>
            <span @click="outSociety(1,index)" class="out_span" v-show="!item.isIn">退出</span>
            <span @click="outSociety(0,index)" class="in_span" v-show="item.isIn">加入</span>
          </li>
          <li v-show="showAll==3" class="societyList" v-for="(item,index) in addArr">
            <div><img :src=item.imgUrl alt=""></div>
            <span>{{item.societyId}}</span>
            <span>{{item.societyName}}</span>
            <span>{{item.societyManage}}</span>
            <span @click="editSociety(1,index)" class="out_span">取消</span>
          </li>
          <li v-show="showAll==4" class="societyList" v-for="(item,index) in outArr">
            <div><img :src=item.imgUrl alt=""></div>
            <span>{{item.societyId}}</span>
            <span>{{item.societyName}}</span>
            <span>{{item.societyManage}}</span>
            <span @click="editSociety(0,index)" class="out_span">取消</span>
          </li>
        </ul>
      </div>
      <!--社团管理员-->
      <div class="manage_menber" v-show="userRole==2">
        社团管理员
      </div>
      <div class="manage_menber" v-show="userRole==3">
        教师
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
        userRole:'',
        showAll: '1',
        hadArr: [
          {imgUrl: require('../../assets/img/home1.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团'},
          {imgUrl: require('../../assets/img/home1.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团'},
          {imgUrl: require('../../assets/img/home1.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团'},
          {imgUrl: require('../../assets/img/home1.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团'},
          {imgUrl: require('../../assets/img/home1.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团'},
          {imgUrl: require('../../assets/img/home1.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团'},
        ],
        allArr: [
          {imgUrl: require('../../assets/img/bg1.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 1},
          {imgUrl: require('../../assets/img/bg1.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 0},
          {imgUrl: require('../../assets/img/bg1.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 1},
          {imgUrl: require('../../assets/img/bg1.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 1},
          {imgUrl: require('../../assets/img/bg1.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 0},
          {imgUrl: require('../../assets/img/bg1.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 1},
        ],
        addArr:[
          {imgUrl: require('../../assets/img/bg2.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 1},
          {imgUrl: require('../../assets/img/bg2.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 0},
          {imgUrl: require('../../assets/img/bg2.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 1},
          {imgUrl: require('../../assets/img/bg2.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 1},
          {imgUrl: require('../../assets/img/bg2.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 0},
          {imgUrl: require('../../assets/img/bg2.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 1},
        ],
        outArr:[
          {imgUrl: require('../../assets/img/bg6.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 1},
          {imgUrl: require('../../assets/img/bg6.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 0},
          {imgUrl: require('../../assets/img/bg6.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 1},
          {imgUrl: require('../../assets/img/bg6.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 1},
          {imgUrl: require('../../assets/img/bg6.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 0},
          {imgUrl: require('../../assets/img/bg6.jpg'), societyId: '001',societyManage:'小可爱', societyName: '摄影社团', isIn: 1},
        ],
      }
    },
    methods: {
      /*退出社团*/
      outSociety(operating, index) {
        /*operating 1 退出 0 加入*/
        var confirmWord = '';
        if (operating) {
          confirmWord = '是否退出此社团?'
        } else {
          confirmWord = '是否加入此社团?'
        }
        this.$confirm(confirmWord, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          /*发送请求*/
          this.$message({
            type: 'success',
            message: '申请发送成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '正在审核'
          });
        });
      },
      /*取消申请、退出*/
      editSociety(operating, index){
        /*operating 1 取消申请加入 0 取消申请退出*/
        var confirmWord = '';
        if (operating) {
          confirmWord = '是否取消申请加入社团?'
        } else {
          confirmWord = '是否取消申请退出社团?'
        }
        this.$confirm(confirmWord, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          /*发送请求*/
          this.$message({
            type: 'success',
            message: '申请发送成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '正在审核'
          });
        });
      }
    },

    mounted(){
      this.userRole = localStorage.getItem('userRole')
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .society {
    box-sizing border-box;
    padding: 80px 100px;
    /*margin-left: 100px;*/
    width: 1264px;
    overflow hidden
    .bgc {
      border-radius: 8px;
      background-color: #fff;
    }
    .btn {
      padding: 40px 40px 20px;
      display: flex;
      align-items: center;
      justify-content:space-around;
    }
    .list {
      border-radius: 8px;
      padding: 20px 50px 40px;
      box-sizing border-box;
      width: 100%;
      background-color: #fff;
      min-height: 600px;
      li {
        padding: 4px 0;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-bottom: 1px solid #989898;
        color: #333;
        div {
          line-height: 40px;
          text-align: center
          display: flex;
          justify-content: center;
          align-items: center;
          width: 180px;
          font-size: 18px
          img {
            width: 120px;
            height: 80px;
          }
        }
        span {
          font-size: 18px;
          line-height: 40px;
          width: 200px;
          text-align: center;
          display: inline-block;
          word-wrap: break-word;
        }
      }
      .societyList {
        color: #989898;
      }
    }

    .out_span, in_span {
      cursor: pointer;
    }
    .out_span:hover {
      color: #f56c6c
    }

    .in_span:hover {
      color: #409eff
    }

  }

</style>
