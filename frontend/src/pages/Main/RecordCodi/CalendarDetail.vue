<template lang="">
  <div>
    <Header />
    <div class="content">
      <v-row class="fill-height">
        <v-col>
          <v-sheet height="64">
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
          <v-sheet height="600">
            <v-calendar
              ref="calendar"
              v-model="focus"
              color="rgb(133 125 177 / 80%)"
              type="month"
            >
            
            <template v-slot:day="{ date }">
            <v-row
            >
              <template v-if="tracked[date]">
                <v-sheet
                  tile
                  class="ml-3"
                >
                <div class="mt-4">
                  <v-img contain class="img-thumbnail" :src="tracked[date]"/>
                </div>
                </v-sheet>
              </template>
            </v-row>
          </template>
            </v-calendar>
          </v-sheet>
        </v-col>
      </v-row>
    </div>
  </div>
</template>
<script>
import Header from '@/components/common/BackTitleHeader.vue';
export default {
  data() {
    return {
      focus: '',
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
      tracked: {
        '2021-11-10': "https://mblogthumb-phinf.pstatic.net/20160510_32/mkhjsk_14628892954266DY4i_PNG/1.png?type=w2",
        '2021-11-08': "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSY4emgdTEJmNK72ydRTyzds0y4WWx09BdN5fid5oUaDo-kSkhadoMK3k1Dkwnu-HwuPIg&usqp=CAU",
        '2021-11-07': "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUzlBJLjR-RMcFJ4lP-Y-QzvfpXcqh3zrzwYoaMDCDoo0WDXh7M0kMlnkj8tnxasdzRPY&usqp=CAU",
        '2021-11-06': "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkM1WYcuIZYNpaq63j2zJLPVVOJM5z6c6TpVLhpKXUHEU5ypHaveekmWZtqpbdwwT0R94&usqp=CAU",
      },
      colors: ['#1867c0', '#fb8c00', '#000000'],
      isLoadingCalendar:false
    };
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
  },
  components: {
    Header,
  },
};
</script>
<style scoped>
.content {
  margin: 10px 5px;
}
.img-thumbnail {
  /* width: 20%; */
  height: 50%;
  object-fit: cover;
}
</style>
