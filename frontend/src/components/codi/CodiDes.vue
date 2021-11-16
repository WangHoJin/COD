<template lang="">
  <v-container class="codiDes">
    <!-- 코디 인기 정보 start-->
    <v-row>
      <!-- 좋아요 -->
      <v-col cols="3" sm="12" md="12" lg="12"
        ><v-icon color="#CCBEE3">mdi-heart</v-icon>
        <h5 class="purpleText">{{ codi.liked }}</h5>
      </v-col>
      <!-- 댓글 -->
      <v-col cols="2" sm="12" md="12" lg="12">
        <v-icon color="#CCBEE3">mdi-comment</v-icon>
        <h5 class="purpleText">{{ codi.comment }}</h5>
      </v-col>
      <!-- 공백 -->
      <v-spacer></v-spacer>
      <!-- 등록일 -->
      <v-col class="createAt" cols="5" sm="12" md="12" lg="12">
        <h5 class="grayText">등록일 {{ codi.codiCreatedAt.split(`T`)[0] }}</h5></v-col
      >
    </v-row>
    <!-- 코디 인기 정보 end-->

    <!-- 코디 설명 start-->
    <v-row>
      <v-col cols="12" sm="12" md="12" lg="12">
        <v-divider class="pb-4"></v-divider>
        <!-- 작성자 -->
        <h5 class="purpleText">{{ codi.userNickname }}</h5>
        <br />
        <!-- 코디설명 -->
        <h4 class="">
          {{ codi.codiDescription }}
        </h4>
        <!-- 태그 -->
        <span v-for="tag in codi.codiTag.split(`,`)" :key="tag" class="tag mt-3 mr-3"
          >#{{ tag }}</span
        >
      </v-col>
    </v-row>
    <!-- 코디 설명 end-->
  </v-container>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

export default {
  data() {
    return {
      codiDetail: "",
      tags: [],
      createdAt: [],
      drawer: null,
    };
  },
  computed: {
    ...mapGetters(["codi"]),
  },
  created() {
    this.selectCodi();
  },
  methods: {
    ...mapActions(["getCodi"]),
    selectCodi() {
      let codiId = this.$route.params.no;
      this.getCodi(codiId);
    },
  },
};
</script>
<style scoped>
.codiDes {
  /* background-color: #d6d6d6; */
  padding: 20px;
}
.createAt {
  margin: auto;
}
.tag {
  color: #ffff;
  background-color: #c4bddd;
  border-radius: 5px;
  /* height: 25px; */
  /* width: 50px; */
  padding: 5px;
  text-align: center;
  display: inline-block;
}
.grayText {
  color: #a9a9a9;
  display: inline;
}
.purpleText {
  color: #c4bddd;
  display: inline;
  /* font-family: BMHANNAAir !important; */
}
</style>
