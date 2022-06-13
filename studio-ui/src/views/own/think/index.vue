<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="思考标题" prop="thinkTitle">
        <el-input
          v-model="queryParams.thinkTitle"
          placeholder="请输入思考标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" v-hasPermi="['own:think:list']">搜索</el-button>
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
          v-hasPermi="['own:think:add']"
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
          v-hasPermi="['own:think:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['own:think:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="thinkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="序号"
        type="index"
        width="50">
      </el-table-column>
<!--      <el-table-column label="个人心得主键id" align="center" prop="thinkId" />-->
      <el-table-column label="标题" align="center" prop="thinkTitle" />
      <el-table-column label="感悟思考" align="center" prop="content" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-opportunity"
            @click="previewThink(scope.row)"
          >预览心得</el-button>
          <el-button
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['own:think:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['own:think:remove']"
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

    <!-- 预览总结对话框-->
    <el-dialog class="previewDialog" title="感悟思考" :visible.sync="previewOpen" :before-close="handlePreviewClose" width="1000px" append-to-body>
      <Editor v-model="curThinkHTML" :minHeight="200" :readOnly="true"></Editor>
    </el-dialog>

    <!-- 添加或修改ZfThink对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" :close-on-click-modal="false" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="思考标题" prop="thinkTitle">
          <el-input v-model="form.thinkTitle" placeholder="请输入思考标题" />
        </el-form-item>
        <el-form-item label="感悟思考">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" v-hasPermi="['own:think:add','own:think:edit']">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listThink, getThink, delThink, addThink, updateThink } from "@/api/own/think";

export default {
  name: "Think",
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
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // ZfThink表格数据
      thinkList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 感悟思考时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        thinkTitle: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        thinkTitle: [
          { required: true, message: "思考标题不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "感悟思考不能为空", trigger: "blur" }
        ],
      },
      //是否打开预览感悟思考窗口
      previewOpen: false,
      //思考内容
      curThinkHTML: '',
      //删除提示信息
      names: ""
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询ZfThink列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listThink(this.queryParams).then(response => {
        this.thinkList = response.rows;
        this.total = response.total;
        this.loading = false;
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
        thinkId: null,
        thinkTitle: null,
        content: null,
        createTime: null,
        updateTime: null,
        userId: null
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
      this.daterangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.thinkId)
      this.single = selection.length!==1
      this.multiple = !selection.length
      //构造出删除项的提示内容
      let names = '';
      selection.forEach((ele,index)=>{
        if (index == selection.length-1) {
          names += ele.thinkTitle
        }else{
          names += ele.thinkTitle + ","
        }
      })
      this.names = names
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加个人心得";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const thinkId = row.thinkId || this.ids
      getThink(thinkId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改个人心得";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.thinkId != null) {
            updateThink(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addThink(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const thinkIds = row.thinkId || this.ids;
      const names = row.thinkTitle || this.names
      this.$modal.confirm('是否确认删除标题为"' + names + '"的数据项？').then(function() {
        return delThink(thinkIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    //预览思考总结
    previewThink(row){
      this.previewOpen = true;
      this.curThinkHTML = row.content
    },
    //关闭预览窗口
    handlePreviewClose(){
      this.previewOpen = false;
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('own/think/export', {
        ...this.queryParams
      }, `think_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<style>
  /*调整预览的样式*/
  .previewDialog .ql-toolbar.ql-snow{
    display: none;
  }

  .previewDialog .editor{
    border: 1px solid #ccc !important;
  }
</style>
