<template>
  <div class="bread">
    <!-- 放置分级面包屑 -->
    <!-- 需要公司名称的数据-->
    <!-- 公司名、symbol、行业、esg总评、股价 -->
    <el-breadcrumb :separator-icon="ArrowRight">
      <el-breadcrumb-item :to="{ path: '/' }">
        <p style="color:black;width:100%;">Home</p>
      </el-breadcrumb-item>
      <el-breadcrumb-item>
        <p style="color:black;width:100%;">花旗公司</p>
      </el-breadcrumb-item>
    </el-breadcrumb>
    <span>
      <h1>{{ companyname }}:{{ symbol }} {{ industry }} </h1>
      <h2>公司股价：{{ stockPrice }} 总ESG:{{ esgwhole }}</h2>
    </span>
    <el-divider />
  </div>
  <!--卡片视图区域-->
  <el-card class="box-card">
    <div>
      <h4 style="color:#00a19b;bottom: 10%;width:100%;">
        基本面数据
      </h4>
      <el-table :data="basedata" border style="width: 100%,background-color: rgba(60, 141, 188, 0.5);">
        <el-table-column prop="information" label="" width="180" />
        <el-table-column prop="equity" label="股东权益" width="180" />
        <el-table-column prop="netprofit" label="净利润" width="180" />
        <el-table-column prop="income" label="总营业收入" />
        <el-table-column prop="ying" label="市盈率" />
        <el-table-column prop="jing" label="市净率" />
        <el-table-column prop="xiao" label="市销率" />
        <el-table-column prop="xian" label="市现率" />
        <el-table-column prop="retu" label="净资产收益率" />
        <el-table-column prop="yield" label="股息率" />
      </el-table>
    </div>
  </el-card>
  <!--卡片视图区域-->


  <el-row>
    <el-col :span="12">
      <el-card class="box-card22" style="height: 400px">
        <div class="grid-content ep-bg-purple" v-if="!loading">
          <h4 style="color:#7183dd;bottom: 100%;width:100%;">ESG三维雷达图</h4>

          <div class="radar-charts-container">
            <RadarChart v-if="chartData.length && chartIndicators.length" ref="radarChartRef" :data="chartData"
              :indicators="chartIndicators" />
            <RadarChart2 v-if="chartData2.length && chartIndicators2.length" ref="radarChartRef2" :data="chartData2"
              :indicators="chartIndicators2" />
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="12">
      <el-card class="box-card23" style="height: 400px;">
        <div class="grid-content ep-bg-purple-light">
          <h4 style="color:#7183dd;bottom: 100%;height:100%;width:100%;">ESG评级表</h4>
          <div class="evaluate">
            <el-table :data="ESGtable" border style="width: 100%">
              <el-table-column prop="project" label="项目" width="180" />

              <el-table-column prop="E" label="E" width="180" />
              <el-table-column prop="S" label="S" width="180" />
              <el-table-column prop="G" label="G" />
            </el-table>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>

  <el-row style="padding-bottom:2%">
    <el-col :span="12">
      <el-card class="box-card31" style="height: 300px;">
        <span class="grid-content ep-bg-purple-light">
          <h4 style="color:#6da4cc;bottom: 10%;height:100%;width:100%;">行业关键字</h4>
        </span>
        <div class="buttonchange">
          <el-button class="btn_anniu" @click="change(0)" :class="{ button: 0 === number }" size="large" color="#96caf4" plain
            round>{{ keyword1 }}</el-button>
          <el-button class="btn_anniu" @click="change(1)" :class="{ button: 1 === number }" size="large" color="#96caf4" plain
            round>{{ keyword2 }}</el-button>
          <el-button class="btn_anniu" @click="change(2)" :class="{ button: 2 === number }" size="large" color="#96caf4" plain
            round>{{ keyword3 }}</el-button>
          <div class="right3">
            <div v-show='0 === number'>
              <p>{{ text1 }}</p>
            </div>
            <div v-show='1 === number'>
              <p>{{ text2 }}</p>
            </div>
            <div v-show='2 === number'>
              <p>{{ text3 }}</p>
            </div>
          </div>
        </div>
      </el-card>
    </el-col>

    <el-col :span="12">
      <el-card class="box-card33" style="height: 300px;">
        <div class="grid-content ep-bg-purple-light">
          <h4 style="color:#6da4cc;bottom: 10%;height:100%;width:100%;">词云图</h4>
        </div>
        <section class="cloud-bed">
          <div class="cloud-box">
            <span v-for="(item, index) in dataList" :key="index" @click="getDataInfo(item)"
              :style="{ color: item.color, background: item.bgColor }">
              <div class="item">
                {{ item.name }}
              </div>
            </span>
          </div>
        </section>
      </el-card>
    </el-col>
  </el-row>
    <!-- 底栏 -->
    <div style="position: static;background-color:#02040d;height: 75px;position: relative;">
      <div style="color:white;display: flex;align-items: center;justify-content:center;height: 100%;">
          <p>2022-2023第十八届“花旗杯”金融创新应用大赛参赛作品</p>
      </div>
  </div>
