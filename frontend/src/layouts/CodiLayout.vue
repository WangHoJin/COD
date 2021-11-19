<template>
  <v-app>
    <Header
      :hasBack="headerHasBack"
      :title="headerTitle"
      :color="headerColor"
      :isDark="headerIsDark"
      :listFlag="listFlag"
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
// import CodiHeader from "../components/common/CodiHeader.vue";
import Footer from "../components/common/Footer.vue";
import Header from "../components/common/BackTitleHeader.vue";

export default {
  name: "CodiLayout",
  components: {
    Footer,
    Header,
  },
  data() {
    return {
      headerTitle: null,
      headerHasBack: null,
      headerIsDark: null,
      headerColor: "white",
      listFlag: false,
    };
  },
  created() {
    console.log("this.$route.name", name);
    if (name == "codiList") {
      this.headerHasBack = true;
      this.headerTitle = "코디";
      this.listFlag = false;
    } else if (name == "codiCreate") {
      this.headerHasBack = true;
      this.headerTitle = "코디 등록";
      this.listFlag = false;
    } else if (name == "codiDetail") {
      this.headerHasBack = true;
      this.headerTitle = this.$route.params.no + "번 코디 상세 정보";
      this.listFlag = true;
    }
  },
};
</script>
<style scoped></style>
