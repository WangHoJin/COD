<template>
  <div><Password /></div>
</template>
<script>
import Password from "@/components/setting/Password.vue";

export default {
  components: { Password },
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
