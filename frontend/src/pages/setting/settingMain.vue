<template>
  <div><SettingList /></div>
</template>
<script>
import SettingList from "@/components/setting/SettingList.vue";

export default {
  components: { SettingList },
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
