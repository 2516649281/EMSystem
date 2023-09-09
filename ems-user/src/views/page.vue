<template>
  <van-nav-bar
    :title="tabList[active].title"
    left-text="后退"
    right-text="前进"
    left-arrow
    @click-left="leftPage(-1)"
    @click-right="leftPage(1)"
  />
  <router-view />
  <van-tabbar v-model="active">
    <van-tabbar-item
      v-for="tab in this.tabList"
      :key="tab.index"
      :icon="tab.icon"
      @click="clickItem(tab.url)"
    >
      {{ tab.title }}
    </van-tabbar-item>
  </van-tabbar>
</template>

<script>
export default {
  data() {
    return {
      active: 0,
      tabList: [
        { index: 0, title: "主页", icon: "wap-home-o", url: "/index" },
        { index: 1, title: "刷题", icon: "edit", url: "/pro" },
        { index: 2, title: "模拟考试", icon: "orders-o", url: "/exam" },
        { index: 3, title: "历史记录", icon: "notes-o", url: "/his" },
        { index: 4, title: "我的", icon: "user-o", url: "/user" },
      ],
    };
  },
  created() {},
  methods: {
    clickItem(url) {
      this.$router.push(url);
    },
    leftPage(index) {
      this.$router.go(index);
    },
  },
};
</script>

<style>
</style>