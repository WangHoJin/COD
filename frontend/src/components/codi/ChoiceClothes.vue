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
          <v-tabs>
            <v-tab>전체</v-tab>
            <v-tab>상의</v-tab>
            <v-tab>하의</v-tab>
            <v-tab>아우터</v-tab>
            <v-tab>신발</v-tab>
            <v-tab>악세사리</v-tab>
          </v-tabs>
        </v-row>
        <v-row>
          <v-col class="" cols="4" md="4"
            ><v-card class="codiImg">
              <v-img height="100px" src="@/assets/test/상의.jpg" @click="addClothes()"></v-img>
            </v-card>
          </v-col>
          <v-col class="" cols="4" md="4">
            <v-card class="codiImg">
              <v-img height="100px" src="@/assets/test/바지.png"></v-img>
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
    ...mapGetters[("clothes", "codi")],
  },
  methods: {
    ...mapActions["getClothes"],
    addClothes() {
      console.log("추가");
      let payload = {
        clothesId: this.idx++,
        path: require("@/assets/test/바지.png"),
      };
      this.$store.dispatch("getClothes", payload);
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
