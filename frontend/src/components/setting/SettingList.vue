<template>
  <v-container>
    <v-row class="divider">
      <v-col cols="12" sm="3" md="2" lg="1"
        ><h3 class="settingText" @click="clickLiked()">좋아요 누른 코디 보기</h3>
      </v-col>
    </v-row>
    <v-row class="divider">
      <v-col cols="12" sm="3" md="2" lg="1"
        ><h3 class="settingText" @click="clickProfile()">프로필 편집</h3>
      </v-col>
    </v-row>
    <!-- <v-row class="divider">
      <v-col cols="12" sm="3" md="2" lg="1"
        ><h3 class="settingText" @click="clickPassword()">비밀번호 변경</h3>
      </v-col>
    </v-row> -->
    <v-row class="divider">
      <v-col cols="12" sm="3" md="2" lg="1"
        ><h3 class="settingText" @click="clickLogout()">로그아웃</h3>
      </v-col>
    </v-row>
    <v-row class="divider">
      <v-col cols="12" sm="3" md="2" lg="1"
        ><h3 class="settingText" @click="clickDelete()">회원 탈퇴</h3>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import { deleteUser } from "@/api/user";
import { mapActions } from "vuex";
export default {
  methods: {
    ...mapActions(["logout"]),
    clickLiked() {
      this.$router.push({
        name: `settingLikedCodi`,
      });
    },
    clickProfile() {
      this.$router.push({
        name: `settingUpadateProfile`,
      });
    },
    clickPassword() {
      this.$router.push({
        name: `settingChangePassword`,
      });
    },
    clickLogout() {
      this.logout().then(() => {
        this.$router.push({
          name: "sign-in",
        });
      });
    },
    clickDelete() {
      let accessToken = this.$store.state.auth.accessToken;
      console.log(accessToken);
      deleteUser(accessToken)
        .then(() => {
          this.$router.push({
            name: "sign-in",
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
<style scoped>
.divider {
  border-top: 1px solid white;
  border-bottom: 1px solid #e5eaef;
  padding: 5px;
}
.settingText {
  /* font-family: Noto Sans; */
  padding-left: 10px;
  font-style: normal;
  font-weight: bold;
  font-size: 15px;
  line-height: 20px;
  display: flex;
  align-items: center;
  letter-spacing: 0.25px;
}
</style>
