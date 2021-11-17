<template>
  <v-container>
    <!-- 프로필 사진 start -->
    <v-row class="profileImg">
      <v-spacer></v-spacer>
      <v-col cols="6" sm="3" md="3" lg="3">
        <v-avatar size="169">
          <img src="@/assets/test/profile.jpg" />
        </v-avatar>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
    <!-- 프로필 사진 end -->

    <!-- 프로필 사진 변경 버튼 start -->
    <v-row>
      <v-spacer></v-spacer>
      <v-col cols="6" sm="3" md="3" lg="3">
        <h4 style="text-align: center">프로필 사진 변경</h4>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
    <!-- 프로필 사진 변경 버튼 end -->

    <!-- 프로필 정보 start -->
    <v-container class="profileDes">
      <v-form v-model="valid">
        <v-row>
          <v-col cols="3" sm="12" md="12" lg="12">
            <h5 class="kindText">닉네임</h5>
          </v-col>
          <v-text-field value="호진님" label="" readonly></v-text-field>
        </v-row>
        <v-row>
          <v-col cols="3" sm="12" md="12" lg="12">
            <h5 class="kindText">소개글</h5>
          </v-col>
          <v-text-field value="소개글 입니다 :)" label="" readonly></v-text-field>
        </v-row>
        <v-row>
          <v-col cols="12" sm="12" md="12" lg="12">
            <h5 class="" style="color: #857db1" @click="clickInfo()">* 개인 정보 조회</h5>
          </v-col>
        </v-row>
      </v-form>
    </v-container>
    <!-- 프로필 정보 end -->
    <v-container>
      <v-row>
        <v-btn elevation="2" color="#857DB1" block
          ><h4 style="color: white" @click="clickUpdate()">수정하기</h4></v-btn
        >
      </v-row>
    </v-container>
  </v-container>
</template>
<script>
export default {
  methods: {
    clickInfo() {},
    clickUpdate() {},
  },
};
</script>
<style scoped>
.profileDes {
  /* background-color: #d6d6d6; */
  padding: 30px 20px 100px 20px;
}
.profileImg {
  padding-top: 40px;
}
.kindText {
  color: #414141;
  /* display: inline; */
  padding-top: 12px;
  /* font-family: BMHANNAAir !important; */
}
.contentText {
  color: #6f6f6f;
  display: inline;
}
</style>
