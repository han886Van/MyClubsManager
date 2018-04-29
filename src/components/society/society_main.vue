<template>
    <div class="society">
        <div class="bgc">
            <div class="search_box">
                <div>
                    <span>社团编号</span>
                    <el-input v-model="idInput" placeholder="请输入内容"></el-input>
                </div>
                <div>
                    <span>社团名字</span>
                    <el-input v-model="nameInput" placeholder="请输入内容"></el-input>
                </div>
                <el-button type="info" plain>搜索</el-button>
            </div>
            <!--社团成员-->
            <div class="member" v-show="userRole==1">
                <div class="title">
                    <span>序号</span>
                    <span>编号</span>
                    <span>名字</span>
                    <span>管理员</span>
                    <span>地点</span>
                    <span>人数</span>
                    <span>操作</span>
                </div>
                <ul class="list">
                    <li v-show="showAll==1" class="societyList" v-for="(item,index) in hadArr">
                        <span>{{index+1}}</span>
                        <span>{{item.societyId}}</span>
                        <span>{{item.societyName}}</span>
                        <span>{{item.societyManage}}</span>
                        <span>{{item.adress}}</span>
                        <span>{{item.num}}</span>
                        <div>
                            <el-button type="info" @click="outSociety(1,index)" plain>退出</el-button>
                        </div>
                    </li>
                    <li v-show="showAll==2" class="societyList" v-for="(item,index) in allArr">
                        <span>{{index+1}}</span>
                        <span>{{item.societyId}}</span>
                        <span>{{item.societyName}}</span>
                        <span>{{item.societyManage}}</span>
                        <span>{{item.adress}}</span>
                        <span>{{item.num}}</span>
                        <div>
                            <el-button v-show="!item.isIn" type="danger" @click="outSociety(1,index)">退出</el-button>
                            <el-button v-show="item.isIn" type="primary" @click="outSociety(0,index)">加入</el-button>
                        </div>
                    </li>
                    <li v-show="showAll==3" class="societyList" v-for="(item,index) in getInArr">
                        <span>{{index+1}}</span>
                        <span>{{item.societyId}}</span>
                        <span>{{item.societyName}}</span>
                        <span>{{item.societyManage}}</span>
                        <span>{{item.adress}}</span>
                        <span>{{item.num}}</span>
                        <div>
                            <el-button type="warning" @click="editSociety(1,index)" plain>取消加入</el-button>

                        </div>
                    </li>
                    <li v-show="showAll==4" class="societyList" v-for="(item,index) in outingArr">
                        <span>{{index+1}}</span>
                        <span>{{item.societyId}}</span>
                        <span>{{item.societyName}}</span>
                        <span>{{item.societyManage}}</span>
                        <span>{{item.adress}}</span>
                        <span>{{item.num}}</span>
                        <div>
                            <el-button type="success"  @click="editSociety(0,index)" plain>取消退出</el-button>
                        </div>
                    </li>
                </ul>
            </div>
            <!--社团管理员-->
            <div class="manage_menber" v-show="userRole==2">
                <div class="btn">
                    <el-button type="success" @click="showAll=1">可管理社团</el-button>
                    <el-button type="primary" @click="showAll=2">&nbsp;全部社团&nbsp;</el-button>
                </div>
                <ul class="list">
                    <li>
                        <div>社团图片</div>
                        <span>社团编号</span>
                        <span>社团名</span>
                        <span>管理员</span>
                        <span>操作</span>
                    </li>
                    <li class="societyList" v-for="(item,index) in hadArr">
                        <div><img :src=item.imgUrl alt=""></div>
                        <span>{{item.societyId}}</span>
                        <span>{{item.societyName}}</span>
                        <span>{{item.societyManage}}</span>
                        <span @click="outSociety(1,index)" class="out_span">编辑</span>
                    </li>
                </ul>
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
                userRole: '',
                showAll: '1',
                hadArr: [
                    {
                        imgUrl: require('../../assets/img/home1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        adress: '操场',
                        num: 55
                    },
                    {
                        imgUrl: require('../../assets/img/home1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        adress: '操场',
                        num: 55
                    },
                    {
                        imgUrl: require('../../assets/img/home1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        adress: '操场',
                        num: 55
                    },
                    {
                        imgUrl: require('../../assets/img/home1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        adress: '操场',
                        num: 55
                    },
                    {
                        imgUrl: require('../../assets/img/home1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        adress: '操场',
                        num: 55
                    },
                    {
                        imgUrl: require('../../assets/img/home1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        adress: '操场',
                        num: 55
                    },
                ],
                allArr: [
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 1,
                        adress: '操场',
                        num: 55
                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 0,
                        adress: '操场',
                        num: 55
                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 1,
                        adress: '操场',
                        num: 55
                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 1,
                        adress: '操场',
                        num: 55
                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 0,
                        adress: '操场',
                        num: 55
                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 1,
                        adress: '操场',
                        num: 55
                    },
                ],
                outingArr:[
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 1,
                        adress: '操场',
                        num: 55,
                        status:1,
                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 0,
                        adress: '操场',
                        num: 55,
                        status:0,
                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 1,
                        adress: '操场',
                        num: 55,

                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 1,
                        adress: '操场',
                        num: 55,

                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 0,
                        adress: '操场',
                        num: 55,

                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 1,
                        adress: '操场',
                        num: 55,

                    },
                ],
                getInArr:[
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 1,
                        adress: '操场',
                        num: 55,
                        status:1,
                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 0,
                        adress: '操场',
                        num: 55,
                        status:0,
                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 1,
                        adress: '操场',
                        num: 55,

                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 1,
                        adress: '操场',
                        num: 55,

                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 0,
                        adress: '操场',
                        num: 55,

                    },
                    {
                        imgUrl: require('../../assets/img/bg1.jpg'),
                        societyId: '001',
                        societyManage: '小可爱',
                        societyName: '摄影社团',
                        isIn: 1,
                        adress: '操场',
                        num: 55,

                    },
                ],
                myRouter: '',
                idInput: '',
                nameInput: ''


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

                });
            }
        },

        mounted(){
            /*社员 ：1 已加入社团 2 全部社团 3 操作中社团 管理员：  */
            this.showAll = this.$route.query.myRouter;
            console.log(this.showAll + 'myrouter');
            this.userRole = localStorage.getItem('userRole');
            console.log(this.userRole + '角色 社团管理router里的');
        },
        watch: {
            $route(){
                this.showAll = this.$route.query.myRouter;
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
        padding: 40px;
        .bgc {
            border-radius: 8px;
            background-color: #fff;
        }
        /*.list {
          border-radius: 8px;
          padding: 20px 50px 40px;
          box-sizing border-box;
          width: 100%;
          background-color: #fff;
          min-height: 600px;

        }*/
        .search_box {
            margin-bottom: 20px;
            div {
                display: inline-block;
                margin-right: 10px;
            }
        }
        .title {
            color :#000000;
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom :20px;
            span {
                text-align: center;
                width: 146px;
            }
        }
        .list {
            li {
                cursor :pointer;
                display: flex;
                justify-content: space-between;
                align-items: center;
                line-height: 80px;
                span {
                    width: 146px;
                    text-align: center;
                }
                div {
                    display: inline-block;
                    width: 146px;
                    text-align: center;
                }
            }
                li:nth-child(2n){
                    background-color: #f4f4f5
                }
            li:nth-child(2n+1){
                background-color: #fff
            }
            li:nth-child(2n):hover{
                background: #e4e4e4;
            }
            li:nth-child(2n+1):hover{
                background: #e4e4e4;
            }
        }
    }

</style>
