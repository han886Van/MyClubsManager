<template>
  <div class="discussion">
    <el-tabs v-model="activeName" >
      <el-tab-pane label="新增帖子" name="first">
        <ul class="dis_box">
          <li>
            <div style="display: flex; justify-content: space-between;align-items: center;">
              <span>发帖：</span>
              <el-input
                type="textarea"
                resize="none"
                placeholder="请输入内容"
                v-model="textarea">
              </el-input>
            </div>
          <div class="imgBox">
            <el-upload
              action="http://localhost:8080/associationMg/attachment/uploadFile"
              list-type="picture-card"
              :limit=9
              :on-preview="handlePictureCardPreview"
              :on-success="handlSuccess"
              :on-exceed="handleExceed"
              :on-remove="handleRemove">
              <i class="el-icon-plus"></i>
            </el-upload>
          </div>
            <div class="send_btn">
              <el-button  @click="sendDis" type="primary">发帖</el-button>
            </div>
          </li>
        </ul>
      </el-tab-pane>
      <el-tab-pane label="社团论坛" name="second">
        <ul class="dis_box">
          <li class="top"></li>
          <li v-for="(item,index) in forumList">
            <div class="dis_user flex_box">
              <img :src="item.masterPic" alt="">
              <div>
                <span>{{item.master}}</span>
                <span class="span_mar_top">{{item.time}}</span>
              </div>
            </div>
            <span>{{item.content}}</span>
            <div class="dis_img" v-show="item.images">
              <img  v-for="img in item.images" :src="img" alt="">
            </div>
            <div class="edit_btn">
              <i class="iconfont icon-dianzan orange" @click="isLike(index)"
                 :class="{'orange_color':item.like}"></i><span v-show="item.like_total!=0" :class="{'orange_color':item.like}">{{item.like_total}}</span>
              <i class="iconfont icon-pinglun orange" @click="addDis(index)"></i>
              <span>12</span>
              <el-button type="text" @click="deletDis" v-show="item.is_own==1"><i
                class="iconfont icon-shanchu orange"></i>
              </el-button>
            </div>
            <div class="dis_detail flex_box">
              <img src="../../assets/img/bg2.jpg" alt="">
              <div>
                <span>小可爱：lallalalallalalalallalal</span>
                <span class="span_mar_top">2017.08.11&nbsp;&nbsp;&nbsp;12:00
               <i class="iconfont icon-dianzan orange" @click="isLike(index)"
                  :class="{'orange_color':item.like}"></i><span :class="{'orange_color':item.like}"
                                                                class="dis_detail_i">123</span>
            <i class="iconfont icon-pinglun orange" @click="addDis(index)"></i>
                  <span class="dis_detail_i">123</span>
            </span>
              </div>
            </div>
          </li>
          <li v-show="showNo" class="noList">暂无帖子</li>
        </ul>
        <div class="myPagination">
          <div>
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-size="10"
              layout="total, prev, pager, next, jumper"
              :total="listCount">
            </el-pagination>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
    <!--添加评论-->
    <el-dialog
      title="提示"
      :visible.sync="centerDialogVisible"
      width="30%"
      center>
      <span>需要注意的是内容是默认不居中的</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: '',
    components: {},
    data () {
      return {
        activeName: 'second',
        centerDialogVisible: false,//添加评论模块
        textarea: '',//发帖子内容
        dianzanIndex: -1,
        value1: '',
        disList: [
          {
            isAccount: true,//1 可删除 0 不可删除
            master: '小仙女',
            masterPic: require('../../assets/img/home1.jpg'),
            innerPic: require('../../assets/img/bg2.jpg'),
            time: '昨天 12:00',
            speech: '评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论',
            like: true,
            visitor: [{
              name: '小可爱}],',
              toAnswer: '小仙女',
              visitorPic: require('../../assets/img/bg2.jpg'),
              visitor: 'lallalalallalalalallalal',
              year: '2017.08.11',
              time: '12:00'
            }, {
              name: '小可爱}],',
              toAnswer: '小仙女',
              visitorPic: require('../../assets/img/bg2.jpg'),
              visitor: 'lallalalallalalalallalal',
              year: '2017.08.11',
              time: '12:00'
            }, {
              name: '小可爱}],',
              toAnswer: '小仙女',
              visitorPic: require('../../assets/img/bg2.jpg'),
              visitor: 'lallalalallalalalallalal',
              year: '2017.08.11',
              time: '12:00'
            }],
          },
          {
            isAccount: false,//1 可删除 0 不可删除
            master: '小仙女',
            masterPic: require('../../assets/img/home1.jpg'),
            innerPic: '',
            time: '昨天 12:00',
            speech: '评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论评论',
            like: false,
          },
        ],
        currentPage: 1,
        forumList:[],
        listCount:1,
        showNo:false,
        briefIntroduction:'',
        dialogImageUrl: '',
        dialogVisible: false,
        headImgArr:[],
      }
    },
    mounted(){

    },
    created() {
      this.createFunc()
    },
    methods: {
      createFunc(){
        this.userId = localStorage.getItem('userId');
        this.url = this.localhost + 'associationMg/forum/getForums';
        this.getList(1)
      },
      getList(val){
        this.forumList = [];
        var images;
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json = {
          start: val,
          userId:this.userId
        };
       /* if(state){
          json.state=state
        }*/

        this.$http.post(this.url, json).then(
          (success) => {
          var response = success.data;
          console.log(response);
          if (response.msg == 666) {
            this.listCount = parseInt(response.listCount);
            if (response.forumList.length == 0) {
              this.showNo = true
            } else {
              this.showNo = false;
              for (var i = 0; i < response.forumList.length; i++) {
               if(response.forumList[i].imgs!='0'){
                 images= response.forumList[i].imgs.split(",")
               }else {
                 images=''
               }
                var obj={
                  content:response.forumList[i].content,
                  id:response.forumList[i].id,
                  images:images,
                  is_own:response.forumList[i].is_own,
                  like_total:response.forumList[i].like_total,
                  reply_id:response.forumList[i].reply_id,
                  time:response.forumList[i].time,
                  user_id:response.forumList[i].user_id,
                  user_name:response.forumList[i].user_name,
                };
                this.forumList.push(obj);
              }
              console.log(this.forumList);
            }
          } else {
            this.showNo = true
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
        });
        setTimeout(() => {
          loading.close();
      }, 500);
      },
      /*分页器*/
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
      this.getList(val)
      },
      handleRemove(file, fileList) {
        this.headImgArr=[];
        for(var i =0;i<fileList.length;i++){
          this.headImgArr.push(fileList[i].response.headImg);
          console.log(this.headImgArr,'handleRemove');
        }
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      handlSuccess(response, file, fileList){
        this.headImgArr=[];
        for(var i =0;i<fileList.length;i++){
          this.headImgArr.push(fileList[i].response.headImg);
          console.log(this.headImgArr,'handlSuccess');
        }
      },
      handleExceed(){
        this.$message.error('抱歉，最多只能传9张图片');
      },
      /*发送帖子*/
      sendDis() {
        console.log('发送帖子');
        this.$message({
          message: '恭喜你，发帖成功',
          type: 'success'
        });
        //发送错误
        /* this.$message.error('错了哦，这是一条错误消息');*/
      },
      //删除评论
      deletDis() {
        console.log('删除评论');
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
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
      //取消或者点赞
      isLike(index){
        this.disList[index].like = !this.disList[index].like;
      },
      addDis(index) {
        this.$prompt('请输评论信息', '评论', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputErrorMessage: '评论不能为空',
          inputPattern: /\S/,
        }).then(({value}) => {
          var reg = /^\s*$/g;
          this.$message({
            type: 'success',
            message: '已发表评论'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
      },
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
  .discussion {
    margin-left: 90px;
    box-sizing border-box;
    background: #fff;
    padding: 10px 20px;
    margin-top: 50px;
    border-radius: 8px;
    .iconfont {
      cursor: pointer;
    }
    .dis_box {
      min-height:340px;
      li {
        width: 1024px
        padding: 20px;
        margin-top: 20px;
        font-size: 16px;
        border-bottom: 1px solid #ccc;
      }

      li:nth-last-child(1) {
        border-bottom: none;
      }
      li:first-child {
        border-bottom: none;
      }
    }
    .dis_user {
      margin-bottom: 10px;
      img {
        width: 50px;
        height: 50px;
        border-radius: 50%;
      }
      div {
        margin-left: 10px;
        display: inline-block;
      }
      span {
        font-size: 14px;
        display: block;
      }
    }
    .dis_img {
      margin: 10px 0;
      img {
        width: 100px;
        height: 100px;
      }
    }
    .edit_btn {
      text-align: right
      i {
        margin-left: 20px;
        font-size: 20px;

      }
      .icon-dianzan {
        font-size: 24px;
      }
      .icon-shanchu {
        font-size: 22px;
      }

    }
    .dis_detail {
      background-color: #efefef
      margin-top: 20px;
      padding: 10px;
      div {
        display: inline-block;
        font-size: 16px;
      }
      img {
        width: 40px;
        height: 40px;
        border-radius 50%;
        margin-right: 6px;
      }
      span {
        font-size: 14px;
        display: block;
      }
      .dis_detail_i {
        display: inline-block;
        font-size: 12px;
      }
      .icon-pinglun {
        font-size: 16px;
      }
    }
    .send_btn {
      float: right;
    }
    .noList{
      font-size: 22px;
      text-align: center
      justify-content :center !important;
    }
    .imgBox{
      margin-left:60px;
    }
  }
</style>
