<template>
  <!-- 코디 목록 start -->
  <v-row>
    <v-col cols="9" sm="3" md="3" lg="3">
      <h4 class="pl-4" style="display: inline-block">코디</h4>
    </v-col>
    <v-col class="codiList" v-for="(c, i) in codies" :key="i" cols="4" sm="3" md="3" lg="3">
      <v-card outlined>
        <v-img class="codiImg" src="@/assets/test/codisample.png"></v-img>
      </v-card>
    </v-col>
  </v-row>
  <!-- 코디 목록 end -->
</template>
<script>
import { mapGetters, mapActions } from "vuex";
export default {
  computed: {
    ...mapGetters(["codies"]),
  },
  created() {
    this.selectCodies();
  },
  methods: {
    ...mapActions(["getCodies"]),
    selectCodies() {
      let userId = this.$route.params.no;
      let payload = { userId: userId, page: 1, size: 9 };
      this.getCodies(payload);
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
</style>
