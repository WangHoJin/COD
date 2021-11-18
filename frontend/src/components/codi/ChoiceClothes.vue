<template>
  <v-dialog transition="dialog-bottom-transition" max-width="600">
    <template v-slot:activator="{ on, attrs }">
      <v-btn class="clothesBtn" v-bind="attrs" v-on="on"
        ><v-icon large color="#979797">mdi-minus</v-icon></v-btn
      >
    </template>
    <template v-slot:default="dialog">
      <v-container class="openClothes">
        <v-row>
          <v-tabs show-arrows>
            <v-tab>전체</v-tab>
            <v-tab>상의</v-tab>
            <v-tab>하의</v-tab>
            <v-tab>아우터</v-tab>
            <v-tab>신발</v-tab>
            <v-tab>악세사리</v-tab>
          </v-tabs>
        </v-row>
        <v-row>
          <v-col v-for="c in $store.state.clothes.clothesList" :key="c.id" cols="4" md="4"
            ><v-card class="codiImg">
              <v-img height="100px" :src="c.clothImgUrl" @click="addClothes()"></v-img>
            </v-card>
          </v-col>
        </v-row>
        <v-row>
          <v-card-actions class="justify-end">
            <v-btn text @click="dialog.value = false">Close</v-btn>
          </v-card-actions>
        </v-row>
      </v-container>
    </template>
  </v-dialog>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data: function () {
    return { idx: 1 };
  },
  computed: {
    ...mapGetters[["usedClothes", "clothesList"]],
  },
  created() {
    this.setClothesList();
  },
  methods: {
    ...mapActions(["getUsedClothes", "getClothesList"]),
    setClothesList() {
      let userId = this.$store.state.auth.loginUser.userId;
      let payload = { userId: userId, page: 1, size: 100 };
      this.getClothesList(payload);
    },
    addClothes() {
      console.log("추가");
      let payload = {
        clothesId: this.idx++,
        path: "https://cod-bucket.s3.ap-northeast-2.amazonaws.com/static/9fb4d3c0-bb13-4044-8689-1806c90c661cyaleHood.png",
      };
      this.$store.dispatch("getUsedClothes", payload);
      // this.getClothes(payload);
    },
  },
};
</script>
<style scoped>
.clothesBtn {
  position: fixed;
  left: 0.3%;
  bottom: 7%;
  width: 99%;
  border-radius: 10px;
  border: 2px solid #979797;
  border-bottom-color: #ffff;
}
.openClothes {
  background-color: #d6d6d6;
}
.codiImg {
  margin: auto;
  border: 2px solid #a9a9a9;
  width: 100%;
  height: 100%;
}
</style>
