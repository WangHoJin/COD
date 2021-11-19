<template>
  <v-container style="padding: 0 0 70px 0">
    <div v-if="loading" style="width: 100%; text-align: center; margin-top: 30%">
      <!-- {{ loading }} -->
      <v-progress-circular
        :width="4"
        color="rgb(213 217 221)"
        indeterminate
        :size="60"
      ></v-progress-circular>
    </div>
    <div v-else-if="!codiList || codiList.length == 0" class="mt-16 pt-10 text-center">
      <v-icon x-large class="mt-16 mb-3">mdi-package-variant</v-icon>
      <h4>아직 아무도 코디를 올리지 않았어요 😥</h4>
    </div>
    <div v-else v-cloak>
      <div v-for="codi in codiList" :key="codi.codiId">
        <v-flex style="padding: 12px 12px 8px 12px">
          <div class="profileInfo">
            <v-row>
              <v-col cols="auto" style="padding-right: 0">
                <v-img
                  v-if="!codi.userProfileImg"
                  @click="userClick(codi.userId)"
                  class="profileImg"
                  src="@/assets/logo/login.png"
                />
                <v-img
                  v-else
                  @click="userClick(codi.userId)"
                  class="profileImg"
                  :src="codi.userProfileImg"
                >
                  <template v-slot:placeholder>
                    <v-row class="fill-height ma-0" align="center" justify="center">
                      <v-progress-circular
                        indeterminate
                        color="grey lighten-5"
                      ></v-progress-circular>
                    </v-row>
                  </template>
                </v-img>
              </v-col>
              <v-col cols="auto" style="padding: 20px 0px 18px 12px">
                <h4 @click="userClick(codi.userId)">{{ codi.userNickname }}</h4>
              </v-col>
              <v-col style="padding: 23px 0px 18px 6px; width: 45px">
                <h5
                  v-if="isFollow(codi.userId)"
                  @click="createFollow(codi.userId)"
                  style="color: #857db1"
                >
                  팔로우
                </h5>
                <h5
                  v-if="!isFollow(codi.userId)"
                  @click="deleteFollow(codi.userId)"
                  style="color: #9e9e9e"
                >
                  팔로잉
                </h5>
              </v-col>
            </v-row>
          </div>
        </v-flex>
        <v-flex>
          <div>
            <v-img :src="codi.codiThumbnail" style="background-color: rgb(133 125 177 / 35%)" />
          </div>
        </v-flex>
        <v-flex style="padding: 12px">
          <v-row>
            <!-- 좋아요 -->
            <v-col cols="auto" sm="12" md="12" lg="12" style="padding-right: 0"
              ><v-icon
                v-if="isLiked(codi.codiId)"
                @click="deleteCodiLiked(codi.codiId)"
                color="#CCBEE3"
                >mdi-heart</v-icon
              >
              <v-icon
                v-if="!isLiked(codi.codiId)"
                @click="createCodiLiked(codi.codiId)"
                color="#CCBEE3"
                >mdi-heart-outline</v-icon
              >
              <h5 class="purpleText">{{ codi.liked }}</h5>
            </v-col>
            <!-- 댓글 -->
            <v-col cols="auto" sm="12" md="12" lg="12">
              <v-icon @click="comment(codi.codiId)" color="#CCBEE3">mdi-comment</v-icon>
              <h5 class="purpleText">{{ codi.comment }}</h5>
            </v-col>
            <!-- 공백 -->
            <v-spacer></v-spacer>
            <!-- 등록일 -->
            <v-col class="createAt" cols="7" sm="12" md="12" lg="12" align="right">
              <h5 class="grayText"></h5
            ></v-col>
          </v-row>
        </v-flex>
        <v-flex style="padding: 0 12px 0 12px">
          <v-row>
            <v-col cols="auto" style="padding-bottom: 0">
              <h5 style="font-weight: bold">{{ codi.userNickname }}</h5>
            </v-col>
            <v-col style="padding-left: 0; padding-bottom: 0">
              <h5>{{ codi.codiDescription }}</h5>
            </v-col>
          </v-row>
          <v-row>
            <v-col
              cols="auto"
              class="mt-1 mb-1"
              v-for="(tag, index) in splitTag(codi.codiTag)"
              :key="tag"
              style="padding: 0 0 12px 5px"
            >
              <h5 v-if="index >= 1" class="tag">#{{ tag }}</h5>
              <h5 v-if="index < 1" style="padding-left: 7px" class="tag">#{{ tag }}</h5>
            </v-col>
          </v-row>
        </v-flex>
        <!-- <feed-comment v-bind:codi="codi" /> -->
      </div>
    </div>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import { createFollow, deleteFollow } from '@/api/follow';
