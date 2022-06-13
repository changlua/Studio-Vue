<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="竞赛名称" prop="raceName">
        <el-input
          v-model="queryParams.raceName"
          placeholder="请输入竞赛名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="竞赛成员" prop="raceMembers">
<!--        <el-input-->
<!--          v-model="queryParams.raceMembers"-->
<!--          placeholder="请输入竞赛成员"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
        <el-select v-model="queryParams.raceMembers" filterable placeholder="请选择">
          <el-option
            v-for="item in memberOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="竞赛开始时间" label-width="100px">
        <el-date-picker
          v-model="daterangeRaceBeginTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="竞赛结束时间" label-width="100px">
        <el-date-picker
          v-model="daterangeRaceEndTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="竞赛类型" prop="raceFlag">
        <el-select v-model="queryParams.raceFlag" filterable placeholder="请选择">
          <el-option label="个人" value="1"></el-option>
          <el-option label="团队" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" v-hasPermi="['team:race:list']">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasRole="['manage', 'teacher']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasRole="['manage', 'teacher']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="teamMultiple"
          @click="handleDelete"
          v-hasRole="['manage', 'teacher']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['team:race:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="raceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="序号"
        type="index"
        width="50">
      </el-table-column>
<!--      <el-table-column label="竞赛主键id" align="center" prop="raceId" />-->
      <el-table-column label="竞赛名称" align="center" prop="raceName" />
      <el-table-column label="竞赛类型" align="center" prop="raceFlag" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.raceFlag == '1'" type="success">个人</el-tag>
          <el-tag v-else type="danger">团队</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="竞赛成员" align="center" prop="teamMemberNames" />
      <el-table-column label="成员信息" align="center" prop="raceFlag" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-custom"
            @click="seeMemberInfo(scope.row)"
          >查看信息</el-button>
        </template>
      </el-table-column>
      <el-table-column label="竞赛开始时间" align="center" prop="raceBeginTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.raceBeginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="竞赛结束时间" align="center" prop="raceEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.raceEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="获奖证书图片" align="center" prop="raceCcie" width="200">
        <!--    挂载预览图片    -->
        <template slot-scope="scope">
          <image-preview :src="scope.row.raceCcie" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="参赛图片" align="center" prop="pics" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-picture-outline"
            @click="previewPics(scope.row)"
          >查看图片</el-button>
        </template>
      </el-table-column>
      <el-table-column label="经验总结" align="center" prop="raceSummarize" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-opportunity"
            @click="previewThink(scope.row)"
          >预览总结</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            :disabled="scope.row.raceFlag == '1'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasRole="['manage', 'teacher']"
          >修改</el-button>
          <el-button
            :disabled="scope.row.raceFlag == '1'"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasRole="['manage']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 参与成员-->
    <el-dialog class="previewDialog" title="参与成员" :visible.sync="memberInfoOpen" :before-close="handleMemberClose" width="800px" append-to-body>
      <el-row :gutter="10" type="flex" justify="center">
        <el-col :offset="2">
          <el-table :data="membersTable" style="width: 100%" max-height="300">
            <el-table-column fixed prop="realName" label="姓名" width="150"></el-table-column>
            <el-table-column fixed prop="roleName" label="角色" width="150"></el-table-column>
            <el-table-column fixed prop="gradeNum" label="年级" width="150"></el-table-column>
            <el-table-column fixed prop="majorName" label="专业" width="150"></el-table-column>
          </el-table>
        </el-col>
      </el-row>
    </el-dialog>

    <!-- 预览竞赛图片对话框-->
    <el-dialog class="previewPicsDialog" title="竞赛图片" :visible.sync="previewPicsOpen" :before-close="handlePreviewPicsClose" width="1000px" append-to-body>
      <viewer :images="curPics">
        <ul class="imgUrl">
          <li v-for="(pic,index) of curPics">
            <img :src="pic.resUrl" :key="index" alt="图片描述">
          </li>
        </ul>
      </viewer>
    </el-dialog>

    <!-- 预览总结对话框-->
    <el-dialog class="previewDialog" title="经验总结" :visible.sync="previewOpen" :before-close="handlePreviewClose" width="1000px" append-to-body>
      <Editor v-model="curThinkHTML" :minHeight="200" :readOnly="true"></Editor>
    </el-dialog>

    <!-- 添加或修改ZfRace对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="竞赛名称" prop="raceName">
          <el-input v-model="form.raceName" placeholder="请输入竞赛名称" />
        </el-form-item>
        <el-form-item label="竞赛成员" prop="raceMembers">
          <el-select v-model="form.raceMembers" multiple placeholder="请选择">
            <el-option
              v-for="item in memberOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
