<template>
  <v-app>
    <CodiHeader :title="title" />
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
  name: "ClothesLayout",
  data() {
    return {
      title: "",
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
      if (this.$route.name == "clothesList") {
        this.title = "옷장";
      } else if (this.$route.name == "clothesCreate") {
        this.title = "옷 등록";
      } else if (this.$route.name == "clothesDetail") {
        this.title = this.$route.params.no + "번 옷 상세 정보";
      }
    },
  },
};
</script>
<style scoped></style>
