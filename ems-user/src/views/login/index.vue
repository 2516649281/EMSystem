<template>
  <header class="login">
    <van-icon name="point-gift-o" class="orange mt-30 logo"></van-icon>
    <div class>
      <!-- 手机号码 -->
      <div class="handset ml-20 mr-20 flex jc-sb pb-5">
        <input v-model="phone" class="f14" type="text" placeholder="手机号" />
        <input class="gray f12 bg-fff" type="button" value=" 获取验证码" />
      </div>
      <!-- 验证码 -->
      <div class="handset ml-20 mr-20 flex jc-sb mt-20 pb-5">
        <input v-model="smsCode" class="f14" placeholder="验证码" />
      </div>

      <!-- 登录 -->
      <button @click="login" class="butt ml-20 mr-20 fff">登录</button>
    </div>
  </header>
</template>

<script>
import { constants } from "crypto";
export default {
  data() {
    return {
      phone: "13800000000",
      smsCode: "123456"
    };
  },
  methods: {
    login() {
      // 发送请求,用post方式
      let url = "/login";
      // 请求参数
      let data = {
        phone: this.phone,
        smsCode: this.smsCode
      };
      this.$axios.post(url, data).then(res => {
        // 修改登陆状态
        this.$store.commit("updateLogin", true);
        // 把token存入store
        this.$store.commit("updateToken", res.user.token);
        // 把用户名存入store
        this.$store.commit("updateUsername", res.user.phone);
        this.$router.push("/my");
      }).catch(err=> {
        console.log('登陆失败')
      });
    }
  }
};
</script>

<style lang="less" scoped>
@import "./login.less";
.logo {
  font-size: 100px !important;
  margin-bottom: 150px;
}
</style>

