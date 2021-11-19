<template>
  <div>
    <v-row>
      <!-- 프로필 사진 start -->
      <v-col class="profile ml-3 mt-2" cols="3" sm="3" md="3" lg="3">
        <v-avatar size="70">
          <img v-if="this.loginUser.profile" :src="loginUser.profile" />
          <img v-if="!this.loginUser.profile" src="@/assets/test/profile.jpg" />
        </v-avatar>
      </v-col>
      <!-- 프로필 사진 end -->

      <!-- 팔로우 및 소개글 start -->
      <v-col class="follow mt-2 ml-1" cols="5">
        <h5 class="grayText" @click="clickFollower()">팔로워 {{ followerList.length }}</h5>
        &nbsp;
        <h5 class="grayText" @click="clickFollowing()">팔로잉 {{ followingList.length }}</h5>
        <!-- <v-img class="grade" width="15px" src="@/assets/icon/mypage/medal.png" /> -->
        <h5 class="blackText">{{ loginUser.introduction }}</h5>
      </v-col>
      <!-- 팔로우 및 소개글 end -->

      <v-col class="" cols="4" v-if="!this.$route.params.no == loginUser.userId">
        <v-icon
          v-if="isFollow(this.$route.params.no)"
          @click="deleteCodiLiked(this.$route.params.no)"
          large
          color="#CCBEE3"
          >mdi-star</v-icon
        >
        <v-icon
          v-if="!isFollow(this.$route.params.no)"
          @click="createFollow(this.$route.params.no)"
          large
          color="#9e9e9e"
          >mdi-star-outline</v-icon
        >
      </v-col>
    </v-row>

    <!-- 구분선 -->
    <v-row class="divideArea">
      <div class="divide"></div>
    </v-row>
    <!-- 구분선 -->
  </div>
</template>
<script>
import { mapActions, mapGetters } from 'vuex';
import { createFollow, deleteFollow } from '@/api/follow';
export default {
  data() {
    return {};
  },
  computed: {
    ...mapGetters(['loginUser', 'followerList', 'followingList']),
  },
  created() {
    this.countFollow();
  },
  methods: {
    ...mapActions(['getFollower', 'getFollowing']),
    clickFollowing() {
      this.$router.push({ name: `mypageFollow` });
    },
    clickFollower() {
      this.$router.push({ name: 'mypageFollow' });
    },
    countFollow() {
      let userId = this.$route.params.no;
      let payload1 = { page: 1, size: 1000, toUserId: userId };
      this.getFollower(payload1);
      let payload2 = { page: 1, size: 1000, fromUserId: userId };
      this.getFollowing(payload2);
    },
    isFollow(userId) {
      for (var i = 0; i < this.$store.state.feed.followList.length; i++) {
        if (this.$store.state.feed.followList[i].userId === userId) {
          return false;
        }
      }
      return true;
    },
    createFollow(toUserId) {
      let accessToken = this.$store.state.auth.accessToken;
      let payload = { toUserId: toUserId };
      createFollow(toUserId, accessToken).then(() => {
        this.setFollowList();
        console.log('팔로우 성공');
      });
    },
    deleteFollow(toUserId) {
      let accessToken = this.$store.state.auth.accessToken;
      deleteFollow(toUserId, accessToken).then(() => {
        this.setFollowList();
        console.log('팔로우 취소');
      });
      this.setFollowList();
    },
  },
};
</script>
<style scoped>
.grayText {
  color: #a9a9a9;
  display: inline-block;
}
.profile {
  text-align: center;
}
.follow {
  padding-left: 0px;
}
.grade {
  top: 2px;
  left: 10px;
  display: inline-block;
}
.divideArea {
  height: 50px;
}
.divide {
  position: absolute;
  left: 0;
  width: 100%;
  padding-top: 20px;
  border-bottom: 10px solid #e5eaef;
}
</style>
