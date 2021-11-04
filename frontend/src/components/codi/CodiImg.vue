<template lang="">
  <v-container class="codiImgBox" fluid>
    <v-row>
      <v-col cols="12" sm="12" md="12" lg="12"
        ><v-card class="codiImg"
          ><v-img
            class=""
            lazy-src="https://picsum.photos/id/11/10/6"
            src="https://picsum.photos/id/11/500/300"
          ></v-img>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {};
</script>
<style scoped>
.codiImgBox {
  background-color: #d6d6d6;
  padding: 20px;
  /* width: 414px;
  height: 414px; */
}
.codiImg {
  width: 100%;
  height: 100%;
}
</style>
