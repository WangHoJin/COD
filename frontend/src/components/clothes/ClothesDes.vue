<template lang="">
  <v-container class="clothesDes">
    <!-- 태그 정보 start -->
    <v-row class="">
      <!-- 태그들 -->
      <v-col cols="12">
        <v-icon class="mx-2" color="#857DB1" small> mdi-tag </v-icon>
        <div style="display: inline-block" v-for="(tag, index) in tags" :key="index">
          <v-chip class="mx-1 my-3" small color="#C4BDDD" style="display: inline-block">
            <h4 class="tagText">{{ tag }}</h4>
          </v-chip>
        </div>
      </v-col>
    </v-row>
    <!-- 태그 정보 end -->
    <!-- 옷 설명 start-->
    <v-row>
      <v-col cols="3" sm="12" md="12" lg="12">
        <h5 class="kindText">카테고리</h5>
      </v-col>
      <v-col cols="9" sm="12" md="12" lg="12">
        <h5 class="contentText">{{ clothes.clothType }}</h5>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="3" sm="12" md="12" lg="12">
        <h5 class="kindText">색상</h5>
      </v-col>
      <v-col cols="9" sm="12" md="12" lg="12">
        <h5 class="contentText">{{ clothes.clothColor }}</h5>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="3" sm="12" md="12" lg="12">
        <h5 class="kindText">계절</h5>
      </v-col>
      <v-col cols="9" sm="12" md="12" lg="12">
        <h5 class="contentText">{{ clothes.clothSeason }}</h5>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="3" sm="12" md="12" lg="12">
        <h5 class="kindText">보유</h5>
      </v-col>
      <v-col cols="9" sm="12" md="12" lg="12">
        <h5 class="contentText">{{ clothes.clothIsOwned }}</h5>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="3" sm="12" md="12" lg="12">
        <h5 class="kindText">가격</h5>
      </v-col>
      <v-col cols="9" sm="12" md="12" lg="12">
        <h5 class="contentText">{{ clothes.clothPrice }}</h5>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="3" sm="12" md="12" lg="12">
        <h5 class="kindText">브랜드</h5>
      </v-col>
      <v-col cols="9" sm="12" md="12" lg="12">
        <h5 class="contentText">{{ clothes.clothBrand }}</h5>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="3" sm="12" md="12" lg="12">
        <h5 class="kindText">상품명</h5>
      </v-col>
      <v-col cols="9" sm="12" md="12" lg="12">
        <h5 class="contentText">{{ clothes.clothName }}</h5>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="3" sm="12" md="12" lg="12">
        <h5 class="kindText">사이즈</h5>
      </v-col>
      <v-col cols="9" sm="12" md="12" lg="12">
        <h5 class="contentText">{{ clothes.clothMeasure }}</h5>
      </v-col>
    </v-row>
    <!-- 옷 설명 end-->
  </v-container>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

export default {
  data() {
    return {
      tags: ["#출근복", "#캐주얼", "#데일리"],
    };
  },
  computed: {
    ...mapGetters(["clothes"]),
  },
  created() {
    this.selectClothes();
  },
  methods: {
    ...mapActions(["getClothes"]),
    selectClothes() {
      let clothesId = this.$route.params.no;
      console.log(this.$route.params.no);
      this.getClothes(clothesId);
    },
  },
};
</script>
<style scoped>
.clothesDes {
  /* background-color: #d6d6d6; */
  padding: 20px 20px 100px 20px;
}
.contentText {
  color: #6f6f6f;
  display: inline;
}
.kindText {
  color: #414141;
  display: inline;
  /* font-family: BMHANNAAir !important; */
}
.tagText {
  color: #ffffff;
}
</style>
