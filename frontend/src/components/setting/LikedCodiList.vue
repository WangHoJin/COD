<template>
  <v-container>
    <v-row>
      <v-col
        class="codiList"
        v-for="(c, i) in codiLikedList"
        :key="i"
        cols="4"
        sm="3"
        md="3"
        lg="3"
      >
        <v-card outlined>
          <v-img @click="codiClick(c.codiId)" class="codiImg" :src="c.codiThumbnail"> </v-img>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import { mapGetters, mapActions } from "vuex";
export default {
  computed: {
    ...mapGetters(["codiLikedList"]),
  },
  created() {
    this.seletCodiLiked();
  },
  methods: {
    ...mapActions(["getCodiLiked"]),
    seletCodiLiked() {
      let userId = this.$store.state.auth.loginUser.userId;
      let token = this.$store.state.auth.accessToken;
      let payload = { userId: userId, page: 1, size: 100, accessToken: token };
      this.getCodiLiked(payload);
    },
    codiClick(codiId) {
      console.log("코디 클릭" + codiId);
      this.$router.push({
        path: `/codi/detail/` + codiId,
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
  height: 116px;
  /* height: 100%; */
}
</style>
