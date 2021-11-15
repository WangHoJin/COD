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
              <v-btn text small fab @click="clickPlus(week[0])">
                <v-icon> mdi-plus </v-icon>
              </v-btn>
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
              <v-btn text small fab @click="clickPlus(week[1])">
                <v-icon> mdi-plus </v-icon>
              </v-btn>
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
              <v-btn text small fab @click="clickPlus(week[2])">
                <v-icon> mdi-plus </v-icon>
              </v-btn>
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
              <v-btn text small fab @click="clickPlus(week[3])">
                <v-icon> mdi-plus </v-icon>
              </v-btn>
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
              <v-btn text small fab @click="clickPlus(week[4])">
                <v-icon> mdi-plus </v-icon>
              </v-btn>
            </v-col>
          </v-row>
        </v-col>
        <!-- 세로 선 -->
        <v-divider :vertical="true" />
        <v-col class="cell">
          <v-row>
            <v-col :class="{ today: isToday[5] }">
              {{ week[5].getDate() }} {{ days[week[5].getDay()] }}
            </v-col>
          </v-row>
          <v-row>
            <v-col class="text-center">
              <v-btn text small fab @click="clickPlus(week[5])">
                <v-icon> mdi-plus </v-icon>
              </v-btn>
            </v-col>
          </v-row>
        </v-col>
        <v-divider :vertical="true" />
        <v-col class="cell">
          <v-row>
            <v-col :class="{ today: isToday[6] }">
              {{ week[6].getDate() }} {{ days[week[6].getDay()] }}
            </v-col>
          </v-row>
          <v-row>
            <v-col class="text-center">
              <v-btn text small fab @click="clickPlus(week[6])">
                <v-icon> mdi-plus </v-icon>
              </v-btn>
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
export default {
  data() {
    return {
      currentDate: new Date(),
      days: ['일', '월', '화', '수', '목', '금', '토'],
      week: [],
      isToday: [],
    };
  },
  created() {
    let day = this.currentDate.getDay();
    for (let i = -1 * (day - 1); i <= 7 - day; i++) {
      this.week.push(this.addDays(this.currentDate, i));
    }
    for (let i = 0; i < 7; i++) {
      if (this.currentDate.getDate() == this.week[i].getDate()) this.isToday.push(true);
      else this.isToday.push(false);
    }
  },
  methods: {
    mvCalendatDetail() {
      this.$router.push({ name: 'record-coid-detail' });
    },
    addDays(date, days) {
      // date는 문자열로 받는다 ex, '2020-10-15'
      var result = new Date(date);
      result.setDate(result.getDate() + days);
      return result;
    },
    clickPlus(date) {
      console.log(date);
      this.$router.push({ name: `record-codi-select` });
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
</style>
