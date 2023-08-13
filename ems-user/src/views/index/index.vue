<template>
  <div>
    <van-tabs v-model="active" @change="onchange">
      <van-tab title="樱桃"></van-tab>
      <van-tab title="牧马人"></van-tab>
      <van-tab title="雷蛇"></van-tab>
    </van-tabs>

    <van-list style="margin-top: 10px;">
      <van-cell
        v-for="(item,index) in list"
        :key="index"
        :title="item.name"
        :value="item.saleDate"
      />
    </van-list>

    <Footer page="index"></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer";
// import { Tabbar, TabbarItem } from "vant";
// import { Tab, Tabs } from "vant";

export default {
  // components: {
  //   [Tab.name]: Tab,
  //   [Tabs.name]: Tabs
  // },
  data() {
    return {
      active: 0,
      list: [],
      // 樱桃
      list1: [
        { name: "樱桃机械键盘1", price: 400, saleDate: 1566893214215 },
        { name: "樱桃机械键盘2", price: 500, saleDate: 1566892214215 },
        { name: "樱桃机械键盘3", price: 800, saleDate: 1566893215215 }
      ],
      // 牧马人
      list2: [
        { name: "牧马人机械键盘1", price: 200, saleDate: 1566893214215 },
        { name: "牧马人机械键盘2", price: 500, saleDate: 1566893114215 }
      ],
      // 雷蛇
      list3: [
        { name: "雷蛇机械键盘1", price: 200, saleDate: 1566893214215 },
        { name: "雷蛇机械键盘2", price: 500, saleDate: 1566193214215 },
        { name: "雷蛇机械键盘3", price: 400, saleDate: 1566393212215 },
        { name: "雷蛇机械键盘4", price: 800, saleDate: 1566892214215 }
      ]
    };
  },
  created() {
    let index = this.active + 1;
    this.list = this[`list${index}`]; // this.list1
    // this.getGoods();
  },
  components: {
    Footer
  },
  methods: {
    onchange(index) {
      this.list = this[`list${index + 1}`];
    },

    getGoods() {
      let url = "/goods"; // /api/goods
      this.$axios
        .get(url)
        .then(res => {
          console.log("res", res);
        })
        .catch(err => {
          console.log("err", err);
        });
    }
  }
};
</script>

<style>
</style>
