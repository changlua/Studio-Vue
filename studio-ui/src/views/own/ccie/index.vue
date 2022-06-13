<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="获奖时间">
        <el-date-picker
          v-model="daterangeCcciGetTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" v-hasPermi="['own:ccie:list']" @click="handleQuery">搜索</el-button>
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
          v-hasPermi="['own:ccie:add']"
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
          v-hasPermi="['own:ccie:edit']"
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
          v-hasPermi="['own:ccie:remove']"
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

    <el-table v-loading="loading" :data="ccieList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="序号"
        type="index"
        width="50">
      </el-table-column>
<!--      <el-table-column label="获奖证书主键id" align="center" prop="ccieId" />-->
      <el-table-column label="证书名称" align="center" prop="ccieName" width="200"/>
      <el-table-column label="获奖证书" align="center" prop="ccieImg" width="200">
        <!--    挂载预览图片    -->
        <template slot-scope="scope">
          <image-preview :src="scope.row.ccieImg" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="获奖时间" align="center" prop="ccciGetTime" width="300">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.ccciGetTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="经验总结" align="center" prop="ccieThink" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-opportunity"
            @click="previewThink(scope.row)"
            v-hasRole="['member']"
          >预览总结</el-button>
          <el-button
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['own:ccie:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['own:ccie:remove']"
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
    <el-dialog class="previewDialog" title="经验总结" :visible.sync="previewOpen" :before-close="handlePreviewClose" width="1000px" append-to-body>
      <Editor v-model="curThinkHTML" :minHeight="200" :readOnly="true"></Editor>
    </el-dialog>

    <!-- 添加或修改ZfCcie对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" :close-on-click-modal="false" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="证书名称" prop="ccieName">
          <el-input v-model="form.ccieName" placeholder="请输入证书名称" />
        </el-form-item>
        <el-form-item label="获奖证书">
          <image-upload v-model="form.ccieImg" :limit="1" :fileSize="10"/>
        </el-form-item>
        <el-form-item label="获奖时间" prop="ccciGetTime">
          <el-date-picker clearable
            v-model="form.ccciGetTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择获奖时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="经验总结" prop="ccieThink">
          <Editor v-model="form.ccieThink" :minHeight="200"></Editor>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm"  v-hasPermi="['own:ccie:add','own:ccie:edit']">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCcie, getCcie, delCcie, addCcie, updateCcie } from "@/api/own/ccie";

export default {
  name: "Ccie",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      //选中内容名称
      names: '',
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // ZfCcie表格数据
      ccieList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //是否显示预览弹出层
      previewOpen: false,
      // 经验总结时间范围
      daterangeCcciGetTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ccieImg: null,
        ccciGetTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        ccieName: [
          { required: true, message: "证书名称不能为空", trigger: "blur" }
        ],
      },
      //当前思考总结的HTML
      curThinkHTML: ''
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询ZfCcie列表 */
    getList() {
      this.loading = true;
      //构造查询对象
      this.queryParams.params = {};
      if (null != this.daterangeCcciGetTime && '' != this.daterangeCcciGetTime) {
        this.queryParams.params["beginCcciGetTime"] = this.daterangeCcciGetTime[0];
        this.queryParams.params["endCcciGetTime"] = this.daterangeCcciGetTime[1];
      }
      listCcie(this.queryParams).then(response => {
        // console.log ("response=>",response)
        this.ccieList = response.rows;
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
        ccieId: null,
        ccieName: null,
        ccieImg: null,
        ccciGetTime: null,
        ccieThink: null,
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
      this.daterangeCcciGetTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.ccieId)
      //构造出删除项的提示内容
      let names = '';
      selection.forEach((ele,index)=>{
        if (index == selection.length-1) {
          names += ele.ccieName
        }else{
          names += ele.ccieName + ","
        }
      })
      this.names = names
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加获奖证书";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();//重置表单
      const ccieId = row.ccieId || this.ids
      getCcie(ccieId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改获奖证书";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          //使用表单是否有id来作为判断条件
          if (this.form.ccieId != null) {
            updateCcie(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCcie(this.form).then(response => {
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
      const ccieIds = row.ccieId || this.ids;
      const names = row.ccieName || this.names
      this.$modal.confirm('是否确认删除证书名称为"' + names + '"？').then(function() {
        return delCcie(ccieIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    //预览思考总结
    previewThink(row){
      this.previewOpen = true;
      this.curThinkHTML = row.ccieThink
    },
    handlePreviewClose(){
      this.previewOpen = false;
    },
    /** 导出按钮操作 */
    // handleExport() {
    //   this.download('own/ccie/export', {
    //     ...this.queryParams
    //   }, `获奖证书_${new Date().getTime()}.xlsx`)  //对应的文件名为获奖证书_时间戳
    // }
  }
};
</script>

<style >

  /*调整预览的样式*/
  .previewDialog .ql-toolbar.ql-snow{
    display: none;
  }

  .previewDialog .editor{
    border: 1px solid #ccc !important;
  }
</style>
