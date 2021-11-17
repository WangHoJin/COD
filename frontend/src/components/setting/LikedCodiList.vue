<template>
  <v-container>
    <v-row>
      <v-col class="codiList" v-for="i in 30" :key="i" cols="4" sm="3" md="3" lg="3">
        <v-card outlined>
          <v-img class="codiImg" src="@/assets/test/codisample.png"></v-img>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {};
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
