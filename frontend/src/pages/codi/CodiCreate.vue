<template>
  <div>
    <CodiCreateForm />
    <ChoiceClothes />
  </div>
</template>
<script>
import ChoiceClothes from "../../components/codi/ChoiceClothes.vue";
import CodiCreateForm from "../../components/codi/CodiCreateForm.vue";
export default {
  components: {
    ChoiceClothes,
    CodiCreateForm,
  },
};
</script>
<style scoped></style>
