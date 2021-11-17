<template>
  <div>
    <CodiList />
  </div>
</template>
<script>
import CodiList from "../../components/codi/CodiList.vue";
export default {
  components: {
    // Header,
    CodiList,
  },
  data() {
    return {
      page: 1,
    };
  },
};
</script>
<style scoped></style>
