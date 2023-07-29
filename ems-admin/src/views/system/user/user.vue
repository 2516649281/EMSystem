<template>
  <div class="app-container">
    <el-form :inline="true" :model="selectForm" class="demo-form-inline">
      <el-form-item label="用户名">
        <el-input
          v-model="selectForm.name"
          placeholder="用户名"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item label="选择角色">
        <el-select v-model="selectForm.role" clearable placeholder="选择角色">
          <el-option
            :label="role.name"
            :value="role.id"
            v-for="role in roleList"
            :key="role.id"
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
          @click="deleteUser(ids)"
          :icon="deleteLoading ? 'el-icon-loading' : 'el-icon-delete'"
        >批量删除
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
          <!-- 性别特殊列 -->
          <el-tag
            :type="scope.row.sex | sexColor"
            v-else-if="table.value === 'sex'"
          >{{ scope.row.sex === 1 ? "男" : "女" }}
          </el-tag>
          <!-- 状态特殊列 -->
          <el-tag
            :type="scope.row.status | statusFilter"
            v-else-if="table.value === 'status'"
          >
            <!-- 其他列 -->
            {{ scope.row.status === 0 ? "正常" : "异常" }}
          </el-tag>
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
            @click="deleteUser([scope.row.id])"
            icon="el-icon-delete"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改弹窗 -->
    <el-dialog
      title="修改用户"
      :visible.sync="updateDialogVisible"
      label-position="left"
      center
    >
      <el-form
        :model="oldUser"
        :rules="userRules"
        status-icon
        ref="ruleForm"
        label-width="20%"
      >
        <el-form-item label="用户名" prop="name">
          <el-input v-model="oldUser.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="oldUser.nickName" clearable></el-input>
        </el-form-item>
        <el-form-item label="用户性别" prop="sex">
          <el-radio-group v-model="oldUser.sex">
            <el-radio :label="1" border>男</el-radio>
            <el-radio :label="0" border>女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="角色" prop="role">
          <el-select v-model="oldUser.role" clearable placeholder="选择角色">
            <el-option
              :label="role.name"
              :value="role.id"
              v-for="role in roleList"
              :key="role.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="电子邮箱" prop="email">
          <el-input v-model="oldUser.email" clearable></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="oldUser.phone" clearable></el-input>
        </el-form-item>
        <el-form-item label="用户状态" prop="status" v-if="oldUser.id !== id">
          <el-radio-group v-model="oldUser.status">
            <el-radio :label="1" border>封禁</el-radio>
            <el-radio :label="0" border>正常</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button
          type="primary"
          @click="updateUser(oldUser)"
          :icon="editLoading ? 'el-icon-loading' : ''"
        >确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {getRoles, getUsers} from "@/api/table";
import {updateUser, deleteUser, getInfo} from "@/api/user";
import {mapGetters} from "vuex";

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        0: "success",
        1: "danger",
      };
      return statusMap[status];
    },
    sexColor(sex) {
      const sexMap = {
        0: "warning",
        1: "",
      };
      return sexMap[sex];
    },
  },
  computed: {
    ...mapGetters(["id"]),
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
      title: "考试管理系统-用户表",
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
          name: "用户名",
          value: "name",
          width: 110,
        },
        {
          name: "昵称",
          value: "nickName",
          width: 110,
        },
        {
          name: "性别",
          value: "sex",
          width: 110,
        },
        {
          name: "电子邮箱",
          value: "email",
          width: 110,
        },
        {
          name: "电话号码",
          value: "phone",
          width: 110,
        },
        {
          name: "状态",
          value: "status",
          width: 110,
        },
      ],
      oldUser: {},
      roleList: null,
      userRules: {
        name: [{required: true, message: "用户名不得为空!", trigger: "blur"}],
        nickName: [
          {required: true, message: "昵称不得为空!", trigger: "blur"},
        ],
        email: [
          {required: true, message: "电子邮箱不得为空!", trigger: "blur"},
        ],
        phone: [
          {required: true, message: "手机号不得为空!", trigger: "blur"},
        ],
        sex: [
          {
            required: true,
            message: "请选择性别",
            trigger: "change",
          },
        ],
        role: [
          {
            required: true,
            message: "请选择角色",
            trigger: "change",
          },
        ],
        status: [
          {
            required: true,
            message: "请选择状态",
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
    //查看所有用户
    fetchData() {
      this.listLoading = true;
      getUsers().then((response) => {
        this.list = response.data;
        this.listLoading = false;
      });
      getRoles().then((response) => {
        this.roleList = response.data;
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
    showUpdate(oldUser) {
      this.updateDialogVisible = true;
      this.oldUser = oldUser;
    },
    //修改用户信息
    updateUser(newUser) {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          this.editLoading = true;
          updateUser(newUser).then((response) => {
            if (response.success) {
              this.$message({
                showClose: true,
                message: "修改用户成功!",
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
    //删除用户
    deleteUser(ids) {
      if (ids === null || ids.length === 0) {
        this.$message({
          showClose: true,
          message: "请选择至少一个用户!",
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
      this.$confirm("此操作将永久删除该用户,是否继续?(真的很久)", "警告", {
        confirmButtonText: "删除",
        cancelButtonText: "点错了",
        type: "warning",
      }).then(() => {
        this.deleteLoading = true;
        deleteUser(ids).then((response) => {
          if (response.success) {
            this.$message({
              showClose: true,
              message: "删除用户成功!",
              type: "success",
            });
            this.fetchData();
          }
        });
        this.deleteLoading = false;
      });
    },
    //按条件搜索用户
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
