<template>
  <div><Profile /></div>
</template>
<script>
import Profile from "@/components/setting/Profile.vue";

export default {
  components: { Profile },
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
