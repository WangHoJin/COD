<template>
  <div>
    코디목록페이지
    <CodiList />
    <v-btn id="createBtn" v-show="!hidden" color="#857DB1" dark absolute top right fab>
      <v-icon>mdi-plus</v-icon>
    </v-btn>
    <v-pagination id="listPage" v-model="page" :length="4" circle></v-pagination>
  </div>
</template>
<script>
import CodiList from "../../components/codi/CodiList.vue";
export default {
  components: {
    CodiList,
  },
  data() {
    return {
      page: 1,
    };
  },
};
</script>
<style scoped>
#createBtn {
  position: fixed;
  width: 52px;
  height: 52px;
  left: 80%;
  top: 78%;
}
#listPage {
  position: absolute;
  left: 13%;
  top: 90%;
}
</style>
