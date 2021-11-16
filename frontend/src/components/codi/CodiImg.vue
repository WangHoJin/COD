<template lang="">
  <v-container class="codiImgBox" fluid>
    <v-row>
      <v-col cols="12" sm="12" md="12" lg="12"
        ><v-card class="codiImg">
          <v-img class="" height="200px" :src="$store.state.codi.codi.codiThumbnail"></v-img>
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
  created() {
    console.log(this.$store.state.codi.codi.codiThumbnail);
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
