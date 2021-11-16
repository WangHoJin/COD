<template>
  <v-container>
    <v-row v-for="(comment, index) in commentList" :key="comment.commentId">
      <v-col v-if="index < 2" cols="auto" style="padding: 0 0 0 12px">
        <h5 style="font-weight: 700">{{ comment.userNickname }}</h5>
      </v-col>
      <v-col v-if="index < 2" cols="auto" style="padding: 0 0 0 12px">
        <h5>{{ comment.commentContent }}</h5>
      </v-col>
      <v-col
        v-if="index < 2 && comment.userNickname == '호진님'"
        cols="auto"
        align="right"
        style="padding: 0 0 0 12px"
      >
        <h6 class="modiAndDel">수정</h6></v-col
      >
      <v-col
        v-if="index < 2 && comment.userNickname == '호진님'"
        cols="auto"
        align="right"
        style="padding: 0 0 0 6px"
      >
        <h6 class="modiAndDel">삭제</h6>
      </v-col>
    </v-row>
    <v-row>
      <v-col v-if="codi.comment > 2" cols="auto" style="padding: 0 0 0 12px">
        <h6 class="modiAndDel">댓글 모두 보기</h6>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import { getCommentList, updateComment, deleteComment } from "@/api/comment";
export default {
  props: ["codi"],
  component: {},
  computed: {},
  data() {
    return {
      commentList: [],
      // isModiAndDelShow: false,
      // contentRules: [(value)=>!!value || '내용을 입력해주세요'],
      // content:this.comment.commentContent,
      // valid:true,
    };
  },
  computed: {
    // ...mapGetters(["comments"]),
  },
  created() {
    this.selectComments();
    // this.selectComments();
  },
  methods: {
    // ...mapActions(["getComments"]),
    // selectComments() {
    //   let codiId = this.codi;
    //   let payload = { codiId: codiId, page: 1, size: 10 };
    //   this.$store.dispatch("getComments", payload);
    // },

    // modify() {
    //   this.isModiAndDelShow = true;
    //   this.validate();
    //   if (!this.valid) return;
    //   let payload = {
    //     content: this.content,
    //     password: this.password,
    //     Id: this.comment.Id,
    //   };
    // },
    selectComments() {
      // getComment(){
      let codiId = this.codi.codiId;
      let payload = { codiId: codiId, page: 1, size: 10 };
      getCommentList(payload).then((res) => {
        this.commentList = res;
        this.userId = res.userId;
        this.content = res.content;
      });
      // }
    },
  },
};
</script>

<style scoped>
.modiAndDel {
  color: #a9a9a9;
}
</style>
