<template>
  <div>
    <!-- 코디 목록 start -->
    <v-row style="">
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
  computed: {
    ...mapGetters(["codies"]),
  },
  created() {
    console.log("생성");
    this.selectCodies();
  },
  methods: {
    ...mapActions(["getCodies"]),
    selectCodies() {
      console.log();
      let userId = this.$route.params.no;
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
  position: absolute;
  top: -5.6%;
  right: 1%;
  z-index: 1;
}
</style>
