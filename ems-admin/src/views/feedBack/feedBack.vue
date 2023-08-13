<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-button
          type="danger"
          @click="deleteFeedBack(ids)"
          :icon="deleteLoading ? 'el-icon-loading' : 'el-icon-delete'"
          >批量删除
        </el-button>
        <el-button
          type="success"
          @click="addDialogVisible = true"
          icon="el-icon-plus"
          >添加反馈
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
        show-overflow-tooltip
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
      <el-table-column label="操作" ali他gn="center" width="440">
        <template slot-scope="scope">
          <el-button
            type="danger"
            @click="deleteFeedBack([scope.row.id])"
            icon="el-icon-delete"
            >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="添加反馈"
      :visible.sync="addDialogVisible"
      label-position="left"
      center
      width="50%"
    >
      <el-form
        :model="newFeedBack"
        :rules="feedBackRules"
        status-icon
        ref="ruleForm"
      >
        <el-form-item label="反馈消息" prop="message">
          <el-input v-model="newFeedBack.message" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="addFeedBack(newFeedBack)"
          :icon="editLoading ? 'el-icon-loading' : ''"
          >确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getFeedBacks } from "@/api/table";
import { deleteFeedBack, getFeedBackInfo, addFeedBack } from "@/api/feedBack";

export default {
  data() {
    return {
      list: null,
      listLoading: true,
      updateDialogVisible: false,
      editLoading: false,
      deleteLoading: false,
      addDialogVisible: false,
      newFeedBack: {},
      ids: [],
      feedBackList: null,
      feedBackRules: {
        message: [
          { required: true, message: "反馈消息不得为空!", trigger: "blur" },
        ],
      },
      title: "考试管理系统-反馈表",
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
          name: "反馈消息",
          value: "message",
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
    //查看所有反馈
    fetchData() {
      this.listLoading = true;
      getFeedBacks().then((response) => {
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
    //添加反馈
    addFeedBack(feedBack) {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          addFeedBack(feedBack).then((response) => {
            if (response.success) {
              this.$message({
                showClose: true,
                message: "添加反馈成功!",
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
    //删除反馈
    deleteFeedBack(ids) {
      if (ids === null || ids.length === 0) {
        this.$message({
          showClose: true,
          message: "请选择至少一个反馈!",
          type: "warning",
        });
        return;
      }
      this.$confirm("此操作将永久删除该反馈,是否继续?(真的很久)", "警告", {
        confirmButtonText: "删除",
        cancelButtonText: "点错了",
        type: "warning",
      }).then(() => {
        this.deleteLoading = true;
        deleteFeedBack(ids).then((response) => {
          if (response.success) {
            this.$message({
              showClose: true,
              message: "删除反馈成功!",
              type: "success",
            });
            this.fetchData();
          }
        });
        this.deleteLoading = false;
      });
    },
    //按条件搜索反馈
    search(selectForm) {
      this.listLoading = true;
      this.searchLoading = true;
      console.log(selectForm);
      getFeedBackInfo(this.isEntity(selectForm)).then((response) => {
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
