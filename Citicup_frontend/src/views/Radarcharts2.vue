<template>
  <div id="radarChart" ref="radarChart" :style="{ width: '300px', height: '320px' }"></div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  props: {
    data: {
      type: Array,
      required: true,
    },
    indicators: {
      type: Array,
      required: true,
    },
  },
  mounted() {
    this.updateChart();
  },
  watch: {
    data() {
      this.updateChart();
    },
  },
  methods: {
    updateChart() {
      const chart = echarts.init(this.$refs.radarChart);
      const option = {
        title: {
        text: '议题质量性',
        left: 'center', // 可选: 'left', 'right', 'center' 或具体像素值
        top: '230px', // 可选: 'top', 'bottom', 'middle' 或具体像素值
        textStyle: {
          color: '#141', // 标题颜色
          fontSize: 16, // 标题字体大小
          fontWeight: 'bold', // 标题字体粗细
          borderRadius: 3,
        },
      },
        tooltip: {},
        radar: {
          name: {
            textStyle: {
              color: '#fff',
              backgroundColor: '#999',
              borderRadius: 3,
              padding: [3, 5],
            },
          },
          indicator: this.indicators,
        },
        series: [
          {
            name: 'Radar Chart',
            type: 'radar',
            data: this.data,
          },
        ],
      };
      chart.setOption(option);
    },
  },
};
</script>

<style scoped>
/* Add custom CSS here */
.radarChart{
    margin-top: 20px;
}
</style>