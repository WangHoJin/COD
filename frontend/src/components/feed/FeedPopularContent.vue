<template>
  <v-container style="padding: 0">
    <div v-for="codi in codiList" :key="codi.codiId">
      <v-flex style="padding: 12px 12px 8px 12px">
        <div class="profileInfo">
          <v-row>
            <v-col style="padding-right: 0">
              <v-img class="profileImg" src="../../assets/logo/login.png"> </v-img>
            </v-col>
            <v-col style="padding: 20px 0px 18px 12px" cols="auto">
              <h4>{{ codi.userId }}</h4>
            </v-col>
            <v-col style="padding: 23px 0px 18px 12px">
              <h5 style="color: #857db1">팔로우</h5>
            </v-col>
          </v-row>
        </div>
      </v-flex>
      <v-flex>
        <div>
          <v-img src="../../assets/img/feedsample.png"></v-img>
        </div>
      </v-flex>
      <v-flex style="padding: 12px">
        <v-row>
          <!-- 좋아요 -->
          <v-col cols="auto" sm="12" md="12" lg="12" style="padding-right: 0"
            ><v-icon color="#CCBEE3">mdi-heart</v-icon>
            <h5 class="purpleText">{{ codi.liked }}</h5>
          </v-col>
          <!-- 댓글 -->
          <v-col cols="auto" sm="12" md="12" lg="12">
            <v-icon color="#CCBEE3">mdi-comment</v-icon>
            <h5 class="purpleText">{{ codi.comment }}</h5>
          </v-col>
          <!-- 공백 -->
          <v-spacer></v-spacer>
          <!-- 등록일 -->
          <v-col class="createAt" cols="7" sm="12" md="12" lg="12" align="right">
            <v-icon class="grayText">mdi-bookmark-outline</v-icon>
            <h5 class="grayText"></h5
          ></v-col>
        </v-row>
      </v-flex>
      <v-flex style="padding: 0 12px 0 12px">
        <v-row>
          <v-col cols="auto" style="padding-bottom: 0">
            <h5 style="font-weight: bold">{{ codi.userId }}</h5>
          </v-col>
          <v-col style="padding-left: 0; padding-bottom: 0">
            <h5>{{ codi.codiDescription }}</h5>
          </v-col>
        </v-row>
        <v-row>
          <v-col style="padding-top: 0px">
            <h5 class="tag">#데일리룩</h5>
            <h5 class="tag">#내일뭐입지</h5>
          </v-col>
        </v-row>
      </v-flex>
      <feed-comment v-bind:codi="codi" />
    </div>
  </v-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import FeedComment from "./FeedComment.vue";
export default {
  props: ["codi"],
  components: { FeedComment },
  data() {
    return {
      codiList: [],
    };
  },
  computed: {
    ...mapGetters(["popularCodies"]),
  },
  watch: {
    codies: function () {
      console.log("watch");
      // this.selectCodies();
    },
  },
  created() {
    this.setPopularCodies();
  },
  methods: {
    ...mapActions(["getPopularCodies"]),
    setPopularCodies() {
      let payload = { startDate: "2021-10-23", endDate: "2021-11-15", page: 1, size: 10 };
      this.getPopularCodies(payload);
      this.codiList = this.popularCodies;
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
  padding-right: 5px;
}
</style>
