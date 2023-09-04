<template>
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
        { index: 1, title: "主页", icon: "notes-o", url: "" },
        { index: 2, title: "我的", icon: "user-o", url: "/user" },
      ],
    };
  },
  created() {},
  methods: {
    clickItem(url) {
      this.$router.push(url);
    },
  },
};
</script>

<style>
</style>