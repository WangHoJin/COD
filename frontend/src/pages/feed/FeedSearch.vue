<template>
  <div>
    <!-- <v-container> -->
    <div>
      <feed-search />
    </div>
    <div>
      <v-tabs v-model="tab" fixed-tabs color="#857DB1">
        <v-tab v-for="item in items" :key="item.tab">
          {{ item.tab }}
        </v-tab>
      </v-tabs>
      <v-tabs-items v-model="tab">
        <v-tab-item v-for="item in items" :key="item.tab">
          <feed-search-user-result v-if="item.tab === '유저검색'" />
          <feed-search-tag-result v-if="item.tab === '태그검색'" />
        </v-tab-item>
      </v-tabs-items>
    </div>
    <!-- </v-container> -->
  </div>
</template>

<script>
import FeedSearch from '../../components/feed/FeedSearch.vue';
import FeedSearchUserResult from '../../components/feed/FeedSearchUserResult.vue';
import FeedSearchTagResult from '../../components/feed/FeedSearchTagResult.vue';

export default {
  components: { FeedSearch, FeedSearchUserResult, FeedSearchTagResult },
  data() {
    return {
      page: 1,
      tab: null,
      items: [{ tab: '유저검색' }, { tab: '태그검색' }],
    };
  },
};
</script>

<style></style>