</template>



<script>
import * as echarts from 'echarts'
import axios from 'axios'
import wordcloud from 'echarts-wordcloud';
import RadarChart from './Radarcharts.vue';
import RadarChart2 from './Radarcharts2.vue';



// import RadarChart from '../components/RadarChart'
import { forEach } from 'lodash';
export default {
  components: { RadarChart, RadarChart2 },
  name: "word-cloud",
  data() {
    return {
      chartData: [
        {
          value: [],
          name: '议题广泛性',
        },
      ],
      chartIndicators: [
        { name: 'E', max: 1 },
        { name: 'S', max: 1 },
        { name: 'G', max: 1 },
      ],
      chartData2: [
        {
          value: [],
          name: '议题质量性',
        },
      ],
      chartIndicators2: [
        { name: 'E', max: 1 },
        { name: 'S', max: 1 },
        { name: 'G', max: 1 },
      ],
      radarChartRef: null, // 添加这一行
      radarChartRef2: null, // 添加这一行
      number: 0,
      esgdata: [],
      companyname: '',
      symbol: '',
      industry: '',
      esgwhole: '',
      stockPrice: '',
      keyword1: '',
      keyword2: '',
      keyword3: '',
      text1: '',
      text2: '',
      text3: '',
      basedata: [
        {
          information: '',
          equity: '',
          netprofit: '',
          income: '',
          ying: '',
          jing: '',
          xiao: '',
          xian: '',
          retu: '',
          yield: '',
        }, {
          information: '行业排名',
          equity: '',
          netprofit: '',
          income: '',
          ying: '',
          jing: '',
          xiao: '',
          xian: '',
          retu: '',
          yield: '',
        }
      ],
      ESGtable: [
        {
          project: '参考性得分',
          E: '',
          S: '',
          G: ''

        },
        {
          project: '议题广泛性',
          E: '',
          S: '',
          G: ''
        },
        {
          project: '议题质量性',
          E: '',
          S: '',
          G: ''

        }
      ],
      ShabitList: {
        textName: [{ text: '平均线' }, { text: '得分' }],
        content: [
          { uValue: 8.2, avgValue: 7.6, name: 'E' },
          { uValue: 5.6, avgValue: 7.4, name: 'S' },
          { uValue: 6.4, avgValue: 8.0, name: 'G' },

        ]
      },
      timer: 50, // 球体转动速率
      radius: 0, // 词云球体面积大小
      dtr: Math.PI / 180, //鼠标滑过球体转动速度
      active: false, // 默认加载是否开启转动
      lasta: 0, // 上下转动
      lastb: 0.5, // 左右转动
      distr: true,
      tspeed: 0, // 鼠标移动上去时球体转动
      mouseX: 0,
      mouseY: 0,
      tagAttrList: [],
      tagContent: null,
      cloudContent: null,
      sinA: '',
      cosA: '',
      sinB: '',
      cosB: '',
      sinC: '',
      cosC: '',
      dataList: [
        {
          name: '',
          value: '10',
          bgColor: 'rgb(57, 193, 207,0.12)',
          color: '#39c1cf',
        },
        {
          name: '',
          value: '4',
          bgColor: 'rgb(66, 105, 245,0.12)',
          color: '#4269f5',
        },
        {
          name: '',
          value: '2',
          bgColor: 'rgb(184, 107, 215,0.12)',
          color: '#b86bd7',
        },
        {
          name: '',
          value: '1',
          bgColor: 'rgb(243, 84, 83,0.12)',
          color: '#f35453',
        },
        {
          name: '',
          value: '',
          bgColor: 'rgb(250, 116, 20,0.12)',
          color: '#FA7414',
        },
        {
          name: '',
          value: '10',
          bgColor: 'rgb(255, 171, 30,0.12)',
          color: '#FFAB1E',
        },
        {
          name: '',
          value: '',
          bgColor: 'rgb(136, 104, 217,0.12)',
          color: '#8868D9',
        },
        {
          name: '',
          value: '',
          bgColor: 'rgb(42, 184, 230,0.12)',
          color: '#2AB8E6',
        },
        {
          name: '',
          value: '',
          bgColor: 'rgb(117, 133, 162,0.12)',
          color: '#7585A2',
        },
        {
          name: '',
          value: '',
          bgColor: 'rgb(117, 133, 162,0.12)',
          color: '#7585A2',
        },
        {
          name: '',
          value: '',
          bgColor: 'rgb(117, 133, 162,0.12)',
          color: '#7585A2',
        },
        {
          name: '',
          value: '',
          bgColor: 'rgb(57, 193, 207,0.12)',
          color: '#39c1cf',
        },
        {
          name: '',
          value: '',
          bgColor: 'rgb(66, 105, 245,0.12)',
          color: '#4269f5',
        },
        {
          name: '',
          value: '',
          bgColor: 'rgb(184, 107, 215,0.12)',
          color: '#b86bd7',
        },
        {
          name: '',
          value: '',
          bgColor: 'rgb(243, 84, 83,0.12)',
          color: '#f35453',
        },

        {
          name: '',
          value: '',
          bgColor: 'rgb(117, 133, 162,0.12)',
          color: '#7585A2',
        },
        {
          name: '',
          value: '',
          bgColor: 'rgb(117, 133, 162,0.12)',
          color: '#7585A2',
        },
      ],
      loading: true

    }
  },

  mounted() {
    this.fetchChartData(),
      this.$nextTick(() => {
        this.radius = document.querySelector('.cloud-box').offsetWidth / 2
        this.initWordCloud()
      }),

      this.$data.symbol = this.$route.query.symbol,
      axios({
        method: 'post',
        url: '/company/getEsg',
        params: {
          symbol: this.symbol,
        }
      }).then((res) => {
        this.$data.esgdata = res.data,
          this.$data.ESGtable[0].E = res.data.esg,
          this.$data.ESGtable[0].S = res.data.esg,
          this.$data.ESGtable[0].G = res.data.esg,
          this.$data.ESGtable[1].E = res.data.env,
          this.$data.ESGtable[1].S = res.data.soc,
          this.$data.ESGtable[1].G = res.data.gov,

          this.$data.ShabitList.content[0].uValue = res.data.e_score,
          this.$data.ShabitList.content[1].uValue == res.data.s_score,
          this.$data.ShabitList.content[2].uValue == res.data.g_score,
          this.$data.ESGtable[2].E = res.data.e_score,
          this.$data.ESGtable[2].S = res.data.s_score,
          this.$data.ESGtable[2].G = res.data.g_score,
          this.$data.chartData[0].value.push(Number(res.data.env));
        this.$data.chartData[0].value.push(Number(res.data.soc));
        this.$data.chartData[0].value.push(Number(res.data.gov));
        this.$data.chartData2[0].value.push(Number(res.data.e_score));
        this.$data.chartData2[0].value.push(Number(res.data.s_score));
        this.$data.chartData2[0].value.push(Number(res.data.g_score));
        this.loading = false;

        console.log(this.$data.chartData2[0].value)

        console.log(res)

      }, error => {
        console.log('错误', error.message)
      }),

      axios({
        method: 'post',
        url: '/company/getEsgBySymbol',
        params: {
          symbol: this.symbol,
        }
      }).then((res) => {
        this.$data.esgwhole = res.data,
          console.log(res)
      }, error => {
        console.log('错误', error.message)
      }),



      axios({
        method: 'post',
        url: '/words-score/getWordFre',
        params: {
          symbol: this.symbol,
        }
      }).then((res) => {
        console.log(res),
          console.log(res.data[0][0]);
        for (var i = 0; i < 41; i++) {
          this.$data.dataList[i].name = res.data[0][i];
          this.$data.dataList[i].value = res.data[1][i] / 100;
        }
      }, error => {
        console.log('错误', error.message)
      }),
      axios({
        method: 'post',
        url: '/fundamentals/getBySymbol',
        params: {
          symbol: this.symbol,
        }
      }).then((res) => {
        this.$data.companyname = res.data.name,
          this.$data.stockPrice = res.data.stockPrice,
          this.$data.basedata[0].information = res.data.name,
          console.log(res)
      }, error => {
        console.log('错误', error.message)
      }), axios({
        method: 'post',
        url: '/keyword/getKeyAndContent',
        params: {
          symbol: this.symbol,
        }
      }).then((res) => {
        this.$data.industry = res.data.keyword.industry,
          this.$data.keyword1 = res.data.keyword.keyword1,
          this.$data.keyword2 = res.data.keyword.keyword2,
          this.$data.keyword3 = res.data.keyword.keyword3,
          this.$data.text1 = res.data.strategy.text1,
          this.$data.text2 = res.data.strategy.text2,
          this.$data.text3 = res.data.strategy.text3,
          console.log(res)

      }, error => {
        console.log('错误', error.message)
      }),

      axios({
        method: 'get',
        url: `/fundamentals_rank/info/${this.symbol}`,
        params: {
          symbol: this.symbol,
        }
      }).then((res) => {
        this.$data.basedata[0].equity = res.data.fundamentals.equity,
          this.$data.basedata[0].netprofit = res.data.fundamentals.netprofit,
          this.$data.basedata[0].income = res.data.fundamentals.income,
          this.$data.basedata[0].ying = res.data.fundamentals.ying,
          this.$data.basedata[0].jing = res.data.fundamentals.jing,
          this.$data.basedata[0].xiao = res.data.fundamentals.xiao,
          this.$data.basedata[0].xian = res.data.fundamentals.xian,
          this.$data.basedata[0].retu = res.data.fundamentals.retu,
          this.$data.basedata[0].yield = res.data.fundamentals.yield,
          this.$data.basedata[1].equity = res.data.fundamentalsRank.equity,
          this.$data.basedata[1].netprofit = res.data.fundamentalsRank.netprofit,
          this.$data.basedata[1].income = res.data.fundamentalsRank.income,
          this.$data.basedata[1].ying = res.data.fundamentalsRank.ying,
          this.$data.basedata[1].jing = res.data.fundamentalsRank.jing,
          this.$data.basedata[1].xiao = res.data.fundamentalsRank.xiao,
          this.$data.basedata[1].xian = res.data.fundamentalsRank.xian,
          this.$data.basedata[1].retu = res.data.fundamentalsRank.retu,
          this.$data.basedata[1].yield = res.data.fundamentalsRank.yield,

          console.log(res)
      }, error => {
        console.log('错误', error.message)
      })

  },
  beforeDestroy() {
    document.querySelector('body').removeAttribute('style'),
      clearInterval(this.timer)
  },

  methods: {
    change: function (index) {
      this.number = index; //重要处
    },

    getDataInfo(item) {
      console.log(item, 'item')
    },
    async fetchChartData() {
      axios.post('/company/getEsg'), {
        symbol: this.symbol,
      }
        .then(response => {
          this.$data.chartData[0].value.push(Number(res.data.env));
          this.$data.chartData[0].value.push(Number(res.data.soc));
          this.$data.chartData[0].value.push(Number(res.data.gov));
          this.$data.chartData2[0].value.push(Number(res.data.e_score));
          this.$data.chartData2[0].value.push(Number(res.data.s_score));
          this.$data.chartData2[0].value.push(Number(res.data.g_score));

        })
        .catch(error => {
          console.log(error)
        })
    },

    initWordCloud() {
      this.cloudContent = document.querySelector('.cloud-box');
      this.tagContent = this.cloudContent.getElementsByTagName('span');
      for (let i = 0; i < this.tagContent.length; i++) {
        let tagObj = {};
        tagObj.offsetWidth = this.tagContent[i].offsetWidth;
        tagObj.offsetHeight = this.tagContent[i].offsetHeight;
        this.tagAttrList.push(tagObj);
      }
      this.sineCosine(0, 0, 0);
      this.positionAll();
      this.cloudContent.onmouseover = () => {
        this.active = true;
      };
      this.cloudContent.onmouseout = () => {
        this.active = false;
      };
      this.cloudContent.onmousemove = (ev) => {
        let oEvent = window.event || ev;
        this.mouseX = oEvent.clientX - (this.cloudContent.offsetLeft + this.cloudContent.offsetWidth / 2);
        this.mouseY = oEvent.clientY - (this.cloudContent.offsetTop + this.cloudContent.offsetHeight / 2);
        this.mouseX /= 5;
        this.mouseY /= 5;
      };
      setInterval(this.update, this.timer);
    },
    positionAll() {
      let phi = 0;
      let theta = 0;
      let max = this.tagAttrList.length;
      let aTmp = [];
      let oFragment = document.createDocumentFragment();
      //随机排序
      for (let i = 0; i < this.tagContent.length; i++) {
        aTmp.push(this.tagContent[i]);
      }
      aTmp.sort(() => {
        return Math.random() < 0.5 ? 1 : -1;
      });
      for (let i = 0; i < aTmp.length; i++) {
        oFragment.appendChild(aTmp[i]);
      }
      this.cloudContent.appendChild(oFragment);
      for (let i = 1; i < max + 1; i++) {
        if (this.distr) {
          phi = Math.acos(-1 + (2 * i - 1) / max);
          theta = Math.sqrt(max * Math.PI) * phi;
        } else {
          phi = Math.random() * (Math.PI);
          theta = Math.random() * (2 * Math.PI);
        }
        //坐标变换
        this.tagAttrList[i - 1].cx = this.radius * Math.cos(theta) * Math.sin(phi);
        this.tagAttrList[i - 1].cy = this.radius * Math.sin(theta) * Math.sin(phi);
        this.tagAttrList[i - 1].cz = this.radius * Math.cos(phi);
        this.tagContent[i - 1].style.left = this.tagAttrList[i - 1].cx + this.cloudContent.offsetWidth / 2 - this.tagAttrList[i - 1].offsetWidth / 2 + 'px';
        this.tagContent[i - 1].style.top = this.tagAttrList[i - 1].cy + this.cloudContent.offsetHeight / 2 - this.tagAttrList[i - 1].offsetHeight / 2 + 'px';
      }
    },
    update() {
      let angleBasicA;
      let angleBasicB;

      if (this.active) {
        angleBasicA = (-Math.min(Math.max(-this.mouseY, -200), 200) / this.radius) * this.tspeed;
        angleBasicB = (Math.min(Math.max(-this.mouseX, -200), 200) / this.radius) * this.tspeed;
      } else {
        angleBasicA = this.lasta * 0.98;
        angleBasicB = this.lastb * 0.98;
      }

      //默认转动是后是否需要停下
      // lasta=a;
      // lastb=b;

      // if(Math.abs(a)<=0.01 && Math.abs(b)<=0.01)
      // {
      // return;
      // }
      this.sineCosine(angleBasicA, angleBasicB, 0);
      for (let j = 0; j < this.tagAttrList.length; j++) {
        let rx1 = this.tagAttrList[j].cx;
        let ry1 = this.tagAttrList[j].cy * this.cosA + this.tagAttrList[j].cz * (-this.sinA);
        let rz1 = this.tagAttrList[j].cy * this.sinA + this.tagAttrList[j].cz * this.cosA;

        let rx2 = rx1 * this.cosB + rz1 * this.sinB;
        let ry2 = ry1;
        let rz2 = rx1 * (-this.sinB) + rz1 * this.cosB;

        let rx3 = rx2 * this.cosC + ry2 * (-this.sinC);
        let ry3 = rx2 * this.sinC + ry2 * this.cosC;
        let rz3 = rz2;
        this.tagAttrList[j].cx = rx3;
        this.tagAttrList[j].cy = ry3;
        this.tagAttrList[j].cz = rz3;

        let per = 350 / (350 + rz3);

        this.tagAttrList[j].x = rx3 * per - 2;
        this.tagAttrList[j].y = ry3 * per;
        this.tagAttrList[j].scale = per;
        this.tagAttrList[j].alpha = per;

        this.tagAttrList[j].alpha = (this.tagAttrList[j].alpha - 0.6) * (10 / 6);
      }
      this.doPosition();
      this.depthSort();
    },
    doPosition() {
      let len = this.cloudContent.offsetWidth / 2;
      let height = this.cloudContent.offsetHeight / 2;
      for (let i = 0; i < this.tagAttrList.length; i++) {
        this.tagContent[i].style.left = this.tagAttrList[i].cx + len - this.tagAttrList[i].offsetWidth / 2 + 'px';
        this.tagContent[i].style.top = this.tagAttrList[i].cy + height - this.tagAttrList[i].offsetHeight / 2 + 'px';
        // this.tagContent[i].style.fontSize = Math.ceil(12 * this.tagAttrList[i].scale/2) + 8 + 'px';
        this.tagContent[i].style.fontSize = Math.ceil(12 * this.tagAttrList[i].scale / 2) + 2 + 'px';
        this.tagContent[i].style.filter = "alpha(opacity=" + 100 * this.tagAttrList[i].alpha + ")";
        this.tagContent[i].style.opacity = this.tagAttrList[i].alpha;
      }
    },
    depthSort() {
      let aTmp = [];
      for (let i = 0; i < this.tagContent.length; i++) {
        aTmp.push(this.tagContent[i]);
      }
      aTmp.sort((item1, item2) => item2.cz - item1.cz);
      for (let i = 0; i < aTmp.length; i++) {
        aTmp[i].style.zIndex = i;
      }
    },
    sineCosine(a, b, c) {
      this.sinA = Math.sin(a * this.dtr);
      this.cosA = Math.cos(a * this.dtr);
      this.sinB = Math.sin(b * this.dtr);
      this.cosB = Math.cos(b * this.dtr);
      this.sinC = Math.sin(c * this.dtr);
      this.cosC = Math.cos(c * this.dtr);
    }


  },
  watch: {
    chartData: {
      handler() {
        if (this.radarChartRef) {
          this.radarChartRef.updateChart();
        }
      },
      deep: true,
    },
    chartIndicators: {
      handler() {
        if (this.radarChartRef) {
          this.radarChartRef.updateChart();
        }
      },
      deep: true,
    },
    chartData2: {
      handler() {
        if (this.radarChartRef2) {
          this.radarChartRef2.updateChart();
        }
      },
      deep: true,
    },
    chartIndicators2: {
      handler() {
        if (this.radarChartRef2) {
          this.radarChartRef2.updateChart();
        }
      },
      deep: true,
    },
  },
}
</script>







