<template>
  <v-app>
    <Header />
    <v-main>
      <router-view :key="$route.fullPath" />
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import Header from "../components/common/Header.vue";
import Footer from "../components/common/Footer.vue";

export default {
  name: "CodiLayout",
  components: {
    Header,
    Footer,
  },
  created() {
    console.log("Created CodiLayout");
  },
};
</script>
