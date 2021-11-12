<template>
  <div>
    <feed-search />
    <div>
      <v-tabs fixed-tabs color="#857DB1">
        <v-tab>팔로잉 </v-tab>
        <v-tab>인기</v-tab>
      </v-tabs>
    </div>
    <feed-content />
    <feed-comment />
  </div>
</template>

<script>
import FeedSearch from "../../components/feed/FeedSearch.vue";
import FeedContent from "../../components/feed/FeedContent.vue";
import FeedComment from "../../components/feed/FeedComment.vue";

export default {
  components: { FeedSearch, FeedContent, FeedComment },
  data() {
    return {
      page: 1,
    };
  },
};
</script>

<style scoped></style>
