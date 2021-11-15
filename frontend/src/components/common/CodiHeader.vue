<template>
  <v-app-bar app absolute color="white" elevate-on-scroll scroll-target="#scrolling-techniques-7">
    <v-toolbar-title>
      <h4 class="title">{{ title }}</h4>
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <!-- 추가 설정 버튼 start -->
    <v-menu v-if="flag" offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn icon v-bind="attrs" v-on="on">
          <v-icon>mdi-dots-vertical</v-icon>
        </v-btn>
      </template>
      <v-list>
        <v-list-item>
          <v-list-item-title @click="updateClick()">수정하기</v-list-item-title>
        </v-list-item>
        <v-list-item>
          <v-list-item-title @click="deleteClick()">삭제하기</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
    <!-- 추가 설정 버튼 end -->
  </v-app-bar>
</template>

<script>
import { deleteCodi } from "@/api/codi";
export default {
  name: "Header",
  data() {
    return {
      flag: false,
    };
  },
  props: {
    title: String,
  },
  created() {
    this.selectHeader();
  },
  watch: {
    title: function () {
      console.log("제목변경");
      this.selectHeader();
    },
  },
  methods: {
    selectHeader() {
      if (this.title == "codiList") {
        this.title = "코디 목록";
        this.flag = false;
      } else if (this.title == "codiCreate") {
        this.title = "코디 등록";
        this.flag = false;
      } else if (this.title == "codiDetail") {
        this.title = this.$route.params.no + "번 코디 상세 정보";
        this.flag = true;
      }
    },
    updateClick() {
      console.log("수정 클릭");
    },
    deleteClick() {
      console.log("삭제 클릭");
      alert("삭제하시겠습니까?");
      deleteCodi(this.$route.params.no);
      this.$router.push({
        name: codiList,
      });
    },
  },
};
</script>

<style scoped>
.title {
  text-align: center;
  width: 100%;
  color: #000;
  display: inline-block;
}
</style>
