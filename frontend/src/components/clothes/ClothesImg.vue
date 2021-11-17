<template lang="">
  <v-container class="clothesImgBox" fluid>
    <v-row>
      <v-col cols="12" sm="12" md="12" lg="12"
        ><v-card class="clothesImg">
          <v-img class="" height="400px" src="@/assets/test/상의.jpg" contain></v-img>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  data: function () {
    return {};
  },
  methods: {},
};
</script>
<style scoped>
.clothesImgBox {
  background-color: #d6d6d6;
  padding: 20px;
  /* width: 414px;
  height: 414px; */
}
.clothesImg {
  width: 100%;
  height: 100%;
}
</style>
