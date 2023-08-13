<template>
  <div class="page">
    <van-nav-bar title="设置" left-text="返回" left-arrow @click-left="back"></van-nav-bar>

    <van-cell-group class="mt-10">
      <van-cell title="账号id" value="888888"></van-cell>
      <van-cell isLink title="登录密码" value="未设置"></van-cell>
      <van-cell isLink title="安全密码" value="未设置"></van-cell>
    </van-cell-group>

    <!-- 退出登录 -->
    <div class="logout f16 flex fcc bg-fff fixed w100pc" @click="logout">
      <p class="blue">退出登录</p>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    back() {
      history.back();
    },
    async logout() {
      let url = "/logout";
      let res = await this.$axios.post(url);
      // 修改登陆状态
      this.$store.commit("updateLogin", false);
      // 把用户名置空
      this.$store.commit("updateUsername", "");
      // 清除token
      this.$store.commit("updateToken", "");
      this.$router.push("/my");
    }
  }
};
</script>

<style lang="less" scoped>
.logout {
  bottom: 0;
  height: 50px;
  color: #ff5f16;
}
</style>

