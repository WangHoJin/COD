<template>
  <v-container style="padding: 0">
    <v-flex style="padding: 12px 12px 8px 12px">
      <div class="profileInfo">
        <v-row>
          <v-col style="padding-right: 0">
            <v-img class="profileImg" src="../../assets/logo/login.png"> </v-img>
          </v-col>
          <v-col style="padding: 20px 0px 18px 12px">
            <h4>Bogarts_In_Closet</h4>
          </v-col>
          <v-col style="padding: 23px 0px 18px 12px">
            <h5 style="color: #857db1">팔로우</h5>
          </v-col>
        </v-row>
      </div>
    </v-flex>
    <v-flex>
      <div>
        <v-img src="../../assets/img/feedsample.png"></v-img>
      </div>
    </v-flex>
    <v-flex style="padding: 12px">
      <v-row>
        <v-col cols="11" style="padding-right: 0">
          <h5><v-icon style="padding-right: 3px; color: #857db1">mdi-heart</v-icon>1234개</h5>
        </v-col>
        <v-col style="padding-left: 0; padding-right: 0">
          <v-icon>mdi-bookmark-outline</v-icon>
        </v-col>
      </v-row>
    </v-flex>
    <v-flex style="padding: 0 12px 0 12px">
      <v-row>
        <v-col cols="auto">
          <h5 style="font-weight: bold">Boagarts_In_Closet</h5>
        </v-col>
        <v-col style="padding-left: 0">
          <h5>내용입니다~~~</h5>
        </v-col>
      </v-row>
    </v-flex>
  </v-container>
</template>

<script>
export default {};
</script>

<style scoped>
.profileImg {
  border-style: solid;
  border-color: #857db1;
  border-radius: 50px;
  max-height: 45px;
  max-width: 45px;
}
.profileInfo {
  display: inline-block;
}
.col {
  /* padding-right: 0; */
  vertical-align: middle;
  /* padding-left: 0; */
}
</style>
