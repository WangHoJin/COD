<template>
  <v-app>
    <CodiHeader :title="title" :listFlag="listFlag" />
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
  name: "MypageLayout",
  data() {
    return {
      title: "",
      listFlag: false,
    };
  },
  components: {
    CodiHeader,
    Footer,
  },
  created() {
    this.selectHeader();
  },
  watch: {
    title: function () {
      this.selectHeader();
    },
  },
  methods: {
    selectHeader() {
      if (this.$route.name == "settingMain") {
        this.title = "설정";
      } else if (this.$route.name == "settingLikedCodi") {
        this.title = "좋아요 누른 코디";
      } else if (this.$route.name == "settingUpadateProfile") {
        this.title = "프로필 편집";
      } else if (this.$route.name == "settingInfo") {
        this.title = "개인 정보 조회";
      } else if (this.$route.name == "settingChangePassword") {
        this.title = "비밀번호 변경";
      }
    },
  },
};
</script>
<style scoped></style>
