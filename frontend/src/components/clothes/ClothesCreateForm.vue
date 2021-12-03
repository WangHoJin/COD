<template>
  <v-form>
    <v-container class="inputForm">
      <v-row>
        <v-col cols="12" md="4"> <h5>입력하시오</h5></v-col>
        <v-col class="input" cols="12" md="4">
          <v-select :items="category" label="카테고리" solo dens></v-select>
        </v-col>
        <v-col class="input" cols="12" md="4">
          <v-select :items="color" label="색상" solo dens></v-select>
        </v-col>
        <v-col class="input" cols="12" md="4">
          <v-select :items="season" label="계절" solo dens></v-select>
        </v-col>
        <v-col class="input" cols="12" md="4">
          <v-switch inset label="공개"></v-switch>
        </v-col>
        <v-col class="input" cols="12" md="4">
          <v-text-field label="가격"></v-text-field>
        </v-col>
        <v-col class="input" cols="12" md="4">
          <v-text-field label="브랜드"></v-text-field>
        </v-col>
        <v-col class="input" cols="12" md="4">
          <v-text-field label="상품명"></v-text-field>
        </v-col>
        <v-col class="input" cols="12" md="4">
          <v-text-field label="사이즈"></v-text-field>
        </v-col>
      </v-row>
    </v-container>

    <!-- 등록 버튼 start -->
    <v-btn icon id="addBtn" @click="addClothes()">
      <v-icon large color="#857DB1" left> mdi-checkbox-marked-circle </v-icon>
    </v-btn>
    <!-- 등록 버튼 end -->
  </v-form>
</template>
<script>
export default {
  data: function () {
    return {
      category: ['상의', '하의', '아우터', '잡화'],
      color: ['빨강', '주황', '노랑', '파랑', '네이비', '보라', '핑크', '검정', '하양'],
      season: ['봄', '여름', '가을', '겨울'],
    };
  },
  methods: {},
};
</script>
<style scoped>
.inputForm {
  padding-bottom: 100px;
}
.input {
  padding-top: 0px;
  padding-bottom: 0px;
}
#addBtn {
  position: absolute;
  top: -3.8%;
  right: 1%;
  z-index: 100;
}
</style>
