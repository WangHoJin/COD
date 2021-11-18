<template lang="">
  <div>
    <v-container class="codiImgBox" fluid>
      <!-- <v-row>
        <v-col cols="12" sm="12" md="12" lg="12"
          ><v-card class="codiImg">
            <div id="codiCombi">
              <ClothesImg v-for="(c, i) in $store.state.codi.usedClothes" :key="i" :path="c.path" />
            </div>
            <v-button @click="copyImg()">click</v-button>
          </v-card>
        </v-col>
      </v-row> -->

      <!-- 옷 고르기 start -->
      <ChoiceClothes />
      <!-- 옷 고르기 end -->
    </v-container>
  </div>
</template>
<script>
import ChoiceClothes from "@/components/codi/ChoiceClothes.vue";
// import ClothesImg from "@/components/codi/ClothesImg.vue";
import { mapGetters } from "vuex";
import html2canvas from "html2canvas";
import axios from "@/utils/axios";
export default {
  data: function () {
    return {
      width: 0,
      height: 0,
      x: 10,
      y: 0,
      s: 100,
      shot: "",
    };
  },
  computed: {
    ...mapGetters["usedClothes"],
  },
  components: {
    ChoiceClothes,
    // ClothesImg,
  },
  methods: {
    copyImg() {
      console.log("클릭실행");
      let myImg;
      html2canvas(document.querySelector("#codiCombi"), {
        allowTaint: true,
        useCORS: true,
        logging: true,
      }).then((canvas) => {
        myImg = canvas.toDataURL("image/png");
        var blobBin = window.atob(myImg.split(",")[1]); // base64 데이터 디코딩
        var array = [];
        for (var i = 0; i < blobBin.length; i++) {
          array.push(blobBin.charCodeAt(i));
        }
        var file = new Blob([new Uint8Array(array)], { type: "image/png" }); // Blob 생성
        var formdata = new FormData();
        formdata.append("images", file);
        let accessToken = this.$store.state.auth.accessToken;
        axios
          .post("/images", formdata, {
            headers: {
              "X-ACCESS-TOKEN": accessToken,
              "Content-Type": "multipart/form-data",
            },
          })
          .then((res) => {
            console.log(res.data);
          })
          .catch((error) => {
            console.log(error.response.data.message);
          });
      });
    },
  },
};
</script>
<style scoped>
.codiImgBox {
  background-color: #d6d6d6;
  padding: 20px;
  /* width: 414px;
  height: 414px; */
}
.codiImg {
  width: 100%;
  height: 100%;
}
#codiCombi {
  height: 200px;
  width: 345px;
  /* border: 1px solid black; */
  position: relative;
}
#shot {
  border: 1px solid black;
  height: 200px;
  width: 100%;
}
.dragImg {
  /* border: 1px solid white; */
  border: none;
}
.clothesBtn {
  position: fixed;
  left: 0.3%;
  bottom: 7%;
  width: 99%;
  border-radius: 10px;
  border: 2px solid #979797;
  border-bottom-color: #ffff;
}
.openClothes {
  background-color: #d6d6d6;
}
</style>
