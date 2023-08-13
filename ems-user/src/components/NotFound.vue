<template>
  <div class="not-found">
    <van-nav-bar title left-text="返回" left-arrow @click-left="$router.go(-1)"></van-nav-bar>
    <img src="../assets/img/not.jpeg" alt />
  </div>
</template>

<script>
export default {};
</script>

<style lang="less">
.not-found {
  .van-nav-bar {
    background: #ececec;
  }
  img {
    width: 100%;
  }
  min-height: 100vh;
  background: #ececec;
}
</style>