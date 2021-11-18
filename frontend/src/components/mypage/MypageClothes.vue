<template>
  <!-- 옷 목록 start -->
  <v-row>
    <v-col cols="9" sm="3" md="3" lg="3">
      <h4 class="pl-4" style="display: inline-block">옷</h4>
    </v-col>
    <v-col cols="3" sm="3" md="3" lg="3">
      <!-- <h4 @click="ClickAllClothes()">모두 보기</h4> -->
    </v-col>
    <v-col v-for="(c, i) in clothesList" :key="i" cols="4" sm="3" md="3" lg="3">
      <v-card class="clothesImg">
        <v-img class="" height="100px" :src="c.clothImgUrl"></v-img>
      </v-card>
    </v-col>
  </v-row>
  <!-- 옷 목록 end -->
</template>
<script>
import { mapGetters, mapActions } from "vuex";
export default {
  computed: {
    ...mapGetters(["clothesList"]),
  },
  created() {
    this.selectClothes();
  },
  methods: {
    ...mapActions(["getClothesList"]),
    selectClothes() {
      let userId = this.$store.state.auth.loginUser.userId;
      let payload = { userId: userId, page: 1, size: 3 };
      this.getClothesList(payload);
    },
    // ClickAllClothes() {
    //   this.$router.push({
    //     name: "clothesList",
    //   });
    // },
  },
};
</script>
<style scoped></style>
