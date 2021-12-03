<template>
  <div><Info /></div>
</template>
<script>
import Info from "@/components/setting/Info.vue";

export default {
  components: { Info },
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
