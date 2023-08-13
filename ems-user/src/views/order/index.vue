<template>
  <div>
    <van-nav-bar title="我的订单" left-text="返回" @click-left="back" left-arrow/>
    <van-list v-model="loading" :finished="finished" finished-text="没有更多了" @load="onLoad">
      <template v-for="(item,index) in list">
        <van-card
          :num="index"
          :key="item.id"
          :price="item.price"
          :desc="desc"
          :title="title"
          :thumb="item.imageURL"
        />
      </template>
    </van-list>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: [],
      loading: false,
      finished: false
    };
  },

  created() {
    this.getList();
  },

  methods: {
    back() {
      history.back();
    },
    async getList() {
      let url = "/order";
      this.loading = true;
      try {
        let res = await this.$axios.get(url);
        this.list = res.list;
      } catch (error) {}
      this.loading = false;
      this.finished = true;
    }
  }
};
</script>

<style lang="less">
.card__footer {
  padding-top: 10px;
}
.card__tags {
  .van-tag {
    margin-right: 5px;
  }
}
</style>


