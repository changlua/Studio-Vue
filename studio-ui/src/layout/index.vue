<template>
  <div :class="classObj" class="app-wrapper" :style="{'--current-color': theme}">
    <Live2d/>
    <div v-if="device==='mobile'&&sidebar.opened" class="drawer-bg" @click="handleClickOutside"/>
    <!--  引入sidebar侧边栏组件  -->
    <sidebar v-if="!sidebar.hide" class="sidebar-container" />
    <div :class="{hasTagsView:needTagsView,sidebarHide:sidebar.hide}" class="main-container">
      <div :class="{'fixed-header':fixedHeader}">
        <!--   顶部栏目组件    -->
        <navbar />
        <!--   钉子组件，tab栏目    -->
        <tags-view v-if="needTagsView" />
      </div>
      <!--  主体组件    -->
      <app-main />
      <right-panel>
        <settings />
      </right-panel>
    </div>
  </div>
</template>

<script>
import RightPanel from '@/components/RightPanel'
// 引入主页的所有组件
import { AppMain, Navbar, Settings, Sidebar, TagsView } from './components'
// ResizeHandler：调整大小组件
import ResizeMixin from './mixin/ResizeHandler'
// vuex的辅助函数，借助其能够快速获取vuex中的数据
import { mapState } from 'vuex'
// 引入scss文件，都是提前定义好的一些颜色、主题
import variables from '@/assets/styles/variables.scss'

//引入看板娘
import Live2d from '@/components/live2d'

export default {
  name: 'Layout',
  components: {   // 引入组件
    AppMain,
    Navbar,
    RightPanel,
    Settings,
    Sidebar,
    TagsView,
    Live2d
  },
  mixins: [ResizeMixin],  // 混入指定的组件
  computed: {
    // 快速取得指定全局模块中的值
    ...mapState({
      theme: state => state.settings.theme,
      sideTheme: state => state.settings.sideTheme,
      sidebar: state => state.app.sidebar,
      device: state => state.app.device,
      needTagsView: state => state.settings.tagsView,
      fixedHeader: state => state.settings.fixedHeader
    }),
    classObj() {
      return {
        hideSidebar: !this.sidebar.opened,
        openSidebar: this.sidebar.opened,
        withoutAnimation: this.sidebar.withoutAnimation,
        mobile: this.device === 'mobile'
      }
    },
    variables() {
      return variables;
    }
  },
  methods: {
    handleClickOutside() {
      // 调用store中app.js的closeSideBar方法，侧边栏关闭。其中false就是设置sidebar.opened、withoutAnimation为false
      this.$store.dispatch('app/closeSideBar', { withoutAnimation: false })
    }
  }
}
</script>

<style lang="scss" scoped>
  //引入/assets/styles资源
  @import "~@/assets/styles/mixin.scss";
  @import "~@/assets/styles/variables.scss";

  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;

    &.mobile.openSidebar {
      position: fixed;
      top: 0;
    }
  }

  .drawer-bg {
    background: #000;
    opacity: 0.3;
    width: 100%;
    top: 0;
    height: 100%;
    position: absolute;
    z-index: 999;
  }

  .fixed-header {
    position: fixed;
    top: 0;
    right: 0;
    z-index: 9;
    width: calc(100% - #{$base-sidebar-width});
    transition: width 0.28s;
  }

  .hideSidebar .fixed-header {
    width: calc(100% - 54px);
  }

  .sidebarHide .fixed-header {
    width: 100%;
  }

  .mobile .fixed-header {
    width: 100%;
  }
</style>
