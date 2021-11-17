<template>
  <v-footer class="footer" absolute>
    <v-card flat width="100%" class="text-center px-3">
      <v-card-text>
        <v-row>
          <v-col v-for="(icon, i) in icons" :key="i">
            <v-btn class="menuBtn" @click="selectMenu(i)" icon>
              <v-img width="25px" :src="icon" />
            </v-btn>
          </v-col>
        </v-row>
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
    urls: ["main", "feed", "closet", "codi", "mypage"],
  }),
  methods: {
    selectMenu(i) {
      this.$router.push({ name: this.urls[i] }).catch((err) => {});
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
  /* margin: 0px 17px; */
}
</style>
