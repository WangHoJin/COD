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
import Footer from "../components/common/Footer.vue";
import Header from "../components/common/BackTitleHeader.vue";

export default {
  name: "MypageLayout",
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
    if (this.$route.name == "mypageMain") {
      this.headerTitle = this.$store.state.auth.userInfo.nickname;
      this.headerHasBack = true;
    } else if (this.$route.name == "mypageFollow") {
      this.headerTitle = this.$store.state.auth.userInfo.nickname + "의 친구들";
      this.headerHasBack = true;
    }
  },
};
</script>
<style scoped></style>
