<template lang="">
  <v-item-group class="imgList">
    <!-- 코디 리스트 start -->
    <v-container>
      <v-row>
        <v-col v-for="codi in codies" :key="codi.codiId" cols="4" sm="3" md="2" lg="1">
          <!-- <v-col v-for="no in 20" :key="no" cols="4" sm="3" md="2" lg="1"> -->
          <v-card class="codiImg">
            <v-img
              height="100px"
              :src="codi.codiThumbnail"
              oneerror="https://picsum.photos/id/11/500/300"
              @click="codiClick(codi.codiId)"
            ></v-img>
            <!-- {{ codi.codiThumbnail }} -->
          </v-card>
        </v-col>
        <!-- <infinite-loading @infinite="infiniteHandler" spinner="waveDots"></infinite-loading> -->
      </v-row>
    </v-container>
    <!-- 코디 리스트 end -->

    <!-- 코디 페이지 start -->
    <v-pagination id="listPage" v-model="page" :length="4" circle></v-pagination>
    <!-- 코디 페이지 end -->

    <!-- 코디 추가 버튼 start -->
    <v-btn id="createBtn" color="#857DB1" dark absolute top right fab @click="addClick()">
      <v-icon>mdi-plus</v-icon>
    </v-btn>
    <!-- 코디 추가 버튼 end -->
  </v-item-group>
</template>
<script>
// import InfiniteLoading from "vue-infinite-loading";
import { mapGetters, mapActions } from 'vuex';
export default {
  data() {
    return {
      limit: 0,
      page: 1,
    };
  },
  components: {
    // InfiniteLoading,
  },
  computed: {
    ...mapGetters(['codies']),
  },
  created() {
    this.selectCodies();
  },
  methods: {
    ...mapActions(['getCodies']),
    selectCodies() {
      let userId = this.$store.state.auth.loginUser.userId;
      let payload = { userId: userId, page: 1, size: 10 };
      this.getCodies(payload);
    },
    infiniteHandler($state) {
      let payload = { userId: 2, page: 1, size: 2 };
      this.getCodies(payload).then((result) => {
        if (result) {
          $state.loaded();
        } else {
          $state.complete();
        }
      });
    },
    codiClick(codiId) {
      console.log('코디 클릭' + codiId);
      this.$router.push({
        path: `detail/` + codiId,
      });
    },
    addClick() {
      this.$router.push({
        path: `create/`,
      });
    },
  },
};
</script>
<style scoped>
.codiImg {
  margin: auto;
  border: 2px solid #a9a9a9;
  width: 100%;
  height: 100%;
}
.imgList {
  height: 710px;
  overflow: scroll;
}
#createBtn {
  position: fixed;
  width: 52px;
  height: 52px;
  left: 80%;
  top: 77%;
}
#listPage {
  position: absolute;
  left: 13%;
  top: 94%;
}
</style>
