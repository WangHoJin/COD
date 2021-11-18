<template>
  <v-app-bar app absolute color="white" outline>
    <!-- 뒤로가기 버튼 start -->
    <v-btn icon @click="$router.go(-1)">
      <v-icon large> mdi-chevron-left </v-icon>
    </v-btn>
    <!-- 뒤로가기 버튼 end -->

    <!-- 메뉴 타이틀 start -->
    <v-toolbar-title>
      <h4 class="title">{{ title }}</h4>
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <!-- 메뉴 타이틀 end -->

    <!-- 추가 설정 버튼 start -->
    <v-menu v-if="listFlag" offset-y>
      <template v-slot:activator="{ on, attrs }">
        <v-btn icon v-bind="attrs" v-on="on">
          <v-icon>mdi-dots-vertical</v-icon>
        </v-btn>
      </template>
      <v-list>
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
    return {};
  },
  props: {
    title: String,
    listFlag: Boolean,
  },

  methods: {
    deleteClick() {
      console.log("삭제 클릭");
      let token = this.$store.state.auth.accessToken;
      deleteCodi(this.$route.params.no, token);
      alert("삭제하시겠습니까?");
      this.$router.push({
        name: "codi",
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
