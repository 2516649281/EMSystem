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
        <el-button type="primary" @click="search(selectForm)">查询</el-button>
        <el-button type="danger" @click="search(selectForm)"
        >批量删除
        </el-button
        >
      </el-form-item>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column
        type="selection"
        width="30"
        align="center"
      ></el-table-column>
      <el-table-column align="center" label="序号" width="110">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户名" width="110">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="昵称" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.nickName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="性别" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.sex | sexColor"
          >{{ scope.row.sex === 1 ? "男" : "女" }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="电子邮箱" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column label="电话" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phone }}</span>
        </template>
      </el-table-column>
      <el-table-column
        class-name="status-col"
        label="状态"
        width="110"
        align="center"
      >
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter"
          >{{ scope.row.status === 0 ? "正常" : "异常" }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button @click="showUpdate(scope.row)" type="primary"
          >修改
          </el-button
          >
          <el-button type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 修改弹窗 -->
    <el-dialog title="修改用户" :visible.sync="updateDialogVisible" width="30%">
      <el-form :model="oldUser" :rules="userRules" status-icon ref="ruleForm">
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
        <el-form-item label="用户状态" prop="status">
          <el-radio-group v-model="oldUser.status">
            <el-radio :label="1" border>封禁</el-radio>
            <el-radio :label="0" border>正常</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateUser(oldUser)">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {getRoles, getUsers} from "@/api/table";
import {updateUser, deleteUser, getInfo} from "@/api/user";

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
  data() {
    return {
      list: null,
      listLoading: true,
      updateDialogVisible: false,
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
    //显示修改窗
    showUpdate(oldUser) {
      this.updateDialogVisible = true;
      this.oldUser = oldUser;
    },
    //修改用户信息
    updateUser(newUser) {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          updateUser(newUser).then((response) => {
            if (response.success) {
              this.$message({
                showClose: true,
                message: "修改用户成功!",
                type: "success",
              });
              this.updateDialogVisible = false;
            } else {
              this.$message({
                showClose: true,
                message: "修改用户失败!",
                type: "error",
              });
            }
          });
        } else {
          return false;
        }
      });
    },
    //按条件搜索用户
    search(selectForm) {
      this.listLoading = true;
      console.log(selectForm);
      getInfo(this.isEntity(selectForm)).then((response) => {
        this.list = response.data;
        this.listLoading = false;
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
      console.log(newObj);
      return newObj;
    },
  },
};
</script>
