<template lang="">
  <div>
    <Header title="회원가입" />
    <div class="mt-14 mb-12">
      <div class="content" style="text-align: center">
        <v-form ref="form" v-model="valid">
          <div  v-if="code==404" class="text-left mb-3" style="width:100%">
          <v-chip outlined small color="red" class="error-chip">이미 존재하는 이메일입니다.</v-chip>
          </div>
          <v-text-field
            v-model="email"
            class="mb-2"
            label="이메일"
            :rules="emailRules"
            prepend-icon="mdi-email"

            color="rgba(133, 125, 177, 0.8)"
          ></v-text-field>
          <v-text-field
            v-model="password"
            prepend-icon="mdi-lock"
            label="비밀번호"
            :rules="passwordRules"
            type="password"
            color="rgba(133, 125, 177, 0.8)"
          ></v-text-field>
          <v-text-field
            v-model="repassword"
            prepend-icon="mdi-lock-check"
            label="비밀번호 확인"
            :rules="repasswordRules"
            type="password"
            color="rgba(133, 125, 177, 0.8)"
          ></v-text-field>

          <v-text-field
          prepend-icon="mdi-account-edit"
           v-model="name" 
           :rules="nameRules"
           label="이름" color="rgba(133, 125, 177, 0.8)"></v-text-field>

          <div  v-if="code==405" class="text-left mb-3" style="width:100%">
          <v-chip outlined small color="red" class="error-chip">이미 존재하는 닉네임입니다.</v-chip>
          </div>
          <v-text-field
            v-model="nickname"
            prepend-icon="mdi-account-edit"
            label="닉네임"
            :rules="nicknameRules"
            color="rgba(133, 125, 177, 0.8)"
          ></v-text-field>
        <v-menu
      color="rgba(133, 125, 177, 0.8)"
      ref="menu"
      v-model="menu"
      :close-on-content-click="false"
      transition="scale-transition"
      offset-y
      min-width="auto"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-text-field
          color="rgba(133, 125, 177, 0.8)"
          v-model="birth"
          label="생년월일"
          :rules="birthRules"
          prepend-icon="mdi-calendar"
          readonly
          v-bind="attrs"
          v-on="on"
        ></v-text-field>
      </template>
      <v-date-picker
        v-model="birth"
        :active-picker.sync="activePicker"
        color="rgba(133, 125, 177, 0.8)"
        :max="(new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)"
        min="1950-01-01"
        @change="save"
      ></v-date-picker>
    </v-menu>
      
        </v-form>
        <div class="text-left">
          <p>성별</p>
          <div>
            <span class="mr-2">
              <v-btn
                @click="isManSelected = true"
                :class="{ selected: isManSelected, 'not-selected': !isManSelected }"
              >
                남
              </v-btn>
            </span>
            <span>
              <v-btn
                @click="isManSelected = false"
                :class="{ selected: !isManSelected, 'not-selected': isManSelected }"
              >
                여
              </v-btn>
            </span>
          </div>
        </div>
        <v-btn @click="regist" dark color="rgba(133, 125, 177, 0.8)" class="round-btn py-5 mt-12 mb-5">
          회원가입
        </v-btn>
      </div>
    </div>
  </div>
</template>
<script>
import Header from '@/components/common/BackTitleHeader.vue';
import { mapGetters } from 'vuex';
import {signUp} from '@/api/user.js'

export default {
  components: {
    Header,
  },
  data() {
    return {
      code:null,
      valid:true,
      isManSelected: true,
      email:null,
      password:null,
      repassword:null,
      name:null,
      birth:null,
      activePicker:null,
      menu:null,
      nickname:null,
      emailRules: [
        (v) => !!v || '이메일을 입력해주세요',
        (v) => /.+@.+\..+/.test(v) || '이메일의 형태가 아닙니다',
      ],
      passwordRules: [
        (v) => !!v || '비밀번호를 입력해주세요',
        (v) => (v && v.length >= 5) || '비밀번호는 5글자 이상이여야 합니다',
      ],
      repasswordRules: [
        (v) => !!v || '비밀번호 확인을 입력해주세요',
        (v) => (v == this.password) || '비밀번호가 일치하지 않습니다',
      ],
      nameRules: [
        (v) => !!v || '이름을 입력해주세요',
      ],
      nicknameRules: [
        (v) => !!v || '닉네임을 입력해주세요',
      ],
      birthRules: [
        (v) => !!v || '생일을 입력해주세요',
      ],
    };
  }, 
  watch: {
      menu (val) {
        val && setTimeout(() => (this.activePicker = 'YEAR'))
      },
    },
  methods: {
      save (birth) {
        this.$refs.menu.save(birth)
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
      regist(){
        this.validate();
        if(!this.valid) return;
        let info = {
        "email" : this.email,
        "name" : this.name,
        "password" : this.password,
        "nickname" : this.nickname,
        "birth" : this.birth,
        "gender" : this.isManSelected?"남":"여",
      };
      signUp(info).then((res)=>{
        console.log("회원가입 성공");
        alert("회원가입에 성공하였습니다.");
        console.log(res);
        this.code=null;
        this.resetValidation();
        this.isManSelected = true;
        this.reset();
        this.$router.push({ name: 'sign-in' });
      }).catch((error)=>{
        console.log("회원가입 실패");
        let code = error.response.data.code;
        let message = error.response.data.message;
        console.error("code", code);
        console.error("message",message);
        //이미 존재하는 이메일
        this.code = code;
      });

      }
  }
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
.selected {
  color: white !important;
  background-color: rgba(133, 125, 177, 0.8) !important;
}
.not-selected {
  color: gray !important;
  background-color: white !important;
}
.error-chip{
  align-self: left;
}
</style>
