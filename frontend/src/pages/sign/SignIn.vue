<template lang="">
  <div class="mt-10 mb-12">
    <div class="content" style="text-align: center">
      <div class="px-16 mb-9">
        <v-img src="@/assets/logo/login-with-text.png"></v-img>
      </div>
      <v-form ref="form" v-model="valid" lazy-validation>
        <v-text-field
          class="mb-2"
          v-model="email"
          label="이메일"
          :rules="emailRules"
          color="rgba(133, 125, 177, 0.8)"
        ></v-text-field>
        <v-text-field
          v-model="password"
          type="password"
          label="비밀번호"
          :rules="passwordRules"
          color="rgba(133, 125, 177, 0.8)"
        ></v-text-field>
        <v-btn @click="login" dark color="rgba(133, 125, 177, 0.8)" class="round-btn py-5 my-5">
          로그인
        </v-btn>
        <!-- <v-btn @click="logout" dark color="rgba(133, 125, 177, 0.8)" class="round-btn py-5 my-5">
          로그아웃
        </v-btn> -->
      </v-form>
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
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
      valid: true,
      email: null,
      password: null,
      emailRules: [
        (v) => !!v || '이메일을 입력해주세요',
        (v) => /.+@.+\..+/.test(v) || '이메일의 형태가 아닙니다',
      ],
      passwordRules: [
        (v) => !!v || '비밀번호를 입력해주세요',
        (v) => (v && v.length >= 5) || '비밀번호는 5글자 이상이여야 합니다',
      ],
    };
  },
  computed: {
    ...mapGetters(['loginUser', 'accessToken']),
  },
  watch: {
    loginUser: function () {
      console.log('loginUser', this.loginUser);
    },
  },
  methods: {
    logout() {
      this.$store.dispatch('logout');
    },
    login() {
      this.validate();
      if (!this.valid) return;
      let payload = { email: this.email, password: this.password };
      this.$store
        .dispatch('login', payload)
        .then(() => {
          console.log('로그인 성공');
          this.reset();
          this.resetValidation();
          this.$router.push({
            name: 'main',
          });
        })
        .catch(function (error) {
          console.log('로그인 실패');
          //   console.error('error', error);
          alert('로그인에 실패하였습니다');
          console.error(error.response.data.message);
        });
    },
    mvSignUp() {
      this.$router.push({
        name: 'sign-up',
      });
    },
    validate() {
      this.$refs.form.validate();
    },
    reset() {
      this.$refs.form.reset();
    },
    resetValidation() {
      this.$refs.form.resetValidation();
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