import { createCodiLiked, deleteCodiLiked } from '@/api/codiLiked';
export default {
  props: ['codi'],
  data() {
    return {
      loading: true,
      codiList: [],
      followingList: [],
      likedList: [],
    };
  },
  computed: {
    ...mapGetters(['popularCodies', 'followList', 'codiLikedList']),
  },
  watch: {
    followList: function () {
      console.log('watch');
      console.log(this.followList);
      this.followingList = this.followList;
      this.setFollowingCodies();
    },
    codiLikedList: function () {
      this.likedList = this.codiLikedList;
      this.setPopularCodies();
      this.setFollowingCodies();
    },
  },
  created() {
    // setTimeout(function () {
    //   this.loading = false;
    //   console.log(this.loading);
    // }, 1000);
    this.setPopularCodies();
    this.setFollowList();
    this.setCodiLikedList();
  },

  methods: {
    ...mapActions(['getPopularCodies', 'getFollows', 'getFollowingCodies', 'getCodiLiked']),
    setPopularCodies() {
      let payload = { startDate: '1900-01-01', endDate: '2100-12-31', page: 1, size: 1000 };
      this.getPopularCodies(payload).then(() => {
        this.codiList = this.popularCodies;
        this.loading = false;
        console.log('@@@@@@@@@@@@@popularCodies', this.popularCodies);
      });
    },
    splitTag(text) {
      return text.split(',');
    },
    setFollowList() {
      let payload = {
        fromUserId: this.$store.state.auth.loginUser.userId,
        toUserId: '',
        page: 1,
        size: 10,
      };
      this.getFollows(payload);
      // this.followingList = this.followList;
      // this.$store.dispatch("getFollows", payload).then(() => {
      // });
      // this.followingList = this.followList.map((a) => a.userId);
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
    setFollowingCodies() {
      let accessToken = this.$store.state.auth.accessToken;
      let payload = { page: 1, size: 10, accessToken: accessToken };
      this.getFollowingCodies(payload);
    },
    isLiked(codiId) {
      for (var i = 0; i < this.$store.state.feed.codiLikedList.length; i++) {
        if (this.$store.state.feed.codiLikedList[i].codiId === codiId) {
          return true;
        }
      }
      return false;
    },
    setCodiLikedList() {
      let accessToken = this.$store.state.auth.accessToken;
      let payload = { page: 1, size: 10, accessToken: accessToken };
      this.getCodiLiked(payload);
      this.likedList = this.codiLikedList.map((a) => a.codiId);
    },
    createCodiLiked(codiId) {
      let accessToken = this.$store.state.auth.accessToken;
      let payload = { codiId: codiId };
      createCodiLiked(payload, accessToken).then(() => {
        this.setCodiLikedList();
        this.setPopularCodies();
        console.log('좋아요 성공');
      });
    },
    deleteCodiLiked(codiId) {
      let accessToken = this.$store.state.auth.accessToken;
      deleteCodiLiked(codiId, accessToken).then(() => {
        this.setCodiLikedList();
        this.setPopularCodies();
        console.log('좋아요 취소');
      });
      this.setCodiLikedList();
    },
    comment(codiId) {
      this.$router.push({
        path: 'comment/' + codiId,
      });
    },
    userClick(userId) {
      this.$router.push({
        path: '../mypage/' + userId,
      });
    },
  },
};
</script>

<style scoped>
.profileImg {
  border-style: solid;
  border-color: #857db1;
  border-radius: 50px;
  max-height: 45px;
  max-width: 45px;
}
.profileInfo {
  display: inline-block;
}
.col {
  vertical-align: middle;
}

.createAt {
  margin: auto;
}

.grayText {
  color: #a9a9a9;
  display: inline;
}
.purpleText {
  color: #c4bddd;
  display: inline;
  padding-left: 5px;
  /* font-family: BMHANNAAir !important; */
}
.tag {
  color: #c4bddd;
  display: inline;
  /* padding-right: 5px; */
}
[v-cloak]::before {
  content: '로딩중...';
}
[v-cloak] > * {
  display: none;
}
</style>
