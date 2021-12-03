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
  name: "CodiLayout",
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
      if (this.$route.name == "codiList") {
        this.title = "코디 목록";
        this.listFlag = false;
      } else if (this.$route.name == "codiCreate") {
        this.title = "코디 등록";
        this.listFlag = false;
      } else if (this.$route.name == "codiDetail") {
        this.title = this.$route.params.no + "번 코디 상세 정보";
        this.listFlag = true;
      }
    },
  },
};
</script>
<style scoped></style>
