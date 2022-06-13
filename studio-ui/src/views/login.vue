<template>
  <div class="login">
    <div class="section dwo">
      <div class="container">
        <div class="row full-height justify-content-center">
          <div class="col-12 text-center align-self-center py-5 pad-top-0">
            <div class="section pb-5 pt-5 pt-sm-2 text-center">
              <input class="checkbox" type="checkbox" id="reg-log" name="reg-log">
              <div class="card-3d-wrap mx-auto">
                <div class="card-3d-wrapper bgk">
                  <div class="card-front">
                    <div class="center-wrap">
                      <div class="section text-center" v-on:keyup.enter="login">
                        <h4 class="mb-4 pb-3">长路工作室 登录</h4>
                        <div class="form-group">
                          <input type="email"  name="username" class="form-style" v-model="loginForm.username" placeholder="请输入用户名" id="username" autocomplete="off">
                          <i class="fa fa-user-circle-o" aria-hidden="true"></i>
                        </div>
                        <div class="form-group mt-2">
                          <input type="password" name="logpass" class="form-style" v-model="loginForm.password" placeholder="请输入密码" id="logpass" autocomplete="off">
                          <i class="fa fa-unlock-alt" aria-hidden="true"></i>
                        </div>
                        <div class="form-group mt-2" style="margin-top: 12px !important;">
                          <input type="text" name="logcode" class="form-style code-style" v-model="loginForm.code" placeholder="请输入验证码" id="logpass" autocomplete="off">
                          <i class="fa fa-shield" aria-hidden="true"></i>
                          <div style="height: 0;">
                            <img :src="codeUrl" @click="getCode" class="login-code-img"/>
                          </div>
                        </div>
                        <div class="form-group remberbox" style="color: #ffeba7;">
                          <div>
                            <!--    对于dom结点，可以设置ref引用，之后可以根据this.$refs.rembox.checked获取是否选中      -->
                             <input ref="rembox" type="checkbox" style="position: initial;!important;" name="remember" value="记住我" >
                              <p style="display:inline;position: relative;top: -2px;left: 6px;">记住密码</p>
                          </div>
                        </div>
                        <a href="javascript:" class="btn mt-4" @click="login" v-if="!loading">登录</a>
                        <div class="d-flex justify-content-between mt-4 text-left">
                          <div class="flex-fill">
                            <p>没有帐号？<label for="reg-log">立即注册</label></p>
                          </div>
                          <div class="flex-fill text-right">
                            <!-- <p><a href="javascript:" class="forgot link">忘记密码？</a></p> -->
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="card-back">
                    <div class="center-wrap">
                      <div class="section text-center" v-on:keyup.enter="register()">
                        <h4 class="mb-4 pb-3">欢迎加入我们(>‿◠) 👊</h4>
                        <div class="form-group">
                          <input type="text" name="username" class="form-style" v-model="registerForm.username" placeholder="请输入用户名" id="logname" autocomplete="off">
                          <i class="fa fa-user-circle-o" aria-hidden="true"></i>
                        </div>
                        <div class="form-group mt-2">
                          <input type="password" name="password" class="form-style" v-model="registerForm.password" placeholder="请输入密码" id="reg-password" autocomplete="off">
                          <i class="fa fa-unlock-alt" aria-hidden="true"></i>
                        </div>
                        <div class="form-group mt-2">
                          <input type="text" name="invitationCode" class="form-style" v-model="registerForm.invitationCode" placeholder="请输入邀请码" id="logpass" autocomplete="off">
                          <i class="fa fa-id-card" aria-hidden="true"></i>
                        </div>
                        <a href="javascript:" class="btn mt-4" @click="doRegister()">注册</a>
                        <p class="mb-0 mt-4 text-center">已有帐号？<label for="reg-log">立即登录</label></p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getCodeImg, register } from "@/api/login";
import Cookies from "js-cookie";
import { encrypt, decrypt, loginPasswordEncrypt } from '@/utils/jsencrypt'
import { isEmpty } from '@/utils/webtool.js'
import { Message } from 'element-ui'


