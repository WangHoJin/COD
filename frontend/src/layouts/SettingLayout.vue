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
    if (this.$route.name == "settingMain") {
      this.headerTitle = "설정";
      this.headerHasBack = true;
    } else if (this.$route.name == "settingLikedCodi") {
      this.headerTitle = "좋아요 누른 코디";
      this.headerHasBack = true;
    } else if (this.$route.name == "settingUpadateProfile") {
      this.headerTitle = "프로필 편집";
      this.headerHasBack = true;
    } else if (this.$route.name == "settingInfo") {
      this.headerTitle = "개인 정보 조회";
      this.headerHasBack = true;
    } else if (this.$route.name == "settingChangePassword") {
      this.headerTitle = "비밀번호 변경";
      this.headerHasBack = true;
    }
  },
};
</script>
<style scoped></style>