<!--          <el-input v-model="form.raceMembers" placeholder="请输入竞赛成员" />-->
        </el-form-item>
        <el-form-item label="竞赛开始时间" prop="raceBeginTime">
          <el-date-picker clearable
                          v-model="form.raceBeginTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择竞赛开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="竞赛结束时间" prop="raceEndTime">
          <el-date-picker clearable
                          v-model="form.raceEndTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择竞赛结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="获奖证书">
          <image-upload v-model="form.raceCcie" :limit="1" :fileSize="10"/>
        </el-form-item>
        <el-form-item label="参赛图片">
          <image-upload v-model="form.pics" :limit="5" :fileSize="15"/>
        </el-form-item>
        <el-form-item label="经验总结" prop="raceSummarize">
          <Editor v-model="form.raceSummarize" :minHeight="200"></Editor>
        </el-form-item>
        <el-tooltip class="item" effect="dark" content="团队成员也可以编辑噢！" placement="top-start">
          <i class="el-icon-info" style="position: relative;bottom: 252px;left: 28px;color: #409EFF;"></i>
        </el-tooltip>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getRace, delRace,getRaceMembers, addRace, updateRace } from "@/api/own/race";
import { listRace ,getMemberOptions } from "@/api/team/race";

export default {
  name: "Race",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 非团队禁用
      teamMultiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // ZfRace表格数据
      raceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 经验总结时间范围
      daterangeRaceBeginTime: [],
      // 经验总结时间范围
      daterangeRaceEndTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        raceName: null,
        raceMembers: null,
        raceBeginTime: null,
        raceEndTime: null,
        raceFlag: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        raceName: [
          { required: true, message: "竞赛名称不能为空", trigger: "blur" }
        ],
        raceMembers: [
          { required: true, message: "竞赛成员不能为空", trigger: "blur" }
        ],
        raceBeginTime: [
          { required: true, message: "竞赛开始时间不能为空", trigger: "blur" }
        ],
        raceEndTime: [
          { required: true, message: "竞赛结束时间不能为空", trigger: "blur" }
        ],
      },
      //是否显示预览图片弹出层
      previewPicsOpen: false,
      //当前预览的图片集
      curPics: [],
      //是否显示预览弹出层
      previewOpen: false,
      //当前思考总结的HTML
      curThinkHTML: '',
      //成员选项 [{label:'',value:''},{label:'',value:''}]
      memberOptions: [],
      value1: '',
      //删除提示信息
      names: "",
      //是否显示查看成员信息弹出层
      memberInfoOpen: false,
      //成员表格信息
      membersTable: [],
    };
  },
  created() {
    this.getList();
    //获取菜单选项信息
    this.getOptions();
  },
  methods: {
    /** 查询ZfRace列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeRaceBeginTime && '' != this.daterangeRaceBeginTime) {
        this.queryParams.params["beginRaceBeginTime"] = this.daterangeRaceBeginTime[0];
        this.queryParams.params["endRaceBeginTime"] = this.daterangeRaceBeginTime[1];
      }
      if (null != this.daterangeRaceEndTime && '' != this.daterangeRaceEndTime) {
        this.queryParams.params["beginRaceEndTime"] = this.daterangeRaceEndTime[0];
        this.queryParams.params["endRaceEndTime"] = this.daterangeRaceEndTime[1];
      }
      listRace(this.queryParams).then(response => {
        this.raceList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getOptions(){
      getMemberOptions().then(response => {
        //构造选项数组
        const mOptions = []
        response.data.forEach((user)=>{
          mOptions.push({
            "label": user.realName,
            "value": user.userId
          })
        })
        this.memberOptions = mOptions
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        raceId: null,
        raceName: null,
        raceMembers: null,
        raceBeginTime: null,
        raceEndTime: null,
        raceFlag: null,
        raceSummarize: null,
        createTime: null,
        updateTime: null,
        userIds: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      console.log("value1:",this.value1)
      this.daterangeRaceBeginTime = [];
      this.daterangeRaceEndTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      // console.log("selection=>", selection.length)
      this.ids = selection.map(item => item.raceId)
      this.single = selection.length!==1
      this.multiple = !selection.length
      //选中的必须都是团队teamMultiple=true
      let i = 0;
      while (i < selection.length) {
        if (selection[i].raceFlag == "1") {
          this.teamMultiple = true
          break;
        }
        i++;
      }
      if (i == selection.length) {
        this.teamMultiple = false
      }
      //构造出删除项的提示内容
      let names = '';
      selection.forEach((ele,index)=>{
        if (index == selection.length-1) {
          names += ele.raceName
        }else{
          names += ele.raceName + ","
        }
      })
      this.names = names
    },
    //打开成员信息对话框
    seeMemberInfo(row){
      // console.log(row)
      getRaceMembers(row.raceId).then((response)=>{
        this.membersTable = response.data
      }).catch(err=>console.log(err))
      this.memberInfoOpen = true
    },
    //关闭成员信息对话框
    handleMemberClose(){
      this.memberInfoOpen = false
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加团队竞赛";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const raceId = row.raceId || this.ids
      getRace(raceId).then(response => {
        this.form = response.data;
        //竞赛id字符串转为数组，如："1,2" => ["1","2"]，后面的map(Number)意思是["1","2"]=>[1,2]
        this.form.raceMembers = response.data.raceMembers.split(",").map(Number)
        this.open = true;
        this.title = "修改团队竞赛";
        //获取团队成员选项信息
        this.getOptions();
        //处理返回过来的图片资源
        let pics = []
        this.form.pics.forEach((pic)=>{
          pics.push(pic.resUrl)
        })
        pics = pics.join(",")
        this.form.pics = pics
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          //对提交的表单进行处理
          this.processForm()
          //进行表单提交操作
          if (this.form.raceId != null) {
            updateRace(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRace(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    //对要提交的表单进行处理
    processForm(){
      //1、处理参赛成员：将数组[1,2] => "1,2"来传递到后台保存
      this.form.raceMembers = this.form.raceMembers.join(",")
      //2、处理图片资源：将多个图片合成的字符串来转为对象存储 "xxx.jpg,xxx.png" => [{resUrl:"xxx.jpg",resName:"xxx.jpg"},{...}]
      let formPics = []
      let pics = this.form.pics
      const picArr = pics.split(",")  //现将多个图片url使用,相隔的转为数组，接着封装为对象
      picArr.forEach((pic)=>{
        formPics.push({
          "resUrl": pic,
          "resName": pic.substring(pic.lastIndexOf('/') + 1)
        })
      })
      this.form.pics = formPics
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const raceIds = row.raceId || this.ids;
      this.names = row.raceName || this.names
      this.$modal.confirm('是否确认删除团队竞赛为"' + this.names + '"的数据项？').then(function() {
        return delRace(raceIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    //预览多张图片
    previewPics(row){
      this.previewPicsOpen = true
      this.curPics = row.pics
      console.log("row.pics",row.pics)
    },
    //关闭图片预览窗口
    handlePreviewPicsClose(){
      this.previewPicsOpen = false
    },
    //预览思考总结
    previewThink(row){
      this.previewOpen = true;
      this.curThinkHTML = row.raceSummarize
    },
    //关闭预览窗口
    handlePreviewClose(){
      this.previewOpen = false;
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('team/race/export', {
        ...this.queryParams
      }, `竞赛_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<style scoped>
  /*调整预览的样式*/
  .previewDialog .ql-toolbar.ql-snow{
    display: none;
  }

  .previewDialog .editor{
    border: 1px solid #ccc !important;
  }

  .imgUrl {
    display: flex;
    flex-wrap: wrap;
  }
  .imgUrl li{
    width: 175px;
    height: 148px;
    list-style: none;
    border:2px solid #CCC;
    border-radius: 3px;
    padding: 1px;
    margin: 10px;
    cursor: pointer;
  }
  .imgUrl li img{
    width:100%;
    height: 100%;
  }
</style>
