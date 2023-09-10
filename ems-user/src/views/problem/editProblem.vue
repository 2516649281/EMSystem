<template>
  <div class="problem-type">
    <van-tag :type="problemInfo.type === 0 ? 'success' : 'primary'">{{
      problemInfo.type === 0 ? "选择题" : "主观题"
    }}</van-tag>
    <van-tag :color="isMuSelect ? '#7232dd' : '#FF7400'">{{
      isMuSelect ? "多选" : "单选"
    }}</van-tag>
    <van-count-down :time="time" @finish="finish" />
    <p class="problem-main">{{ problemInfo.main }}</p>
  </div>
  <div class="problem-awe" v-if="problemInfo.type === 0">
    <van-checkbox-group v-model="userAnswer">
      <van-cell-group v-if="isMuSelect"
        ><van-cell
          v-for="(option, index) in problemInfo.optionList"
          :key="option[0]"
          :title="`${option[0]}.${option[1]}`"
          @click="toggle(index)"
        >
          <template #right-icon>
            <van-checkbox :name="option[0]" ref="checkBox" />
          </template>
        </van-cell>
      </van-cell-group>
      <template v-else>
        <van-button
          plain
          hairline
          type="primary"
          v-for="(option, index) in problemInfo.optionList"
          :name="option[0]"
          :key="option[0]"
          ref="btn"
          @click="buttonClick(index)"
          >{{ option[0] }}.{{ option[1] }}</van-button
        >
      </template>
    </van-checkbox-group>
    <div class="submit-select" v-if="isMuSelect">
      <van-button plain hairline type="success" @click="edit(userAnswer)"
        >提交</van-button
      >
    </div>
  </div>
  <div class="problem-text" v-if="problemInfo.type === 1"></div>
  <div class="problem-parse" v-if="parseShow">
    <p><van-tag type="success">解析</van-tag>{{ problemInfo.parse }}</p>
    <p><van-tag type="warning">答案</van-tag>{{ problemInfo.answer }}</p>
  </div>
</template>

<script>
import { getInfo } from "../../api/problem";
export default {
  data() {
    return {
      problemInfo: {},
      //时*分*秒*毫秒
      time: 1 * 5 * 60 * 1000,
      //是否是多选
      isMuSelect: false,
      //用户答案
      userAnswer: [],
      //是否显示解析和答案
      parseShow: false,
      //错误答案
      falseAnswer: [],
      //正确答案
      trueAnswer: [],
      selectBtnId: 0,
    };
  },
  created() {
    this.getProblem();
  },
  methods: {
    getProblem() {
      getInfo({ id: sessionStorage.getItem("current_problemId") }).then(
        (response) => {
          var data = response.data.data[0];
          var options = data.options;
          //转为Map集合
          data.optionList = new Map(Object.entries(JSON.parse(options)));
          this.isMuSelect = data.answer.length > 1;
          this.problemInfo = data;
        }
      );
    },
    finish() {
      console.log(111);
    },
    //选择
    edit(userAnswer) {
      //先转成数组再排序最后转回字符串
      var a = this.problemInfo.answer.split("");
      var ua = userAnswer;
      var a = a.sort().toString();
      ua = ua.sort();
      ua = ua.toString();
      a = a.toString();
      this.parseShow = true;
    },
    //单选逻辑
    buttonClick(index) {
      var answer = this.problemInfo.answer;
      console.log(this.$refs.btn[index]);
      console.log(answer);
      //判断是否正确

      //将点击的按钮和正确答案按钮更改颜色
    },
    //选中逻辑
    toggle(index) {
      this.$refs.checkBox[index].toggle();
    },
  },
};
</script>

<style scoped>
.problem-type {
  width: 100%;
  height: 90px;
}
.problem-main {
  width: 100%;
  height: 50%;
  /* font-size: 15px; */
}
.van-count-down {
  float: right;
}
.problem-awe .van-button,
.van-checkbox {
  margin-top: 10px;
}
.van-button {
  width: 100%;
}
.problem-parse {
}
</style>