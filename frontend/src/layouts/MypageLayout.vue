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
      if (this.$route.name == "mypageMain") {
        this.title = this.$store.state.auth.loginUser.nickname;
        this.listFlag = false;
      } else if (this.$route.name == "mypageFollow") {
        this.title = this.$store.state.auth.loginUser.nickname + "의 친구들";
        this.listFlag = false;
      }
    },
  },
};
</script>
<style scoped></style>
