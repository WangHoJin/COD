<template>
  <div></div>
</template>
<script>
// import MypageFollow from "@/components/mypage/MypageFollow.vue";

export default {
  components: {},
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
<style scoped></style>
