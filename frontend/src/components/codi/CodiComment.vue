<template lang="">
  <v-container class="codiComment">
    <!-- 댓글 start-->
    <!-- <v-row v-for="comment in comments" :key="comment.id">
      <v-col cols="12" sm="12" md="12" lg="12">
        <v-divider class="pb-4"></v-divider> -->
    <!-- 댓글 작성자 -->
    <!-- <h5 class="blackText">{{ comment.userNickname }}</h5> -->
    <!-- 수정,삭제 버튼 -->
    <!-- <a class="grayTextBtn">수정</a> -->

    <!-- <a @click="deleteComments(comment.commentId)" class="grayTextBtn">삭제</a> -->
    <!-- 댓글 내용 -->
    <!-- <h4 class="">{{ comment.commentContent }}</h4> -->
    <!-- </v-col> -->
    <!-- </v-row> -->
    <!-- 댓글 end-->
    <v-row v-for="comment in comments" :key="comment.commentId">
      <v-col cols="auto" style="padding-right: 0">
        <v-img class="profileImg" src="../../assets/logo/login.png"> </v-img>
      </v-col>
      <v-col>
        <v-row>
          <v-col style="padding-top: 14px; padding-bottom: 0">
            <h5 style="display: inline">{{ comment.userNickname }} &nbsp; &nbsp;</h5>
            <h5 style="display: inline" v-if="!(isModify && comment.commentId == commentId)">
              {{ comment.commentContent }}
            </h5>
            <v-text-field
              v-model="selectCommentContent"
              append-icon="mdi-pencil"
              v-if="isModify && comment.commentId == commentId"
              type="text"
              style="display: inline-block"
              dense
              color="#857db1"
              @click:append="updateComment(comment.commentId)"
              v-on:keyup.enter="updateComment(comment.commentId)"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="auto" align="right" style="padding: 0 0 0 12px">
            <h6 class="modiAndDel">{{ comment.commentCreatedAt.split(`T`)[0] }}</h6>
          </v-col>
          <v-col
            v-if="isWriter(comment.userId)"
            cols="auto"
            align="right"
            style="padding: 0 0 0 12px"
          >
            <h6
              @click="selectComment(comment.commentId, comment.commentContent)"
              class="modiAndDel"
            >
              수정
            </h6></v-col
          >
          <v-col
            v-if="isWriter(comment.userId)"
            cols="auto"
            align="right"
            style="padding: 0 0 0 6px"
          >
            <h6 @click="deleteComment(comment.commentId)" class="modiAndDel">삭제</h6>
          </v-col>
          <v-col
            v-if="isModify && comment.commentId == commentId"
            cols="auto"
            align="right"
            style="padding: 0 0 0 6px"
          >
            <h6 @click="cancelUpdate()" class="modiAndDel">취소</h6>
          </v-col>
          <v-col
            v-if="comment.commentCreatedAt != comment.commentUpdatedAt"
            cols="auto"
            align="right"
            style="padding: 0 0 0 6px"
          >
            <h6 class="modiAndDel">수정됨</h6>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
    <v-row
      style="
        background-color: #ffffff;
        width: 100%;
        padding: 15px 15px 0px 0px;
        position: fixed;
        bottom: 95px;
        z-index: 10;
      "
    >
      <v-text-field
        v-model="content"
        type="text"
        append-icon="mdi-pencil"
        outlined
        dense
        hide-details=""
        @click:append="createComment()"
        placeholder="댓글 쓰기"
        color="#857db1"
        style="background-color: #ffffff"
        v-on:keyup.enter="createComment()"
      >
      </v-text-field>
    </v-row>
  </v-container>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
import { createComment, updateComment, deleteComment } from "@/api/comment";

export default {
  data() {
    return {
      codiId: this.$route.no,
      commentList: [],
      isModify: false,
      commentId: "",
      content: "",
      selectCommentContent: "",
      updateCommentContent: "",
    };
  },
  computed: {
    ...mapGetters(["comments"]),
  },
  watch: {
    comments: function () {
      this.commentList = this.comments;
    },
  },
  created() {
    this.getCommentList();
  },
  methods: {
    ...mapActions(["getComments"]),
    getCommentList() {
      let codiId = this.$route.params.no;
      let payload = { codiId: codiId, page: 1, size: 1000 };
      this.$store.dispatch("getComments", payload);
    },
    isWriter(userId) {
      if (userId === this.$store.state.auth.loginUser.userId) {
        return true;
      } else {
        return false;
      }
    },
    selectComment(commentId, content) {
      this.isModify = true;
      this.commentId = commentId;
      console.log(this.isModify + " " + this.commentId);
      this.selectCommentContent = content;
    },
    deleteComment(commentId) {
      console.log("댓삭");
      let accessToken = this.$store.state.auth.accessToken;
      deleteComment(commentId, accessToken).then(() => {
        this.getCommentList();
      });
    },
    updateComment(commentId) {
      let accessToken = this.$store.state.auth.accessToken;
      let payload = { content: this.selectCommentContent };
      updateComment(payload, commentId, accessToken).then(() => {
        this.getCommentList();
      });
      this.updateCommentContent = "";
    },
    createComment() {
      let accessToken = this.$store.state.auth.accessToken;
      let payload = { codiId: this.$route.params.no, content: this.content };
      createComment(payload, accessToken).then(() => {
        this.getCommentList();
      });
      this.content = "";
    },
    cancelUpdate() {
      this.isModify = false;
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
.profileImg {
  border-style: solid;
  border-color: #857db1;
  border-radius: 50px;
  max-height: 45px;
  max-width: 45px;
}
.modiAndDel {
  color: #a9a9a9;
}
</style>
