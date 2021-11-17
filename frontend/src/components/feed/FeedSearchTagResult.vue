<template>
  <v-container>
    <v-flex style="padding: 12px 0 8px 0">
      <v-row>
        <v-col class="codiList" v-for="(c, i) in codies" :key="i" cols="4" sm="3" md="3" lg="3">
          <v-card outlined>
            <v-img
              class="codiImg"
              src="@/assets/img/feedsample.png"
              @click="codiClick(c.codiId)"
            ></v-img>
          </v-card>
        </v-col>
      </v-row>
    </v-flex>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["codies"]),
  },
  watch: {
    codies: function () {},
  },
  created() {},
  methods: {
    ...mapActions(["getCodies"]),
    search() {
      let payload = { nickname: this.input, tag: this.input, page: 1, size: 10 };
      this.getCodies(payload);
      // this.$router.push({
      //   name: "search",
      // });
      console.log(this.input);
      console.log(this.userList);
    },
    codiClick(codiId) {
      // console.log("코디 클릭" + codiId);
      this.$router.push({
        path: `../codi/detail/` + codiId,
      });
    },
  },
};
</script>

<style scoped>
.profileImg {
  border-style: solid;
  border-color: #857db1;
  border-radius: 50px;
  max-height: 45px;
  max-width: 45px;
}
.codiList {
  padding: 0px;
}
.codiImg {
  width: 100%;
  height: 100%;
}
</style>