export default {
  name: 'LoginMain',
  inject: ['reload'],
  // 定义属性
  data () {
    return {
      codeUrl: "",
      loginForm: {
        username: "",
        password: "",
        code: "",
        uuid: ""
      },
      infoName: {
        username: '用户名',
        password: '密码',
        invitationCode: '邀请码'
      },
      registerForm: {
        username: '',
        password: '',
        invitationCode: ''
      },
      rememberMe: "",
      loading: false,
      // 验证码开关
      captchaOnOff: true,
    }
  },
  // 计算属性，会监听依赖属性值随之变化
  computed: {
  },
  // 监控data中的数据变化
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  // 方法集合
  methods: {
    getCode() {
      getCodeImg().then(res => {
        // console.log("login1.vue-res:",res)
        this.captchaOnOff = res.captchaOnOff === undefined ? true : res.captchaOnOff;
        if (this.captchaOnOff) {
          // this.codeUrl = "data:image/png;base64," + res.data.img;
          this.codeUrl = res.data.img;
          this.loginForm.uuid = res.data.uuid
        }
      });
    },
    getCookie() {
      const username = Cookies.get('username')
      const password = Cookies.get('password')
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : decrypt(password)
      };
      //对于记住我由于是ref绑定，所以需要在mounted()挂载之后进行判定
    },
    login() {
      // 校验是否为空
      for (const key in this.loginForm) {
        if (isEmpty(this.loginForm[key])) {
          this.$message({
            showClose: true,
            message: this.infoName[key] + '为空请填写！',
            type: 'error'
          })
          return
        }
      }
      this.handleLogin()
    },
    handleLogin() {
      // 验证通过
      this.loading = true;// 加载按钮动画
      let rememberMe = this.$refs.rembox.checked
      if (rememberMe === true) {// 判断是否记住我
        Cookies.set('username', this.loginForm.username, { expires: 30 })
        Cookies.set('password', encrypt(this.loginForm.password), { expires: 30 })
        Cookies.set('rememberMe', rememberMe, { expires: 30 })
      } else {
        Cookies.remove('username')
        Cookies.remove('password')
        Cookies.remove('rememberMe')
      }
      //对登录密码进行非对称加密
      const loginObj = JSON.parse(JSON.stringify(this.loginForm)) //进行深拷贝
      loginObj.password = loginPasswordEncrypt(loginObj.password) //非对称加密对密码
      console.log("loginform=>",loginObj)
      this.$store.dispatch('Login', loginObj).then(() => {
        // console.log("跳转")
        Message({
          message: '登录成功！',
          type: 'success',
          duration: 1500
        })
        this.$router.push({ path: this.redirect || "/" }).catch(()=>  {});
      }).catch(() => {
        this.loading = false
        if (this.captchaOnOff) {
          this.getCode()
        }
      });
    },
    doRegister () {
      for (const key in this.registerForm) {
        if (isEmpty(this.registerForm[key])) {
          this.$message({
            showClose: true,
            message: this.infoName[key] + '为空请填写！',
            type: 'error'
          })
          return
        }
      }
      // 通过校验
      // this.registerForm.password = encrypt(this.registerForm.password)
      const rLoading = this.openLoading()
      //对注册密码进行非对称加密
      const registerObj = JSON.parse(JSON.stringify(this.registerForm)) //进行深拷贝
      registerObj.password = loginPasswordEncrypt(registerObj.password) //非对称加密对密码
      //注册请求
      register(registerObj).then((result) => {
          console.log("result:",result)
          if (result.code === 200) {
            this.$message({
              showClose: true,
              message: result.msg,
              type: 'success'
            })
            // 存储到cookie用户名7天
            Cookies.set('username', this.registerForm.username, { expires: 30 })
            Cookies.set('password', encrypt(this.registerForm.password), { expires: 30 })
            rLoading.close()
            this.reload() // 刷新页面
          } else {
            this.$message({
              showClose: true,
              message: result.message + '注册失败！',
              type: 'error'
            })
          }
          rLoading.close()
          // eslint-disable-next-line handle-callback-err
        }).catch(err => {
          log.info(err)
          rLoading.close()
        })
      rLoading.close()
    },

  },
  // 生命周期 - 创建完成（可以访问当前this实例）
  created () {
    this.getCode();
    this.getCookie();
  },
  // 生命周期 - 挂载完成（可以访问DOM元素）
  mounted () {
    //读取cookie内容来进行记住我
    const rememberMe = Cookies.get('rememberMe')
    if (rememberMe == "true"){
      this.$refs.rembox.checked = true
    }
  },
  beforeCreate () {}, // 生命周期 - 创建之前
  beforeMount () {}, // 生命周期 - 挂载之前
  beforeUpdate () {}, // 生命周期 - 更新之前
  updated () {}, // 生命周期 - 更新之后
  beforeDestroy () {}, // 生命周期 - 销毁之前
  destroyed () {}, // 生命周期 - 销毁完成
  activated () {} // 如果页面有keep-alive缓存功能，这个函数会触发
}
</script>

