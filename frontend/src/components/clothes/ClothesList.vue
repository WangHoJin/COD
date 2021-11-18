<template lang="">
  <v-item-group class="">
    <!-- 옷 목록 탭 start -->
    <v-tabs @change="selectClothes()" v-model="tab" fixed-tabs show-arrows color="#857DB1">
      <v-tab class="tabs" v-for="item in items" :key="item.tab">
        {{ item.tab }}
      </v-tab>
    </v-tabs>
    <!-- 옷 목록 탭 end -->
    <v-tabs-items v-model="tab">
      <v-tab-item v-for="item in items" :key="item.tab">
        <!-- 옷 리스트 start -->
        <div v-if="clothesList.length == 0" class="mt-16 pt-14 text-center">
          <v-icon x-large class="mt-16 mb-3">mdi-hanger</v-icon>
          <h4>옷이 없어요. 텅!</h4>
        </div>
        <v-container>
          <v-row>
            <v-col v-for="(c, i) in clothesList" :key="i" cols="4" sm="3" md="2" lg="1">
              <v-img
                contain
                height="100px"
                :src="c.clothImgUrl"
                @click="clothesClick(c.clothId)"
              ></v-img>
            </v-col>
          </v-row>
        </v-container>
        <!-- 옷 리스트 end -->
      </v-tab-item>
    </v-tabs-items>
    <!-- 옷 페이지 start -->
    <!-- <v-pagination id="listPage" v-model="page" :length="4" circle></v-pagination> -->
    <!-- 옷 페이지 end -->

    <!-- 옷 추가 버튼 start -->
    <v-btn id="createBtn" color="#857DB1" dark absolute top right fab @click="createClick()">
      <v-icon>mdi-plus</v-icon>
    </v-btn>
    <!-- 옷 추가 버튼 end -->
  </v-item-group>
</template>
<script>
import { mapGetters, mapActions } from 'vuex';
export default {
  data() {
    return {
      limit: 0,
      page: 1,
      tab: 0,
      items: [
        { tab: '전체' },
        { tab: '상의' },
        { tab: '하의' },
        { tab: '아우터' },
        { tab: '악세서리' },
      ],
      // type:"",
    };
  },
  components: {},
  computed: {
    ...mapGetters(['clothesList']),
  },
  created() {
    this.selectClothes();
  },
  methods: {
    ...mapActions(['getClothesList']),
    selectClothes() {
      let userId = this.$store.state.auth.loginUser.userId;
      let type = this.items[this.tab].tab;
      console.log(type);
      if (type === '전체') {
        let payload = { userId: userId, page: 1, size: 100 };
        this.getClothesList(payload);
      } else {
        let payload = { userId: userId, type: type, page: 1, size: 100 };
        this.getClothesList(payload);
      }
      console.log(this.clothesList);
    },
    isTab(clothes, tab) {
      if (tab === '전체') return true;
      if (clothes == tab) return true;
      return false;
    },
    clothesClick(clothesId) {
      console.log('코디 클릭' + clothesId);
      this.$router.push({
        path: `detail/` + clothesId,
      });
    },
    createClick() {
      this.$router.push({
        path: `create/`,
      });
    },
  },
};
</script>
<style scoped>
#createBtn {
  position: fixed;
  width: 52px;
  height: 52px;
  left: 80%;
  top: 77%;
}
.tabs {
  position: relative;
  width: 40px;
}
</style>
