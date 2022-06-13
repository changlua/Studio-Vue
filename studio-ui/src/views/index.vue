<template>
  <div class="dashboard-container">
    <!--  长引用的意思  -->
    <blockquote>
      欢迎回来{{name}}，长路工作室的小伙伴！
    </blockquote>
    <el-row :gutter="20">
      <PanelGroup />
    </el-row>
    <el-row type="flex" justify="center" style="margin-left: -69px;margin-right: 82px;!important;" :gutter="40">
      <el-col :span="10">
        <el-card class="box-card box1" style="height: 350px;width: 550px">
          <vue-echarts :option="grades_options" style="height: 300px;width: 450px"></vue-echarts>
        </el-card>
      </el-col>
      <el-col :span="10" class="majorslayout">
        <el-card class="box-card box2" style="height: 350px;width: 700px">
          <vue-echarts :option="majors_options" style="height: 300px;width: 700px"></vue-echarts>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import PanelGroup from './dashboard/components/PanelGroup'

import { getOptions } from '@/api/index'

export default {
  name: 'Index',
  components: {
    PanelGroup
  },
  // 定义属性
  data () {
    return {
      grades_options: {
        title: {
          text: "获奖年份划分",
          subtext: '统计近四年'
        },
        tooltip: {},
        legend: {
          data: ['竞赛', '证书']
        },
        xAxis: {
          // data: ["2019级", "2020级", "2021级", "2022级"],
          // data: testJSON.grades.names,
          data: [],
        },
        yAxis: {},
        series: [
          {
            name: "竞赛",
            type: "bar",
            // data: [5, 20, 36, 10, 10, 20],
            // data: testJSON.grades.values.races,
            data: [],
            itemStyle: {
              color: "#8b6f62"
            }
          },
          {
            name: "证书",
            type: "bar",
            // data: [5, 20, 36, 10, 10, 20],
            // data: testJSON.grades.values.ccies,
            data: [],
            itemStyle: {
              color: "#fcc871"
            }
          },
        ],
      },
      majors_options: {
        title: { text: "专业划分" },
        tooltip: {},
        legend: {
          data: ['个人竞赛','证书']
        },
        xAxis: {
          data: ["计算机应用技术", "物联网应用技术", "大数据技术"],
          // data: testJSON.majors.names,
          // data: [],
          nameTextStyle: {
            fontStyle: "italic",
            overflow: "break"
          },
          //解决x轴文字过长问题，分成两行展示
          axisLabel: {
            interval: 0,
            formatter:function(value,index){
              if(index % 2 != 0){
                return '\n\n' + value;
              }else{
                return value;
              }
            }
          }
        },
        yAxis: {},
        series: [
          {
            name: "个人竞赛",
            type: "bar",
            // data: [5, 20, 36, 10, 10,15,20],
            // data: testJSON.majors.values.races,
            data: [],
            itemStyle: {
              color: "#8b6f62"
            }
          },
          {
            name: "证书",
            type: "bar",
            // data: [5, 20, 36, 10, 10,15,20],
            // data: testJSON.majors.values.ccies,
            data: [],
            itemStyle:{
              color: "#fcc871"
            }
          },
        ],
      },
      //用户名
      name: "",
      testJSON: {}
      // testJSON: {
      //   "grades": {
      //     names: ["2019级", "2020级", "2021级", "2022级"],
      //     values: {
      //       races: [5, 20, 36, 10],
      //       ccies: [5, 20, 36, 10]
      //     }
      //   },
      //   "majors": {
      //     names: ["计算机应用技术", "物联网应用技术", "大数据技术", "移动应用开发","软件技术","计算机网络技术","VR虚拟现实"],
      //     values: {
      //       races: [5, 20, 36, 10, 10,15,20],
      //       ccies: [5, 20, 100, 10, 10,15,20]
      //     }
      //   }
      // }
    }
  },
  // 生命周期 - 创建完成（可以访问当前this实例）
  created () {
    this.name = this.$store.getters.name
  },
  // 方法集合
  methods: {
    initOptions(){
      getOptions().then((response)=>{
        // console.log(response)
        this.testJSON = response.data
        // console.log("this.testJSON=>", this.testJSON.grades.names)
        //初始化年级
        this.grades_options.xAxis.data = this.testJSON.grades.names
        this.grades_options.series[0].data = this.testJSON.grades.values.races
        this.grades_options.series[1].data = this.testJSON.grades.values.ccies
        // console.log("this.grades_options=>", this.grades_options)
        //初始化专业
        this.majors_options.xAxis.data = this.testJSON.majors.names
        this.majors_options.series[0].data = this.testJSON.majors.values.races
        this.majors_options.series[1].data = this.testJSON.majors.values.ccies
        // console.log("this.majors_options=>", this.majors_options)
      }).catch(err=>console.log(err))

    }
  },
  // 计算属性，会监听依赖属性值随之变化
  computed: {
  },
  // 监控data中的数据变化
  watch: {},
  // 生命周期 - 挂载完成（可以访问DOM元素）
  mounted () {
    this.initOptions();
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
  .dashboard-container{
      padding: 20px 45px;

      .box-card{

        border-radius: 11px;

        &.box1{
          padding: 39px 20px 20px 40px;
        }

        &.box2{
          padding: 32px 20px 0px 7px;
        }
      }

      blockquote{
        padding: 10px 20px;
        margin: 0 0 20px;
        font-size: 17.5px;
        border-left: 5px solid #eee;
      }

      font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
      font-size: 13px;
      color: #676a6c;
      overflow-x: hidden;
  }
  @media (max-width:1300px){
    .majorslayout{
      position: relative !important;
      left: -295px !important;
      top: 316px !important;
      //.el-card .box-card .is-always-shadow{
      //  position: relative;
      //  left: -295px;
      //  top: 316px;
      //}
    }

  }

</style>
