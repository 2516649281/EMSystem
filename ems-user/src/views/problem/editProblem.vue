<template>
  <div class="problem-type">
    <van-tag :type="problemInfo.type === 0 ? 'success' : 'primary'">{{
      problemInfo.type === 0 ? "选择题" : "主观题"
    }}</van-tag>
    <van-tag
      v-if="problemInfo.options !== null"
      :color="isMuSelect ? '#7232dd' : '#FF7400'"
      >{{ isMuSelect ? "多选" : "单选" }}</van-tag
    >
    <van-count-down :time="time" @finish="finish" ref="countDown" />
    <div class="problem-main">
      <p v-for="main in problemInfo.mainList" :key="main.$index">
        {{ main }}
      </p>
    </div>
  </div>
  <div class="problem-awe" v-if="problemInfo.type === 0">
    <van-checkbox-group v-model="userAnswer">
      <van-cell-group v-if="isMuSelect"
        ><van-cell
          v-for="(option, index) in problemInfo.optionList"
          :key="option[0]"
          :title="`${option[0]}.${option[1]}`"
          @click="toggle(index)"
          :style="
            trueAnswer.includes(option[0])
              ? checkStyle.success
              : falseAnswer.includes(option[0])
              ? checkStyle.error
              : ''
          "
        >
          <template #right-icon>
            <van-checkbox :name="option[0]" ref="checkBox">
              <template #icon="props">
                <van-icon
                  :name="
                    props.checked
                      ? trueAnswer.includes(option[0])
                        ? 'success'
                        : falseAnswer.includes(option[0])
                        ? 'cross'
                        : 'success'
                      : 'success'
                  "
                  :color="
                    props.checked
                      ? trueAnswer.includes(option[0])
                        ? '#67c23a'
                        : falseAnswer.includes(option[0])
                        ? '#ee0a24'
                        : '#1989fa'
                      : ''
                  "
                  :style="
                    props.checked
                      ? trueAnswer.includes(option[0])
                        ? checkIconStyle.success
                        : falseAnswer.includes(option[0])
                        ? checkIconStyle.error
                        : checkIconStyle.default
                      : checkIconStyle.default
                  "
                ></van-icon>
              </template>
            </van-checkbox>
          </template>
        </van-cell>
      </van-cell-group>
      <template v-else>
        <van-button
          plain
          hairline
          :type="
            trueAnswer[0] === option[0]
              ? 'success'
              : falseAnswer[0] === option[0]
              ? 'danger'
              : 'primary'
          "
          :icon="
            trueAnswer[0] === option[0]
              ? 'success'
              : falseAnswer[0] === option[0]
              ? 'cross'
              : ''
          "
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
      <van-button plain hairline type="primary" @click="edit(userAnswer)"
        >提交</van-button
      >
    </div>
  </div>
  <div class="problem-text" v-if="problemInfo.type === 1">
    <van-button type="primary" @click="lookAnswer">查看答案</van-button>
  </div>
  <div class="problem-parse" v-if="parseShow">
    <van-tag type="success">解析</van-tag>
    <p v-for="pause in problemInfo.pauseList" :key="pause.$index">
      {{ pause }}
    </p>
    <p>
      <van-tag type="warning">答案</van-tag>
    </p>
    <p v-for="answer in problemInfo.answerList" :key="answer.$index">
      {{ answer }}
    </p>
  </div>
</template>

<script>
import { getInfo } from "../../api/problem";
export default {
  data() {
    return {
      problemInfo: {
        mainList: [],
        pauseList: [],
        answerList: [],
      },
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
      checkStyle: {
        success: {
          border: "1px solid #67c23a",
          marginBottom: "10px",
        },
        error: {
          border: "1px solid #ee0a24",
          marginBottom: "10px",
        },
      },
      checkIconStyle: {
        success: {
          border: "1px solid #67c23a",
        },
        error: {
          border: "1px solid #ee0a24",
        },
        default: {
          border: "1px solid #1989fa",
        },
      },
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
          var mains = data.main;
          var mainList = mains.split("\n");
          var pause = data.parse.split("\n");
          if (data.type === 1) {
            data.answerList = data.answer.split("\n");
          }
          //转为Map集合
          if (options != null) {
            data.optionList = new Map(Object.entries(JSON.parse(options)));
          }
          data.mainList = mainList;
          data.pauseList = pause;
          console.log(data);
          this.isMuSelect = data.answer.length > 1;
          this.problemInfo = data;
        }
      );
    },
    //超时逻辑
    finish() {
      // 显示解析
      this.parseShow = true;
      //显示答案
      this.trueAnswer = this.problemInfo.answer;
    },
    //查看答案
    lookAnswer() {
      this.finish();
      this.$refs.countDown.pause();
    },
    //多选
    edit(userAnswer) {
      //先转成数组再排序最后转回字符串
      var a = this.problemInfo.answer.split("");
      var ua = userAnswer;
      var a = a.sort().toString();
      ua = ua.sort();
      ua = ua.toString();
      a = a.toString();
      //显示正确答案
      this.trueAnswer = this.problemInfo.answer;
      //回答错误
      if (ua !== a) {
        this.falseAnswer = ua;
      }
      this.parseShow = true;
    },
    //单选逻辑
    buttonClick(index) {
      //选中的按钮
      var clickBtn = this.$refs.btn[index];
      //正确答案
      var answer = this.problemInfo.answer;
      //选中的选项
      var value = clickBtn.$attrs.name;
      this.trueAnswer[0] = answer;
      //回答错误
      if (answer !== value) {
        this.falseAnswer[0] = value;
      }
      this.parseShow = true;
      this.$refs.countDown.pause();
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
  /* height: 90px; */
}
.problem-main {
  width: 100%;
  /* height: 80%; */
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
.van-cell {
  margin-bottom: 10px;
  border: 1px solid white;
}
.van-icon {
  background: white;
}
.problem-text {
}
</style>