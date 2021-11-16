<template lang="">
  <div>
    <Header title="회원가입" />
    <div class="mt-14 mb-12">
      <div class="content" style="text-align: center">
        <v-form ref="form" v-model="valid">
          <v-text-field
            v-model="email"
            class="mb-2"
            label="이메일"
          prepend-icon="mdi-email"

            color="rgba(133, 125, 177, 0.8)"
          ></v-text-field>
          <v-text-field
            v-model="password"
            prepend-icon="mdi-lock"

            label="비밀번호"
            color="rgba(133, 125, 177, 0.8)"
          ></v-text-field>
          <v-text-field
            v-model="repassword"
            prepend-icon="mdi-lock-check"
            label="비밀번호 확인"
            color="rgba(133, 125, 177, 0.8)"
          ></v-text-field>

          <v-text-field
          prepend-icon="mdi-account-edit"
           v-model="name" label="이름" color="rgba(133, 125, 177, 0.8)"></v-text-field>
          <v-text-field
            v-model="nickname"
            prepend-icon="mdi-account-edit"
            label="닉네임"
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
        <v-btn @click="signup" dark color="rgba(133, 125, 177, 0.8)" class="round-btn py-5 mt-12 mb-5">
          회원가입
        </v-btn>
      </div>
    </div>
  </div>
</template>
<script>
import Header from '@/components/common/BackTitleHeader.vue';
import { mapGetters } from 'vuex';

export default {
  components: {
    Header,
  },
  data() {
    return {
      isManSelected: true,
      email:null,
      password:null,
      repassword:null,
      name:null,
      birth:null,
      menu:null,
      nickname:null
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
      signup(){
            
            


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
</style>