<style lang="css" scoped>
/*@import url("https://lib.sinaapp.com/js/bootstrap/4.2.1/css/bootstrap.min.css");*/
/*@import url("https://s2.pstatp.com/cdn/expire-1-M/font-awesome/4.7.0/css/font-awesome.min.css");*/
/*@import "../assets/css/font-awesome.min.css";*/
@import "../assets/css/bootstrap.min.css";

   .login-code-img{
     position: relative;
     top: -47px;
     left: 113px;
     height: 46px;
     border-radius: 8px;
   }
  .code-style{
    width: 210px !important;
    padding-left: 55px!important;
    position: relative!important;
    left: -78px!important;
  }
  .pad-top-0{
    padding-top: 0px !important;
  }
  .login {
    font-family: "Poppins", sans-serif;
    font-weight: 300;
    font-size: 15px;
    line-height: 1.7;
    color: #c4c3ca;
    background-color: #1f2029;
    overflow-x: hidden;
  }
  a {
    cursor: pointer;
    transition: all 200ms linear;
  }
  a:hover {
    text-decoration: none;
  }
  .link {
    color: #c4c3ca;
  }
  .link:hover {
    color: #ffeba7;
  }
  p {
    font-weight: 500;
    font-size: 14px;
    line-height: 1.7;
  }
  h4 {
    font-weight: 600;
    margin-bottom: 10px !important;
  }
  h6 span {
    padding: 0 20px;
    text-transform: uppercase;
    font-weight: 700;
  }
  .section.dwo {
    position: relative;
    width: 100%;
    display: block;
  }
  .full-height {
    min-height: 100vh;
  }
  [type="checkbox"]:checked,
  [type="checkbox"]:not(:checked) {
    position: absolute;
    left: -9999px;
  }
  .checkbox:checked + label,
  .checkbox:not(:checked) + label {
    position: relative;
    display: block;
    text-align: center;
    width: 60px;
    height: 16px;
    border-radius: 8px;
    padding: 0;
    margin: 10px auto;
    cursor: pointer;
    background-color: #ffeba7;
  }
  .checkbox:checked + label:before,
  .checkbox:not(:checked) + label:before {
    position: absolute;
    display: block;
    width: 36px;
    height: 36px;
    border-radius: 50%;
    color: #ffeba7;
    background-color: #102770;
    font-family: "unicons";
    content: "\eb4f";
    z-index: 20;
    top: -10px;
    left: -10px;
    line-height: 36px;
    text-align: center;
    font-size: 24px;
    transition: all 0.5s ease;
  }
  .checkbox:checked + label:before {
    transform: translateX(44px) rotate(-270deg);
  }

  .card-3d-wrap {
    position: relative;
    width: 440px;
    max-width: 100%;
    height: 400px;
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    perspective: 800px;
    position: relative;
    top: -44px;
  }
  .card-3d-wrapper {
    width: 100%;
    height: 114%;
    position: absolute;
    top: 0;
    left: 0;
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    transition: all 600ms ease-out;
  }
  .card-front,
  .card-back {
    width: 100%;
    height: 100%;
    background-color: #2a2b38;
    background-image: url("../assets/pat.svg");
    background-position: bottom center;
    background-repeat: no-repeat;
    background-size: 300%;
    position: absolute;
    border-radius: 6px;
    left: 0;
    top: 0;
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    -webkit-backface-visibility: hidden;
    -moz-backface-visibility: hidden;
    -o-backface-visibility: hidden;
    backface-visibility: hidden;
    border: 1px solid #ffffff21;
  }
  .card-back {
    transform: rotateY(180deg);
  }
  .checkbox:checked ~ .card-3d-wrap .card-3d-wrapper {
    transform: rotateY(180deg);
  }
  .center-wrap {
    position: absolute;
    width: 100%;
    padding: 0 35px;
    top: 50%;
    left: 0;
    transform: translate3d(0, -50%, 35px) perspective(100px);
    z-index: 20;
    display: block;
  }

  .form-group {
    position: relative;
    display: block;
    margin: 0;
    padding: 0;
  }
  .form-style {
    padding: 13px 20px;
    padding-left: 55px;
    height: 48px;
    width: 100%;
    font-weight: 500;
    border-radius: 4px;
    font-size: 14px;
    line-height: 22px;
    letter-spacing: 0.5px;
    outline: none;
    color: #c4c3ca;
    background-color: #1f2029;
    border: none;
    -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
    box-shadow: 0 4px 8px 0 rgba(21, 21, 21, 0.2);
  }
  .form-style:focus,
  .form-style:active {
    border: none;
    outline: none;
    box-shadow: 0 4px 8px 0 rgba(21, 21, 21, 0.2);
  }

  .fa{
    position: absolute;
    top: 0;
    left: 18px;
    height: 48px;
    font-size: 24px;
    line-height: 48px;
    text-align: left;
    color: #ffeba7;
    -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
  }
  .form-group input:-ms-input-placeholder {
    color: #c4c3ca;
    opacity: 0.7;
    -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
  }
  .form-group input::-moz-placeholder {
    color: #c4c3ca;
    opacity: 0.7;
    -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
  }
  .form-group input:-moz-placeholder {
    color: #c4c3ca;
    opacity: 0.7;
    -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
  }
  .form-group input::-webkit-input-placeholder {
    color: #c4c3ca;
    opacity: 0.7;
    -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
  }
  .form-group input:focus:-ms-input-placeholder {
    opacity: 0;
    -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
  }
  .form-group input:focus::-moz-placeholder {
    opacity: 0;
    -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
  }
  .form-group input:focus:-moz-placeholder {
    opacity: 0;
    -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
  }
  .form-group input:focus::-webkit-input-placeholder {
    opacity: 0;
    -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
  }

  .btn {
    width: 90px !important;
    border-radius: 4px;
    height: 44px;
    font-size: 13px;
    font-weight: 600;
    text-transform: uppercase;
    -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
    padding: 0 30px;
    letter-spacing: 1px;
    display: -webkit-inline-flex;
    display: -ms-inline-flexbox;
    display: inline-flex;
    -webkit-align-items: center;
    -moz-align-items: center;
    -ms-align-items: center;
    align-items: center;
    -webkit-justify-content: center;
    -moz-justify-content: center;
    -ms-justify-content: center;
    justify-content: center;
    -ms-flex-pack: center;
    text-align: center;
    border: none;
    background-color: #ffeba7;
    color: #102770;
    box-shadow: 0 8px 24px 0 rgba(255, 235, 167, 0.2);
  }

  .btn:hover {
    background-color: #f3d97f;
    color: #061f6e;
    box-shadow: 0 8px 24px 0 rgba(16, 39, 112, 0.2);
  }

  .logo {
    position: absolute;
    top: 30px;
    right: 30px;
    display: block;
    z-index: 100;
    transition: all 250ms linear;
  }
  .logo img {
    height: 26px;
    width: auto;
    display: block;
  }
  label {
    margin-bottom: 0;
    cursor: pointer;
  }
  label:hover {
    color: #ffeba7;
  }

  .bgk {
    background-color: #151823;
    animation: textColor 10s ease infinite;
  }
  .bgk:after {
    position: absolute;
    content: "";
    top: 1vw;
    left: 0;
    right: 0;
    z-index: -1;
    height: 100%;
    width: 100%;
    margin: 0 auto;
    transform: scale(0.78);
    -webkit-filter: blur(2vw);
    background: linear-gradient(270deg, #0fffc1, #7e0fff);
    background-size: 150% 150%;
    animation: animateGlow 10s ease infinite;
  }
  @keyframes animateGlow {
    0% {
      background-position: 0% 50%;
    }
    50% {
      background-position: 100% 50%;
    }
    100% {
      background-position: 0% 50%;
    }
  }
  @keyframes textColor {
    0% {
      color: #7e0fff;
    }
    50% {
      color: #0fffc1;
    }
    100% {
      color: #7e0fff;
    }
  }

  /* 自定义 */
  .remberbox div{
    margin-top: 12px;
    position: relative;
    left: -35%;
  }

  .remberbox >>> .el-checkbox__input.is-checked .el-checkbox__inner, .el-checkbox__input.is-indeterminate .el-checkbox__inner{
    color: #ffeba7 !important;
    border-color: #ffeba7 !important;
    background-color: #2a580d !important;
  }
  .remberbox >>> .el-checkbox__input.is-checked+.el-checkbox__label{
    color: #ffeba7 !important;
  }
  .remberbox input{
    position: relative;
    top: 3px;
  }
</style>
