<template>
  <v-app>
    <!-- <v-main> -->
    <v-container>
      <router-view :key="$route.fullPath" />
    </v-container>
    <!-- </v-main> -->
    <Footer />
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
