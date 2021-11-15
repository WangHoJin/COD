<template>
  <!-- <v-app> -->
  <!-- <v-main> -->
  <div>
    <!-- <v-container> -->
    <router-view :key="$route.fullPath" />
    <!-- </v-container> -->

    <Footer />
  </div>
  <!-- </v-main> -->
  <!-- </v-app> -->
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
