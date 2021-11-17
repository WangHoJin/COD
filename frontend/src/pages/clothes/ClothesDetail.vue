<template>
  <div>
    <ClothesImg />
    <ClothesDes />
  </div>
</template>
<script>
import ClothesImg from "../../components/clothes/ClothesImg.vue";
import ClothesDes from "../../components/clothes/ClothesDes.vue";

export default {
  components: {
    ClothesImg,
    ClothesDes,
  },
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
<style>
v-row {
  background-color: yellow;
}
</style>