<style scoped>
.box-card {
  box-shadow: 7px;
  background: #d2ede7;
  margin-top: 20px;
  margin-bottom: 10px;
  font-size: 20px;
  margin-left: 40px;
  margin-right: 60px;
}

.box-card22 {
  margin-top: 20px;
  background: #c4ccf4;
  margin-bottom: 10px;
  font-size: 20px;
  margin-left: 40px;
  margin-right: 60px;
}

.box-card23 {
  margin-top: 20px;
  background-color: #c4ccf4;
  margin-bottom: 10px;
  font-size: 20px;
  margin-left: 10px;
  margin-right: 60px;
}

.box-card31 {
  background-color: #cde8ff;
  margin-top: 20px;
  margin-bottom: 10px;
  font-size: 20px;
  margin-left: 40px;
  margin-right: 60px;
}

.box-card33 {
  background-color: #d7e5ef;
  margin-top: 20px;
  margin-bottom: 10px;
  font-size: 20px;
  margin-left: 10px;
  margin-right: 60px;
}

body {
  font-family: 'PT Sans Caption', "Helvetica Neue", Arial, Helvetica, Geneva, sans-serif;
  font-size: 5vh;
}

.bread {
  margin-left: 1cm;
  margin-top: 0.3cm;
}

.button {
  margin-left: 10px;
  color: #306a2f;
  font-size: 30px;
  font-weight: bold;
}

