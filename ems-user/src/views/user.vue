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
      v-model:show="avatarDialog"
      title="上传头像"
      show-cancel-button
      confirm-button-text="上传"
      show-confirm-button
      @confirm="uploadAvatar(fileList)"
  >
    <van-field label="头像" center>
      <template #input>
        <van-uploader
            v-model="fileList"
            multiple
            :max-count="1"
            :after-read="getFile"
            :before-read="setAvatar"
        >
        </van-uploader>
      </template>
    </van-field>
    <van-cell title="大小" value="不得超过2MB"></van-cell>
    <van-cell title="格式" value="jpg/png"></van-cell>
  </van-dialog>
  <van-dialog
      v-model:show="updateDialog"
      title="修改信息"
    show-cancel-button
      @confirm="updateTest"
      confirm-button-text="修改"
      show-confirm-button
  >
    <van-cell-group inset>
      <van-form ref="updateUserForm" @submit="updateTest(updateUserInfo)">
        <van-field label="修改密码?">
          <template #input>
            <van-switch v-model="updatePassword"/>
          </template>
        </van-field>
        <van-field
            v-for="cell in cellList"
            :key="cell.index"
            :label="cell.title"
        >
          <template #input v-if="cell.value === 'sex'"
          >
            <van-radio-group
                direction="horizontal"
                v-model="updateUserInfo[cell.value]"
                :name="cell.value"
                :placeholder="cell.label"
                :rules="[{ required: true, message: `${cell.message}` }]"
            >
              <van-radio :name="0">女</van-radio>
              <van-radio :name="1">男</van-radio>
            </van-radio-group>
          </template
          >
          <template #input v-else>
            <van-field
                v-model="updateUserInfo[cell.value]"
                :name="cell.value"
                :placeholder="cell.label"
                :rules="[{ required: true, message: `${cell.message}` }]"
            >
            </van-field>
          </template>
        </van-field>
        <van-field
            v-model="updateUserInfo.password"
            type="password"
            name="password"
            label="密码"
            placeholder="密码"
            :rules="[{ validator: PasswordTest }]"
            v-if="updatePassword"
        />
        <van-field
            v-model="updateUserInfo.aPassword"
            type="password"
            name="aPassword"
            label="确认密码"
            placeholder="确认密码"
            :rules="[{ validator: PasswordTest }]"
            v-if="updatePassword"
        />
      </van-form>
    </van-cell-group>
  </van-dialog>
</template>

<script>
import {avatar, getInfo, logout, setAvatar, updateUser} from "../api/user";
import {showConfirmDialog, showSuccessToast, showToast} from "vant";

export default {
  data() {
    return {
      cellList: [
        {
          index: 0,
          title: "用户名",
          value: "name",
          label: "您登陆时的账号",
          message: "账号不得为空!",
        },
        {
          index: 1,
          title: "昵称",
          value: "nickName",
          label: "设置昵称",
          message: "昵称不得为空!",
        },
        {
          index: 4,
          title: "性别",
          value: "sex",
          label: "性别",
          message: "请选择至少一个!",
        },
        {
          index: 2,
          title: "电话",
          value: "phone",
          label: "您的电话",
          message: "电话不得为空!",
        },
        {
          index: 3,
          title: "邮箱",
          value: "email",
          label: "您的电子邮箱",
          message: "电子邮箱不得为空!",
        },
      ],
      userInfo: {},
      userAvatar: [],
      showOver: false,
      updateDialog: false,
      avatarDialog: false,
      fileList: [],
      updateUserInfo: {},
      id: "",
      updatePassword: false,
    };
  },
  created() {
    this.id = sessionStorage.getItem("user_id");
    this.getUser(this.id);
    this.getHeader(this.id);
  },
  methods: {
    //获取头像
    getHeader(userId) {
      avatar({ userId: userId }).then((response) => {
        if (this.userAvatar) {
          this.userAvatar = null;
        }
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
        showToast("上传头像图片只能是 JPG或PNG 格式!");
      }
      if (!isLt2M) {
        showToast("上传头像图片大小不能超过 2MB!");
      }
      console.log(`${isJPG}-${isLt2M}`);
      return isJPG && isLt2M;
    },
    //头像上传逻辑
    uploadAvatar(file) {
      this.fileList[0].status = "uploading";
      var data = new FormData();
      data.append("file", file[0].file);
      setAvatar({userId: this.id}, data).then((response) => {
        if (response.data.success) {
          showSuccessToast("修改头像成功!");
          this.getHeader(this.id);
          this.fileList[0].status = "done";
        } else {
          this.fileList[0].status = "failed";
        }
      });
    },
    //登出
    logout() {
      showConfirmDialog({
        title: "退出登录",
        message: "你真的要退出登录吗?",
      })
          .then(() => {
            var token = sessionStorage.getItem("token");
            logout({token}).then((response) => {
              if (response.data.data) {
              this.$router.push("/login");
              }
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
    updateTest() {
      //校验
      this.$refs.updateUserForm.validate().then(
          (res) => {
            //校验通过时
            this.updateUser(this.updateUserInfo);
          },
          (res) => {
            //校验失败时
            return false;
          }
      );
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
      //判空
      if (userInfo.password === "") {
        userInfo.password = null;
      }
      updateUser(userInfo).then((response) => {
        if (response.data.success) {
          showSuccessToast("修改成功!");
          this.updateDialog = false;
          //查询
          this.getUser(this.id);
        }
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