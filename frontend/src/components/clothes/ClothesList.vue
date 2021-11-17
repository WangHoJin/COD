<template lang="">
  <v-item-group class="">
    <!-- 옷 목록 탭 start -->
    <v-tabs v-model="tab" fixed-tabs show-arrows color="#857DB1">
      <v-tab class="tabs" v-for="item in items" :key="item.tab">
        {{ item.tab }}
      </v-tab>
    </v-tabs>
    <!-- 옷 목록 탭 end -->
    <v-tabs-items v-model="tab">
      <v-tab-item v-for="item in items" :key="item.tab">
        <!-- 옷 리스트 start -->
        <v-container>
          <v-row>
            <v-col v-for="(c, i) in clothes" :key="i" cols="4" sm="3" md="2" lg="1">
              <v-card v-if="isTab(c, item.tab)" class="">
                <v-img height="100px" src="@/assets/test/바지.png"></v-img>
              </v-card>
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
    <v-btn id="createBtn" color="#857DB1" dark absolute top right fab>
      <v-icon>mdi-plus</v-icon>
    </v-btn>
    <!-- 옷 추가 버튼 end -->
  </v-item-group>
</template>
<script>
// import { mapGetters, mapActions } from "vuex";
export default {
  data() {
    return {
      limit: 0,
      page: 1,
      tab: null,
      items: [
        { tab: "전체" },
        { tab: "상의" },
        { tab: "하의" },
        { tab: "아우터" },
        { tab: "잡화" },
      ],
      clothes: ["아우터", "상의", "상의", "상의", "하의", "하의", "상의", "하의", "하의"],
    };
  },
  components: {},
  computed: {
    // ...mapGetters(["clotheses"]),
  },
  created() {},
  methods: {
    // ...mapActions(["getCodies"]),
    isTab(codi, tab) {
      if (codi == tab) return true;
      return false;
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
