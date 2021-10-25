<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png" />
    <MainLayout />
  </div>
</template>

<script>
import MainLayout from './layouts/MainLayout.vue';

export default {
  name: 'App',
  components: {
    MainLayout,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
