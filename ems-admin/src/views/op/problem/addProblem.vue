<template>
  <div class="app-container">
    <el-form
        ref="form"
        :model="newProblem"
        label-width="120px"
        :rules="problemRules"
    >
      <el-form-item label="题干" prop="main">
        <el-input v-model="newProblem.main" type="textarea"/>
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-radio-group v-model="newProblem.type">
          <el-radio :label="1" border>主观题</el-radio>
          <el-radio :label="0" border>客观题</el-radio>
        </el-radio-group>
      </el-form-item>
      <template v-if="newProblem.type === 0">
        <el-button @click="addOptions(newProblem.options.length)"
        >新增选项
        </el-button
        >
        <el-form-item
            v-for="item in newProblem.options"
            :label="'选项' + item.index"
            :key="item.index"
            :prop="'index.' + index + '.text'"
            :rules="{ required: true, message: '选项不得为空!', trigger: 'blur' }"
        >
          <el-input v-model="item.text"></el-input
          >
          <el-button @click.prevent="removeProblem(item)">删除</el-button>
        </el-form-item>
      </template>
      <el-form-item label="答案" prop="answer"
      >
        <el-input v-model="newProblem.answer" type="textarea"/>
      </el-form-item>
      <el-form-item label="解析" prop="parse">
        <el-input v-model="newProblem.parse" type="textarea"/>
      </el-form-item>
      <el-form-item label="得分" prop="score">
        <el-input-number
            v-model="newProblem.score"
            :precision="2"
            :step="0.1"
            :max="50"
        ></el-input-number>
      </el-form-item>
      <el-form-item>
        <el-button @click="returnTable">返回上级</el-button>
        <el-button type="primary" @click="onSubmit">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      newProblem: {
        type: 0,
        options: [
          {
            index: "A",
          },
        ],
        score: 0,
      },
      problemRules: {
        main: [{required: true, message: "题干不得为空!", trigger: "blur"}],
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
          {
            min: 0,
            max: 50,
            message: "得分应介于0~50之间",
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
    };
  },
  methods: {
    onSubmit() {
      this.$message("submit!");
    },
    onCancel() {
      this.$message({
        message: "cancel!",
        type: "warning",
      });
    },
    //新增选项
    addOptions(index) {
      this.newProblem.options.push({
        text: "",
        index: String.fromCharCode("A".charCodeAt(0) + index),
      });
    },
    //删除选项
    removeProblem(item) {
      var index = this.newProblem.options.indexOf(item);
      if (index !== -1) {
        this.newProblem.options.splice(index, 1);
      }
    },
    //跳转到添加页面
    returnTable() {
      this.$router.push("/pro");
    },
  },
};
</script>

<style scoped>
.line {
  text-align: center;
}
</style>
