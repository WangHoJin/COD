<template>
  <v-app>
    <FeedHeader :title="title" />
    <v-main>
      <router-view :key="$route.fullPath" />
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import FeedHeader from "../components/common/FeedHeader.vue";
import Footer from "../components/common/Footer.vue";

export default {
  name: "FeedLayout",
  components: {
    FeedHeader,
    Footer,
  },
  data() {
    return {
      title: "",
    };
  },
  created() {
    console.log("Created FeedLayout");
    this.selectHeader();
  },
  watch: {
    title: function () {
      this.selectHeader();
    },
  },
  methods: {
    selectHeader() {
      if (this.$route.name == "feedMain") {
        this.title = "기웃기옷";
      } else if (this.$route.name == "feedSearch") {
        this.title = "검색";
      } else if (this.$route.name == "feedComment") {
        this.title = "댓글";
      }
    },
  },
};
</script>
