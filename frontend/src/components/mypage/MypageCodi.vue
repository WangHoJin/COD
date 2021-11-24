<template>
  <div>
    <!-- 코디 목록 start -->
    <v-row v-if="codies != null" style="">
      <v-col cols="9" sm="3" md="3" lg="3">
        <h4 class="pl-4" style="display: inline-block">코디</h4>
      </v-col>
    </v-row>
    <v-row>
      <v-col class="codiList" v-for="(c, i) in codies" :key="i" cols="4" sm="3" md="3" lg="3">
        <v-card outlined>
          <v-img
            contain
            height="120px"
            class="codiImg"
            :src="c.codiThumbnail"
            @click="codiClick(c.codiId)"
          ></v-img>
        </v-card>
      </v-col>
    </v-row>
    <v-row v-if="codies == 0" style="height: 200px">
      <v-col>
        <div class="text-center">
          <v-icon x-large class="mt-11 mb-3">mdi-package-variant</v-icon>
          <h4>아직 코디에 코디가 없습니다!</h4>
        </div>
      </v-col>
    </v-row>
    <!-- 코디 목록 end -->

    <!-- 등록 버튼 start -->
    <v-btn icon class="settingBtn" @click="moveSetting()">
      <v-icon large color="#857DB1" left> mdi-cog </v-icon>
    </v-btn>
    <!-- 등록 버튼 end -->
  </div>
</template>
<script>
import { mapGetters, mapActions } from "vuex";
export default {
  props: {
    userInfo: Object,
  },
  computed: {
    ...mapGetters(["codies"]),
  },
  watch: {
    userInfo: function () {
      this.selectCodies();
    },
  },
  created() {
    this.selectCodies();
  },
  methods: {
    ...mapActions(["getCodies", "getInfo"]),
    selectCodies() {
      console.log("this.userInfo.userId", this.userInfo.userId);
      let userId = this.userInfo.userId;
      let payload = { userId: userId, page: 1, size: 9 };
      this.getCodies(payload);
    },
    codiClick(codiId) {
      this.$router.push({
        path: `../codi/detail/` + codiId,
      });
    },
    moveSetting() {
      this.$router.push({
        name: "settingMain",
      });
    },
  },
};
</script>
<style scoped>
.codiList {
  padding: 0px;
}
.codiImg {
  width: 100%;
  height: 100%;
}
.settingBtn {
  position: fixed;
  top: 1%;
  right: 1%;
  z-index: 100;
}
</style>
