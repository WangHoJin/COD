<template>
  <div>
    <Header
      :hasBack="headerHasBack"
      :title="headerTitle"
      :color="headerColor"
      :isDark="headerIsDark"
    />
    <v-main>
      <v-container fluid>
        <router-view :key="$route.fullPath" />
      </v-container>
    </v-main>
    <Footer />
  </div>
</template>

<script>
import Footer from '../components/common/Footer.vue';
import Header from '../components/common/BackTitleHeader.vue';

export default {
  name: 'MainLayout',
  components: {
    Footer,
    Header,
  },
  data() {
    return {
      headerTitle: null,
      headerHasBack: null,
      headerIsDark: null,
      headerColor: 'white',
    };
  },
  created() {
    let name = this.$route.name;
    console.log('this.$route.name', name);
    if (name == 'main') {
      this.headerTitle = 'Closet of Days';
      this.headerIsDark = true;
      this.headerColor = 'rgb(133, 125, 177)';
    } else if (name.includes('record-codi')) {
      this.headerHasBack = true;
      this.headerTitle = '코디 기록하기';
    }
  },
};
</script>
<style scoped>
.full {
  /* padding: 0px !important; */
}
</style>
