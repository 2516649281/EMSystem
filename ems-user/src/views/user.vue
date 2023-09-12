<template>
  <van-cell-group>
    <van-cell
      title="头像"
      label="点击修改头像"
      is-link
      center
      @click="showDialog = true"
    >
      <van-image width="60" height="60" :src="userAvatar" />
    </van-cell>
    <van-cell
      v-for="cell in cellList"
      :key="cell.index"
      :title="cell.title"
      :value="
        cell.value === 'sex'
          ? userInfo[cell.value] === 0
            ? '女'
            : '男'
          : userInfo[cell.value]
      "
      :label="cell.label"
      center
    />
  </van-cell-group>
  <van-button type="danger" @click="logout()" style="width: 100%"
  >退出登录
  </van-button
  >
  <van-overlay :show="showOver" @click="showOver = false">
    <div class="main"><van-loading type="spinner">加载中...</van-loading></div>
  </van-overlay>
  <van-dialog
    v-model="showDialog"
    title="上传头像"
    show-cancel-button
    message-align="center"
  >
    <van-uploader
      v-model="fileList"
      :after-read="setAvatar"
      multiple
      :max-count="1"
    />
  </van-dialog>
</template>

<script>
import {getInfo, avatar, logout} from "../api/user";
import {showConfirmDialog} from "vant";
export default {
  data() {
    return {
      cellList: [
        { index: 0, title: "用户名", value: "name", label: "您登陆时的账号" },
        { index: 1, title: "昵称", value: "nickName", label: "设置昵称" },
        { index: 4, title: "性别", value: "sex", label: "性别" },
        { index: 2, title: "电话", value: "phone", label: "您的电话" },
        { index: 3, title: "邮箱", value: "email", label: "您的电子邮箱" },
      ],
      userInfo: {},
      userAvatar: [],
      showOver: false,
      showDialog: false,
      fileList: [],
    };
  },
  created() {
    var id = sessionStorage.getItem("user_id");
    this.getUser(id);
    this.getHeader(id);
  },
  methods: {
    //获取头像
    getHeader(userId) {
      avatar({ userId: userId }).then((response) => {
        this.userAvatar = window.URL.createObjectURL(response.data);
      });
    },
    //获取用户信息
    getUser(userId) {
      this.showOver = true;
      getInfo({ id: userId }).then((response) => {
        this.userInfo = response.data.data[0];
        this.showOver = false;
      });
    },
    //设置头像
    setAvatar(file) {
      const isJPG = file.type === "image/jpeg" || file.type === "image/png";
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        ShowNotify("上传头像图片只能是 JPG或PNG 格式!");
      }
      if (!isLt2M) {
        ShowNotify("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    //登出
    logout() {
      showConfirmDialog({
        title: "退出登录",
        message: "你真的要退出登录吗?",
      }).then(() => {
        logout(sessionStorage.getItem("token")).then((response) => {
          this.$router.push("/login");
        });
      });
    },
  },
};
</script>

<style scoped>
.main {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}
.van-loading {
  width: 120px;
  height: 120px;
}
</style>