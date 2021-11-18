<template>
  <vue-draggable-resizable
    :w="100"
    :h="100"
    :x="100"
    :y="10"
    @dragging="onDrag"
    @resizing="onResize"
    :parent="true"
    class="dragImg"
  >
    <v-img
      contain
      crossOrigin="true"
      class=""
      lazy-src="https://picsum.photos/id/11/10/6"
      :src="path"
    ></v-img>
    <!-- 선택한 이미지 -->
  </vue-draggable-resizable>
</template>
<script>
export default {
  methods: {},
  props: { path: String },
  created() {
    console.log(this.path);
  },
  methods: {
    onResize: function (x, y, width, height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      // console.log("x좌표 : " + x + " " + "y좌표 : " + y);
    },
    onDrag: function (x, y) {
      this.x = x;
      this.y = y;
    },
  },
};
</script>
<style scoped></style>
