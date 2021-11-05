<template>
  <div>
    코디등록페이지
    <CodiImg />
    <CodiCreateForm />
  </div>
</template>
<script>
import CodiImg from "../../components/codi/CodiImg.vue";
import CodiCreateForm from "../../components/codi/CodiCreateForm.vue";
export default {
  components: { CodiImg, CodiCreateForm },
};
</script>
<style></style>
