<template lang="">
  <div>
    <Header title="회원가입" />
    <div class="mt-10 mb-12">
      <div class="content" style="text-align: center">
        <v-text-field class="mb-2" label="이메일" color="rgba(133, 125, 177, 0.8)"></v-text-field>
        <v-text-field label="비밀번호" color="rgba(133, 125, 177, 0.8)"></v-text-field>
        <v-text-field label="비밀번호 확인" color="rgba(133, 125, 177, 0.8)"></v-text-field>

        <v-text-field label="이름" color="rgba(133, 125, 177, 0.8)"></v-text-field>
        <v-text-field label="생년월일" color="rgba(133, 125, 177, 0.8)"></v-text-field>
        <v-text-field label="닉네임" color="rgba(133, 125, 177, 0.8)"></v-text-field>
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
        <v-btn dark color="rgba(133, 125, 177, 0.8)" class="round-btn py-5 mt-16 mb-5">
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
    };
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
.selected {
  color: white !important;
  background-color: rgba(133, 125, 177, 0.8) !important;
}
.not-selected {
  color: gray !important;
  background-color: white !important;
}
</style>
