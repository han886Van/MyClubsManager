<template>
  <div class="discussion">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="新增帖子" name="first">
        <ul class="dis_box">
          <li>
            <div style="display: flex; justify-content: space-between;align-items: center;"><span>发帖：</span>
              <el-input
                type="textarea"
                resize="none"
                placeholder="请输入内容"
                v-model="textarea">
              </el-input>
            </div>
            <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :file-list="fileList2"
              list-type="picture">
              <el-button size="small" type="primary">上传图片</el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
            <div class="send_btn">
              <el-button  @click="sendDis" type="primary">发帖</el-button>
            </div>
          </li>
        </ul>
      </el-tab-pane>
      <el-tab-pane label="社团论坛" name="second">
        <ul class="dis_box">
          <!--本人发的可以删除-->
          <!--不是本人发的不可以删除-->
          <li class="top">
            <span>时间筛选：</span>
            <el-date-picker
              v-model="value1"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
            <el-button>搜索</el-button>
          </li>
          <li v-for="(item,index) in disList">
            <div class="dis_user flex_box">
              <img :src="item.masterPic" alt="">
              <div>
                <span>{{item.master}}</span>
                <span class="span_mar_top">{{item.time}}</span>
              </div>
            </div>
            <span>{{item.speech}}</span>
            <div class="dis_img"><img :src="item.innerPic" alt=""></div>
            <div class="edit_btn">
              <i class="iconfont icon-dianzan orange" @click="isLike(index)"
                 :class="{'orange_color':item.like}"></i><span :class="{'orange_color':item.like}">123</span>
              <i class="iconfont icon-pinglun orange" @click="addDis(index)"></i>
              <span>12</span>
              <el-button type="text" @click="deletDis" v-show="item.isAccount"><i
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
        </ul>
        <div class="myPagination">
          <div>
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-size="10"
              layout="total, prev, pager, next, jumper"
              :total="400">
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
        fileList2: [{
          name: 'food.jpeg',
          url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        }, {
          name: 'food2.jpeg',
          url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        },
          {name: 'bg.jpeg', url: require('../../assets/img/home1.jpg')}
        ],
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
        currentPage: 1
      }
    },
    mounted(){

    },
    methods: {
      /*分页器*/
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },
      handleClick(tab, event) {
        console.log(tab, event);
      },
      /*
       * 上传文件方法
       * */
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      /*
       * 上传文件方法
       * */
      handlePreview(file) {
        console.log(file);
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
          inputValidator(value){
            if (value == '') {
              return false

            } else {
              return true
            }
          },
        }).then(({value}) => {
          var reg = /^\s*$/g;
          if (reg.test(value)) {

            this.$message({
              type: 'success',
              message: '已发表评论'
            });
          } else {

          }
          console.log(value);

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
  }
</style>
