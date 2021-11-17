<template lang="">
  <v-container class="clothesImgBox" fluid>
    <v-row class="img">
      <v-col cols="12" sm="12" md="12" lg="12"
        ><v-card class="clothesImg">
          <v-img
            class=""
            height="400px"
            src="https://cod-bucket.s3.ap-northeast-2.amazonaws.com/feedsample.png"
            contain
          >
          <template v-slot:placeholder>
        <v-row
          class="fill-height ma-0"
          align="center"
          justify="center"
        >
          <v-progress-circular
            indeterminate
            color="black lighten-5"
          ></v-progress-circular>
        </v-row>
      </template>
          </v-img>
        </v-card>
      </v-col>
    </v-row>
    <v-file-input
      class="upload"
      label="File input"
      filled
      prepend-icon="mdi-camera"
      @change="change()"
    ></v-file-input>
  </v-container>
</template>
<script>
export default {
  data: function () {
    return {};
  },
  methods: {
    // 이미지 업로드
    change() {
      ext = $(this).val().split(".").pop().toLowerCase(); //확장자
      //배열에 추출한 확장자가 존재하는지 체크
      if ($.inArray(ext, ["gif", "png", "jpg", "jpeg"]) == -1) {
        resetFormElement($(this)); //폼 초기화
        window.alert("이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)");
      } else {
        file = $("#img").prop("files")[0];
        blobURL = window.URL.createObjectURL(file);
        $("#image_preview img").attr("src", blobURL);
        $("#image_preview").slideDown(); //업로드한 이미지 미리보기
        $(this).slideUp(); //파일 양식 감춤
      }
    },
  },
};
</script>
<style scoped>
.clothesImgBox {
  background-color: #d6d6d6;
  padding: 20px;
  /* width: 414px;
  height: 414px; */
}
.clothesImg {
  width: 100%;
  height: 100%;
}
.upload {
  align-items: center;
  align-items: center;
  text-align: center;
}
</style>
