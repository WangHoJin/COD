<template>
  <div>
    <Header />
    <router-view />
    <Footer />
  </div>
</template>

<script>
import Header from '../components/common/Header.vue';
import Footer from '../components/common/Footer.vue';

export default {
  name: 'MainLayout',
  components: {
    Header,
    Footer,
  },
};
</script>
