!
<template lang="">
  <div>
    <Header title="코디 기록하기" />

    <v-tabs
      :centered="true"
      color="#857DB1"
      style="margin-top: 80px"
      align-with-title
      v-model="active_tab"
    >
      <v-tab class="ma-0"> 코디에서 고르기 </v-tab>
      <v-tab class="ma-0"> 옷장에서 조합하기 </v-tab>
    </v-tabs>
  </div>
</template>
<script>
import Header from '@/components/common/Header.vue';
export default {
  data() {
    return {
      active_tab: '0',
    };
  },
  components: { Header },
};
</script>
<style lang=""></style>
