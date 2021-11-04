<template lang="">
  <v-container class="codiComment">
    <!-- 댓글 start-->
    <v-row v-for="n in 3" :key="n">
      <v-col cols="12" sm="12" md="12" lg="12">
        <v-divider class="pb-4"></v-divider>
        <!-- 댓글 작성자 -->
        <h5 class="blackText">ssafy.lee</h5>
        <!-- 수정,삭제 버튼 -->
        <a class="grayTextBtn">수정</a>

        <a class="grayTextBtn">삭제</a>
        <!-- 댓글 내용 -->
        <h4 class="">
          댓글입니다:D 댓글입니다:D 댓글입니다:D 댓글입니다:D 댓글입니다:D 댓글입니다:D
        </h4>
      </v-col>
    </v-row>
    <!-- 댓글 end-->
  </v-container>
</template>
<script>
export default {};
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
