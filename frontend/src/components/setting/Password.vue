<template>
  <div>
    <!-- 비밀번호 확인 start -->
    <v-container v-if="!flag" class="passwordDes">
      <v-row>
        <v-col cols="3" sm="12" md="12" lg="12">
          <h5 class="kindText">비밀번호</h5>
        </v-col>
        <v-text-field type="password"></v-text-field>
      </v-row>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <!-- 체크 버튼 start -->
      <v-row>
        <v-btn elevation="2" color="#857DB1" block
          ><h4 class="buttonText" @click="clickCheck()">비밀 번호 확인</h4></v-btn
        >
      </v-row>
      <!-- 체크 버튼 end -->
    </v-container>
    <!-- 비밀번호 확인 end -->

    <!-- 비밀번호 확인 start -->
    <v-container v-if="flag" class="passwordDes">
      <v-row>
        <v-col cols="4" sm="12" md="12" lg="12">
          <h5 class="kindText">새 비밀번호</h5>
        </v-col>
        <v-text-field type="password"></v-text-field>
      </v-row>
      <v-row>
        <v-col cols="4" sm="12" md="12" lg="12">
          <h5 class="kindText">새 비밀번호 확인</h5>
        </v-col>
        <v-text-field type="password"></v-text-field>
      </v-row>
      &nbsp;&nbsp;&nbsp;&nbsp;
      <!-- 체크 버튼 start -->
      <v-row>
        <v-btn elevation="2" color="#857DB1" block
          ><h4 class="buttonText" @click="clickUpdate()">비밀 번호 변경</h4></v-btn
        >
      </v-row>
      <!-- 체크 버튼 end -->
    </v-container>
    <!-- 비밀번호 확인 end -->
  </div>
</template>
<script>
export default {
  data() {
    return {
      flag: false,
    };
  },
  methods: {
    clickCheck() {
      this.flag = !this.flag;
    },
    clickUpdate() {
      alert("비밀번호 변경");
    },
  },
};
</script>
<style scoped>
.passwordDes {
  /* background-color: #d6d6d6; */
  padding: 40px 30px 100px 30px;
}
.kindText {
  color: #414141;
  padding-top: 12px;
  /* font-family: BMHANNAAir !important; */
}
.buttonText {
  color: white;
  font-family: BMHANNAAir !important;
}
</style>
