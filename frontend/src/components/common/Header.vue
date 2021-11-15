<template>
  <v-app-bar app dense color="#857DB1" dark fade-img-on-scroll>
    <v-app-bar-title>
      <h4>{{ title }}</h4>
    </v-app-bar-title>
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
