<template lang="">
  <v-container class="codiComment">
    <!-- 댓글 start-->
    <v-row v-for="comment in comments" :key="comment.id">
      <v-col cols="12" sm="12" md="12" lg="12">
        <v-divider class="pb-4"></v-divider>
        <!-- 댓글 작성자 -->
        <h5 class="blackText">{{ comment.userNickname }}</h5>
        <!-- 수정,삭제 버튼 -->
        <a class="grayTextBtn">수정</a>

        <a class="grayTextBtn">삭제</a>
        <!-- 댓글 내용 -->
        <h4 class="">{{ comment.commentContent }}</h4>
      </v-col>
    </v-row>
    <!-- 댓글 end-->
  </v-container>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

export default {
  data() {
    return {
      commentList: [],
    };
  },
  computed: {
    ...mapGetters(["comments"]),
  },
  created() {
    this.selectComments();
  },
  methods: {
    ...mapActions(["getComments"]),
    selectComments() {
      let codiId = this.$route.params.no;
      let payload = { codiId: codiId, page: 1, size: 10 };
      this.$store.dispatch("getComments", payload);
    },
  },
};
</script>
<style scoped>
.codiComment {
  padding-top: 0px;
  padding-bottom: 20px;
  padding-left: 20px;
  padding-right: 20px;
}
.blackText {
  color: #a9a9a9;
  width: 280px;
  display: inline-block;
}
.grayTextBtn {
  color: #a9a9a9;
  display: inline-block;
  font-size: 13px;
  padding-left: 2px;
  padding-right: 2px;
}
</style>
