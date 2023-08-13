
<template>
  <div>
    <div></div>
    <Footer page="demo"></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer";
export default {
  components: {
    Footer
  },
  i18n: {
    "zh-CN": {
      type: "加载类型",
      color: "自定义颜色",
      text: "加载文案"
    },
    "en-US": {
      type: "Type",
      color: "Color",
      text: "Text"
    }
  }
};
</script>

<style lang="less" scoped>
div {
  height: 100px;
  width: 100px;
  background: green;
}
.van-loading {
  display: inline-block;
  margin: 5px 0 5px 20px;
  background: gray;
}
</style>