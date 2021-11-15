<template>
  <v-app>
    <v-main>
      <!-- <v-container fluid> -->
      <router-view :key="$route.fullPath" />
      <!-- </v-container> -->
      <Footer />
    </v-main>
  </v-app>
</template>

<script>
import Footer from '../components/common/Footer.vue';

export default {
  name: 'MainLayout',
  components: {
    Footer,
  },
};
</script>
