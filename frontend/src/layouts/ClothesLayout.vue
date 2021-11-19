<template>
  <v-app>
    <Header
      :hasBack="headerHasBack"
      :title="headerTitle"
      :color="headerColor"
      :isDark="headerIsDark"
    />
    <v-main>
      <!-- <v-container> -->
      <router-view :key="$route.fullPath" />
      <!-- </v-container> -->
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import Header from "../components/common/BackTitleHeader.vue";
import Footer from "../components/common/Footer.vue";

export default {
  name: "ClothesLayout",
  data() {
    return {
      headerTitle: null,
      headerHasBack: null,
      headerIsDark: null,
      headerColor: "white",
    };
  },
  components: {
    Header,
    Footer,
  },
  created() {
    if (this.$route.name == "clothesList") {
      this.headerTitle = "옷장";
      this.headerHasBack = true;
    } else if (this.$route.name == "clothesCreate") {
      this.headerHasBack = true;
      this.headerTitle = "옷 등록";
    } else if (this.$route.name == "clothesDetail") {
      this.headerHasBack = true;
      this.headerTitle = "옷 상세 정보";
    }
  },
};
</script>
<style scoped></style>
