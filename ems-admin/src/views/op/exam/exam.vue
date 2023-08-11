<template>
  <div class="app-container">
    <el-form :inline="true" :model="selectForm" class="demo-form-inline">
      <el-form-item>
        <el-button
          type="danger"
          @click="deleteExam(ids)"
          :icon="deleteLoading ? 'el-icon-loading' : 'el-icon-delete'"
        >批量删除
        </el-button>
        <el-button
          type="success"
          @click="addDialogVisible = true"
          icon="el-icon-plus"
        >添加试卷
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
          <!-- 类型特殊列 -->
          <el-tag
            :type="scope.row.type | statusFilter"
            v-else-if="table.value === 'type'"
          >{{ scope.row.type === 0 ? "后端" : "前端" }}
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
            @click="deleteExam([scope.row.id])"
            icon="el-icon-delete"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改弹窗 -->
    <el-dialog
      title="修改试卷"
      :visible.sync="updateDialogVisible"
      label-position="left"
      center
      width="50%"
    >
      <el-form
        :model="oldExam"
        :rules="examRules"
        status-icon
        ref="ruleForm"
        label-width="20%"
      >
        <el-form-item label="试卷名" prop="name">
          <el-input v-model="oldExam.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="考试时间(min)" prop="time">
          <el-input-number v-model="oldExam.time" :min="1"></el-input-number>
        </el-form-item>
        <el-form-item label="题库列表">
          <el-select
            filterable
            v-model="oldPermissionIds"
            collapse-tags
            multiple
            placeholder="请选择"
            value-key="id"
            @change="loadScore($event)"
          >
            <el-option
              v-for="item in problems"
              :key="item.id"
              :label="item.main"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="总分" prop="score">
          <el-input-number
            v-model="oldExam.score"
            :precision="1"
            :step="0.1"
          ></el-input-number>
        </el-form-item>
        <el-form-item label="合格分数" prop="pass">
          <el-input-number
            v-model="oldExam.pass"
            :precision="1"
            :step="0.1"
          ></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="updateExam(oldExam)"
          :icon="editLoading ? 'el-icon-loading' : ''"
        >确 定
        </el-button>
      </div>
    </el-dialog>
    <el-dialog
      title="添加试卷"
      :visible.sync="addDialogVisible"
      label-position="left"
      center
      width="50%"
    >
      <el-form
        :model="newExam"
        :rules="examRules"
        status-icon
        ref="ruleForm"
        label-width="15%"
      >
        <el-form-item label="试卷名" prop="name">
          <el-input v-model="newExam.name" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="addExam(newExam)"
          :icon="editLoading ? 'el-icon-loading' : ''"
        >确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {getExams, getProblems} from "@/api/table";
import {addExam, deleteExam, getExamById, updateExam,} from "@/api/exam";

export default {
  data() {
    return {
      list: null,
      ids: null,
      listLoading: true,
      updateDialogVisible: false,
      addDialogVisible: false,
      editLoading: false,
      deleteLoading: false,
      problems: [],
      permissionExams: [],
      oldPermissionIds: [],
      oldExam: {},
      newExam: {
        isDefault: 1,
      },
      examRules: {
        name: [{required: true, message: "试卷名不得为空!", trigger: "blur"}],
        time: [
          {required: true, message: "考试时间不得为空!", trigger: "blur"},
        ],
        score: [
          {required: true, message: "考试分数不得为空!", trigger: "blur"},
        ],
        pass: [
          {required: true, message: "合格分数不得为空!", trigger: "blur"},
        ],
      },
      title: "考试管理系统-试卷表",
      //字段备注
      column: {},
      //表格
      tableColumn: [
        {
          name: "编号",
          value: "id",
          width: 115,
        },
        {
          name: "试卷名",
          value: "name",
          width: 115,
        },
        {
          name: "考试时间",
          value: "time",
          width: 115,
        },
        {
          name: "总分",
          value: "score",
          width: 110,
        },
        {
          name: "合格分数",
          value: "pass",
          width: 115,
        },
      ],
    };
  },
  created() {
    this.fetchData();
    this.getExcel();
    this.getProblems();
  },
  methods: {
    //查看所有试卷
    fetchData() {
      this.listLoading = true;
      getExams().then((response) => {
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
    //获取所有的题库
    getProblems() {
      getProblems().then((response) => {
        this.problems = response.data;
      });
    },
    //显示修改窗
    showUpdate(oldExam) {
      //获取已绑定的题库ID
      getExamById({examId: oldExam.id}).then((response) => {
        this.oldPermissionIds = response.data.problemList;
      });
      this.updateDialogVisible = true;
      this.oldExam = oldExam;
    },
    //添加试卷
    addExam(exam) {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          addExam(exam).then((response) => {
            if (response.success) {
              this.$message({
                showClose: true,
                message: "添加试卷成功!",
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
    //修改试卷信息
    updateExam(newExam) {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          this.editLoading = true;
          //添加
          var problemList = this.oldPermissionIds.map((v) => {
            var obj = {};
            obj.id = v.id;
            return obj;
          });
          newExam.problemList = problemList;
          updateExam(newExam).then((response) => {
            if (response.success) {
              this.$message({
                showClose: true,
                message: "修改试卷成功!",
                type: "success",
              });
              this.fetchData();
            }
            this.fetchData();
            this.getProblems();
            this.editLoading = false;
            this.updateDialogVisible = false;
          });
        } else {
          return false;
        }
      });
    },
    //删除试卷
    deleteExam(ids) {
      if (ids === null || ids.length === 0) {
        this.$message({
          showClose: true,
          message: "请选择至少一个试卷!",
          type: "warning",
        });
        return;
      }
      this.$confirm("此操作将永久删除该试卷,是否继续?(真的很久)", "警告", {
        confirmButtonText: "删除",
        cancelButtonText: "点错了",
        type: "warning",
      }).then(() => {
        this.deleteLoading = true;
        deleteExam(ids).then((response) => {
          if (response.success) {
            this.$message({
              showClose: true,
              message: "删除试卷成功!",
              type: "success",
            });
            this.fetchData();
          }
        });
        this.deleteLoading = false;
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
    //实时更新总分
    loadScore(val) {
      var score = 0;
      var pass = 0;
      console.log(val);
      val.forEach((v) => {
        score += v.score;
        pass = score * 0.6;
      });
      this.oldExam.score = score;
      this.oldExam.pass = pass;
    },
  },
};
</script>
<style lang="scss" scoped>
::v-deep .demo-form-inline {
  .el-input__inner {
    width: 150px;
  }
}

::v-deep .el-dialog {
  .el-input__inner {
    width: 80%;
  }
}
</style>
