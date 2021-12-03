import { Doughnut, mixins } from 'vue-chartjs';
import chartjsPluginDoughnutlabel from 'chartjs-plugin-doughnutlabel';
export default {
  extends: Doughnut,
  mixins: [mixins.reactiveProp],
  props: ['chartData', 'options'],
  mounted() {
    this.renderChart(this.chartData, this.options);
  },
};
