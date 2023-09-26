<template>
  <van-cell-group>
    <van-cell
      title="头像"
      label="点击修改头像"
      is-link
      center
      @click="avatarDialog = true"
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
      is-link
      @click="initUpdate(userInfo)"
      center
    />
  </van-cell-group>
  <van-button type="danger" @click="logout()" style="width: 100%"
  >退出登录
  </van-button>
  <van-overlay :show="showOver" @click="showOver = false">
    <div class="main"><van-loading type="spinner">加载中...</van-loading></div>
  </van-overlay>
  <van-dialog
      v-model:show="updateDialog"
      title="修改信息"
    show-cancel-button
      :beforeClose="updateTest(updateUserInfo)"
      confirmButtonText="修改"
  >
    <van-cell-group inset>
      <van-form ref="updateUserForm">
        <van-field
            name="name"
            v-model="updateUserInfo.name"
            label="用户名"
            placeholder="用户名"
            :rules="[{ required: true, message: '请填写用户名' }]"
        />
        <van-field
            v-model="updateUserInfo.password"
            type="password"
            name="password"
            label="密码"
            placeholder="密码"
            :rules="[{ validator: PasswordTest }]"
        />
        <van-field
            v-model="updateUserInfo.aPassword"
            type="password"
            name="aPassword"
            label="确认密码"
            placeholder="确认密码"
            :rules="[{ validator: PasswordTest }]"
        />
        <van-field label="选择性别" name="sex">
          <template #input>
            <van-radio-group
                v-model="updateUserInfo.sex"
                direction="horizontal"
                :rules="[{ required: true, message: '请指定性别' }]"
            >
              <van-radio :name="0">女</van-radio>
              <van-radio :name="1">男</van-radio>
            </van-radio-group>
          </template>
        </van-field>
        <van-field
            label="手机号"
            v-model="updateUserInfo.phone"
            placeholder="手机号"
            name="phone"
            :rules="[{ required: true, message: '请输入手机号' }]"
        />
        <van-field
            v-model="updateUserInfo.email"
            placeholder="邮箱"
            name="email"
            label="邮箱"
            :rules="[{ required: true, message: '请输入邮箱' }]"
        />
      </van-form>
    </van-cell-group>
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
      updateDialog: false,
      avatarDialog: false,
      fileList: [],
      updateUserInfo: {},
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
      })
          .then(() => {
            logout(sessionStorage.getItem("token")).then((response) => {
              this.$router.push("/login");
            });
          })
          .catch(() => {
          });
    },
    //初始化更新逻辑
    initUpdate(userInfo) {
      this.updateUserInfo = userInfo;
      this.updateDialog = true;
    },
    //更新校验
    updateTest(updateUserInfo) {
      this.$nextTick(() => {
        console.log(this.$refs["updateUserForm"]);
      });
    },
    //确认密码校验逻辑
    PasswordTest(val) {
      if (!val) {
        return "密码不得为空";
      }
      //判断两次输入的密码是否一致
      if (val !== this.updateUserInfo.password) {
        return "两次输入的密码不一致";
      }
      if (val.length < 8) {
        return "密码不得少于8位";
      }
      return true;
    },
    //更新用户主逻辑
    updateUser(userInfo) {
      console.log(userInfo);
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