<template>
  <v-form>
    <v-container v-if="true">
      <v-row>
        <!-- 옷 사용 목록 start -->
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
        <!-- 옷 사용 목록 end -->

        <v-col class="" cols="12" md="4">
          <v-divider class="pb-4"></v-divider>
        </v-col>
      </v-row>
    </v-container>
    <!-- 코디 입력 폼 start -->
    <v-container v-if="true">
      <v-row>
        <v-col class="inputForm" cols="12" md="4">
          <v-textarea label="코디명" auto-grow outlined rows="1"></v-textarea>
          <v-textarea label="태그" auto-grow outlined rows="1" row-height="15"></v-textarea>
          <v-textarea outlined label="설명"></v-textarea>
        </v-col>
      </v-row>
      <v-row>
        <v-spacer></v-spacer>
        <v-spacer></v-spacer>
        <v-col class="switchBtn" cols="4" md="4"><v-switch label="공개"></v-switch></v-col>
      </v-row>
      <v-row>
        <v-spacer></v-spacer>
        <v-spacer></v-spacer>
        <v-col class="" cols="4" md="4">
          <v-btn class="mr-4" @click="submit"> submit </v-btn></v-col
        >
      </v-row>
    </v-container>
    <!-- 코디 입력 폼 end -->

    <!-- 등록 버튼 start -->
    <v-btn icon id="addBtn" @click="addClothes()">
      <v-icon large color="#857DB1" left> mdi-checkbox-marked-circle </v-icon>
    </v-btn>
    <!-- 등록 버튼 end -->
  </v-form>
</template>
<script>
import { createCodi } from "@/api/codi";
export default {
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
    addClothes() {
      alert("확인");
      let coordinates = [{}];
      let codi = {
        name: "테스트코디5",
        tag: "데일리, 일상, 힙합",
        description: "테스트코디입니다5",
        thumbnail: "@/assets/test/상의.jpg",
        coordinate: coordinates,
      };
      createCodi(codi).then(() => {
        console.log("등록 성공");
      });
    },
  },
};
</script>
<style scoped>
.inputForm {
  padding-top: 30px;
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
  position: fixed;
  top: 1%;
  right: 1%;
  z-index: 1;
}
</style>
