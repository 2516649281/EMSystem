export default {
  data() {
    return {};
  },
  fomatDate(time) {
    let date = new Date(time);
    let Y = date.getFullYear();
    let M = date.getMonth() + 1;
    let D = date.getDate();
    return `${Y}年-${M}月-${D}日`;
  }
};
