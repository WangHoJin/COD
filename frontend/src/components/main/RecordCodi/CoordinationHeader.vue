<template>
  <v-app-bar app dense color="white" fade-img-on-scroll>
    <v-btn style="margin-left: -10px" small fab text @click="$router.go(-1)">
      <img src="@/assets/icon/back.png" style="margin-left: 20px; height: 20px; width: 20px" />
      <v-icon>mdi-chevron-lef</v-icon>
    </v-btn>

    <v-spacer></v-spacer>
    <v-btn text>
      <v-icon small> mdi-plus</v-icon>
      내 코디에도 추가
    </v-btn>
  </v-app-bar>
</template>

<script>
export default {
  name: 'Header',
  props: {
    title: String,
  },
};
</script>

<style scoped></style>
