!
<template lang="">
  <div class="mt-5">
    <h3 class="mb-2">나의 코디 일기</h3>
    <!-- <v-calendar :weekdays="[1, 2, 3, 4, 5, 6, 0]" type="month"> </v-calendar> -->
    <v-divider class="mt-2" />
    <div class="calendar">
      <!-- 1단 -->
      <v-row>
        <!-- 월요일 -->
        <v-col class="cell">
          <!-- 상단 -->
          <v-row>
            <v-col :class="{ today: isToday[0] }">
              {{ week[0].getDate() }} {{ days[week[0].getDay()] }}
            </v-col>
          </v-row>
          <!-- +버튼 or 이미지 -->
          <v-row>
            <v-col class="text-center">
              <div
                v-if="recordMap.get(dateToString(week[0]))"
                @click="click(week[0])"
                style="text-align: -webkit-center"
              >
                <v-img
                  contain
                  class="img"
                  :src="recordMap.get(dateToString(week[0])).codiDiaryThumbnail"
                />
              </div>
              <div v-else>
                <v-btn text small fab @click="clickPlus(week[0])">
                  <v-icon> mdi-plus </v-icon>
                </v-btn>
              </div>
            </v-col>
          </v-row>
        </v-col>
        <!-- 세로 선 -->
        <v-divider :vertical="true" />
        <!-- 화요일 -->
        <v-col class="cell">
          <!-- 상단 -->
          <v-row>
            <v-col :class="{ today: isToday[1] }">
              {{ week[1].getDate() }} {{ days[week[1].getDay()] }}
            </v-col>
          </v-row>
          <!-- +버튼 or 이미지 -->
          <v-row>
            <v-col class="text-center">
              <div
                v-if="recordMap.get(dateToString(week[1]))"
                @click="click(week[1])"
                style="text-align: -webkit-center"
              >
                <v-img
                  contain
                  class="img"
                  :src="recordMap.get(dateToString(week[1])).codiDiaryThumbnail"
                />
              </div>
              <div v-else>
                <v-btn text small fab @click="clickPlus(week[1])">
                  <v-icon> mdi-plus </v-icon>
                </v-btn>
              </div>
            </v-col>
          </v-row>
        </v-col>
        <!-- 세로 선 -->
        <v-divider :vertical="true" />
        <!-- 수요일 -->
        <v-col class="cell">
          <!-- 상단 -->
          <v-row>
            <v-col :class="{ today: isToday[2] }">
              {{ week[2].getDate() }} {{ days[week[2].getDay()] }}
            </v-col>
          </v-row>
          <!-- +버튼 or 이미지 -->
          <v-row>
            <v-col class="text-center">
              <div
                v-if="recordMap.get(dateToString(week[2]))"
                @click="click(week[2])"
                style="text-align: -webkit-center"
              >
                <v-img
                  contain
                  class="img"
                  :src="recordMap.get(dateToString(week[2])).codiDiaryThumbnail"
                />
              </div>
              <div v-else>
                <v-btn text small fab @click="clickPlus(week[2])">
                  <v-icon> mdi-plus </v-icon>
                </v-btn>
              </div>
            </v-col>
          </v-row>
        </v-col>
        <!-- 세로 선 -->
        <v-divider :vertical="true" />
        <!-- 목요일 -->
        <v-col class="cell">
          <!-- 상단 -->
          <v-row>
            <v-col :class="{ today: isToday[3] }">
              {{ week[3].getDate() }} {{ days[week[3].getDay()] }}
            </v-col>
          </v-row>
          <!-- +버튼 or 이미지 -->
          <v-row>
            <v-col class="text-center">
              <div
                v-if="recordMap.get(dateToString(week[3]))"
                @click="click(week[3])"
                style="text-align: -webkit-center"
              >
                <v-img
                  contain
                  class="img"
                  :src="recordMap.get(dateToString(week[3])).codiDiaryThumbnail"
                />
              </div>
              <div v-else>
                <v-btn text small fab @click="clickPlus(week[3])">
                  <v-icon> mdi-plus </v-icon>
                </v-btn>
              </div>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
      <!-- 2단 구분 선 -->
      <v-divider class="my-3" />
      <!-- 금요일 -->
      <v-row>
        <v-col class="cell">
          <!-- 상단 -->
          <v-row>
            <v-col :class="{ today: isToday[4] }">
              {{ week[4].getDate() }} {{ days[week[4].getDay()] }}
            </v-col>
          </v-row>
          <!-- +버튼 or 이미지 -->
          <v-row>
            <v-col class="text-center">
              <div
                v-if="recordMap.get(dateToString(week[4]))"
                @click="click(week[4])"
                style="text-align: -webkit-center"
              >
                <v-img
                  contain
                  class="img"
                  :src="recordMap.get(dateToString(week[4])).codiDiaryThumbnail"
                />
              </div>
              <div v-else>
                <v-btn text small fab @click="clickPlus(week[4])">
                  <v-icon> mdi-plus </v-icon>
                </v-btn>
              </div>
            </v-col>
          </v-row>
        </v-col>
        <!-- 세로 선 -->
        <v-divider :vertical="true" />
        <!-- 토요일 -->
        <v-col class="cell">
          <v-row>
            <v-col :class="{ today: isToday[5] }">
              {{ week[5].getDate() }} {{ days[week[5].getDay()] }}
            </v-col>
          </v-row>
          <v-row>
            <v-col class="text-center">
              <div
                v-if="recordMap.get(dateToString(week[5]))"
                @click="click(week[5])"
                style="text-align: -webkit-center"
              >
                <v-img
                  contain
                  class="img"
                  :src="recordMap.get(dateToString(week[5])).codiDiaryThumbnail"
                />
              </div>
              <div v-else>
                <v-btn text small fab @click="clickPlus(week[5])">
                  <v-icon> mdi-plus </v-icon>
                </v-btn>
              </div>
            </v-col>
          </v-row>
        </v-col>
        <!-- 세로 선 -->
        <v-divider :vertical="true" />
        <!-- 일요일 -->
        <v-col class="cell">
          <v-row>
            <v-col :class="{ today: isToday[6] }">
              {{ week[6].getDate() }} {{ days[week[6].getDay()] }}
            </v-col>
          </v-row>
          <v-row>
            <v-col class="text-center">
              <div
                v-if="recordMap.get(dateToString(week[6]))"
                @click="click(week[6])"
                style="text-align: -webkit-center"
              >
                <v-img
                  contain
                  class="img"
                  :src="recordMap.get(dateToString(week[6])).codiDiaryThumbnail"
                />
              </div>
              <div v-else>
                <v-btn text small fab @click="clickPlus(week[6])">
                  <v-icon> mdi-plus </v-icon>
                </v-btn>
              </div>
            </v-col>
          </v-row>
        </v-col>
        <v-divider :vertical="true" />
        <v-col class="text-center cell" style="align-self: center">
          <div>
            <v-btn
              @click="mvCalendatDetail"
              text
              fab
              x-large
              style="margin-left: -10px; margin-top: 25px"
            >
              <v-icon> mdi-calendar </v-icon>
            </v-btn>
          </div>
        </v-col>
      </v-row>
    </div>
    <!-- 마감 구분 선 -->
    <v-divider class="mt-1" />
  </div>