.buttonchange {

  margin-left: 2cm;
  margin-top: 0.3cm;
}

.redardiagram {
  size: 20px;
}

.block {
  margin-right: 20px;
  position: relative;
}


.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.cloud-bed {
  width: 260px;
  height: 130px;
  margin: auto;
}

.cloud-box {
  position: relative;
  margin: 0px auto 0px;
  width: 100%;
  height: 100%;
  background: #69363600;
}

.cloud-box span {
  position: absolute;
  padding: 5px 13px;
  top: 0px;
  font-weight: bold;
  font-size: 100%;
  left: 0px;
  background-image: linear-gradient(to bottom, red, #fff);
  background-clip: text;
  color: transparent;
  width: 100px;
  height: 50px;
  border-radius: 100%;


  display: flex;
  align-items: center;
  justify-content: center;

  /* line-height: 50px;
      overflow:hidden;
      white-space: nowrap;
      text-overflow: ellipsis; */
}

.item {
  font-size: 30px;
}

.radar-charts-container {
  display: flex;
  /* 使用 Flexbox 布局 */
  justify-content: 40px;
  /* 在雷达图之间添加空间 */
  align-items: flex-start;
  /* 对齐雷达图的顶部边缘 */

}

.grid-content ep-bg-purple-light {
  display: flex;
  /* 使用 Flexbox 布局 */
  justify-content: 40px;
  /* 在雷达图之间添加空间 */
  align-items: flex-start;
  /* 对齐雷达图的顶部边缘 */

}

.evaluate {
  margin-top: 50px;
}</style>