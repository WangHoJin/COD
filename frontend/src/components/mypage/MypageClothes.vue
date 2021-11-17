<template>
  <!-- 옷 목록 start -->
  <v-row>
    <v-col cols="9" sm="3" md="3" lg="3">
      <h4 class="pl-4" style="display: inline-block">옷</h4>
    </v-col>
    <v-col cols="3" sm="3" md="3" lg="3">
      <h4>모두 보기</h4>
    </v-col>
    <v-col v-for="i in 3" :key="i" cols="4" sm="3" md="3" lg="3">
      <v-card class="clothesImg">
        <v-img class="" height="100px" src="@/assets/test/바지.png"></v-img>
      </v-card>
    </v-col>
  </v-row>
  <!-- 옷 목록 end -->
</template>
<script>
export default {};
</script>
<style scoped></style>
