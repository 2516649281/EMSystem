<template>
  <div>
    <keep-alive>
      <router-view v-if="$route.meta.keepAlive"></router-view>
    </keep-alive>
    <router-view v-if="!$route.meta.keepAlive"></router-view>
  </div>
</template>

<script>
console.log(process.env.NODE_ENV);
export default {
  name: "app",
  created() {
    this.$axios.get("/category/all").then(res => {
      console.log(res);
    });
  }
};
</script>