</template>
<script>
import { mapGetters } from 'vuex';
import { getDirary, getDiraryList } from '@/api/diaries.js';
export default {
  data() {
    return {
      currentDate: new Date(),
      days: ['일', '월', '화', '수', '목', '금', '토'],
      week: [],
      isToday: [],
      recordMap: new Map(),
    };
  },
  created() {
    // 이번 주 구하기
    let day = this.currentDate.getDay();
    for (let i = -1 * (day - 1); i <= 7 - day; i++) {
      this.week.push(this.addDays(this.currentDate, i));
    }
    for (let i = 0; i < 7; i++) {
      if (this.currentDate.getDate() == this.week[i].getDate()) this.isToday.push(true);
      else this.isToday.push(false);
    }

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
  methods: {
    click(date) {
      let record = this.recordMap.get(this.dateToString(date));
      console.log(record);
      if (record) {
        this.$router.push({ path: `/record-codi/update/${record.codiDiaryId}` });
      }
    },
    mvCalendatDetail() {
      this.$router.push({ name: 'record-codi-detail' });
    },
    addDays(date, days) {
      // date는 문자열로 받는다 ex, '2020-10-15'
      var result = new Date(date);
      result.setDate(result.getDate() + days);
      return result;
    },
    clickPlus(date) {
      console.log(date);
      let str = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
      this.$router.push({ path: `record-codi/select?date=${str}` });
    },
    dateToString(date) {
      return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
    },
  },
};
</script>
<style scoped>
.calendar {
  padding: 10px;
}

.cell {
  padding: 10px;
  width: 20%;
  margin-bottom: 20px;
}

.cell .row .col {
  font-size: 0.78rem;
}
.today {
  color: #857db1;
  font-weight: bold;
}
.img {
  /* width: 70px;
  height: 70px;
  object-fit: cover; */
}
</style>
