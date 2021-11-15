<template>
  <v-app-bar app dense color="white" fade-img-on-scroll>
    <v-btn style="margin-left: -10px" small fab text @click="$router.go(-1)">
      <img src="@/assets/icon/back.png" style="margin-left: 20px; height: 20px; width: 20px" />
      <v-icon>mdi-chevron-lef</v-icon>
    </v-btn>
    <v-app-bar-title v-if="title" class="app-title ml-2">{{ title }}</v-app-bar-title>
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

<style scoped>
.app-title {
  font-family: BMHANNAPro !important;
  font-size: 1.1rem;
}
</style>
