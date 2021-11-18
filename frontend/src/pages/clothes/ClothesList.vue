<template>
  <div>
    <ClothesList />
  </div>
</template>
<script>
import ClothesList from "../../components/clothes/ClothesList.vue";
export default {
  components: {
    // Header,
    ClothesList,
  },
  data() {
    return {
      page: 1,
    };
  },
};
</script>
<style scoped></style>
