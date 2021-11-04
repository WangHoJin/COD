<template lang="">
  <div>
    <Header title="Closet of Days" />
    <div class="content">
      <Weather />
      <CodiDiary />
      <ChallengeForest />
      <ColorChart />
    </div>
  </div>
</template>
<script>
import Header from '@/components/common/Header.vue';
import Weather from '@/components/main/Weather';
import CodiDiary from '@/components/main/CodiDiary';
import ChallengeForest from '@/components/main/ChallengeForest';
import ColorChart from '@/components/main/ColorChart/ColorChart';

export default {
  components: {
    Header,
    Weather,
    CodiDiary,
    ColorChart,
    ChallengeForest,
  },
};
</script>
<style scoped>
.content {
  padding: 10px;
}
</style>
