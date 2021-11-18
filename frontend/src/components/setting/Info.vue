<template>
  <v-container class="infoDes">
    <!-- 개인 정보 start -->
    <v-row>
      <v-col cols="3" sm="12" md="12" lg="12">
        <h5 class="kindText">이름</h5>
      </v-col>
      <v-col cols="9" sm="12" md="12" lg="12">
        <h5 class="contentText">{{ loginUser.name }}</h5>
      </v-col>
      <v-col cols="3" sm="12" md="12" lg="12">
        <h5 class="kindText">이메일</h5>
      </v-col>
      <v-col cols="9" sm="12" md="12" lg="12">
        <h5 class="contentText">{{ loginUser.email }}</h5>
      </v-col>
      <v-col cols="3" sm="12" md="12" lg="12">
        <h5 class="kindText">성별</h5>
      </v-col>
      <v-col cols="9" sm="12" md="12" lg="12">
        <h5 class="contentText">{{ loginUser.gender }}</h5>
      </v-col>
      <v-col cols="3" sm="12" md="12" lg="12">
        <h5 class="kindText">생년월일</h5>
      </v-col>
      <v-col cols="9" sm="12" md="12" lg="12">
        <h5 class="contentText">{{ loginUser.birth }}</h5>
      </v-col>
    </v-row>

    <!-- 개인 정보 end -->
  </v-container>
</template>
<script>
import { mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters(["loginUser"]),
  },
  methods: {
    clickInfo() {},
    clickUpdate() {},
  },
};
</script>
<style scoped>
.infoDes {
  /* background-color: #d6d6d6; */
  padding: 40px 30px 100px 40px;
}
.profileImg {
  padding-top: 40px;
}
.kindText {
  color: #414141;
  display: inline;
  /* padding-top: 12px; */
  /* font-family: BMHANNAAir !important; */
}
.contentText {
  color: #6f6f6f;
  display: inline;
}
</style>
