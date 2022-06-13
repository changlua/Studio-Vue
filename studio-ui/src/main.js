// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import { parseTime, resetForm, addDateRange, selectDictLabel, selectDictLabels, handleTree } from "@/utils/ruoyi";

//导入指令
import directive from './directive' // directive

//引入jquery
import $ from "jquery/dist/jquery"
window.jquery = window.$ = $

// 全局方法挂载
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectDictLabels = selectDictLabels
Vue.prototype.handleTree = handleTree

import Cookies from 'js-cookie'
import plugins from './plugins' // plugins

import { download } from '@/utils/request'

// 头部标签组件
import VueMeta from 'vue-meta'
import './assets/icons' // 图标
import './permission' // 权限控制
import '@/assets/styles/index.scss' // global css
import '@/assets/styles/ruoyi.scss' // ruoyi css

import 'font-awesome/css/font-awesome.min.css'

Vue.config.productionTip = false

// 自定义表格工具组件
import RightToolbar from "@/components/RightToolbar"
// 分页组件
import Pagination from "@/components/Pagination";
// 富文本组件
import Editor from "@/components/Editor"
// 文件上传组件
import FileUpload from "@/components/FileUpload"
// 图片上传组件
import ImageUpload from "@/components/ImageUpload"
// 图片预览组件
import ImagePreview from "@/components/ImagePreview"


// 全局组件挂载
Vue.component('RightToolbar', RightToolbar)
Vue.component('Pagination', Pagination)
Vue.component('Editor', Editor)
Vue.component('FileUpload', FileUpload)
Vue.component('ImageUpload', ImageUpload)
Vue.component('ImagePreview', ImagePreview)
// 全局方法挂载
Vue.prototype.download = download

// 用于设置页面字体大小
Vue.use(Element, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
})
Vue.use(plugins)
Vue.use(VueMeta)
Vue.use(directive)  //使用指令插件

//挂在加载效果   使用方式：const rLoading = this.openLoading()   rLoading = false
Vue.prototype.openLoading = function () {
  const loading = this.$loading({
    lock: true,
    text: '正在加载...',
    spinner: 'el-icon-loading',
    background: 'rgba(0, 0, 0, 0.3)',
    target: '.sub-main',
    body: true,
    customClass: 'mask'
  })
  setTimeout(function () {
    loading.close()
  }, 5000)
  return loading
}

//引入vue-echarts
import 'echarts'
import ECharts from 'vue-echarts'
Vue.component('VueEcharts', ECharts)

//自定义引入组件
//1、引入Viewer
import Viewer from 'v-viewer'
import 'viewerjs/dist/viewer.css'
Vue.use(Viewer, {
  defaultOptions: {
    zIndex: 9999
  }
})
Viewer.setDefaults({
  Options: {
    'inline': true, 'button': true, 'navbar': true,
    'title': true, 'toolbar': true, 'tooltip': true,
    'movable': true, 'zoomable': true, 'rotatable': true,
    'scalable': true, 'transition': true, 'fullscreen': true,
    'keyboard': true, 'url': 'data-source'
  }
})

const isDebug_mode = process.env.NODE_ENV !== 'production';
Vue.config.debug = isDebug_mode;
Vue.config.devtools = isDebug_mode;
Vue.config.productionTip = isDebug_mode;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
