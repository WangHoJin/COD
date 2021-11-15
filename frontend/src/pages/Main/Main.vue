<template lang="">
  <div>
    <Header title="Closet of Days" />
    <div class="content mb-8">
      <Weather />
      <CodiDiary />
      <ColorChart />
    </div>
  </div>
</template>
<script>
import Header from '@/components/common/Header.vue';
import Weather from '@/components/main/Weather';
import CodiDiary from '@/components/main/CodiDiary';
import ColorChart from '@/components/main/ColorChart/ColorChart';

export default {
  components: {
    Header,
    Weather,
    CodiDiary,
    ColorChart,
  },
};
</script>
<style scoped>
.content {
  padding: 25px;
}
</style>
