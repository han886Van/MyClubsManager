<template>
    <div class="home">
        <div class="bgc"></div>
        <div class="login">
            <p>登录</p>
            <el-radio-group v-model="formLabelAlign.radio" @change="chooseRule()">
                <el-radio :label="1">学生</el-radio>
                <el-radio :label="2">教师</el-radio>
                <el-radio :label="3">管理员</el-radio>
            </el-radio-group>
            <div class="my_form">
                <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
                    <el-form-item label="账号">
                        <el-input v-model="formLabelAlign.name"></el-input>
                    </el-form-item>
                    <el-form-item label="密码">
                        <el-input type="password" v-model="formLabelAlign.passWord"></el-input>
                    </el-form-item>
                    <el-button type="primary" @click="login">立即登录</el-button>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapGetters, mapMutations} from 'vuex'
    export default {
        name: '',
        components: {},
        data () {
            return {
                labelPosition: 'right',
                formLabelAlign: {
                    name: '',
                    passWord: '',
                    radio: 1
                }
            }
        },
        methods: {
            ...mapMutations([
                'USER_ROULE',
            ]),
            chooseRule(){
//        console.log(this.formLabelAlign.radio);
            },
            login(){
//        console.log(this.formLabelAlign);
                localStorage.setItem("userRole", this.formLabelAlign.radio);
                this.USER_ROULE(localStorage.getItem('userRole'));
                this.$router.push({path: '/manage'});

            }
        },
        mounted(){

        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="stylus" rel="stylesheet/stylus">
    .home {
        overflow: hidden
        width: 100%
        height: 100%
        box-sizing: border-box;
        .bgc {
            width: 100%
            height: 93%
            position: absolute;
            background: url("../assets/img/bg6.jpg") no-repeat;
            background-position: center;
            background-size: 100% 100%;
            z-index: -22;
        }
        .login {
            margin-top: 20px;
            color: #fff;
            width: 100%;
            height: 100%;
            position: relative;
            p {
                text-align: center;
                font-size: 30px;
                margin-bottom: 30px;
            }
            .my_form {

            }

        }
    }
</style>
