<template>
  <div>
    <p class="title">欢迎使用考试管理系统</p>
    <van-form @submit="login(userLogin)">
      <van-cell-group inset>
        <van-field
          v-model="userLogin.username"
          name="用户名"
          label="用户名"
          placeholder="用户名"
          :rules="[{ required: true, message: '请填写用户名' }]"
        />
        <van-field
          v-model="userLogin.password"
          type="password"
          name="密码"
          label="密码"
          placeholder="密码"
          :rules="[{ required: true, message: '请填写密码' }]"
        />
      </van-cell-group>
      <div style="margin: 16px">
        <van-button round block type="primary" native-type="submit">
          提交
        </van-button>
      </div>
    </van-form>
  </div>
</template>

<script>
import { login } from "../api/user";
import { showNotify } from "vant";
import JWT from "jwt-decode";

export default {
  data() {
    return {
      userLogin: {
        username: "",
        password: "",
      },
    };
  },
  created() {},
  methods: {
    login(user) {
      login(user).then((response) => {
        if (response.data.success) {
          showNotify({ type: "success", message: "登录成功!" });
          //存入token
          sessionStorage.setItem("token", response.data.data);
          var id = JWT(response.data.data).user;
          sessionStorage.setItem("user_id", id);
          this.$router.push("/");
        }
      });
    },
  },
};
</script>

<style scoped>
.title {
  text-align: center;
  font-size: 20px;
}
</style>