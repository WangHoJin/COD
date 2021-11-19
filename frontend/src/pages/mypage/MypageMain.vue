<template>
  <v-container fluid>
    <MypageDetail :userInfo="userInfo" />
    <MypageClothes :userInfo="userInfo" />
    <MypageCodi :userInfo="userInfo" />
  </v-container>
</template>
<script>
import MypageDetail from "@/components/mypage/MypageDetail.vue";
import MypageClothes from "@/components/mypage/MypageClothes.vue";
import MypageCodi from "@/components/mypage/MypageCodi.vue";
import { mapActions, mapGetters } from "vuex";
export default {
  components: {
    MypageDetail,
    MypageClothes,
    MypageCodi,
  },
  computed: {
    ...mapGetters(["userInfo"]),
  },
  data() {
    return {
      no: this.$route.params.no,
    };
  },
  watch: {
    // userInfo: function () {
    //   console.log("와치");
    //   this.setUserInfo();
    // },
  },
  created() {
    this.no = this.$route.params.no;
    this.setUserInfo();
  },
  methods: {
    ...mapActions(["getInfo"]),
    setUserInfo() {
      let userId = this.$route.params.no;
      this.getInfo(userId).then((res) => {});
    },
  },
};
</script>
<style>
v-row {
  background-color: yellow;
}
</style>
