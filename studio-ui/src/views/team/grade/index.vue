<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="年级" prop="gradeNum">
        <el-input
          v-model="queryParams.gradeNum"
          placeholder="请输入年级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" v-hasPermi="['team:grade:list']">搜索</el-button>
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
          v-hasPermi="['team:grade:add']"
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
          v-hasPermi="['team:grade:edit']"
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
          v-hasPermi="['team:grade:remove']"
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

    <el-table v-loading="loading" :data="gradeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="序号"
        type="index"
        width="50">
      </el-table-column>
<!--      <el-table-column label="年级主键id" align="center" prop="gradeId" />-->
      <el-table-column label="年级" align="center" prop="gradeNum" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['team:grade:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['team:grade:remove']"
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

    <!-- 添加或修改ZfGrade对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" :close-on-click-modal="false" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="年级" prop="gradeNum">
          <el-input v-model="form.gradeNum" placeholder="请输入年级" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" v-hasPermi="['team:major:grade', 'team:major:grade']">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listGrade, getGrade, delGrade, addGrade, updateGrade } from "@/api/team/grade";

export default {
  name: "Grade",
  data() {
    //校验年级
    var validateGrade= (rule, value, callback) => {
      if (value === '') {
        callback(new Error('年级不能为空'));
      } else if (!Number.isInteger(Number(value))) {
        callback(new Error('年级必须是数字'));
      } else {
        callback();
      }
    };
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
      // ZfGrade表格数据
      gradeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        gradeNum: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        gradeNum: [
          // { required: true, message: "年级不能为空"},
          // { type: 'number', message: "必须为数字值",trigger: 'blur'}
          { validator: validateGrade, trigger: 'blur'}
        ]
      },
      //删除提示信息
      names: ""
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询ZfGrade列表 */
    getList() {
      this.loading = true;
      listGrade(this.queryParams).then(response => {
        this.gradeList = response.rows;
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
        gradeId: null,
        gradeNum: null
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.gradeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
      //构造出删除项的提示内容
      let names = '';
      selection.forEach((ele,index)=>{
        if (index == selection.length-1) {
          names += ele.gradeNum
        }else{
          names += ele.gradeNum + ","
        }
      })
      this.names = names
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加年级";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const gradeId = row.gradeId || this.ids
      getGrade(gradeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改年级";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.gradeId != null) {
            updateGrade(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGrade(this.form).then(response => {
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
      const gradeIds = row.gradeId || this.ids;
      const names = row.gradeNum || this.names
      this.$modal.confirm('是否确认删除年级为"' + names + '"的数据项？').then(function() {
        return delGrade(gradeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    // handleExport() {
    //   this.download('team/grade/export', {
    //     ...this.queryParams
    //   }, `grade_${new Date().getTime()}.xlsx`)
    // }
  }
};
</script>
