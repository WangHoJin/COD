<template>
  <div>
    <!-- 옷 목록 start -->
    <v-row v-if="clothesList != null" style="">
      <v-col cols="8" sm="3" md="3" lg="3">
        <h4 class="pl-4" style="display: inline-block">옷</h4>
      </v-col>
      <v-col cols="4" sm="3" md="3" lg="3">
        <h4 v-if="flag" @click="selectAllClothes()">모두 펼치기</h4>
        <h4 v-if="!flag" @click="select3Clothes()">모두 닫기</h4>
      </v-col>
    </v-row>

    <v-row v-if="clothesList == 0" style="height: 200px">
      <v-col>
        <div class="text-center">
          <v-icon x-large class="mt-11 mb-3">mdi-hanger</v-icon>
          <h4>아직 옷장에 옷이 없습니다!</h4>
        </div>
      </v-col>
    </v-row>
    <v-row>
      <v-col v-for="(c, i) in clothesList" :key="i" cols="4" sm="3" md="3" lg="3">
        <v-card class="clothesImg">
          <v-img class="" height="100px" :src="c.clothImgUrl"></v-img>
        </v-card>
      </v-col>
    </v-row>
    <!-- 옷 목록 end -->
  </div>
</template>
<script>
import { mapGetters, mapActions } from "vuex";
export default {
  data() {
    return {
      flag: false,
    };
  },
  computed: {
    ...mapGetters(["clothesList"]),
  },
  created() {
    this.select3Clothes();
  },

  methods: {
    ...mapActions(["getClothesList"]),
    select3Clothes() {
      let userId = this.$store.state.auth.loginUser.userId;
      let payload = { userId: userId, page: 1, size: 3 };
      this.flag = !this.flag;
      this.getClothesList(payload);
    },
    selectAllClothes() {
      let userId = this.$store.state.auth.loginUser.userId;
      let payload = { userId: userId, page: 1, size: 100 };
      this.flag = !this.flag;
      this.getClothesList(payload);
    },
  },
};
</script>
<style scoped></style>
