<template>
  <div>
    <!-- 팔로우 탭 start -->
    <v-tabs fixed-tabs color="#857DB1">
      <v-tab v-for="(tab, i) in tabs" :key="i" @click="clickTab(tab)">{{ tab }}</v-tab>
    </v-tabs>
    <!-- 팔로우 탭 end -->

    <v-container fluid>
      <!-- 팔로워 목록 start -->
      <v-flex v-show="follow" class="followList">
        <v-row v-for="i in 4" :key="i">
          <v-col class="profileImg" cols="auto">
            <v-avatar size="50">
              <img src="@/assets/test/profile.jpg" />
            </v-avatar>
          </v-col>
          <v-col>
            <h4 class="followName">daily_daily</h4>
          </v-col>
          <v-col>
            <h5 v-if="follow" class="follower" @click="clickFollow()">팔로워</h5>
            <h5 v-else-if="!follow" class="following" @click="clickFollow()">팔로잉</h5>
          </v-col>
        </v-row>
      </v-flex>
      <!-- 팔로워 목록 end -->
      <!-- 팔로잉 목록 start -->
      <v-flex v-show="!follow" class="followList">
        <v-row v-for="i in 5" :key="i">
          <v-col class="profileImg" cols="auto">
            <v-avatar size="50">
              <img src="@/assets/test/profile.jpg" />
            </v-avatar>
          </v-col>
          <v-col>
            <h4 class="followName">옷장속 보거트</h4>
          </v-col>
          <v-col>
            <h5 v-if="follow" class="follower" @click="clickFollow()">팔로워</h5>
            <h5 v-else-if="!follow" class="following" @click="clickFollow()">팔로잉</h5>
          </v-col>
        </v-row>
      </v-flex>
      <!-- 팔로잉 목록 end -->
    </v-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      tabs: ["팔로워", "팔로잉"],
      follow: true,
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
.follower {
  color: #857db1;
  text-align: right;
  margin: 16px 0px 13px 13px;
}
.following {
  color: #979797;
  text-align: right;
  margin: 16px 0px 13px 13px;
}
</style>
