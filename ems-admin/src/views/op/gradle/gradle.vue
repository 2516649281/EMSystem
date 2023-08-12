<template>
  <div class="app-container">
    <el-form :inline="true" :model="selectForm" class="demo-form-inline">
      <el-form-item label="年级名">
        <el-input
          v-model="selectForm.name"
          placeholder="年级名"
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
          @click="deleteGradle(ids)"
          :icon="deleteLoading ? 'el-icon-loading' : 'el-icon-delete'"
        >批量删除
        </el-button>
        <el-button
          type="success"
          @click="addDialogVisible = true"
          icon="el-icon-plus"
        >添加年级
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
            @click="deleteGradle([scope.row.id])"
            icon="el-icon-delete"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改弹窗 -->
    <el-dialog
      title="修改年级"
      :visible.sync="updateDialogVisible"
      label-position="left"
      center
      width="60%"
    >
      <el-form
        :model="oldGradle"
        :rules="gradleRules"
        status-icon
        ref="ruleForm"
      >
        <el-form-item label="年级名" prop="name">
          <el-input v-model="oldGradle.name" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="updateGradle(oldGradle)"
          :icon="editLoading ? 'el-icon-loading' : ''"
        >确 定
        </el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="添加年级"
      :visible.sync="addDialogVisible"
      label-position="left"
      center
      width="50%"
    >
      <el-form
        :model="newGradle"
        :rules="gradleRules"
        status-icon
        ref="ruleForm"
      >
        <el-form-item label="年级名" prop="name">
          <el-input v-model="newGradle.name" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="addGradle(newGradle)"
          :icon="editLoading ? 'el-icon-loading' : ''"
        >确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {getGrades} from "@/api/table";
import {
  updateGradle,
  deleteGradle,
  getGradleInfo,
  addGradle,
} from "@/api/gradle";

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
      oldGradle: {},
      newGradle: {},
      ids: [],
      gradleList: null,
      gradleRules: {
        name: [{required: true, message: "年级名不得为空!", trigger: "blur"}],
      },
      selectForm: {},
      title: "考试管理系统-年级表",
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
          name: "年级名",
          value: "name",
          width: 220,
        },
      ],
    };
  },
  created() {
    this.fetchData();
    this.getExcel();
  },
  methods: {
    //查看所有年级
    fetchData() {
      this.listLoading = true;
      getGrades().then((response) => {
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
    //添加年级
    addGradle(gradle) {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          addGradle(gradle).then((response) => {
            if (response.success) {
              this.$message({
                showClose: true,
                message: "添加年级成功!",
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
    //修改年级信息
    updateGradle(newGradle) {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          this.editLoading = true;
          updateGradle(newGradle).then((response) => {
            if (response.success) {
              this.$message({
                showClose: true,
                message: "修改年级成功!",
                type: "success",
              });
              this.fetchData();
              this.editLoading = false;
            }
          });
          this.updateDialogVisible = false;
        } else {
          return false;
        }
      });
    },
    //删除年级
    deleteGradle(ids) {
      if (ids === null || ids.length === 0) {
        this.$message({
          showClose: true,
          message: "请选择至少一个年级!",
          type: "warning",
        });
        return;
      }
      this.$confirm("此操作将永久删除该年级,是否继续?(真的很久)", "警告", {
        confirmButtonText: "删除",
        cancelButtonText: "点错了",
        type: "warning",
      }).then(() => {
        this.deleteLoading = true;
        deleteGradle(ids).then((response) => {
          if (response.success) {
            this.$message({
              showClose: true,
              message: "删除年级成功!",
              type: "success",
            });
            this.fetchData();
          }
        });
        this.deleteLoading = false;
      });
    },
    //按条件搜索年级
    search(selectForm) {
      this.listLoading = true;
      this.searchLoading = true;
      console.log(selectForm);
      getGradleInfo(this.isEntity(selectForm)).then((response) => {
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
    showUpdate(oldGradle) {
      this.oldGradle = oldGradle;
      this.updateDialogVisible = true;
    },
  },
};
</script>
