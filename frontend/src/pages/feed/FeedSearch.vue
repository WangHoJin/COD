<template>
  <v-container>
    <div>
      <feed-search />
    </div>
    <div>
      <v-tabs fixed-tabs color="#857DB1">
        <v-tab>유저검색 </v-tab>
        <v-tab>태그</v-tab>
      </v-tabs>
    </div>
    <feed-search-result />
  </v-container>
</template>

<script>
import FeedSearch from "../../components/feed/FeedSearch.vue";
import FeedSearchResult from "../../components/feed/FeedSearchResult.vue";

export default {
  components: { FeedSearch, FeedSearchResult },
  data() {},
};
</script>

<style></style>
