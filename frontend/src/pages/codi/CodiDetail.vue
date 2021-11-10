<template>
  <div>
    {{ no }}번 코디상세페이지
    <CodiImg />
    <CodiDes />
    <CodiComment />
  </div>
</template>
<script>
import CodiImg from "../../components/codi/CodiImg.vue";
import CodiDes from "../../components/codi/CodiDes.vue";
import CodiComment from "../../components/codi/CodiComment.vue";

export default {
  components: { CodiImg, CodiDes, CodiComment },
  data() {
    return {
      no: "",
    };
  },
  created() {
    this.no = this.$route.params.no;
  },
};
</script>
<style>
v-row {
  background-color: yellow;
}
</style>
