<template>
  <v-form>
    <v-container>
      <!-- 옷 조합 start -->
      <v-row class="square">
        <v-col class="inner" cols="auto" sm="12" md="12" lg="12">
          <v-card class="codiImg">
            <div class="codiCombi">
              <ClothesImg v-for="(c, i) in $store.state.codi.usedClothes" :key="i" :path="c.path" />
            </div>
          </v-card>
        </v-col>
      </v-row>
      <!-- 옷 조합 end -->

      <!-- 옷 사용 목록 start -->
      <v-row>
        <v-col class="" cols="12" md="4">
          <v-divider class="pb-4"></v-divider>
          <h5>사용목록</h5>
        </v-col>
        <v-col class="" cols="4" md="4" v-for="(c, i) in $store.state.codi.usedClothes" :key="i"
          ><v-card class="codiImg">
            <v-icon class="cancelBtn" @click="removeClothes(c.clothesId)">mdi-close</v-icon>
            <v-img height="100px" :src="c.path"></v-img>
          </v-card>
        </v-col>
      </v-row>
      <!-- 옷 사용 목록 end -->

      <!-- 코디 입력 폼 start -->
      <v-row>
        <v-col class="inputForm" cols="12" md="4">
          <v-divider class="pb-4"></v-divider>
        </v-col>
        <v-col class="inputForm" cols="12" md="4">
          <v-textarea label="코디명" auto-grow outlined rows="1" v-model="name"></v-textarea>
          <v-textarea
            label="태그"
            auto-grow
            outlined
            rows="1"
            row-height="15"
            v-model="tag"
          ></v-textarea>
          <v-textarea outlined label="설명" v-model="description"></v-textarea>
        </v-col>
      </v-row>
    </v-container>
    <!-- 코디 입력 폼 end -->

    <!-- 등록 버튼 start -->
    <v-btn icon id="addBtn" @click="clickCreate()">
      <v-icon large color="#857DB1" left> mdi-checkbox-marked-circle </v-icon>
    </v-btn>
    <!-- 등록 버튼 end -->
  </v-form>
</template>
<script>
import ClothesImg from "@/components/codi/ClothesImg.vue";
import { createCodi } from "@/api/codi";
import html2canvas from "html2canvas";
import axios from "@/utils/axios";
export default {
  components: {
    ClothesImg,
  },
  data: function () {
    return {
      name: "",
      tag: "",
      description: "",
      thumbnail: "",
      coordinate: [],
    };
  },
  methods: {
    removeClothes(clothesId) {
      console.log("삭제했다");
      const temp = this.$store.state.codi.usedClothes;
      console.log("기존 배열", temp);
      const newlist = [];
      for (var i = 0; i < temp.length; i++) {
        if (temp[i].clothesId != clothesId) {
          newlist.push(temp[i]);
        }
      }
      console.log("새로운 배열", newlist);
      this.$store.dispatch("removeUsedClothes", newlist);
    },
    addClothes(path) {
      console.log("위치", this.thumbnail);
      let codi = {
        name: this.name,
        tag: this.tag,
        description: this.description,
        thumbnail: path,
        coordinate: "json",
      };
      let token = this.$store.state.auth.accessToken;
      createCodi(codi, token).then(() => {
        console.log("등록 성공");
      });
    },
    clickCreate() {
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
            this.addClothes(res.data);
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
.inputForm {
  /* padding-top: 30px; */
  padding-bottom: 0px;
}
.switchBtn {
  padding-top: 0px;
}
.codiImg {
  margin: auto;
  border: 2px solid #a9a9a9;
  width: 100%;
  height: 100%;
}
.cancelBtn {
  position: absolute;
  z-index: 1;
  right: 0%;
}
#addBtn {
  position: absolute;
  top: -6.5%;
  right: 1%;
  z-index: 1;
}
.dragImg {
  /* border: 1px solid white; */
  border: none;
}
.square {
  width: 100%;
  margin: 0;
}
.square:after {
  content: "";
  display: block;
  padding-bottom: 100%;
}
.codiCombi {
  height: 100%;
  width: 345px;
  /* border: 1px solid black; */
  position: relative;
}
</style>
