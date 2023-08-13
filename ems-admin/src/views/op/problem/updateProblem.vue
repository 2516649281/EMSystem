<template>
  <div class="app-container">
    <el-form
      ref="ruleForm"
      :model="oldProblem"
      label-width="120px"
      :rules="problemRules"
    >
      <el-form-item label="题干" prop="main">
        <el-input v-model="oldProblem.main" type="textarea"/>
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-radio-group v-model="oldProblem.type">
          <el-radio :label="1" border>主观题</el-radio>
          <el-radio :label="0" border>客观题</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="科目" prop="subjectId">
        <el-select
          filterable
          v-model="oldProblem.subjectId"
          clearable
          placeholder="选择科目"
        >
          <el-option
            :label="subject.name"
            :value="subject.id"
            v-for="subject in subjects"
            :key="subject.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="年级" prop="gradleId">
        <el-select
          filterable
          v-model="oldProblem.gradleId"
          clearable
          placeholder="选择年级"
        >
          <el-option
            :label="gradle.name"
            :value="gradle.id"
            v-for="gradle in grades"
            :key="gradle.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="addOptions(options.length)">新增选项</el-button>
      </el-form-item>
      <template v-if="oldProblem.type === 0">
        <el-form-item
          v-for="item in options"
          :label="'选项' + item.index"
          :key="item.index"
          :rules="{ required: true, message: '选项不得为空!', trigger: 'blur' }"
        >
          <el-input v-model="item.text"></el-input>
          <el-button @click.prevent="removeProblem(item)">删除</el-button>
        </el-form-item>
      </template>
      <el-form-item label="答案" prop="answer">
        <el-input v-model="oldProblem.answer" prop="answer"></el-input>
      </el-form-item>
      <el-form-item label="解析" prop="parse">
        <el-input v-model="oldProblem.parse" type="textarea"/>
      </el-form-item>
      <el-form-item label="得分" prop="score">
        <el-input-number
          v-model="oldProblem.score"
          :precision="1"
          :step="0.1"
          :max="50"
        ></el-input-number>
      </el-form-item>
      <el-form-item>
        <el-button @click="returnTable">返回上级</el-button>
        <el-button type="primary" @click="addProblem(oldProblem)"
        >提交
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {updateProblem} from "@/api/problem";
import {getGrades, getSubjects} from "@/api/table";
import {getProblem} from "./public";

export default {
  data() {
    return {
      grades: [],
      subjects: [],
      options: [
        {
          index: "A",
          text: "",
        },
      ],
      oldProblem: {
        type: 0,
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
            min: 0.0,
            max: 50.0,
            message: "得分应介于0~50之间",
            type: "number",
          },
        ],
        subjectId: [
          {
            required: true,
            message: "请选择科目!",
            trigger: "change",
          },
        ],
        gradleId: [
          {
            required: true,
            message: "请选择年级!",
            trigger: "change",
          },
        ],
      },
    };
  },
  created() {
    this.initData();
    this.getSubjects();
  },
  methods: {
    // 初始化数据
    initData() {
      this.oldProblem = getProblem();
      var option = JSON.parse(this.oldProblem.options);
      var i = 0;
      for (const key in option) {
        var obj = {};
        obj["index"] = key;
        obj["text"] = option[key];
        this.options[i] = obj;
        i++;
      }
      console.log(this.options);
    },
    //修改
    addProblem(problem) {
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          //选择题处理逻辑
          if (problem.type === 0) {
            var map = new Map();
            this.options.forEach((v) => {
              map.set(v.index, v.text);
            });
            problem.options = JSON.stringify(Object.fromEntries(map));
          } else {
            problem.options = null;
          }
          updateProblem(problem).then((response) => {
            if (response.success) {
              this.$message({
                showClose: true,
                message: "修改题目成功!",
                type: "success",
              });
              this.returnTable();
            }
          });
        } else {
          return false;
        }
      });
    },
    //获取列表
    getSubjects() {
      getSubjects().then((response) => {
        this.subjects = response.data;
      });
      getGrades().then((response) => {
        this.grades = response.data;
      });
    },
    //新增选项
    addOptions(index) {
      this.options.push({
        text: "",
        index: String.fromCharCode("A".charCodeAt(0) + index),
      });
    },
    //删除选项
    removeProblem(item) {
      var index = this.options.indexOf(item);
      if (index !== -1) {
        this.options.splice(index, 1);
      }
    },
    //跳转到主页面
    returnTable() {
      this.$router.push("/op/pro");
    },
  },
};
</script>

<style scoped>
.line {
  text-align: center;
}
</style>
