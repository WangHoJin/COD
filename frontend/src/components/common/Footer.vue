<template>
  <v-footer class="footer" app>
    <v-row class="px-6" style="background-color: white">
      <v-col class="text-center" v-for="(icon, i) in icons" :key="i">
        <v-btn class="menuBtn" @click="selectMenu(i)" icon>
          <v-img width="15px" :src="icon" />
        </v-btn>
      </v-col>
    </v-row>
  </v-footer>
</template>

<script>
export default {
  name: 'Footer',
  components: {},
  data: () => ({
    icons: [
      require('@/assets/icon/menu/home.png'),
      require('@/assets/icon/menu/feed.png'),
      require('@/assets/icon/menu/closet.png'),
      require('@/assets/icon/menu/codi.png'),
      require('@/assets/icon/menu/mypage.png'),
    ],
    urls: ['main', 'feed', 'clothes', 'codi', 'mypage'],
  }),
  methods: {
    selectMenu(i) {
      if (i == 4) {
        let userId = this.$store.state.auth.loginUser.userId;
        this.$router.push({ name: this.urls[i], params: { no: userId } }).catch((err) => {});
      } else this.$router.push({ name: this.urls[i] }).catch((err) => {});
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
}
</style>
