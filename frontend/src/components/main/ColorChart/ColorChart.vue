!
<template lang="">
  <div>
    <h3 class="mt-7">내 옷장 색 비율</h3>
    <v-divider class="mt-2"> </v-divider>
    <div class="chart">
      <DoughnutChart :chartData="chartData" :options="chartOptions" />
    </div>
  </div>
</template>
<script>
import DoughnutChart from './DoughnutChart.js';
export default {
  components: { DoughnutChart },
  data() {
    return {
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
        labels: ['Green', 'Red', 'Blue'],
        datasets: [
          {
            label: 'Data One',
            backgroundColor: ['#41B883', '#E46651', '#00D8FF'],
            data: [1, 10, 5],
            borderWidth: 1,
          },
        ],
      },
    };
  },
  created() {
    let firstColor = '검정';
    this.chartOptions.plugins.doughnutlabel.labels[1].text = firstColor;
  },
};
</script>
<style scoped>
.chart {
  padding: 10px;
}
</style>
