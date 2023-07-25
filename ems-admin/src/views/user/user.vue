<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="编号" width="95">
        <template slot-scope="scope">
          {{ scope.$index }}
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
          {{ scope.row.sex === 0 ? "女" : "男" }}
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
          <el-tag :type="scope.row.status | statusFilter">{{
              scope.row.status === 0 ? "正常" : "异常"
            }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button>修改</el-button>
          <el-button>删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {getUsers} from "@/api/table";

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
      listLoading: true,
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
    },
  },
};
</script>
