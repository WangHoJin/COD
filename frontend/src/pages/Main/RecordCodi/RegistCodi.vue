<template lang="">
  <div>
    <!-- <Header /> -->
    <div class="content">
      <span class="ma-3 date">{{ date }} {{ days[new Date(date).getDay()] }}</span>
      <div class="box ma-3 pa-2">
        <v-img class="img-thumbnail" contain :src="codiImg" />
      </div>
      <v-textarea class="mx-3 my-5" solo label="메모를 입력해보세요 (최대 200자)" v-model="content">
      </v-textarea>
      <SBtn class="mx-3" :click="registCodiRecord" msg="코디 기록하기" />
    </div>
  </div>
</template>
<script>
// import Header from '@/components/common/BackTitleHeader.vue';
import SBtn from '@/components/common/SquareButton.vue';
import { mapGetters, mapActions } from 'vuex';
import { createDirary, updateDirary, getDirary, getDiraryList } from '@/api/diaries.js';

export default {
  data() {
    return {
      date: this.$route.query.date,
      days: ['일', '월', '화', '수', '목', '금', '토'],
      content: null,
      codiImg: null,
      recordInfo: null,
    };
  },
  created() {
    //기존에 등록한 기록을 수정하는 경우
    let recordId = this.$route.params.id;
    if (recordId) {
      let accessToken = this.$store.state.auth.accessToken;
      getDirary(recordId, accessToken)
        .then((res) => {
          this.recordInfo = res;
          this.content = this.recordInfo.codiDiaryContent;
          this.codiImg = this.recordInfo.codiDiaryThumbnail;
        })
        .catch((error) => console.error(error));
    }
    //셀렉트 코디 한 경우
    else {
      this.codiImg = this.codi.codiThumbnail;
    }
  },
  methods: {
    registCodiRecord() {
      let accessToken = this.$store.state.auth.accessToken;
      // 수정
      if (this.recordInfo) {
        let record = { content: this.content };
        updateDirary(record, this.recordInfo.codiDiaryId, accessToken).then(() => {
          console.log('수정 완료했습니다!');
        });
      }
      // 등록
      else {
        let record = {
          date: this.date,
          thumbnail: this.codiImg,
          content: this.content,
        };
        createDirary(record, accessToken)
          .then((res) => {
            console.log(res);
            alert('등록 완료되었습니다!');
          })
          .catch((error) => {
            console.log(error);
            // console.log(error.response.data);
          });
      }
      this.$router.push({ name: 'main' });
    },
  },
  computed: {
    ...mapGetters(['codi']),
  },
  components: {
    // Header,
    SBtn,
  },
};
</script>
<style scoped>
.content {
  margin-top: 30px;
  padding: 0px 10px;
}
.box {
  height: 50vw !important;
  width: 50vw;
  border-radius: 10%;
  border-color: #857db1;
  border-width: 2px;
  border-style: dashed;
  height: 80vw;
}
.date {
  font-weight: bold;
}
.img-thumbnail {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
