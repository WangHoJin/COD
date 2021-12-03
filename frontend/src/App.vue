<template>
  <v-app>
    <router-view :key="$route.fullPath"></router-view>
  </v-app>
</template>

<script>
export default {
  name: 'App',

  components: {},

  data: () => ({
    //
  }),
};
</script>
<style>
@import 'assets/css/font.css';
</style>
