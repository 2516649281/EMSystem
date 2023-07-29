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
          </el-button
          >
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
      >
        <template slot-scope="scope">
          <!-- ID特殊列 -->
          <template v-if="table.value === 'id'">{{
              scope.$index + 1
            }}
          </template>
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
          <el-transfer
            v-model="permissionIds"
            :data="perList"
            :titles="['未拥有', '已拥有']"
            :button-texts="['撤权', '授权']"
          ></el-transfer>
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
import {getRoles} from "@/api/table";
import {
  updateRole,
  deleteRole,
  setPermissionRole,
  getPermissionRole,
  deletePermissionRole,
  getRoleInfo,
  addRole,
} from "@/api/role";
import {getPermissionInfo} from "@/api/permission";

export default {
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
      perList: [],
      roleRules: {
        name: [{required: true, message: "角色名不得为空!", trigger: "blur"}],
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
          width: 220,
        },
        {
          name: "角色名",
          value: "name",
          width: 220,
        },
      ],
    };
  },
  created() {
    this.fetchData();
    this.initPermission();
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
      getPermissionInfo().then((response) => {
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
          //判断是否为默认角色
          if (
            (newRole.id === "0") |
            (newRole.id === "232b9005ab8a466495ca0b1f741e5adc")
          ) {
            this.$message({
              showClose: true,
              message: "不得修改默认角色!",
              type: "error",
            });
            return;
          }
          this.editLoading = true;
          // 修改本体
          updateRole(newRole);
          //修改关系
          //1.删除所有已授权内容
          if (this.oldPermissionIds.length !== 0) {
            deletePermissionRole(this.oldPermissionIds);
          }
          //构造条件
          var pr = this.permissionIds.map((v) => {
            var obj = {};
            obj["roleId"] = newRole.id;
            obj["permissionId"] = v;
            return obj;
          });
          if (pr.length !== 0) {
            //2.添加新的权限
            setPermissionRole(pr).then((response) => {
              if (response.success) {
                this.$message({
                  showClose: true,
                  message: "修改角色成功!",
                  type: "success",
                });
                this.updateDialogVisible = false;
                this.fetchData();
              }
            });
          }
          this.editLoading = false;
        } else {
          return false;
        }
      });
    },
    //删除角色
    deleteRole(ids) {
      for (let i = 0; i < ids.length; i++) {
        if (
          (ids[i] === "0") |
          (ids[i] === "232b9005ab8a466495ca0b1f741e5adc")
        ) {
          this.$message({
            showClose: true,
            message: "不得删除默认角色!",
            type: "error",
          });
          return;
        }
      }
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
    //初始化权限列表
    initPermission() {
      getPermissionInfo().then((response) => {
        var list = response.data;
        const data = [];
        list.forEach((v) => {
          data.push({
            key: v.id,
            label: `${v.name} ${v.sign}`,
          });
        });
        this.perList = data;
      });
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
      //获取已获得的
      getPermissionRole({roleId: oldRole.id}).then((response) => {
        //权限ID
        var ids = response.data.map((v) => {
          return v.permissionId;
        });
        //实体ID
        var idsA = response.data.map((v) => {
          return v.id;
        });
        //保存实体ID
        this.oldPermissionIds = idsA;
        this.permissionIds = ids;
      });
      this.oldRole = oldRole;
      this.updateDialogVisible = true;
    },
  },
};
</script>
