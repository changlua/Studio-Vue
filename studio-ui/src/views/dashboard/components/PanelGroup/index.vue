<template>
  <el-row :gutter="40" class="panel-group">
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel">
        <div class="card-panel-icon-wrapper icon-people">
          <svg-icon icon-class="peoples1" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            人数
          </div>
<!--          <count-to :start-val="0" :end-val="102400" :duration="2600" class="card-panel-num" />-->
          <count-to :start-val="0" :end-val="countsData[0]" :duration="2000" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel">
        <div class="card-panel-icon-wrapper icon-message">
          <svg-icon icon-class="project" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            团队竞赛
          </div>
<!--          <count-to :start-val="0" :end-val="81212" :duration="3000" class="card-panel-num" />-->
          <count-to :start-val="0" :end-val="countsData[1]" :duration="2200" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="race" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            竞赛
          </div>
<!--          <count-to :start-val="0" :end-val="9280" :duration="3200" class="card-panel-num" />-->
          <count-to :start-val="0" :end-val="countsData[2]" :duration="2400" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel">
        <div class="card-panel-icon-wrapper icon-shopping">
          <svg-icon icon-class="ccie" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            获奖证书
          </div>
<!--          <count-to :start-val="0" :end-val="13600" :duration="3600" class="card-panel-num" />-->
          <count-to :start-val="0" :end-val="countsData[3]" :duration="2600" class="card-panel-num" />
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import CountTo from 'vue-count-to'

import { getCounts } from '@/api/index'

export default {
  name: 'PanelGroup',
  components: {
    CountTo
  },
  // 定义属性
  data () {
    return {
      countsData: [100,50,60,100]
    }
  },
  // 计算属性，会监听依赖属性值随之变化
  computed: {
  },
  // 监控data中的数据变化
  watch: {},
  // 生命周期 - 创建完成（可以访问当前this实例）
  created () {
    this.init()
  },
  // 方法集合
  methods: {
    init(){
      getCounts().then((response)=>{
        // console.log("response=>", response)
        this.countsData = response.data
      }).catch(err=>console.log(err))
    }
  },
  // 生命周期 - 挂载完成（可以访问DOM元素）
  mounted () {
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

<style lang="scss" scoped>
  .panel-group {
    margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

  &:hover {
  .card-panel-icon-wrapper {
    color: #fff;
  }

  .icon-people {
    background: #40c9c6;
  }

  .icon-message {
    background: #36a3f7;
  }

  .icon-money {
    background: #f4516c;
  }

  .icon-shopping {
    background: #34bfa3
  }
  }

  .icon-people {
    color: #40c9c6;
  }

  .icon-message {
    color: #36a3f7;
  }

  .icon-money {
    color: #f4516c;
  }

  .icon-shopping {
    color: #34bfa3
  }

  .card-panel-icon-wrapper {
    float: left;
    margin: 14px 0 0 14px;
    padding: 16px;
    transition: all 0.38s ease-out;
    border-radius: 6px;
  }

  .card-panel-icon {
    float: left;
    font-size: 48px;
  }

  .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
          font-size: 20px;
        }
      }
    }
  }

  @media (max-width:550px) {
    .card-panel-description {
      display: none;
    }

    .card-panel-icon-wrapper {
      float: none !important;
      width: 100%;
      height: 100%;
      margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
      }
    }
  }
</style>
