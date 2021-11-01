<template>
  <v-app>
    <Header />
    <v-main>
      <v-container>
        <router-view :key="$route.fullPath" />
      </v-container>
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import Header from '../components/common/Header.vue';
import Footer from '../components/common/Footer.vue';

export default {
  name: 'MainLayout',
  components: {
    Header,
    Footer,
  },
};
</script>
