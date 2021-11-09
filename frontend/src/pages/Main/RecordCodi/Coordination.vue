<template lang="">
  <div>
    <Header title="코디 기록하기" />
    <div class="content">
      <div class="box"></div>
      <div style="position: absolute; bottom: 0px; width: 95%">
        <SBtn :click="mvRegist" msg="코디 완료 하기" />
      </div>
    </div>
  </div>
</template>
<script>
import Header from '@/components/main/RecordCodi/Header.vue';
import SBtn from '@/components/common/SquareButton.vue';

export default {
  methods: {
    mvRegist() {},
  },
  components: {
    Header,
    SBtn,
  },
};
</script>
<style scoped>
.content {
  position: relative;
  padding: 150px 10px;
  background-color: #e5e5e5;
}
.box {
  height: 80vw;
  background-color: white;
}
</style>
