<template>
  <div>
    <!-- 팔로우 탭 start -->
    <v-tabs v-model="tab" fixed-tabs color="#857DB1">
      <v-tab v-for="item in items" :key="item.tab">
        {{ item.tab }}
      </v-tab>
    </v-tabs>
    <!-- 팔로우 탭 end -->
    <v-tabs-items v-model="tab">
      <v-tab-item v-for="item in items" :key="item.tab">
        <!-- 팔로워 목록 start -->
        <MypageFollowerList v-if="item.tab === '팔로워'" />
        <!-- 팔로워 목록 end -->

        <!-- 팔로잉 목록 start -->
        <MypageFollowingList v-else-if="item.tab === '팔로잉'" />
        <!-- 팔로잉 목록 end -->
      </v-tab-item>
    </v-tabs-items>
    <v-container fluid> </v-container>
  </div>
</template>
<script>
import MypageFollowerList from "@/components/mypage/MypageFollowerList.vue";
import MypageFollowingList from "@/components/mypage/MypageFollowingList.vue";
export default {
  components: {
    MypageFollowerList,
    MypageFollowingList,
  },
  data() {
    return {
      tab: null,
      items: [{ tab: "팔로워" }, { tab: "팔로잉" }],
    };
  },
  methods: {
    clickFollow() {
      this.follow = !this.follow;
    },
    clickTab(tab) {
      if (tab == "팔로워") this.follow = true;
      else this.follow = false;
    },
  },
};
</script>
<style scoped></style>
