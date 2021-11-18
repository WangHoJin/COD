<template lang="">
  <div>
    <h3 class="mt-7">내 옷장 색 비율</h3>
    <v-divider class="mt-2"> </v-divider>
    <div v-if="loading" class="chart">
      <DoughnutChart :chartData="chartData" :options="chartOptions" />
    </div>
  </div>
</template>
<script>
import DoughnutChart from './DoughnutChart.js';
import { mapGetters, mapActions } from 'vuex';

export default {
  components: { DoughnutChart },
  data() {
    return {
      loading: false,
      chartOptions: {
        // maintainAspectRatio: false,
        hoverBorderWidth: 20,
        cutoutPercentage: 80,
        legend: { display: true, position: 'right', labels: { boxWidth: 12 } },
        plugins: {
          doughnutlabel: {
            labels: [
              {
                text: '1위',
                font: {
                  size: '20',
                  weight: 'bold',
                },
              },
              {
                text: '',
                font: {
                  size: '30',
                  weight: 'bold',
                },
              },
            ],
          },
        },
      },
      chartData: {
        labels: [],
        datasets: [
          {
            label: 'Data One',
            backgroundColor: [],
            data: [],
            borderWidth: 1,
          },
        ],
      },
    };
  },
  created() {
    let colorInfo = {
      빨간색: '#E53935',
      노란색: '#FFD54F',
      주황색: '#FFA726',
      파란색: '#1E88E5',
      보라색: '#7E57C2',
      회색: '#B0BEC5',
      흰색: '#F5F5F5',
      검은색: '#212121',
      분홍색: '#F48FB1',
      초록색: '#689F38',
    };

    let userId = this.$store.state.auth.loginUser.userId;
    let payload = { userId: userId, page: 1, size: 1000 };
    let map = new Map();
    this.getClothesList(payload).then(() => {
      for (let clothes of this.clothesList) {
        if (!map.get(clothes.clothColor)) map.set(clothes.clothColor, 1);
        else map.set(clothes.clothColor, map.get(clothes.clothColor) + 1);
      }
      let maxColor = { color: '', count: 0 };
      for (let data of map) {
        // console.log(data);
        this.chartData.labels.push(data[0]);
        this.chartData.datasets[0].data.push(data[1]);
        this.chartData.datasets[0].backgroundColor.push(colorInfo[data[0]]);
        if (maxColor.count < data[1]) {
          maxColor.color = data[0];
          maxColor.count = data[1];
        }
      }
      this.chartOptions.plugins.doughnutlabel.labels[1].text = maxColor.color;
      this.loading = true;
      console.log(this.chartData);
    });

    let firstColor = '검정';
    this.chartOptions.plugins.doughnutlabel.labels[1].text = firstColor;
  },
  computed: {
    ...mapGetters(['clothesList']),
  },
  methods: {
    ...mapActions(['getClothesList']),
  },
};
</script>
<style scoped>
.chart {
  padding: 10px;
}
</style>
