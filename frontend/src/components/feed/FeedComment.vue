<template>
  <v-container>
    <v-row>
      <v-col cols="auto" style="padding: 0 0 0 12px">
        <h5 style="font-weight: 700">yeniiii</h5>
      </v-col>
      <v-col cols="auto" style="padding: 0 0 0 12px">
        <h5>댓글 남기고 갑니당^_^</h5>
      </v-col>
      <v-col cols="auto" align="right" style="padding: 0 0 0 12px">
        <h6 class="modiAndDel">수정</h6></v-col
      >
      <v-col cols="auto" align="right" style="padding: 0 0 0 6px">
        <h6 class="modiAndDel">삭제</h6>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="auto" style="padding: 0 0 0 12px">
        <h5 style="font-weight: 700">ssafy</h5>
      </v-col>
      <v-col style="padding: 0 0 0 12px">
        <h5>내일 이렇게 입어야지</h5>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { getComment, updateComment, deleteComment } from "@/api/comment";
export default {
  props: [],
  component: {},
  computed: {},
  data() {
    return {
      // isModiAndDelShow: false,
      // contentRules: [(value)=>!!value || '내용을 입력해주세요'],
      // content:this.comment.commentContent,
      // valid:true,
    };
  },
  methods: {
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
  },
};
</script>

<style scoped>
.modiAndDel {
  color: #a9a9a9;
}
</style>
