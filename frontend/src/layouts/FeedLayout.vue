<template>
  <v-app>
    <Header
      :hasBack="headerHasBack"
      :title="headerTitle"
      :color="headerColor"
      :isDark="headerIsDark"
    />
    <v-main>
      <router-view :key="$route.fullPath" />
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import Header from "../components/common/BackTitleHeader.vue";
import Footer from "../components/common/Footer.vue";

export default {
  name: "FeedLayout",
  components: {
    Header,
    Footer,
  },
  data() {
    return {
      headerTitle: null,
      headerHasBack: null,
      headerIsDark: null,
      headerColor: "white",
    };
  },
  created() {
    let name = this.$route.name;
    console.log("this.$route.name", name);
    if (name == "feedMain") {
      this.headerHasBack = true;
      this.headerTitle = "기웃기옷";
    } else if (name == "feedSearch") {
      this.headerHasBack = true;
      this.headerTitle = "검색";
    } else if (name == "feedComment") {
      this.headerHasBack = true;
      this.headerTitle = "댓글";
    }
  },
};
</script>
