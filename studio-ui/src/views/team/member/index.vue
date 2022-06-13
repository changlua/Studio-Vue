<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
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
      <el-form-item label="角色" prop="role">
        <el-select v-model="queryRoleIds" placeholder="请选择角色" filterable multiple clearable
                   :style="{width: '100%'}" @keyup.enter.native="handleQuery">
<!--          <el-option :key="0" label="负责人"-->
<!--                     :value="2"></el-option>-->
<!--          <el-option :key="1" label="成员"-->
<!--                     :value="3"></el-option>-->
          <el-option :key="2" label="历届负责人"
                     :value="4"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="专业" prop="majorId">
        <el-select v-model="queryParams.majorId" placeholder="请选择专业" filterable clearable
                   :style="{width: '100%'}" @keyup.enter.native="handleQuery">
          <el-option v-for="(item, index) in majorIdOptions" :key="index" :label="item.majorName"
                     :value="item.majorId" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="用户性别" prop="sex" >
        <el-select v-model="queryParams.sex" placeholder="请选择用户性别" filterable clearable
                   :style="{width: '100%'}" @keyup.enter.native="handleQuery">
          <el-option :key="0" label="男"
                     :value="0"></el-option>
          <el-option :key="1" label="女"
                     :value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="帐号状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请输入帐号状态" filterable clearable
                   :style="{width: '100%'}" @keyup.enter.native="handleQuery">
          <el-option :key="0"  label="正常"
                     :value="0"></el-option>
          <el-option :key="1" label="停用"
                     :value="1"></el-option>
        </el-select>
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
      <el-form-item style="margin-left: 20px">
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" v-hasPermi="['team:member:list']">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-tooltip class="item" effect="dark" content="邀请码：EMb*Sy6" placement="top">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['team:member:add']"
          >创建账号</el-button>
        </el-tooltip>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['team:member:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleTransfer"
          v-hasRole="['manage', 'teacher']"
        >转让负责人</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-tooltip class="item" effect="dark" content="重置密码：123456" placement="top">
          <el-button
            type="danger"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="multiple"
            @click="handleResetPwd"
            v-hasPermi="['team:member:resetpwd']"
          >重置密码</el-button>
        </el-tooltip>
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
          v-hasPermi="['team:member:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleBackup"
        >数据备份</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="memberList" @selection-change="handleSelectionChange" style="width: 100%">
      <el-table-column fixed type="selection" width="55" align="center" />
      <el-table-column fixed
        label="序号"
        type="index"
        width="50">
      </el-table-column>
      <el-table-column fixed width="100" label="账号名称" align="center" prop="userName" />
      <el-table-column fixed width="100" label="姓名" align="center" prop="realName" />
<!--      <el-table-column label="用户ID" align="center" prop="userId" />-->
      <el-table-column width="150" label="角色" align="center" prop="roleName" >
        <template slot-scope="scope">
          <el-tag v-if="!isEmpty(scope.row.roleName) && scope.row.roleName.indexOf('负责人') != -1"  type="danger">
            {{isEmpty(scope.row.gradeNum) ? 'xxx级负责人' : scope.row.gradeNum + "负责人"}}
          </el-tag>
          <el-tag v-else type="">
            {{ isEmpty(scope.row.roleName) ? '暂无' :  (isEmpty(scope.row.gradeNum) ? 'xxx级' + scope.row.roleName :scope.row.gradeNum + scope.row.roleName)}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column width="150" label="专业" align="center" prop="majorName" />
      <el-table-column width="150" label="年级" align="center" prop="gradeNum" >
        <template slot-scope="scope">
          <span>{{scope.row.gradeNum ? scope.row.gradeNum : ''}}</span>
        </template>
      </el-table-column>
      <el-table-column width="100" label="用户性别" align="center" prop="sex" >
        <template slot-scope="scope">
          <span>{{scope.row.sex == 0 ? '男' : '女'}}</span>
        </template>
      </el-table-column>
      <el-table-column width="150" label="手机号码" align="center" prop="phonenumber" />
      <el-table-column width="180 " label="用户邮箱" align="center" prop="email" />
      <el-table-column width="150" label="帐号状态" align="center" prop="status" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status == 0"  type="success" effect="plain">
            {{"正常"}}
          </el-tag>
          <el-tag v-else type="info" effect="plain">
            {{"已注销"}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column width="150" label="创建时间" align="center" prop="createTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" width="200" label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['team:member:edit']"
          >修改</el-button>
          <el-button v-show="scope.row.status == 1"
                     size="mini"
                     type="text" style="color: #F56C6C"
                     icon="el-icon-magic-stick"
                     @click="handleActive(scope.row)"
                     v-hasPermi="['team:member:active']"
          >激活账号</el-button>
          <el-button v-if="scope.row.status == 0"
            size="mini"
            type="text" style="color: #F56C6C"
            icon="el-icon-delete"
            @click="handleCancelled(scope.row)"
                     v-hasPermi="['team:member:cancelled']"
          >注销账号</el-button>
          <el-button v-else
            size="mini"
            type="text" style="color: #F56C6C"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
                     v-hasPermi="['team:member:remove']"
          >删除账号</el-button>
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

    <!--  添加用户对话框  -->
    <el-dialog title="创建账号" :visible.sync="addopen" width="500px" :close-on-click-modal="false" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户昵称" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户昵称" />
        </el-form-item>
        <el-form-item label="初始密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入初始密码" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <!--    确定提交按钮及取消选框  -->
        <el-button type="primary" @click="submitForm" v-hasPermi="['team:member:add']">确 定</el-button>
        <el-button @click="cancelAdd">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 修改User对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" :close-on-click-modal="false" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="majorName">
          <el-input v-model="form.realName" placeholder="请输入真实姓名" />
        </el-form-item>
        <el-form-item label="用户性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择用户性别" filterable clearable
                     :style="{width: '100%'}">
            <el-option :key="0" label="男"
                       :value="0"></el-option>
            <el-option :key="1" label="女"
                       :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年级" prop="majorName">
          <el-select v-model="form.gradeId" placeholder="请选择年级" filterable clearable
                     :style="{width: '100%'}">
            <el-option v-for="(item, index) in gradeIdOptions" :key="index" :label="item.gradeNum"
                       :value="item.gradeId" :disabled="item.disabled"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="专业名称" prop="majorName">
          <el-select v-model="form.majorId" placeholder="请选择专业" filterable clearable
                     :style="{width: '100%'}">
            <el-option v-for="(item, index) in majorIdOptions" :key="index" :label="item.majorName"
                       :value="item.majorId" :disabled="item.disabled"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" v-hasPermi="['team:member:edit']">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMember, getMember, delMember, updateMember, cancelledMember, activeMember, transfer, resetPwd, addMember } from "@/api/team/member";
import infoApi from "@/api/own/info";
import { loginPasswordEncrypt } from '@/utils/jsencrypt'

export default {
  name: "Member",
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
      // User表格数据
      memberList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示添加用户弹出层
      addopen: false,
      // 年级id时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sex: null,
        status: null,
        createTime: null,
        realName: null,
        majorId: null,
        gradeId: null,
        roleIds: null
      },
      queryRoleIds: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        //新增表单校验
        username: [
          { required: true, message: "用户昵称不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" }
        ],
      },
      //菜单信息
      //[{},{}]格式
      majorIdOptions: [],
      gradeIdOptions: [],
      //提示信息(账号名称)
      names: "",
      //转让信息
      transferTarget: {
        gradeNum: '',
        realName: ''
      }
    };
  },
  created() {
    this.getList();
    //获取菜单数据
    this.getMenu()
  },
  methods: {
    /** 查询User列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      //处理查询角色信息
      // console.log("roleIds => ", this.queryParams.roleIds)
      if (this.queryRoleIds && this.queryRoleIds.length > 0) {
        this.queryParams.roleIds = this.queryRoleIds.join(",")
      }else{
        this.queryParams.roleIds = ""
      }
      listMember(this.queryParams).then(response => {
        this.memberList = response.rows;
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
    // 取消添加窗口按钮
    cancelAdd(){
      this.addopen = false;
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        userId: null,
        userName: null,
        nickName: null,
        userType: null,
        email: null,
        phonenumber: null,
        sex: null,
        avatar: null,
        password: null,
        status: null,
        delFlag: null,
        loginIp: null,
        loginDate: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        realName: null,
        description: null,
        perImg: null,
        majorId: null,
        gradeId: null
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
      this.queryRoleIds = []
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      // console.log(selection)
      this.ids = selection.map(item => item.userId)
      this.single = selection.length!==1
      this.multiple = !selection.length
      //获取转移负责人的目标信息
      if (selection.length == 1) {
        this.transferTarget = {
            gradeNum: selection[0].gradeNum,
            realName: selection[0].realName
        }
      }
      //构造出删除项的提示内容
      let names = '';
      selection.forEach((ele,index)=>{
        if (index == selection.length-1) {
          names += ele.userName
        }else{
          names += ele.userName + ","
        }
      })
      this.names = names
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.addopen = true;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const userId = row.userId || this.ids
      //获取菜单数据
      this.getMenu()
      getMember(userId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户";
        this.form.sex = Number(this.form.sex)
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.userId != null) {
            updateMember(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            const addForm = {
              username: this.form.username,
              password: loginPasswordEncrypt(this.form.password)
            }
            addMember(addForm).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.addopen = false;
              this.getList();
            });
          }
        }
      });
    },
    /**重置密码**/
    handleResetPwd(row){
      const userIds = row.userId || this.ids
      this.$modal.confirm('是否对账号名称为"' + this.names + '"重置密码？').then(function() {
        return resetPwd(userIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("重置成功");
      }).catch(() => {});
    },
    /** 转让负责人 */
    handleTransfer(){
      const userId = this.ids[0]
      this.$modal.confirm('是否将负责人角色转让给' + this.transferTarget.gradeNum + "的"  + '"' + this.transferTarget.realName + '"？').then(function() {
        return transfer(userId);
      }).then(() => {
        location.href = '/index';
      }).catch(() => {});
    },
    /** 注销账号操作 */
    handleCancelled(row) {
      const userId = row.userId
      this.$modal.confirm('是否注销账号名称为"' + row.userName + '"的账号？').then(function() {
        return cancelledMember(userId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("注销成功");
      }).catch(() => {});
    },
    /** 激活账号操作 */
    handleActive(row){
      const userId = row.userId
      this.$modal.confirm('是否激活账号名称为"' + row.userName + '"的账号？').then(function() {
        return activeMember(userId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("激活成功");
      }).catch(() => {});
    },
    /** 删除账号 */
    handleDelete(row){
      const userId = row.userId
      this.$modal.confirm('你确定要删除账号名称为"' + row.userName + '"的账号？【提示：一经删除该账号的数据全部清空！】',{dangerouslyUseHTMLString: true})
        .then(function() {
          return delMember(userId);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    isEmpty(str) {
      if (str === undefined || str === null || str === '' || str.trim() === '') {
        return true
      }
      return false
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('team/member/export', {
        ...this.queryParams
      }, `用户记录表_${new Date().getTime()}.xlsx`)
    },
    /** 备份数据操作 */
    handleBackup() {
      this.download('team/member/backup', {
      }, `数据备份_${new Date().getTime()}.zip`)
    }
  }
};
</script>
