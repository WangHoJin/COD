<template lang="">
  <div>
    <Header />
    <div class="content">
      <span class="ma-3 date">2021-10-13 (수)</span>
      <div class="box ma-3"></div>
      <v-textarea class="mx-3 my-5" solo label="메모를 입력해보세요 (최대 200자)"> </v-textarea>
      <SBtn :click="mvMain" msg="코디 완료 하기" />
    </div>
  </div>
</template>
<script>
import Header from '@/components/common/BackTitleHeader.vue';
import SBtn from '@/components/common/SquareButton.vue';

export default {
  methods: {
    mvMain() {
      this.$router.push({ name: 'main' });
    },
  },
  components: {
    Header,
    SBtn,
  },
};
</script>
<style scoped>
.content {
  margin-top: 80px;
  padding: 0px 10px;
}
.box {
  height: 50vw !important;
  width: 50vw;
  border-radius: 10%;
  border-color: #857db1;
  border-width: 2px;
  border-style: dashed;
  height: 80vw;
}
.date {
  font-weight: bold;
}
</style>
