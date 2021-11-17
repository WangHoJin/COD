<template>
  <v-flex class="followList">
    <v-row v-for="(f, i) in followingList" :key="i">
      <v-col class="profileImg" cols="auto">
        <v-avatar size="50">
          <img src="@/assets/test/profile.jpg" />
        </v-avatar>
      </v-col>
      <v-col>
        <h4 class="followName" @click="clickUser(f.userId)">{{ f.nickname }}</h4>
      </v-col>
      <v-col>
        <h5 v-if="isFollow()" class="following" @click="deleteFollow(f.userId)">팔로잉</h5>
      </v-col>
    </v-row>
  </v-flex>
</template>
<script>
import { deleteFollow } from "@/api/follow";
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      tabs: ["팔로워", "팔로잉"],
    };
  },
  computed: {
    ...mapGetters(["followingList"]),
  },
  watch: {},
  created() {
    this.setFollowingList();
  },
  methods: {
    ...mapActions(["getFollowing"]),
    isFollow() {
      return true;
    },
    deleteFollow(toUserId) {
      let accessToken = this.$store.state.auth.accessToken;
      alert("팔로우 취소 하시겠습니까?");
      deleteFollow(toUserId, accessToken).then(() => {
        this.setFollowingList();
      });
    },
    setFollowingList() {
      let userId = this.$route.params.no;
      let payload = { page: 1, size: 10, fromUserId: userId };
      this.getFollowing(payload).then(console.log(this.followingList));
    },
    clickUser(userId) {
      this.$router.push({
        path: `../` + userId,
      });
    },
  },
};
</script>
<style scoped>
.profileImg {
  padding-right: 0px;
}
.followList {
  padding: 12px 12px 8px 12px;
}
.followName {
  text-align: left;
  margin: 13px 10px 10px 0px;
}
.following {
  color: #979797;
  text-align: right;
  margin: 16px 0px 13px 13px;
}
</style>
