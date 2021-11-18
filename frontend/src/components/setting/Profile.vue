<template>
  <v-container>
    <!-- 프로필 사진 start -->
    <v-row class="profileImg">
      <v-spacer></v-spacer>
      <v-col cols="6" sm="3" md="3" lg="3">
        <v-avatar size="169">
          <img v-if="loginUser.profile != null" :src="loginUser.profile" />
          <img v-else src="@/assets/test/profile.jpg" />
        </v-avatar>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
    <!-- 프로필 사진 end -->

    <!-- 프로필 사진 변경 버튼 start -->
    <v-row>
      <v-spacer></v-spacer>
      <v-col cols="6" sm="3" md="3" lg="3">
        <v-file-input
          class="upload"
          v-model="file"
          hide-input
          filled
          prepend-icon="mdi-camera"
          @change="change"
          style="display: inline"
        >
        </v-file-input>
        <h4 style="text-align: center; display: inline">프로필 사진 변경</h4>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
    <!-- 프로필 사진 변경 버튼 end -->

    <!-- 프로필 정보 start -->
    <v-container class="profileDes">
      <v-form>
        <v-row>
          <v-col cols="3" sm="12" md="12" lg="12">
            <h5 class="kindText">닉네임</h5>
          </v-col>
          <v-text-field
            v-model="nickname"
            :placeholder="loginUser.nickname"
            label=""
          ></v-text-field>
        </v-row>
        <v-row>
          <v-col cols="3" sm="12" md="12" lg="12">
            <h5 class="kindText">소개글</h5>
          </v-col>
          <v-text-field
            v-model="introduction"
            :placeholder="loginUser.introduction"
            label=""
          ></v-text-field>
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
        <v-btn elevation="2" color="#857DB1" @click="clickUpdate" block
          ><h4 style="color: white">수정하기</h4></v-btn
        >
      </v-row>
    </v-container>
  </v-container>
</template>
<script>
import axios from "@/utils/axios";
import { updateUser } from "@/api/user";
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      nickname: "",
      introduction: "",
      uploadImg: null,
      file: null,
    };
  },
  computed: {
    ...mapGetters(["loginUser"]),
  },
  methods: {
    ...mapActions(["getUser"]),
    clickInfo() {
      this.$router.push({
        path: `info/`,
      });
    },
    clickUpdate() {
      if (this.uploadImg == null) {
        console.log("프로필은 안바꿔");
        this.uploadImg = this.loginUser.profile;
      }
      let user = {
        profile: this.uploadImg,
        introduction: this.introduction,
        nickname: this.nickname,
      };
      let token = this.$store.state.auth.accessToken;
      console.log(this.uploadImg);
      updateUser(user, token).then(() => {
        this.getUser(token);
        this.$router.push({
          name: "settingMain",
        });
      });
    },
    // 이미지 업로드
    change(file) {
      console.log(file);
      let ext = file.type.toLowerCase(); //확장자
      // //배열에 추출한 확장자가 존재하는지 체크
      if (
        !(ext == "image/gif" || ext == "image/png" || ext == "image/jpg" || ext == "image/jpeg")
      ) {
        // resetFormElement($(this)); //폼 초기화
        this.file == null;
        window.alert("이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)");
      } else {
        // file = $("#img").prop("files")[0];
        // blobURL = window.URL.createObjectURL(file);
        // $("#image_preview img").attr("src", blobURL);
        // $("#image_preview").slideDown(); //업로드한 이미지 미리보기
        // $(this).slideUp(); //파일 양식 감춤
        let accessToken = this.$store.state.auth.accessToken;
        var fd = new FormData();
        fd.append("images", this.file);
        axios
          .post("/images", fd, {
            headers: {
              "X-ACCESS-TOKEN": accessToken,
              "Content-Type": "multipart/form-data",
            },
          })
          .then((res) => {
            this.uploadImg = res.data;
            console.log(this.uploadImg);
          })
          .catch((error) => {
            console.log(error.response.data.message);
          });
      }
    },
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
