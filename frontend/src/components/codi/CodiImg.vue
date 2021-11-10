<template lang="">
  <v-container class="codiImgBox" fluid>
    <v-row>
      <v-col cols="12" sm="12" md="12" lg="12"
        ><v-card class="codiImg">
          <v-img
            class=""
            lazy-src="https://picsum.photos/id/11/10/6"
            src="https://picsum.photos/id/11/500/300"
          ></v-img>
          <div style="height: 200px; width: 500px; border: 1px solid red; position: relative">
            <vue-draggable-resizable
              :w="100"
              :h="100"
              :x="s"
              :y="10"
              @dragging="onDrag"
              @resizing="onResize"
              :parent="true"
            >
              <v-img
                class=""
                lazy-src="https://picsum.photos/id/11/10/6"
                src="@/assets/test/상의.jpg"
              ></v-img>
            </vue-draggable-resizable>
            <vue-draggable-resizable
              :w="100"
              :h="100"
              @dragging="onDrag"
              @resizing="onResize"
              :parent="true"
            >
              <v-img
                class=""
                lazy-src="https://picsum.photos/id/11/10/6"
                src="@/assets/test/바지.png"
              ></v-img>
            </vue-draggable-resizable>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  data: function () {
    return {
      width: 0,
      height: 0,
      x: 10,
      y: 0,
      s: 100,
    };
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
<style scoped>
.codiImgBox {
  background-color: #d6d6d6;
  padding: 20px;
  /* width: 414px;
  height: 414px; */
}
.codiImg {
  width: 100%;
  height: 100%;
}
</style>
