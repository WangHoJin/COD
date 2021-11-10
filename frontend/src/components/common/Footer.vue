<template>
  <v-footer class="footer">
    <v-card flat width="100%" class="text-center">
      <v-card-text>
        <v-btn v-for="(icon, i) in icons" :key="i" class="menuBtn" @click="selectMenu(i)" icon>
          <img style="width: 35%; padding: 10px 10px" :src="icon" />
        </v-btn>
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>
export default {
  name: "Footer",
  components: {},
  data: () => ({
    icons: [
      require("@/assets/icon/menu/home.png"),
      require("@/assets/icon/menu/feed.png"),
      require("@/assets/icon/menu/closet.png"),
      require("@/assets/icon/menu/codi.png"),
      require("@/assets/icon/menu/mypage.png"),
    ],
    urls: ["main", "wood", "closet", "codi", "mypage"],
  }),
  methods: {
    selectMenu(i) {
      console.log(i - 1);
      console.log(this.urls[i - 1]);
      this.$router.push({
        name: this.urls[i - 1],
      });
    },
  },
};
</script>

<style scoped>
.v-sheet.v-footer:not(.v-sheet--outlined) {
  box-shadow: 0px -4px 10px rgba(0, 0, 0, 0.15);
}

.footer {
  position: fixed;
  left: 0%;
  top: 92%;
  padding: 0px;
  background: #ffffff;
}
.menuBtn {
  margin: 0px 17px;
}
</style>
