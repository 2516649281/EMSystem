<template>
  <div class="index-container">
    {{ name }}
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "index",
  computed: {
    ...mapGetters(["name"]),
  },
  data() {
    return {};
  },
  methods: {},
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
</style>
