<template lang="">
  <v-item-group>
    <v-container class="">
      <v-row>
        <v-col v-for="n in 12" :key="n" cols="4" sm="3" md="2" lg="1">
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
export default {};
</script>
<style scoped>
.codiImg {
  margin: auto;
  border: 2px solid #a9a9a9;
  width: 100%;
  height: 100%;
}
</style>
