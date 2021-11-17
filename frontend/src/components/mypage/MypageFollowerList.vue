<template>
  <v-flex class="followList">
    <v-row v-for="(f, i) in followerList" :key="i">
      <v-col class="profileImg" cols="auto">
        <v-avatar size="50">
          <img src="@/assets/test/profile.jpg" />
        </v-avatar>
      </v-col>
      <v-col>
        <h4 class="followName">{{ f.nickname }}</h4>
      </v-col>
      <v-col>
        <h5 v-if="isFollow(f.userId)" class="follow" @click="createFollow(f.userId)">팔로우</h5>
      </v-col>
    </v-row>
  </v-flex>
</template>
<script>
import { createFollow } from "@/api/follow";
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      tabs: ["팔로워", "팔로잉"],
      follower: [],
    };
  },
  computed: {
    ...mapGetters(["followerList", "followingList"]),
  },
  watch: {},
  created() {
    this.setFollowerList();
  },
  methods: {
    ...mapActions(["getFollower", "getFollowing"]),

    isFollow(userId) {
      for (var i = 0; i < this.followingList.length; i++) {
        // 내가 팔로우한 사람이면 false => 팔로우 버튼이 안보이게
        if (this.followingList[i].userId === userId) {
          return false;
        }
      }
      // 내가 팔로우 하지 않았으면 true => 팔로우 버튼 보이게
      return true;
    },
    createFollow(toUserId) {
      let accessToken = this.$store.state.auth.accessToken;
      alert("팔로우 하시겠습니까?");
      createFollow(toUserId, accessToken).then(() => {
        this.setFollowingList();
      });
    },
    setFollowerList() {
      let userId = this.$store.state.auth.loginUser.userId;
      let payload = { page: 1, size: 10, toUserId: userId };
      this.getFollower(payload);
    },
    setFollowingList() {
      let userId = this.$store.state.auth.loginUser.userId;
      let payload = { page: 1, size: 10, fromUserId: userId };
      this.getFollowing(payload);
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
.follow {
  color: #857db1;
  text-align: right;
  margin: 16px 0px 13px 13px;
}
</style>
