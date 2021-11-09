<template lang="">
  <v-item-group>
    <v-container class="">
      <v-row>
        <v-col v-for="codi in codiList" :key="codi.codiId" cols="4" sm="3" md="2" lg="1">
          <v-card class="codiImg">
            <v-img
              height="100px"
              lazy-src="https://picsum.photos/id/11/10/6"
              src="https://picsum.photos/id/11/500/300"
            ></v-img>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-item-group>
</template>
<script>
import { mapGetters, mapActions } from "vuex";
export default {
  data() {
    return {
      codiList: [],
    };
  },
  computed: {
    ...mapGetters(["codies"]),
  },
  watch: {
    coides: function () {
      this.selectCodies();
    },
  },
  created() {
    this.setCodies();
  },
  methods: {
    ...mapActions(["getCodies"]),
    setCodies() {
      let userId = this.$route.query.userId;
      // console.log(userId);
      let payload = { userId: 2, page: 1, size: 10 };
      this.getCodies(payload);
      this.codiList = this.codies;
    },
    selectCodies() {
      this.codiList = this.codies;
    },
  },
};
</script>
<style scoped>
.codiImg {
  margin: auto;
  border: 2px solid #a9a9a9;
  width: 100%;
  height: 100%;
}
</style>
