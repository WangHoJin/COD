<template>
  <v-container fluid>
    <MypageDetail />
    <MypageClothes />
    <MypageCodi />
  </v-container>
</template>
<script>
import MypageDetail from "@/components/mypage/MypageDetail.vue";
import MypageClothes from "@/components/mypage/MypageClothes.vue";
import MypageCodi from "@/components/mypage/MypageCodi.vue";

export default {
  components: {
    MypageDetail,
    MypageClothes,
    MypageCodi,
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
