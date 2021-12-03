<template>
  <v-app-bar dense app :color="color" :dark="isDark">
    <v-btn v-if="hasBack" style="margin-left: -5px" small fab text @click="$router.go(-1)">
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
    hasBack: Boolean,
    color: String,
    isDark: Boolean,
  },
};
</script>

<style scoped>
.app-title {
  font-family: BMHANNAPro !important;
  font-size: 1.1rem;
}
</style>
