<template>
  <div class="index-container">
    <el-descriptions title="用户信息">
      <el-descriptions-item label="用户名">{{ name }}</el-descriptions-item>
      <el-descriptions-item label="当前头像"
      ><img :src="avatar" class="user-avatar"/>
      </el-descriptions-item>
      <el-descriptions-item label="头像上传">
        <el-upload
          class="upload-demo"
          ref="upload"
          action=""
          :file-list="fileList"
          :auto-upload="false"
          :http-request="fileUpload"
          :limit="1"
          list-type="picture"
        >
          <el-button slot="trigger" size="small" type="primary"
          >选取文件
          </el-button
          >
          <el-button
            style="margin-left: 10px"
            size="small"
            type="success"
            @click="readyUpload"
          >上传到服务器
          </el-button
          >
          <div slot="tip" class="el-upload__tip">
            只能上传jpg/png文件,且不超过2MB
          </div>
        </el-upload>
      </el-descriptions-item
      >
    </el-descriptions>
    <el-calendar v-model="value"></el-calendar>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import {setAvatar} from "@/api/user";

export default {
  name: "index",
  computed: {
    ...mapGetters(["name", "avatar", "id"]),
  },
  data() {
    return {
      fileList: [],
      value: new Date(),
    };
  },
  methods: {
    //头像上传判断
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg" || file.type === "image/png";
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG或PNG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    //手动上传
    readyUpload() {
      this.fileDataLoading = true;
      this.$refs.upload.submit();
    },
    //文件上传主逻辑
    fileUpload(param) {
      var data = new FormData();
      data.append("file", param.file);
      setAvatar({userId: this.id}, data).then((response) => {
        if (response.success) {
          this.$message({
            showClose: true,
            message: "头像上传成功!",
            type: "success",
          });
        }
        this.$store.dispatch("user/getAvatar");
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.index {
  &-container {
    margin: 30px;
  }

  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}

.user-avatar {
  height: 80px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
}
</style>
