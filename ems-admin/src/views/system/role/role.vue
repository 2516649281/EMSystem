<template>
  <div class="app-container">
    <el-form :inline="true" :model="selectForm" class="demo-form-inline">
      <el-form-item label="角色名">
        <el-input
          v-model="selectForm.name"
          placeholder="角色名"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="search(selectForm)"
          :icon="searchLoading ? 'el-icon-loading' : 'el-icon-search'"
          >查询
        </el-button>
        <el-button
          type="danger"
          @click="deleteRole(ids)"
          :icon="deleteLoading ? 'el-icon-loading' : 'el-icon-delete'"
          >批量删除
        </el-button>
        <el-button
          type="success"
          @click="addDialogVisible = true"
          icon="el-icon-plus"
          >添加角色
        </el-button>
      </el-form-item>
      <el-form-item>
        <download-excel
          class="export-excel-wrapper"
          :data="list"
          :fields="column"
          :header="title"
          :name="`${title}.xls`"
        >
          <el-button icon="el-icon-printer" type="warning"
            >导出Excel表格
          </el-button>
        </download-excel>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      @selection-change="selectTable"
      height="500"
    >
      <el-table-column
        type="selection"
        width="30"
        align="center"
      ></el-table-column>
      <el-table-column
        align="center"
        v-for="table in tableColumn"
        :key="table.$index"
        :width="table.width"
        :label="table.name"
        show-overflow-tooltip
      >
        <template slot-scope="scope">
          <!-- ID特殊列 -->
          <template v-if="table.value === 'id'"
            >{{ scope.$index + 1 }}
          </template>
          <!-- 是否默认特殊列 -->
          <el-tag
            :type="scope.row.isDefault | statusFilter"
            v-else-if="table.value === 'isDefault'"
          >
            {{ scope.row.isDefault === 0 ? "默认" : "自定义" }}
          </el-tag>
          <!-- 其他列 -->
          <template v-else>{{ scope.row[table.value] }}</template>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="440">
        <template slot-scope="scope">
          <el-button
            @click="showUpdate(scope.row)"
            type="primary"
            icon="el-icon-edit"
            >修改
          </el-button>
          <el-button
            type="danger"
            @click="deleteRole([scope.row.id])"
            icon="el-icon-delete"
            >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改弹窗 -->
    <el-dialog
      title="修改角色"
      :visible.sync="updateDialogVisible"
      label-position="left"
      center
      width="60%"
    >
      <el-form :model="oldRole" :rules="roleRules" status-icon ref="ruleForm">
        <el-form-item label="角色名" prop="name">
          <el-input v-model="oldRole.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="权限列表">
          <el-select
            filterable
            collapse-tags
            v-model="oldPermissionIds"
            multiple
            placeholder="请选择"
          >
            <el-option
              v-for="item in permissions"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="updateRole(oldRole)"
          :icon="editLoading ? 'el-icon-loading' : ''"
          >确 定
        </el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="添加角色"
      :visible.sync="addDialogVisible"
      label-position="left"
      center
      width="50%"
    >
      <el-form :model="newRole" :rules="roleRules" status-icon ref="ruleForm">
        <el-form-item label="角色名" prop="name">
          <el-input v-model="newRole.name" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="addRole(newRole)"
          :icon="editLoading ? 'el-icon-loading' : ''"
          >确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getPermissions, getRoles } from "@/api/table";
