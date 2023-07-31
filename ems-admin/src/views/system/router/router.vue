<template>
  <div class="app-container">
    <el-form :inline="true" :model="selectForm" class="demo-form-inline">
      <el-form-item label="路由名">
        <el-input
          v-model="selectForm.name"
          placeholder="路由名"
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
          @click="deleteRouter(ids)"
          :icon="deleteLoading ? 'el-icon-loading' : 'el-icon-delete'"
        >批量删除
        </el-button>
        <el-button
          type="success"
          @click="addDialogVisible = true"
          icon="el-icon-plus"
        >添加路由
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
      >
        <template slot-scope="scope">
          <!-- ID特殊列 -->
          <template v-if="table.value === 'id'"
          >{{ scope.$index + 1 }}
          </template>
          <!-- 类型特殊列 -->
          <el-tag
            :type="scope.row.type | statusFilter"
            v-else-if="table.value === 'type'"
          >{{ scope.row.type === 0 ? "后端" : "前端" }}
          </el-tag>
          <!-- 请求方式特殊列 -->
          <el-tag
            :type="scope.row.method | methodFilter"
            v-else-if="table.value === 'method'"
          >{{ scope.row.method }}
          </el-tag>
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
      <el-table-column label="操作" align="center" width="220">
        <template slot-scope="scope">
          <el-button
            @click="showUpdate(scope.row)"
            type="primary"
            icon="el-icon-edit"
          >修改
          </el-button>
          <el-button
            type="danger"
            @click="deleteRouter([scope.row.id])"
            icon="el-icon-delete"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改弹窗 -->
    <el-dialog
      title="修改路由"
      :visible.sync="updateDialogVisible"
      label-position="left"
      center
    >
      <el-form
        :model="oldRouter"
        :rules="routerRules"
        status-icon
        ref="ruleForm"
        label-width="20%"
      >
        <el-form-item label="路由名" prop="name">
          <el-input v-model="oldRouter.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="路由值" prop="value">
          <el-input v-model="oldRouter.value" clearable></el-input>
        </el-form-item>
        <el-form-item label="请求方式" prop="method">
          <el-select
            v-model="oldRouter.method"
            clearable
            placeholder="选择请求方式"
          >
            <el-option
              :label="method"
              :value="method"
              v-for="method in methods"
              :key="method.$index"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-radio-group v-model="oldRouter.type">
            <el-radio :label="1" border>前端</el-radio>
            <el-radio :label="0" border>后端</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="权限" prop="permission">
          <el-checkbox-group
            v-model="oldPermissionIds"
            multiple
            placeholder="请选择必需的权限"
          >
            <el-checkbox
              v-for="item in permissions"
              :key="item.id"
              :label="item.id"
            >
              {{ item.name }}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="updateRouter(oldRouter)"
          :icon="editLoading ? 'el-icon-loading' : ''"
        >确 定
        </el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="添加路由"
      :visible.sync="addDialogVisible"
      label-position="left"
      center
      width="50%"
    >
      <el-form
        :model="newRouter"
        :rules="routerRules"
        status-icon
        ref="ruleForm"
      >
        <el-form-item label="路由名" prop="name">
          <el-input v-model="newRouter.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="路由值" prop="value">
          <el-input v-model="newRouter.value" clearable></el-input>
        </el-form-item>
        <el-form-item label="请求方式" prop="method">
          <el-select
            v-model="newRouter.method"
            clearable
            placeholder="选择请求方式"
          >
            <el-option
              :label="method"
              :value="method"
              v-for="method in methods"
              :key="method.$index"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-radio-group v-model="newRouter.type">
            <el-radio :label="1" border>前端</el-radio>
            <el-radio :label="0" border>后端</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="addRouter(newRouter)"
          :icon="editLoading ? 'el-icon-loading' : ''"
        >确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {getRouters, getPermissions} from "@/api/table";
