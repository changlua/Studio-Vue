import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

/* Layout */
import Layout from '@/layout'

Vue.use(Router)

// 公共路由
export const constantRoutes = [
  {
    path: '/hello',
    name: 'HelloWorld',
    component: HelloWorld
  },
  {
    path: '/login',
    component: () => import('@/views/login'),
    // component:  import('@/views/login'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'index',  //重定向到/index，也就是主页
    children: [         //子路由
      {
        path: 'index',
        component: () => import('@/views/index'),
        name: 'Index',
        meta: { title: '首页', icon: 'dashboard', affix: true }
      }
    ]
  },
  //若是没有该路由，跳转到404页面
  // {
  //   path: "*",
  //   redirect: '/404'
  // },
  {
    path: '/404',
    component: () => import('@/views/error/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error/401'),
    hidden: true
  },
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect')
        // component:  import('@/views/redirect')
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'profile',
        component: () => import('@/views/user/profile/index'),
        name: 'Profile',
        meta: { title: '个人中心', icon: 'user' }
      }
    ]
  },
]

// 动态路由，基于用户权限动态去加载
export const dynamicRoutes = [
  //角色授权（给用户）
  {
    path: '/system/role-auth',
    component: Layout,
    hidden: true,
    permissions: ['system:role:edit'],
    children: [
      {
        path: 'user/:roleId(\\d+)',
        component: () => import('@/views/system/role/authUser'),
        name: 'AuthUser',
        meta: { title: '分配用户', activeMenu: '/system/role' }
      }
    ]
  }
]

export default new Router({
  // mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
