!
<template lang="">
  <div>
    <Header title="코디 기록하기" />

    <v-tabs
      :centered="true"
      color="#857DB1"
      style="margin-top: 80px; width: 100%"
      align-with-title
      fixed-tabs
      v-model="super_tab"
    >
      <v-tab class="ma-0"> 코디에서 고르기 </v-tab>
      <v-tab class="ma-0"> 옷장에서 조합하기 </v-tab>
    </v-tabs>
    <!-- 코디에서 고르기 -->
    <div v-if="super_tab == 0">
      <!-- 중간 서브바 -->
      <v-row class="px-2">
        <!-- 태그들 -->
        <v-col>
          <v-icon class="mx-2" color="black" small> mdi-tag </v-icon>
          <div style="display: inline-block" v-for="(tag, index) in tags" :key="index">
            <v-chip class="mx-1 my-3" small color="#857db15b" style="display: inline-block">{{
              tag
            }}</v-chip>
          </div>
        </v-col>
        <!-- 계절 셀렉트 -->
        <v-col cols="3">
          <v-select
            class="mt-2 mr-2"
            dense
            single-line
            label="계절"
            hide-selected
            loading="white"
            color="black"
            hide-details
            style="font-size: 0.75rem"
            :items="weather"
          ></v-select>
        </v-col>
      </v-row>
      <!-- 코디 그리드 -->
      <div class="codi-content py-7">
        <div class="codi-holder text-center">
          <div class="box"></div>
        </div>
        <div class="codi-holder text-center">
          <div class="box"></div>
        </div>
      </div>
    </div>
    <!-- 옷장에서 조합하기 -->
    <div v-else>
      <!-- 중간 서브바 -->
      <v-row class="py-1 px-2">
        <!-- 태그들 -->
        <v-col>
          <v-tabs
            id="clthes-category-tabs"
            class="my-1 ml-2"
            color="#857DB1"
            v-model="sub_tab"
            height="30px"
          >
            <v-tab class="px-1" style="font-size: 0.75rem; min-width: 0px"> 전체 </v-tab>
            <v-tab class="px-1" style="font-size: 0.75rem; min-width: 0px"> 상의 </v-tab>
            <v-tab class="px-1" style="font-size: 0.75rem; min-width: 0px"> 하의 </v-tab>
            <v-tab class="px-1" style="font-size: 0.75rem; min-width: 0px"> 신발 </v-tab>
            <v-tab class="px-1" style="font-size: 0.75rem; min-width: 0px"> 모자 </v-tab>
            <v-tab class="px-1" style="font-size: 0.75rem; min-width: 0px"> 악세서리 </v-tab>
          </v-tabs>
        </v-col>
        <!-- 계절 셀렉트 -->
        <v-col cols="3">
          <v-select
            class="mr-2"
            dense
            single-line
            label="계절"
            style="font-size: 0.75rem"
            hide-selected
            loading="white"
            color="black"
            hide-details
            :items="weather"
          ></v-select>
        </v-col>
      </v-row>
      <!-- 옷 그리드 -->
      <div class="clothes-content py-7">
        <div class="clothes-holder text-center">
          <div class="box"></div>
        </div>
        <div class="clothes-holder text-center">
          <div class="box"></div>
        </div>
        <div class="clothes-holder text-center">
          <div class="box"></div>
        </div>
        <div class="clothes-holder text-center">
          <div class="box"></div>
        </div>

        <div class="text-center mt-16">
          <SBtn
            :click="mvCordination"
            :msg="'총' + this.selectedClothes.length + '개 아이템 추가'"
          />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Header from '@/components/common/Header.vue';
import SBtn from '@/components/common/SquareButton.vue';

export default {
  data() {
    return {
      super_tab: '0',
      sub_tab: '0',
      tags: ['데일리', '출근', '마실'],
      weather: ['봄', '여름', '가을', '겨울'],
      selectedClothes: [],
    };
  },
  methods: {
    mvCordination() {
      this.$router.push('coordination');
    },
  },
  components: { Header, SBtn },
};
</script>
<style scoped>
.content {
  padding: 20px;
}
.codi-content {
  background-color: #e5e5e5;
  padding: 0 2%;
  min-height: 108vw;
}
.codi-holder {
  display: inline-block;
  width: 50%;
  height: 48vw;
}
.clothes-content {
  background-color: #e5e5e5;
  padding: 0px 3%;
  padding-left: 4%;
  min-height: 108vw;
}
.clothes-holder {
  display: inline-block;
  width: 33%;
  height: 30vw;
}
.box {
  width: 90%;
  height: 90%;
  margin-top: 5%;
  margin-bottom: 5%;
  background-color: white;
  display: inline-block;
}
.v-item-group v-slide-group__prev--disabled {
  display: none !important;
}
</style>
