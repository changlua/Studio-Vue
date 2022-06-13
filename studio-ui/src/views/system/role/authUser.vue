<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
      <el-form-item label="用户名称" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名称"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号码" prop="phonenumber">
        <el-input
          v-model="queryParams.phonenumber"
          placeholder="请输入手机号码"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" v-hasPermi="['system:role:authUser:allocatedList']">搜索</el-button>
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
          @click="openSelectUser"
          v-hasPermi="['system:role:authUser:selectAll']"
        >添加用户</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-circle-close"
          size="mini"
          :disabled="multiple"
          @click="cancelAuthUserAll"
          v-hasPermi="['system:role:authUser:cancelAll']"
        >批量取消授权</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-close"
          size="mini"
          @click="handleClose"
        >关闭</el-button>
      </el-col>
      <!--   右边隐藏搜索栏以及刷新   -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="序号"
        type="index"
        width="50">
      </el-table-column>
      <el-table-column label="用户名称" prop="userName" :show-overflow-tooltip="true" />
      <el-table-column label="用户昵称" prop="nickName" :show-overflow-tooltip="true" />
      <el-table-column label="真实姓名" prop="realName" :show-overflow-tooltip="true" />
      <el-table-column label="邮箱" prop="email" :show-overflow-tooltip="true" />
      <el-table-column label="手机" prop="phonenumber" :show-overflow-tooltip="true" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status == 0"  type="success" effect="plain">
            {{"正常"}}
          </el-tag>
          <el-tag v-else type="info" effect="plain">
            {{"已注销"}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-circle-close"
            @click="cancelAuthUser(scope.row)"
            v-hasPermi="['system:role:authUser:cancel']"
          >取消授权</el-button>
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
    <select-user ref="select" :roleId="queryParams.roleId" @ok="handleQuery" />
  </div>
</template>

<script>
import { allocatedUserList, authUserCancel, authUserCancelAll } from "@/api/system/role";
import selectUser from "./selectUser";

export default {
  name: 'AuthUser',
  components: { selectUser },
  // 定义属性
  data () {
    return {
      // 遮罩层
      loading: true,
      // 选中用户组
      userIds: [],
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户表格数据
      userList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        roleId: undefined,
        userName: undefined,
        phonenumber: undefined
      },
      //删除提示信息
      names: ""
    }
  },
  // 计算属性，会监听依赖属性值随之变化
  computed: {
  },
  // 监控data中的数据变化
  watch: {},
  // 生命周期 - 创建完成（可以访问当前this实例）
  created () {
    const roleId = this.$route.params && this.$route.params.roleId;
    if (roleId) {
      this.queryParams.roleId = roleId;
      this.getList();
    }
  },
  // 方法集合
  methods: {
    /** 查询授权用户列表 */
    getList() {
      this.loading = true;
      allocatedUserList(this.queryParams).then(response => {
          this.userList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    // 返回按钮
    handleClose() {
      const obj = { path: "/system/role" };
      this.$tab.closeOpenPage(obj);
    },
    /** 打开授权用户表弹窗 */
    openSelectUser() {
      this.$refs.select.show();
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
      this.userIds = selection.map(item => item.userId)
      this.multiple = !selection.length
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
    /** 取消授权按钮操作 */
    cancelAuthUser(row) {
      const roleId = this.queryParams.roleId;
      this.$modal.confirm('确认要取消该用户"' + row.userName + '"角色吗？').then(function() {
        return authUserCancel({ userId: row.userId, roleId: roleId });
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("取消授权成功");
      }).catch(() => {});
    },
    /** 批量取消授权按钮操作 */
    cancelAuthUserAll(row) {
      const roleId = this.queryParams.roleId;
      const userIds = this.userIds.join(",");
      const names = row.userName || this.names
      this.$modal.confirm('是否取消用户名称为【' +  names + '】授权数据项？').then(function() {
        return authUserCancelAll({ roleId: roleId, userIds: userIds });
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("取消授权成功");
      }).catch(() => {});
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

<style scoped>

</style>
