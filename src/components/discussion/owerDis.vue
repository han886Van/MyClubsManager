<template>
  <div class="discussion">
    <el-tabs v-model="activeName" >
      <el-tab-pane label="个人帖子" name="second">
        <ul class="dis_box">
          <li v-for="(item,index) in forumList">
            <div class="dis_user flex_box">
              <img v-show="item.headImg!=0"  :src="item.headImg" alt="">
              <img v-show="item.headImg==0"  src="../../assets/img/1.png" alt="">
              <div>
                <span>{{item.user_name}}</span>
                <span class="span_mar_top">{{item.time}}</span>
              </div>
            </div>
            <span>{{item.content}}</span>
            <div class="dis_img" v-show="item.images">
              <img  v-for="img in item.images" :src="img" alt="">
            </div>
            <div class="edit_btn">
              <i class="iconfont icon-dianzan orange" @click="oboutLike(item.is_like,item.id),item.is_like=!item.is_like"
                 :class="{'orange_color':item.is_like}"></i><span v-show="item.like_total!=0" :class="{'orange_color':item.is_like}">{{item.like_total}}</span>
              <i class="iconfont icon-pinglun orange" @click="addDis(item.id)"></i>
              <span>{{item.sonList.length}}</span>
              <el-button type="text" @click="deletDis(item.id)"  v-show="item.is_own==1"><i
                class="iconfont icon-shanchu orange"></i>
              </el-button>
            </div>
            <div class="dis_detail flex_box" v-for="(sonList,index) in item.sonList">
              <img v-show="sonList.headImg!=0"  :src="sonList.headImg" alt="">
              <img v-show="sonList.headImg==0"  src="../../assets/img/1.png" alt="">
              <div>
                <span>{{sonList.user_name}}：{{sonList.content}}</span>
                <span class="span_mar_top">{{sonList.time}}
               <i class="iconfont icon-dianzan orange"  :class="{'orange_color':sonList.is_like}" @click="oboutLike(sonList.is_like,sonList.id),sonList.is_like=!sonList.is_like"></i>
                  <span v-show="sonList.like_total!=0" :class="{'orange_color':item.is_like}" class="dis_detail_i">{{sonList.like_total}}</span>
                  <span v-show="sonList.is_own" class="iconfont orange dis_detail_i icon-shanchu" @click="deletDis(sonList.id)"></span>
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
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        this.forumList = [];
        var images;
        var json = {
          start: val,
          userId:this.userId,
          ownId:this.userId,
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
                    id:response.forumList[i].id,
                    content:response.forumList[i].content,
                    headImg:response.forumList[i].headImg,
                    sonList:response.forumList[i].sonList,
                    is_like:response.forumList[i].is_like,
                    is_own:response.forumList[i].is_own,
                    like_total:response.forumList[i].like_total,
                    reply_id:response.forumList[i].reply_id,
                    time:response.forumList[i].time,
                    user_id:response.forumList[i].user_id,
                    user_name:response.forumList[i].user_name,
                    images:images,
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
        this.getList(val);
        this.currentPage = val;
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
        var images = this.headImgArr.join(",");
        var content =this.textarea;
        console.log(this.textarea);
        if(!this.textarea){
          this.$message.error('错误，请输入帖子内容');
        }else {
          const loading = this.$loading({
            lock: true,
            text: '正在发送请求',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          });
          var url= this.localhost + 'associationMg/forum/saveOrUpdate';
          var  json={
            content:content,
            userId:this.userId,
          };
          if(images){
            json.images =images
          }
          this.$http.post(url, json).then(
            (success) => {
              var response = success.data;
              console.log(response);
              if (response.msg == 666) {
                this.$message({
                  showClose: true,
                  message: '帖子发送成功',
                  type: 'success'
                });
                this.textarea='';
                this.headImgArr=[];
                this.createFunc()
              } else {
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
        }
      },
      /*评论帖子*/
      addDis(replyId) {
        this.$prompt('请输评论信息', '评论', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputErrorMessage: '评论不能为空',
          inputPattern: /\S/,
        }).then(({value}) => {
          this.disdisDis(replyId,value);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
      },
      disdisDis(replyId,content) {
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var url= this.localhost + 'associationMg/forum/saveOrUpdate';
        var  json={
          content:content,
          userId:this.userId,
          replyId:replyId,
        };
        this.$http.post(url, json).then(
          (success) => {
            var response = success.data;
            console.log(response);
            if (response.msg == 666) {
              this.$message({
                showClose: true,
                message: '帖子评论成功',
                type: 'success'
              });
              this.createFunc()
            } else {
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
      },
      //删除帖子 id
      deletDis(id) {
        this.$confirm('是否删除该评论或帖子?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.postDel(id)
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      postDel(id){
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var url= this.localhost +'associationMg/forum/deleteForum';
        var json = {
          id: id,
        };
        this.$http.post(url, json).then(
          (success) => {
            var response = success.data;
            console.log(response);
            if (response.msg == 666) {
              this.$message({
                showClose: true,
                message: '删除成功',
                type: 'success'
              });
              this.createFunc()
            } else {
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
      },
      oboutLike(inLike,id){
        var url;
        var json;
        console.log(inLike);
        if(inLike){
          url= this.localhost +'associationMg/likeComments/cancelLike';
          json={
            commentsId:id,
          }
          this.postLike(url,json)
        }else {
          /*点赞*/
          url= this.localhost +'associationMg/likeComments/saveOrUpdate';
          json={
            commentsId:id,
            userId:this.userId
          }
          this.postLike(url,json)
        }

      },
      postLike(url,json){
        this.$http.post(url, json).then(
          (success) => {
            var response = success.data;
            console.log(response);
            if (response.msg == 666) {
              this.createFunc()
            } else {
              this.$message.error('错误，请求数据失败');
            }
          },
          (error) => {
            this.$message.error('错误，请求数据失败');
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
    .orange_color{
      color: orange;
    }
  }
</style>
