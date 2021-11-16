<template>
  <div>
    <v-row>
      <!-- 프로필 사진 start -->
      <v-col class="profile" cols="3" sm="3" md="3" lg="3">
        <v-avatar size="60">
          <img src="@/assets/test/profile.jpg" />
        </v-avatar>
      </v-col>
      <!-- 프로필 사진 end -->

      <!-- 팔로우 및 소개글 start -->
      <v-col class="follow" cols="5">
        <h5 class="grayText">팔로워 10</h5>
        &nbsp;
        <h5 class="grayText">팔로잉 16</h5>
        <v-img class="grade" width="15px" src="@/assets/icon/mypage/medal.png" />
        <h5 class="blackText">데일리 소개입니다 :)</h5>
      </v-col>
      <!-- 팔로우 및 소개글 end -->
    </v-row>

    <!-- 챌린지 표시 start -->
    <v-row>
      <v-col cols="12" sm="3" md="3" lg="3">
        <h4 class="pb-2">옷장속 보거트의 챌린지</h4>
        <v-avatar class="mr-3" size="50">
          <img src="@/assets/icon/mypage/challenge-add.png" /> </v-avatar
        ><v-avatar class="mr-3" v-for="i in 4" :key="i" size="50">
          <img src="@/assets/icon/mypage/challenge-ex.png" />
        </v-avatar>
      </v-col>
    </v-row>
    <!-- 챌린지 표시 end -->

    <!-- 구분선 -->
    <v-row class="divideArea">
      <div class="divide"></div>
    </v-row>
    <!-- 구분선 -->
  </div>
</template>
<script>
export default {};
</script>
<style scoped>
.grayText {
  color: #a9a9a9;
  display: inline-block;
}
.profile {
  text-align: center;
}
.follow {
  padding-left: 0px;
}
.grade {
  top: 2px;
  left: 10px;
  display: inline-block;
}
.divideArea {
  height: 50px;
}
.divide {
  position: absolute;
  left: 0;
  width: 100%;
  padding-top: 20px;
  border-bottom: 10px solid #e5eaef;
}
</style>
