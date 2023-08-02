<template>
  <div class="app-container">
    <el-form :inline="true" :model="selectForm" class="demo-form-inline">
      <el-form-item label="权限名">
        <el-input
          v-model="selectForm.name"
          placeholder="权限名"
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
            @click="deletePermission(ids)"
            :icon="deleteLoading ? 'el-icon-loading' : 'el-icon-delete'"
        >批量删除
        </el-button>
        <el-button
            type="success"
            @click="addDialogVisible = true"
            icon="el-icon-plus"
        >添加权限
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
              @click="deletePermission([scope.row.id])"
              icon="el-icon-delete"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改弹窗 -->
    <el-dialog
      title="修改权限"
      :visible.sync="updateDialogVisible"
      center
      label-position="left"
      width="60%"
    >
      <el-form
        :model="oldPermission"
        :rules="permissionRules"
        status-icon
        ref="ruleForm"
      >
        <el-form-item label="权限名" prop="name">
          <el-input v-model="oldPermission.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="标记值" prop="sign">
          <el-input v-model="oldPermission.sign" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button
            type="primary"
            @click="updatePermission(oldPermission)"
            :icon="editLoading ? 'el-icon-loading' : ''"
        >确 定
        </el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="添加权限"
      :visible.sync="addDialogVisible"
      label-position="left"
      center
      width="50%"
    >
      <el-form
        :model="newPermission"
        :rules="permissionRules"
        status-icon
        ref="ruleForm"
      >
        <el-form-item label="权限名" prop="name">
          <el-input v-model="newPermission.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="标记值" prop="sign">
          <el-input v-model="newPermission.sign" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button
            type="primary"
            @click="addPermission(newPermission)"
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
  addPermission,
  updatePermission,
  deletePermission,
  getPermissionInfo,
} from "@/api/permission";

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
      ids: null,
      listLoading: true,
      updateDialogVisible: false,
      addDialogVisible: false,
      editLoading: false,
      searchLoading: false,
      deleteLoading: false,
      oldPermission: {},
      newPermission: {},
      permissionRules: {
        name: [{required: true, message: "权限名不得为空!", trigger: "blur"}],
        sign: [{required: true, message: "标记名不得为空!", trigger: "blur"}],
      },
      selectForm: {},
      title: "考试管理系统-权限表",
      //字段备注
      column: {},
      //表格
      tableColumn: [
        {
          name: "编号",
          value: "id",
          width: 110,
        },
        {
          name: "权限名",
          value: "name",
          width: 220,
        },
        {
          name: "标记值",
          value: "sign",
          width: 220,
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
    this.initRouter();
    this.getExcel();
  },
  methods: {
    //查看所有权限
    fetchData() {
      this.listLoading = true;
      getPermissions().then((response) => {
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
    //显示修改窗
    showUpdate(oldPermission) {
      this.updateDialogVisible = true;
      this.oldPermission = oldPermission;
    },
    //添加权限
    addPermission(permission) {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          addPermission(permission).then((response) => {
            if (response.success) {
              this.$message({
                showClose: true,
                message: "添加权限成功!",
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
    //修改权限信息
    updatePermission(newPermission) {
      // //判断是否为默认权限
      // if (newPermission.isDefault === 0) {
      //   this.$message({
      //     showClose: true,
      //     message: "待修改的权限为默认权限,不允许修改!",
      //     type: "error",
      //   });
      //   return;
      // }
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          this.editLoading = true;
          updatePermission(newPermission).then((response) => {
            if (response.success) {
              this.$message({
                showClose: true,
                message: "修改权限成功!",
                type: "success",
              });
            }
          });
          this.editLoading = false;
          this.updateDialogVisible = false;
        } else {
          return false;
        }
      });
    },
    //删除权限
    deletePermission(ids) {
      if (ids === null || ids.length === 0) {
        this.$message({
          showClose: true,
          message: "请选择至少一个权限!",
          type: "warning",
        });
        return;
      }
      this.$confirm("此操作将永久删除该权限,是否继续?(真的很久)", "警告", {
        confirmButtonText: "删除",
        cancelButtonText: "点错了",
        type: "warning",
      }).then(() => {
        this.deleteLoading = true;
        deletePermission(ids).then((response) => {
          if (response.success) {
            this.$message({
              showClose: true,
              message: "删除权限成功!",
              type: "success",
            });
            this.fetchData();
          }
        });
        this.deleteLoading = false;
      });
    },
    //按条件搜索权限
    search(selectForm) {
      this.listLoading = true;
      this.searchLoading = true;
      console.log(selectForm);
      getPermissionInfo(this.isEntity(selectForm)).then((response) => {
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
    initRouter() {
      getRouters().then((response) => {
        var list = response.data;
        var map = new Map();
        //保存数据以便搜索
        list.forEach((v) => {
          map.set(v.id, v.name);
        });
        this.routerList = map;
        console.log(this.routerList);
        const data = [];
        list.forEach((v) => {
          data.push({
            key: v.id,
            label: `${v.name} ${v.value}`,
            pinyin: this.routerList.get(v.id),
          });
        });
        this.routerList = data;
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
    //分类查询路由
    selectRouter(query, item) {
      return item.pinyin.indexOf(query) > -1;
    },
  },
};
</script>
