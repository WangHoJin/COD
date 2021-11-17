<template>
  <div>
    <feed-search />
    <div>
      <v-tabs v-model="tab" fixed-tabs color="#857DB1">
        <v-tab v-for="item in items" :key="item.tab">
          {{ item.tab }}
        </v-tab>
      </v-tabs>
      <v-tabs-items v-model="tab">
        <v-tab-item v-for="item in items" :key="item.tab">
          <feed-following-content v-if="item.tab === '팔로잉'" />
          <feed-popular-content v-if="item.tab === '인기'" />
        </v-tab-item>
      </v-tabs-items>
    </div>
  </div>
</template>

<script>
import FeedSearch from "../../components/feed/FeedSearch.vue";
import FeedFollowingContent from "../../components/feed/FeedFollowingContent.vue";
import FeedPopularContent from "../../components/feed/FeedPopularContent.vue";
import Header from "../../components/common/Header.vue";

export default {
  components: { FeedSearch, FeedFollowingContent, FeedPopularContent },
  data() {
    return {
      page: 1,
      tab: null,
      items: [{ tab: "팔로잉" }, { tab: "인기" }],
    };
  },
};
</script>

<style scoped></style>
