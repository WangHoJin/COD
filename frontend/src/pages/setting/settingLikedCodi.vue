<template>
  <div>
    <LikedCodiList />
  </div>
</template>
<script>
import LikedCodiList from "@/components/setting/LikedCodiList.vue";

export default {
  components: { LikedCodiList },
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
