<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="证书名称" prop="ccieName">
        <el-input
          v-model="queryParams.ccieName"
          placeholder="请输入证书名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="realName">
        <el-input
          v-model="queryParams.realName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="年级" prop="gradeId">
        <el-select v-model="queryParams.gradeId" placeholder="请选择年级" filterable clearable
                   :style="{width: '100%'}" @keyup.enter.native="handleQuery">
          <el-option v-for="(item, index) in gradeIdOptions" :key="index" :label="item.gradeNum"
                     :value="item.gradeId" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="专业" prop="majorId">
        <el-select v-model="queryParams.majorId" placeholder="请选择专业" filterable clearable
                   :style="{width: '100%'}" @keyup.enter.native="handleQuery">
          <el-option v-for="(item, index) in majorIdOptions" :key="index" :label="item.majorName"
                     :value="item.majorId" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
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
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" v-hasPermi="['team:ccie:list']">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['team:ccie:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
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
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['team:ccie:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ccieList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="序号"
        type="index"
        width="50">
      </el-table-column>
      <el-table-column label="获奖时间" align="center" prop="ccciGetTime" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.ccciGetTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="证书名称" align="center" prop="ccieName" width="200"/>
<!--      <el-table-column label="获奖证书主键id" align="center" prop="ccieId" />-->
      <el-table-column label="姓名" align="center" prop="realName" width="120"/>
      <el-table-column label="年级" align="center" prop="gradeNum" width="150"/>
      <el-table-column label="专业" align="center" prop="majorName" width="180"/>
      <el-table-column label="获奖证书" align="center" prop="ccieImg" width="200">
        <template slot-scope="scope">
          <image-preview :src="scope.row.ccieImg" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasRole="['manage', 'teacher']"
          >修改</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--          >删除</el-button>-->
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

    <!-- 添加或修改ZfCcie对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="证书名称" prop="ccieName">
          <el-input v-model="form.ccieName" placeholder="请输入证书名称" />
        </el-form-item>
        <el-form-item label="获奖时间" prop="ccciGetTime">
          <el-date-picker clearable
                          v-model="form.ccciGetTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择获奖时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" v-hasRole="['manage', 'teacher']">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getCcie, delCcie, updateCcie } from "@/api/own/ccie";
import { listCcie } from "@/api/team/ccie";
import infoApi from "@/api/own/info";

export default {
  name: "Ccie",
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
      // ZfCcie表格数据
      ccieList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 用户id时间范围
      daterangeCcciGetTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ccieName: null,
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
      //菜单信息
      //[{},{}]格式
      majorIdOptions: [],
      gradeIdOptions: [],
      //提示信息
      name: ""
    };
  },
  created() {
    this.getList();
    this.getMenu();
  },
  methods: {
    /** 查询ZfCcie列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCcciGetTime && '' != this.daterangeCcciGetTime) {
        this.queryParams.params["beginCcciGetTime"] = this.daterangeCcciGetTime[0];
        this.queryParams.params["endCcciGetTime"] = this.daterangeCcciGetTime[1];
      }
      listCcie(this.queryParams).then(response => {
        this.ccieList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //查询菜单信息
    getMenu(){
      infoApi.getMenu().then(data => {
        // console.log("getMenus:",data)
        let results = data.data
        this.majorIdOptions = results.majors
        this.gradeIdOptions = results.grades
        this.gradeIdOptions.forEach((grade)=>grade.gradeNum = grade.gradeNum + "级")
      }).catch(err=>console.log(err))
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
      this.single = selection.length!==1
      this.multiple = !selection.length
      //统计选中的名称
      const arr = []
      selection.forEach((sele)=>{
        arr.push(`${sele.realName}的【${sele.ccieName}】证书`)
      })
      this.name = arr.join(',')
    },
    /** 新增按钮操作 */
    // handleAdd() {
    //   this.reset();
    //   this.open = true;
    //   this.title = "添加ZfCcie";
    // },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ccieId = row.ccieId || this.ids
      getCcie(ccieId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改证书";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
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
    // handleDelete(row) {
    //   console.log("row=>",row)
    //   if (row.ccieId) {
    //     this.name = `${row.realName}的【${row.ccieName}】证书`
    //   }
    //   const ccieIds = row.ccieId || this.ids;
    //   this.$modal.confirm('是否确认删除' + this.name + '"的数据项？').then(function() {
    //     return delCcie(ccieIds);
    //   }).then(() => {
    //     this.getList();
    //     this.$modal.msgSuccess("删除成功");
    //   }).catch(() => {});
    // },
    /** 导出按钮操作 */
    handleExport() {
      this.download('team/ccie/export', {
        ...this.queryParams
      }, `证书统计_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
