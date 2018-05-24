<template>
  <div class="society">
    <div class="bgc">
      <!--学生-->
      <div class="top" v-show="userRole==1">
        <span>社团管理</span>
        <span>	&gt;</span>
        <span v-show="showAll==2" class="blue">全部社团</span>
        <span v-show="showAll==5" class="blue">全部社团</span>
        <span v-show="showAll==1" class="blue">已进社团</span>
        <span v-show="showAll==3" class="blue">管理社团</span>
        <span v-show="showAll==4" class="blue">申请记录</span>
      </div>
      <div class="top" v-show="userRole==2">
        <span>社团管理</span>
        <span>	&gt;</span>
        <span v-show="showAll==1||showAll==3" class="blue">全部社团</span>
        <span v-show="showAll==2" class="blue">申请社团</span>
      </div>
      <div class="top" v-show="userRole==3">
        <span class="blue">社团管理</span>
      </div>
      <div class="search_box" v-show="userRole==1">
        <div>
          <span>社团分类：</span>
          <el-select v-model="sortSociety" placeholder="社团分类">
            <el-option label="专业学术类" value="1"></el-option>
            <el-option label="科技创新类" value="2"></el-option>
            <el-option label="艺术兴趣类" value="3"></el-option>
            <el-option label="体育健身类" value="4"></el-option>
            <el-option label="公益服务类" value="5"></el-option>
            <el-option label="全部分类" value="6"></el-option>
          </el-select>
        </div>
        <div>
          <span>社团编号：</span>
          <el-input v-model="idInput" placeholder="请输入内容" clearable></el-input>
        </div>
        <div>
          <span>社团名字：</span>
          <el-input v-model="nameInput" placeholder="请输入内容" clearable></el-input>
        </div>
        <el-button @click="searchItem(1)" type="info" plain>搜索</el-button>
        <el-button @click="toRouter('/addSociety')" type="primary">创建社团</el-button>
      </div>
      <div class="search_box" v-show="userRole==2">
        <div>
          <span>社团编号：</span>
          <el-input v-model="idInput" placeholder="请输入内容" clearable></el-input>
        </div>
        <div>
          <span>社团名字：</span>
          <el-input v-model="nameInput" placeholder="请输入内容" clearable></el-input>
        </div>
        <el-button @click="searchTItem()" type="primary"  >搜索</el-button>
        <el-button @click="clearSearch()">清空搜索</el-button>
      </div>
      <!--学生-->
      <div class="member" v-show="userRole==1">
        <div class="title">
          <span>序号</span>
          <span>编号</span>
          <span>分类</span>
          <span>名字</span>
          <span>社长</span>
          <span>地点</span>
          <span>人数</span>
          <span v-show="showAll==4">申请</span>
          <span>操作</span>
        </div>
        <ul class="list">
          <!--社长-->
          <li class="societyList" v-for="(item,index) in lAssociationList">
            <span @click="toRouter('/societyDetails',item.association_id)">{{index+1}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)">{{item.association_id}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==1">专业学术类</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==2">科技创新类</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==3">艺术兴趣类</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==4">体育健身类</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==5">公益服务类</span>
            <span @click="toRouter('/societyDetails',item.association_id)">{{item.name}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)">{{item.user_name}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)">{{item.place}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)">{{item.person_num}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="showAll==4&&item.user_state_num==0">创建中</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="showAll==4&&item.user_state_num==3">解散中</span>
            <!--已进入社团-->
            <div v-show="showAll==1">
              <span @click="toRouter('/edtiSociety',item.association_id)" class="blue">编辑</span>
              <span  class="red_color" @click="dissolution(item.association_id,3)">解散</span>
            </div>
            <!--全部社团-->
            <div v-show="showAll==2||showAll==5">
              <span @click="toRouter('/edtiSociety',item.association_id)"  class="blue">编辑</span>
              <span  class="red_color" @click="dissolution(item.association_id,3)">解散</span>
            </div>
            <!--申请记录-->
            <div v-show="showAll==4">
              <span  v-show="item.user_state_num==0"  class="red_color" @click="dissolution(item.association_id)">取消创建</span>
              <span @click="dissolution(item.association_id,1)" v-show="item.user_state_num==3" class="blue">取消解散</span>
            </div>
          </li>
          <!--社员-->
          <li class="societyList" v-for="(item,index) in associationList">
            <span @click="toRouter('/societyDetails',item.association_id)">{{lAssociationList.length+index+1}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)">{{item.association_id}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==1">专业学术类</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==2">科技创新类</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==3">艺术兴趣类</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==4">体育健身类</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==5">公益服务类</span>
            <span @click="toRouter('/societyDetails',item.association_id)">{{item.name}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)">{{item.user_name}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)">{{item.place}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)">{{item.person_num}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="showAll==4&&item.user_state_num==3">退出中</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="showAll==4&&item.user_state_num==0">加入中</span>
            <!--已进入社团-->
            <div v-show="showAll==1">
              <span @click="aboutSociety(3,item.association_id)" class="red_color">退出</span>
              <!--<el-button  type="danger">退出</el-button>-->
            </div>
            <!--全部社团-->
            <div v-show="showAll==2||showAll==5">
              <span @click="aboutSociety(3,item.association_id)" v-show="item.user_state_num==1" class="red_color">退出</span>
              <span  @click="aboutSociety(0,item.association_id)" v-show="item.user_state_num==0||item.role_name_num==0" class="blue">加入</span>
            </div>
            <!--申请记录-->
            <div v-show="showAll==4">
              <span  @click="cancelSocity(item.association_id)" v-show="item.user_state_num==0" class="red_color">取消加入</span>
              <span @click="cancelSocity(item.association_id,1)" v-show="item.user_state_num==3" class="blue">取消退出</span>
            </div>
          </li>
          <li v-show="showNo" class="noList">暂无社团</li>
        </ul>
      </div>
      <!--教师-->
      <div class="manage_menber" v-show="userRole==2">
        <div class="title">
          <span>序号</span>
          <span>编号</span>
          <span>分类</span>
          <span>名字</span>
          <span>社长</span>
          <span>地点</span>
          <span v-show="showAll==2">申请</span>
          <span v-show="showAll==3||showAll==1">人数</span>
          <span>操作</span>
        </div>
        <ul class="list">
          <li class="societyList" v-for="(item,index) in associationList">
            <span @click="toRouter('/societyDetails',item.association_id)">{{index+1}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)">{{item.association_id}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==1">专业学术类</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==2">科技创新类</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==3">艺术兴趣类</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==4">体育健身类</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.type_id==5">公益服务类</span>
            <span @click="toRouter('/societyDetails',item.association_id)">{{item.name}}</span>
            <span v-show="item.user_name" @click="toRouter('/societyDetails',item.association_id)">{{item.user_name}}</span>
            <span v-show="!item.user_name" @click="toRouter('/societyDetails',item.association_id)">匿名</span>
            <span @click="toRouter('/societyDetails',item.association_id)" >{{item.place}}</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.state==0 && showAll==2">申请创建</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="item.state==3 && showAll==2">申请解散</span>
            <span @click="toRouter('/societyDetails',item.association_id)" v-show="showAll==3">{{item.person_num}}</span>
            <div v-show="showAll==1||showAll==3">
              <span class="red_color" @click="dissolution(item.association_id)">删除</span>
            </div>
            <!--申请创建-->
            <div v-show="showAll==2&&item.state==0">
              <span  class="blue" @click="dissolution(item.association_id,1)">同意</span>
              <span  class="red_color" @click="dissolution(item.association_id)">拒绝</span>
            </div>
            <!--申请解散-->
            <div v-show="showAll==2&&item.state==3">
              <span  class="blue" @click="dissolution(item.association_id)">同意</span>
              <span  class="red_color" @click="dissolution(item.association_id,1)">拒绝</span>
            </div>
          </li>
          <li v-show="showNo" class="noList">暂无社团</li>
        </ul>
      </div>
      <!--管理员-->
      <div class="manage_menber" v-show="userRole==3">
        <div class="title">
          <span>序号</span>
          <span>编号</span>
          <span>分类</span>
          <span>名字</span>
          <span>社长</span>
          <span>地点</span>
          <span>人数</span>
          <span>操作</span>
        </div>
        <ul class="list">
          <li class="societyList" v-for="(item,index) in hadArr">
            <span @click="toRouter('/societyDetails',item.societyId)">{{index+1}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.societyId}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==1">专业学术类</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==2">科技创新类</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==3">艺术兴趣类</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==4">体育健身类</span>
            <span @click="toRouter('/societyDetails',item.societyId)" v-show="item.sort==5">公益服务类</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.societyName}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.societyManage}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.adress}}</span>
            <span @click="toRouter('/societyDetails',item.societyId)">{{item.num}}</span>
            <div>
              <span class="blue"  @click="editSociety(index)">编辑</span>
              <span class="red_color">删除</span>
            </div>
          </li>
        </ul>
      </div>
      <div  class="myPagination">
        <div>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="10"
            layout="total, prev, pager, next, jumper"
            :total= totalNum>
          </el-pagination>
        </div>
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
        /*社长*/
        lAssociationList: [],
        /*社员*/
        associationList: [],
        hadArr: [
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            societyId: '001',
            societyManage: '小可爱',
            societyName: '摄影社团',
            adress: '操场',
            changeName: 0,
            sort: 1,
            status: 1,
            num:55
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            societyId: '001',
            societyManage: '小可爱',
            societyName: '摄影社团',
            adress: '操场',
            changeName: 1,
            sort: 2,
            status: 1,
            num:55
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            societyId: '001',
            societyManage: '小可爱',
            societyName: '摄影社团',
            adress: '操场',
            changeName: 0,
            sort: 3,
            status: 2,
            num:55
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            societyId: '001',
            societyManage: '小可爱',
            societyName: '摄影社团',
            adress: '操场',
            changeName: 1,
            sort: 4,
            status: 1,
            num:55
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            societyId: '001',
            societyManage: '小可爱',
            societyName: '摄影社团',
            adress: '操场',
            changeName: 0,
            sort: 5,
            status: 2,
            num:55
          },
          {
            imgUrl: require('../../assets/img/home1.jpg'),
            societyId: '001',
            societyManage: '小可爱',
            societyName: '摄影社团',
            adress: '操场',
            changeName: 0,
            sort: 1,
            status: 1,
            num:55
          },
        ],
        myRouter: '',
        idInput: '',
        nameInput: '',
        sortSociety: '',
        currentPage:1,
        url:'',
        showNo:false,
        userId:'',
        totalNum:1,
        typeId:''
      }
    },
    methods: {
      createFunc(){
        this.showAll = this.$route.query.myRouter;
        this.userRole = localStorage.getItem('userRole');
        this.userId =localStorage.getItem('userId');
        if(this.userRole==1){
          /*全部社团*/
          if(this.showAll==5||this.showAll==2){
            this.url=this.localhost+'associationMg/association/getAllAssociation';
            this. getList(1,this.url);
            /*已加入社团*/
          }else if(this.showAll==1){
            this.url=this.localhost+'associationMg/association/getOwnAssociation';
            this. getList(1,this.url);
            /*申请记录*/
          }else if(this.showAll==4){
            this.url=this.localhost+'associationMg/association/getApplyAssociation';
            this. getList(1,this.url);
          }
        }else if(this.userRole==2){
          this.typeId  =localStorage.getItem('typeId');
          if(this.showAll==1||this.showAll==3){
            /*全部社团*/
            this.url=this.localhost+'associationMg/association/getAllAssociation';
            this. getTList(1,this.url);
          }else if(this.showAll==2){
            this.url=this.localhost+'associationMg/association/getAssociationApply';
            this. getTList(1,this.url);
          }
        }
      },
      /*学生*/
      getList(val,url){
          this.associationList=[];
          this.lAssociationList=[];
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var userId = this.userId;
        var json ={
          userId:userId,
          start:val
        };
        this.$http.post(url,json).then(
          (success) => {
            var response = success.data;
            console.log(response);
            if(response.msg==666){
                this.totalNum =parseInt(response.total_num);
              if(response.associationList.length==0){
                this.showNo=true
              }else {
                this.showNo=false
              }
              for(var i =0; i<response.associationList.length;i++){
                  if(this.userRole==1){
                    if(response.associationList[i].role_name_num==1){
                      this.lAssociationList.push(response.associationList[i]);
                    }else {
                      this.associationList.push(response.associationList[i]);
                    }
                  }else{
                    this.associationList.push(response.associationList[i]);
                  }
              }
            }else {
              this.$message.error('错误，请求数据失败');
            }
            setTimeout(() => {
              loading.close();
            }, 500);
          }, (error) => {
            setTimeout(() => {
              loading.close();
            }, 500);
            this.$message.error('错误，请求数据失败');
          });
      },
      /*老师*/
      getTList(val,url,associationId,name){
        this.associationList=[];
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var typeId = this.typeId;
        var json ={
          typeId:typeId,
          start:val
        };
        if(associationId){
          json.associationId=associationId
        }
        if(name){
          json.name=name
        }
        this.$http.post(url,json).then(
          (success) => {
          var response = success.data;
          console.log(response);
          if(response.msg==666){
            this.totalNum =parseInt(response.total_num);
            if(response.associationList.length==0){
              this.showNo=true
            }else {
              this.showNo=false
            }
            for(var i =0; i<response.associationList.length;i++){
              this.associationList.push(response.associationList[i]);
            }
          }else {
            this.$message.error('错误，请求数据失败');
          }
          setTimeout(() => {
            loading.close();
          }, 500);
        }, (error) => {
          setTimeout(() => {
            loading.close();
          }, 500);
          this.$message.error('错误，请求数据失败');
        });
      },

        /*分页器*/
      handleSizeChange(val) {
      },
      handleCurrentChange(val) {
          this.currentPage =val;
          if(this.userRole==1){
            if(this.sortSociety==''&&this.idInput==''&&this.nameInput==''){
              this.getList(val,this.url)
            }else {
              this.associationList=[];
              var typeId =this.sortSociety;
              var associationId =this.idInput;
              var name =this.nameInput;
              if(typeId ==6){
                typeId= ''
              }
              this.sendSearch(val,this.url,typeId,associationId,name)
            }
          }else if(this.userRole==2){
            this.getTList(val,this.url,this.idInput,this.nameInput)
          }

      },
      /*学生查找*/
      searchItem(val){
          if(this.sortSociety==''&&this.idInput==''&&this.nameInput==''){
            this.$message({
              type: 'error',
              message: '请输入或选择搜索条件!'
            });
          }else {
            this.associationList=[];
            this.lAssociationList=[];
              var typeId =this.sortSociety;
              var associationId =this.idInput;
              var name =this.nameInput;
              if(typeId ==6){
                typeId= ''
              }
              /*学生*/
            if(this.userRole==1){
              this.sendSearch(val,this.url,typeId,associationId,name)
            }else if(this.userRole==2){
              this.sendSearch(val,this.url,typeId,associationId,name)
            }
          }
      },
      sendSearch(val,url,typeId,associationId,name){
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var userId = this.userId;
        var json ={
          userId:userId,
          start:val
        };
        if(typeId){
          json.typeId=typeId
        }
        if(associationId){
          json.associationId=associationId
        }
        if(name){
          json.name=name
        }
        this.$http.post(url,json).then(
          (success) => {
            var response = success.data;
            if(response.msg==666){
              this.totalNum =parseInt(response.total_num);
              for(var i =0; i<response.associationList.length;i++){
                if(this.userRole==1){
                  if(response.associationList[i].role_name_num){
                    this.lAssociationList.push(response.associationList[i]);
                  }else {
                    this.associationList.push(response.associationList[i]);
                  }
                }else{
                  this.associationList.push(response.associationList[i]);
                }
              }
              if(response.associationList.length==0){
                this.showNo=true
              }else {
                this.showNo=false
              }
            }else {
              this.$message.error('错误，请求数据失败');
            }
            setTimeout(() => {
              loading.close();
            }, 500);
          }, (error) => {
            setTimeout(() => {
              loading.close();
            }, 500);
            this.$message.error('错误，请求数据失败');
          });
      },
      /*教师查找*/
      searchTItem(){
        if(this.idInput==''&&this.nameInput==''){
          this.$message({
            type: 'error',
            message: '请输入或选择搜索条件!'
          });
          this. getTList(1,this.url);
        }else {
          var associationId =this.idInput;
          var name =this.nameInput;
          this.getTList(1,this.url,associationId,name)
        }
      },
      clearSearch(){
        this.idInput='';
        this.nameInput='';
        this.getTList(1,this.url,this.idInput,this.nameInput)
      },
      /*退出社团 加入社团*/
      aboutSociety(userState,associationId) {
        var word = '';
          if(userState){
            word = '请输入退出社团理由';
          }else {
            word = '请输入加入社团理由';
          }
        this.$prompt(word, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /\S/,
          inputErrorMessage: '*理由不能为空',
        }).then(({ value }) => {
              this.sendaAboutSociety(userState,associationId,value)

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
      },
      sendaAboutSociety(userState,associationId,applyComments){
        var url = this.localhost + 'associationMg/associationAndUser/applyAssociation';
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var userId = this.userId;
        var json ={
          associationId:associationId,
          userId:userId,
          applyComments:applyComments,
          userState:userState,
        };
        this.$http.post(url,json).then(
          (success) => {
            var response = success.data;
            console.log(response);
            if(response.msg==666){
              this.$message({
                type: 'success',
                message: '请求发送成功'
              });
            }else {
              this.$message.error('错误，申请失败');
            }
            setTimeout(() => {
              loading.close();
            }, 500);
          }, (error) => {
            setTimeout(() => {
              loading.close();
            }, 500);
            this.$message.error('错误，申请失败');
          });
      },
      /**
       *  解散社团
       *  State：
       1
       同意社团创建
       取消申请社团创建
       取消申请解散社团
       不传state
       拒绝社团创建
       解散社团
       3
       申请解散社团*/
      dissolution(associationId,state){
        var url = this.localhost + 'associationMg/association/modifyAssociationStatus';
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json ={
          associationId:associationId,
        };
        if(state){
          json.state=state;
        };
        this.$http.post(url,json).then(
          (success) => {
            var response = success.data;
            console.log(response);
            if(response.msg==666){
              this.$message({
                message: '请求发送成功！',
                type: 'success'
              });
              this.createFunc()
            }else {
              this.$message.error('错误，申请失败');
            }
            setTimeout(() => {
              loading.close();
            }, 500);
          }, (error) => {
            setTimeout(() => {
              loading.close();
            }, 500);
            this.$message.error('错误，申请失败');
          });
      },
      /**
       *取消加入退出社团
       * @param associationId
       * @param userState 1 取消申请退出社团  取消申请加入社团不传
       */
      cancelSocity(associationId,userState){
        var url = this.localhost + 'associationMg/association/modifyAssociationStatus';
        const loading = this.$loading({
          lock: true,
          text: '正在发送请求',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
        var json ={
          userId:this.userId,
          associationId:associationId,
        };
        if(userState){
          json.userState =userState
        }
        this.$http.post(url,json).then(
          (success) => {
            var response = success.data;
            console.log(response);
            if(response.msg==666){
              this.$message({
                message: '请求发送成功！',
                type: 'success'
              });
              this.createFunc()
            }else {
              this.$message.error('错误，申请失败');
            }
            setTimeout(() => {
              loading.close();
            }, 500);
          }, (error) => {
            setTimeout(() => {
              loading.close();
            }, 500);
            this.$message.error('错误，申请失败');
          });
      },
      toRouter(myRouter,associationId){
        this.$router.push({path: myRouter, query: {'associationId': associationId}})
      },
    },

    mounted(){

    },
    watch: {
      $route(){
        this.sortSociety = '';
        this.showAll = this.$route.query.myRouter;
        this.userId =localStorage.getItem('userId');
        /*学生*/
        if(this.userRole==1){
          /*全部社团*/
          if(this.showAll==5||this.showAll==2){
            this.url=this.localhost+'associationMg/association/getAllAssociation';
            this. getList(1,this.url);
            /*已加入社团*/
          }else if(this.showAll==1){
            this.url=this.localhost+'associationMg/association/getOwnAssociation';
            this. getList(1,this.url);
            /*申请记录*/
          }else if(this.showAll==4){
            this.url=this.localhost+'associationMg/association/getApplyAssociation';
            this. getList(1,this.url);
          }
        }else if(this.userRole==2){
          if(this.showAll==1||this.showAll==3){
            /*全部社团*/
            this.url=this.localhost+'associationMg/association/getAllAssociation';
            this. getTList(1,this.url);
          }else if(this.showAll==2){
            this.url=this.localhost+'associationMg/association/getAssociationApply';
            this. getTList(1,this.url);
          }
        }

      },
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
    width: 1024px;
    background: #fff;
    min-height: 600px;
    border-radius: 8px;
    padding:10px 40px 20px 40px;
    .bgc {
      border-radius: 8px;
      background-color: #fff;
    }
    .top{
        line-height:50px;
        font-size:16px;
        border-bottom :1px solid #ccc;
        margin-bottom:40px;
      }
    .search_box {
      margin-bottom: 20px;
      div {
        display: inline-block;
        margin-right: 10px;
      }
    }
    .title {
      color: #333;
      display: flex;
      justify-content: space-between;
      align-items: center;
      line-height:50px;
      border-radius :8px 8px 0 0;
  span {
        text-align: center;
        width: 146px;
      }
    }
  }

</style>