import {
  addRole,
  deleteRole,
  getRoleById,
  getRoleInfo,
  updateRole,
} from "@/api/role";

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: "success",
        0: "danger",
      };
      return statusMap[status];
    },
  },
  data() {
    return {
      list: null,
      listLoading: true,
      updateDialogVisible: false,
      editLoading: false,
      searchLoading: false,
      deleteLoading: false,
      addDialogVisible: false,
      oldRole: {},
      newRole: {},
      ids: [],
      roleList: null,
      roleRules: {
        name: [{ required: true, message: "角色名不得为空!", trigger: "blur" }],
      },
      selectForm: {},
      oldPermissions: [],
      permissions: [],
      permissionIds: [],
      oldPermissionIds: [],
      title: "考试管理系统-角色表",
      //字段备注
      column: {},
      //表格
      tableColumn: [
        {
          name: "编号",
          value: "id",
          width: 200,
        },
        {
          name: "角色名",
          value: "name",
          width: 200,
        },
        {
          name: "是否默认",
          value: "isDefault",
          width: 200,
        },
      ],
    };
  },
  created() {
    this.fetchData();
    this.getPermission();
    this.getExcel();
  },
  methods: {
    //查看所有角色
    fetchData() {
      this.listLoading = true;
      getRoles().then((response) => {
        this.list = response.data;
        this.listLoading = false;
      });
    },
    //初始化Excel组件
    getExcel() {
      var map = {};
      this.tableColumn.forEach((v) => {
        map[v.name] = v.value;
      });
      this.column = map;
    },
    //获取权限
    getPermission() {
      getPermissions().then((response) => {
        this.permissions = response.data;
      });
    },
    //添加角色
    addRole(role) {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          addRole(role).then((response) => {
            if (response.success) {
              this.$message({
                showClose: true,
                message: "添加角色成功!",
                type: "success",
              });
              this.fetchData();
              this.addDialogVisible = false;
            }
          });
        } else {
          return false;
        }
      });
    },
    //修改角色信息
    updateRole(newRole) {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          // //判断是否为默认角色
          // if (newRole.isDefault === 0) {
          //   this.$message({
          //     showClose: true,
          //     message: "不得修改默认角色!",
          //     type: "error",
          //   });
          //   return;
          // }
          this.editLoading = true;
          var permissionList = this.oldPermissionIds.map((v) => {
            var obj = {};
            obj.id = v;
            return obj;
          });
          newRole.permissionList = permissionList;
          // 修改本体
          updateRole(newRole).then((response) => {
            if (response.success) {
              this.$message({
                showClose: true,
                message: "修改角色成功!",
                type: "success",
              });
              this.fetchData();
              this.updateDialogVisible = false;
            }
          });
          this.editLoading = false;
        } else {
          return false;
        }
      });
    },
    //删除角色
    deleteRole(ids) {
      if (ids === null || ids.length === 0) {
        this.$message({
          showClose: true,
          message: "请选择至少一个角色!",
          type: "warning",
        });
        return;
      }
      this.$confirm("此操作将永久删除该角色,是否继续?(真的很久)", "警告", {
        confirmButtonText: "删除",
        cancelButtonText: "点错了",
        type: "warning",
      }).then(() => {
        this.deleteLoading = true;
        deleteRole(ids).then((response) => {
          if (response.success) {
            this.$message({
              showClose: true,
              message: "删除角色成功!",
              type: "success",
            });
            this.fetchData();
          }
        });
        this.deleteLoading = false;
      });
    },
    //按条件搜索角色
    search(selectForm) {
      this.listLoading = true;
      this.searchLoading = true;
      console.log(selectForm);
      getRoleInfo(this.isEntity(selectForm)).then((response) => {
        this.list = response.data;
        this.listLoading = false;
        this.searchLoading = false;
      });
    },
    //判断字符串
    isEntity(obj) {
      var newObj = {};
      Object.keys(obj).forEach((x) => {
        if ((obj[x] !== undefined) & (obj !== null) & (obj[x] !== "")) {
          console.log(`${x}--->${obj[x]}`);
          newObj[x] = obj[x];
        }
      });
      return newObj;
    },
    //多选逻辑
    selectTable(val) {
      //获取ID
      let ids = val.map((v) => {
        return v.id;
      });
      this.ids = ids;
    },
    //显示修改窗
    showUpdate(oldRole) {
      //获取已绑定的权限ID
      getRoleById({ roleId: oldRole.id }).then((response) => {
        this.oldPermissionIds = response.data.permissionList.map((v) => {
          return v.id;
        });
      });
      this.updateDialogVisible = true;
      this.oldRole = oldRole;
    },
  },
};
</script>
