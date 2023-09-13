<template>
  <van-search
    v-model="value"
    placeholder="搜索题目"
    show-action
    @search="getProblems(menuAction)"
  >
    <template #action>
      <van-button
        @click="getProblems(menuAction)"
        icon="search"
        size="small"
        round
      ></van-button> </template
  ></van-search>
  <van-dropdown-menu>
    <van-dropdown-item v-model="menuAction.gradleId" :options="gradleList" />
    <van-dropdown-item v-model="menuAction.subjectId" :options="subjects" />
  </van-dropdown-menu>
  <van-swipe-cell v-for="problem in problems" :key="problem.id">
    <van-cell
      :border="true"
      :title="
        problem.main.length > 4
          ? problem.main.substring(0, 3) + '...(左滑开始做题)'
          : problem.main
      "
      :value="problem.type === 0 ? '选择题' : '主观题'"
    >
    </van-cell
    ><template #right>
      <van-button
        square
        type="success"
        text="开始做题"
        @click="editProblem(problem.id)"
      /> </template
  ></van-swipe-cell>
</template>

<script>
import { getGradleInfo } from "../../api/gradle";
import { getSubjectInfo } from "../../api/subject";
import { getInfo } from "../../api/problem";
import { getProblems } from "../../api/table";
export default {
  data() {
    return {
      gradleList: [],
      subjects: [],
      menuAction: {
        gradleId: "0",
        subjectId: "0",
      },
      problems: [],
    };
  },
  created() {
    this.getInfo();
    this.getAllProblem();
  },
  methods: {
    //显示下拉框内容
    getInfo() {
      getGradleInfo().then((response) => {
        this.gradleList = response.data.data.map((v) => {
          var gradle = {};
          gradle.text = v.name;
          gradle.value = v.id;
          return gradle;
        });
      });
      getSubjectInfo().then((response) => {
        this.subjects = response.data.data.map((v) => {
          var subject = {};
          subject.text = v.name;
          subject.value = v.id;
          return subject;
        });
      });
    },
    //按条件获取题目信息
    getProblems(problem) {
      getInfo(this.isEntity(problem)).then((response) => {
        this.problems = response.data.data;
      });
    },
    //获取所有题目
    getAllProblem() {
      getProblems().then((response) => {
        this.problems = response.data.data;
      });
    },
    //判断字符串
    isEntity(obj) {
      var newObj = {};
      Object.keys(obj).forEach((x) => {
        if ((obj[x] !== undefined) & (obj !== null) & (obj[x] !== "")) {
          newObj[x] = obj[x];
        }
      });
      return newObj;
    },
    //做题
    editProblem(problemId) {
      //存储ID值
      sessionStorage.setItem("current_problemId", problemId);
      this.$router.push("/ep");
    },
  },
};
</script>

<style>
</style>