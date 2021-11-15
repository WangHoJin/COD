<template>
  <v-app>
    <CodiHeader :title="$route.name" />
    <v-main>
      <!-- <v-container> -->
      <router-view :key="$route.fullPath" />
      <!-- </v-container> -->
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import CodiHeader from "../components/common/CodiHeader.vue";
import Footer from "../components/common/Footer.vue";

export default {
  name: "CodiLayout",
  components: {
    CodiHeader,
    Footer,
  },
  created() {
    console.log("Created CodiLayout");
    console.log(this.$route);
  },
};
</script>
<style scoped></style>