import {
  addRouter,
  updateRouter,
  deleteRouter,
  getRouterInfo,
  getRouterById,
  setPermissionRouter,
} from "@/api/router";
import {deletePermissionRouter, getPermissionRouter} from "@/api/permission";

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: "success",
        0: "danger",
      };
      return statusMap[status];
    },
    methodFilter(status) {
      const methodMap = {
        GET: "primary",
        POST: "success",
        PUT: "warning",
        DELETE: "danger",
      };
      return methodMap[status];
    },
  },
  data() {
    return {
      list: null,
      ids: null,
      listLoading: true,
      updateDialogVisible: false,
      addDialogVisible: false,
      editLoading: false,
      searchLoading: false,
      deleteLoading: false,
      permissions: [],
      permissionRouters: [],
      oldPermissionIds: [],
      oldRouter: {},
      newRouter: {},
      routerRules: {
        name: [{required: true, message: "路由名不得为空!", trigger: "blur"}],
        value: [
          {required: true, message: "路由值不得为空!", trigger: "blur"},
        ],
        value: [
          {required: true, message: "请求方式不得为空!", trigger: "change"},
        ],
        type: [
          {
            required: true,
            message: "请选择类型",
            trigger: "change",
          },
        ],
        permissions: [
          {
            required: true,
            message: "请选择权限",
            trigger: "change",
          },
        ],
      },
      selectForm: {},
      title: "考试管理系统-路由表",
      //字段备注
      column: {},
      //请求方式
      methods: ["GET", "POST", "PUT", "DELETE"],
      //表格
      tableColumn: [
        {
          name: "编号",
          value: "id",
          width: 110,
        },
        {
          name: "路由名",
          value: "name",
          width: 110,
        },
        {
          name: "路由值",
          value: "value",
          width: 200,
        },
        {
          name: "请求方式",
          value: "method",
          width: 110,
        },
        {
          name: "类型",
          value: "type",
          width: 110,
        },
        {
          name: "是否默认",
          value: "isDefault",
          width: 110,
        },
      ],
    };
  },
  created() {
    this.fetchData();
    this.getExcel();
    this.getPermissions();
  },
  methods: {
    //查看所有路由
    fetchData() {
      this.listLoading = true;
      getRouters().then((response) => {
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
    //获取所有的权限
    getPermissions() {
      getPermissions().then((response) => {
        this.permissions = response.data;
      });
    },
    //显示修改窗
    showUpdate(oldRouter) {
      //获取已绑定的权限ID
      getRouterById({routerId: oldRouter.id}).then((response) => {
        this.oldPermissionIds = response.data.permissionList.map((v) => {
          return v.id;
        });
      });
      this.updateDialogVisible = true;
      this.oldRouter = oldRouter;
    },
    //添加路由
    addRouter(router) {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          addRouter(router).then((response) => {
            if (response.success) {
              this.$message({
                showClose: true,
                message: "添加路由成功!",
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
    //修改路由信息
    updateRouter(newRouter) {
      //判断是否为默认路由
      if (newRouter.isDefault === 0) {
        this.$message({
          showClose: true,
          message: "待修改的路由为默认路由,不允许修改!",
          type: "error",
        });
        return;
      }
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          this.editLoading = true;
          updateRouter(newRouter).then((response) => {
            if (response.success) {
              //修改关系
              this.updatePermissionRouter(newRouter.id);
            }
            this.fetchData();
            this.getPermissions();
            this.editLoading = false;
            this.updateDialogVisible = false;
          });
        } else {
          return false;
        }
      });
    },
    //修改关系
    updatePermissionRouter(id) {
      var del = [];
      //获取关系ID
      getPermissionRouter({routerId: id}).then((response) => {
        if (response.data.length !== 0) {
          //首先删除所有关系
          var prId = response.data.map((v) => {
            return v.id;
          });
          deletePermissionRouter(prId);
        }
      });
      //构造条件
      del = this.oldPermissionIds.map((v) => {
        var obj = {
          permissionId: v,
          routerId: id,
        };
        return obj;
      });
      //直接添加
      setPermissionRouter(del).then((response) => {
        if (response.success) {
          this.$message({
            showClose: true,
            message: "修改路由成功!",
            type: "success",
          });
        }
      });
    },
    //删除路由
    deleteRouter(ids) {
      if (ids === null || ids.length === 0) {
        this.$message({
          showClose: true,
          message: "请选择至少一个路由!",
          type: "warning",
        });
        return;
      }
      this.$confirm("此操作将永久删除该路由,是否继续?(真的很久)", "警告", {
        confirmButtonText: "删除",
        cancelButtonText: "点错了",
        type: "warning",
      }).then(() => {
        this.deleteLoading = true;
        deleteRouter(ids).then((response) => {
          if (response.success) {
            this.$message({
              showClose: true,
              message: "删除路由成功!",
              type: "success",
            });
            this.fetchData();
          }
        });
        this.deleteLoading = false;
      });
    },
    //按条件搜索路由
    search(selectForm) {
      this.listLoading = true;
      this.searchLoading = true;
      console.log(selectForm);
      getRouterInfo(this.isEntity(selectForm)).then((response) => {
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
  },
};
</script>
