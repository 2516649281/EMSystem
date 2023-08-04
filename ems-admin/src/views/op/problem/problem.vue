<template>
  <div class="app-container">
    <el-form :inline="true" :model="selectForm" class="demo-form-inline">
      <el-form-item label="题目名">
        <el-input
            v-model="selectForm.name"
            placeholder="题目名"
            clearable
        ></el-input>
      </el-form-item>
      <el-form-item label="选择科目">
        <el-select
            v-model="selectForm.subjectId"
            clearable
            placeholder="选择科目"
        >
          <el-option
              :label="subject.name"
              :value="subject.id"
              v-for="subject in subjectList"
              :key="subject.id"
          ></el-option>
        </el-select>
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
            @click="deleteProblem(ids)"
            :icon="deleteLoading ? 'el-icon-loading' : 'el-icon-delete'"
        >批量删除
        </el-button>
        <el-button type="success" @click="addProblem" icon="el-icon-plus"
        >添加题目
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
              @click="deleteProblem([scope.row.id])"
              icon="el-icon-delete"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改弹窗 -->
    <el-dialog
        title="修改题目"
        :visible.sync="updateDialogVisible"
        label-position="left"
        center
    >
      <el-form
          :model="oldProblem"
          :rules="problemRules"
          status-icon
          ref="ruleForm"
          label-width="20%"
      >
        <el-form-item label="题目名" prop="name">
          <el-input v-model="oldProblem.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="oldProblem.nickName" clearable></el-input>
        </el-form-item>
        <el-form-item label="科目" prop="subject">
          <el-select
              v-model="oldProblem.subjectId"
              clearable
              placeholder="选择科目"
          >
            <el-option
                :label="subject.name"
                :value="subject.id"
                v-for="subject in subjectList"
                :key="subject.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="电子邮箱" prop="email">
          <el-input v-model="oldProblem.email" clearable></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="oldProblem.phone" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button
            type="primary"
            @click="updateProblem(oldProblem)"
            :icon="editLoading ? 'el-icon-loading' : ''"
        >确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {getProblems, getSubjects} from "@/api/table";
import {deleteProblem, getInfo, updateProblem,} from "@/api/problem";

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        0: "success",
        1: "danger",
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
      editLoading: false,
      searchLoading: false,
      deleteLoading: false,
      addDialogVisible: false,
      title: "考试管理系统-题目表",
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
          name: "题干",
          value: "main",
          width: 110,
        },
        {
          name: "选项",
          value: "options",
          width: 110,
        },
        {
          name: "type",
          value: "类型",
          width: 110,
        },
        {
          name: "答案",
          value: "answer",
          width: 110,
        },
        {
          name: "解析",
          value: "parse",
          width: 110,
        },
        {
          name: "得分",
          value: "score",
          width: 110,
        },
      ],
      oldProblem: {},
      newProblem: {
        type: 0,
        options: [
          {
            text: "",
            index: 0,
          },
        ],
      },
      subjectList: null,
      problemRules: {
        main: [{required: true, message: "题干不得为空!", trigger: "blur"}],
        options: [
          {required: true, message: "选项不得为空!", trigger: "blur"},
        ],
        type: [{required: true, message: "类型不得为空!", trigger: "change"}],
        answer: [{required: true, message: "答案不得为空!", trigger: "blur"}],
        parse: [
          {
            required: true,
            message: "解析不得为空!",
            trigger: "blur",
          },
        ],
        score: [
          {
            required: true,
            message: "请输入本题得分",
            trigger: "blur",
          },
        ],
        subjectId: [
          {
            required: true,
            message: "请选择科目!",
            trigger: "change",
          },
        ],
      },
      selectForm: {},
    };
  },
  created() {
    this.fetchData();
    this.getExcel();
  },
  methods: {
    //查看所有题目
    fetchData() {
      this.listLoading = true;
      getProblems().then((response) => {
        this.list = response.data;
        this.listLoading = false;
      });
      getSubjects().then((response) => {
        this.subjectList = response.data;
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
    showUpdate(oldProblem) {
      this.updateDialogVisible = true;
      this.oldProblem = oldProblem;
    },
    //跳转到添加页面
    addProblem() {
      this.$router.push("/addPro");
    },
    //修改题目信息
    updateProblem(newProblem) {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          this.editLoading = true;
          updateProblem(newProblem).then((response) => {
            if (response.success) {
              this.$message({
                showClose: true,
                message: "修改题目成功!",
                type: "success",
              });
              this.updateDialogVisible = false;
              this.fetchData();
            }
            this.editLoading = false;
          });
        } else {
          return false;
        }
      });
    },
    //删除题目
    deleteProblem(ids) {
      if (ids === null || ids.length === 0) {
        this.$message({
          showClose: true,
          message: "请选择至少一个题目!",
          type: "warning",
        });
        return;
      }
      for (let i = 0; i < ids.length; i++) {
        if (ids[i] === this.id) {
          this.$message({
            showClose: true,
            message: "我删我自己?不能哦!",
            type: "error",
          });
          return;
        }
      }
      this.$confirm("此操作将永久删除该题目,是否继续?(真的很久)", "警告", {
        confirmButtonText: "删除",
        cancelButtonText: "点错了",
        type: "warning",
      }).then(() => {
        this.deleteLoading = true;
        deleteProblem(ids).then((response) => {
          if (response.success) {
            this.$message({
              showClose: true,
              message: "删除题目成功!",
              type: "success",
            });
            this.fetchData();
          }
        });
        this.deleteLoading = false;
      });
    },
    //按条件搜索题目
    search(selectForm) {
      this.listLoading = true;
      this.searchLoading = true;
      console.log(selectForm);
      getInfo(this.isEntity(selectForm)).then((response) => {
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
