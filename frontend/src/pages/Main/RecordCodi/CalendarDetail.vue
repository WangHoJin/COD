<template lang="">
  <div>
    <div class="content">
      <v-row style="height:100%">
        <v-col>
          <v-sheet height="60">
            <v-toolbar flat>
              <v-btn outlined small class="mr-4" color="grey darken-2" @click="setToday">
                Today
              </v-btn>
              <v-btn fab text small color="grey darken-2" @click="prev">
                <v-icon small> mdi-chevron-left </v-icon>
              </v-btn>
              <v-toolbar-title v-if="isLoadingCalendar">
                {{ $refs.calendar.title }}
              </v-toolbar-title>
              <v-btn fab text small color="grey darken-2" @click="next">
                <v-icon small> mdi-chevron-right </v-icon>
              </v-btn>
            </v-toolbar>
          </v-sheet>
          <v-sheet height="75vh">
            <v-calendar 
              @click:date="click"
              height="100%"
              ref="calendar"
              v-model="focus"
              color="rgb(133 125 177 / 80%)"
              type="month"
            >
            <template v-slot:day="{ date }" >
              <template v-if="recordMap.get(date)"  >
                <v-sheet
                  tile
                  style="text-align: -webkit-center;"
               >
                  <v-img @click="click({date:date})" class="mt-2" contain width="100%" height="100%" :src="recordMap.get(date).codiDiaryThumbnail"/>
                </v-sheet>
              </template>
          </template>
            </v-calendar>
          </v-sheet>
        </v-col>
      </v-row>
    </div>
  </div>
</template>
<script>
import { mapGetters } from 'vuex';
import { getDirary, getDiraryList } from '@/api/diaries.js';
export default {
  data() {
    return {
      focus: '',
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
      recordMap: new Map(),
      colors: ['#1867c0', '#fb8c00', '#000000'],
      isLoadingCalendar:false
    };
  },
  created(){
    // 달력 기록 가져오기
    let map = new Map();
    let userId = this.loginUser.userId;
    let accessToken = this.$store.state.auth.accessToken;
    getDiraryList({ userId: userId, page: 1, size: 1000 }, accessToken).then((res) => {
      for (let record of res) map.set(record.codiDiaryDate, record);
      this.recordMap = map;
      console.log(this.recordMap);
    });
  },
  computed: {
    ...mapGetters(['loginUser']),
  },
  mounted() {
    this.$refs.calendar.checkChange();
    this.isLoadingCalendar = true;
  },
  methods: {
    setToday() {
      this.focus = '';
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
    click(dateInfo){
      console.log(dateInfo);
      let record = this.recordMap.get(dateInfo.date);
      if(record){
              this.$router.push({ path: `/record-codi/update/${record.codiDiaryId}` });
      } else{
              this.$router.push({ path: `/record-codi/select?date=${dateInfo.date}` });
      }
    },
    dateToString(date) {
      return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
    },
  },
  components: {
    // Header,
  },
};
</script>
<style scoped>
.content {
  /* padding: 10px 12px; */
  padding: 10px 0px;
}
.img-thumbnail {
  /* width: 10vw; */
  /* height: 100%; */
  object-fit: cover;
}
</style>
