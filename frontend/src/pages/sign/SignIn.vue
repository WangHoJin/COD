<template lang="">
  <div class="mt-10 mb-12">
    <div class="content" style="text-align: center">
      <div class="px-16 mb-9">
        <v-img src="@/assets/logo/login-with-text.png"></v-img>
      </div>
      <v-text-field class="mb-2" label="이메일" color="rgba(133, 125, 177, 0.8)"></v-text-field>
      <v-text-field label="비밀번호" color="rgba(133, 125, 177, 0.8)"></v-text-field>
      <v-btn dark color="rgba(133, 125, 177, 0.8)" class="round-btn py-5 my-5"> 로그인 </v-btn>
    </div>
    <div class="px-5 mb-3">
      <v-row>
        <v-col class="muted text-left">
          <v-btn
            text
            class="text-right"
            style="font-size: 0.75rem; color: gray !important"
            disabled
          >
            아직 회원이 아니신가요?
          </v-btn>
        </v-col>
        <v-col cols="4">
          <v-btn
            @click="mvSignUp"
            text
            class="text-right"
            style="font-size: 0.75rem; font-weight: bold"
          >
            회원가입
          </v-btn>
        </v-col>
      </v-row>
    </div>
  </div>
</template>
<script>
export default {
  methods: {
    mvSignUp() {
      this.$router.push({
        name: 'sign-up',
      });
    },
  },
};
</script>
<style scoped>
.content {
  padding: 35px;
}
.round-btn {
  width: 80%;
  /* height: 55px; */
  box-shadow: 0px 4px 30px #b9abd2;
  border-radius: 38px;
  color: white;
}
</style>
