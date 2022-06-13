import router from './router'
import store from './store'  //引入vuex
import { Message } from 'element-ui'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
//引入工具类
import { getToken } from '@/utils/auth'
import { isRelogin } from '@/utils/request'

NProgress.configure({ showSpinner: false })

// '/auth-redirect', '/bind', '/register'
const whiteList = ['/login']

// 路由执行前（所有请求前） //若是没有任何能够匹配的路由这里就会变为/404
router.beforeEach((to, from, next) => {
  NProgress.start()
  // next()
  if (getToken()) {
    // 若是当前有token
    to.meta.title && store.dispatch('settings/setTitle', to.meta.title)
    /* has token*/
    if (to.path === '/login') {  // 若是目标路径是/login，那么进行跳转
      next({ path: '/' })
      NProgress.done()
    } else {
      // 若是当前有token，但是没有角色
      if (store.getters.roles.length === 0) {
        isRelogin.show = true  // 设置要重新登录
        // 拉取完user_info信息
        store.dispatch('GetInfo').then(() => {
          isRelogin.show = false
          store.dispatch('GenerateRoutes').then(accessRoutes => {
            // 根据roles权限生成可访问的路由表
            router.addRoutes(accessRoutes) // 动态添加可访问路由表
            next({ ...to, replace: true }) // hack方法 确保addRoutes已完成
          })
        }).catch(err => {
          store.dispatch('LogOut').then(() => {
            Message.error(err)
            this.$router.push("/login")
          })
        })
      } else {
        // 404走的是这，只有当你有token的时候，访问错误的页面才会跳转到/404，没有token访问到其他页面都是重定向到/login页，通过`/login?redirect=${to.fullPath}`
        // console.log("to.fullPath=>",to.fullPath)
        next()
      }
    }
  } else {
    // 没有token
    if (whiteList.indexOf(to.path) !== -1) {
      // 在免登录白名单，直接进入
      next()
    } else {
      // console.log("to.fullPath=>",to.fullPath)
      next(`/login?redirect=${to.fullPath}`) // 否则全部重定向到登录页
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done()
})
