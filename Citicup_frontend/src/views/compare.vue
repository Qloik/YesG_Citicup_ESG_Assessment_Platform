<template>
    <!-- Quickly compare yourself to your industry peers and get an initial indication of where your gaps might be compared to your competitors. -->
    <div class="hero-bg">
        <div class="container hero-container">
            <h1>
                Quickly compare yourself to your industry peers and get an initial indication of where your gaps might be compared to your competitors.
            </h1>
        </div>
    </div>
    
    <div class="container compare-container">
        
        <div style="padding-bottom:10%">
        <h1 style="color:#00a19b">各行业前5%公司查询</h1>

            <el-dropdown trigger="click" max-height="200px" style="padding-bottom:3%">
                <el-button  size="large" color="#000" plain round>
                    选择行业&nbsp <el-icon><ArrowDown /></el-icon>
                </el-button>
            <template #dropdown>
            <el-dropdown-menu >
                <el-dropdown-item v-for="item in industries" :key="item" :value="item" @click="getTop(item)">{{ item }} </el-dropdown-item>
            </el-dropdown-menu>
            </template>
            </el-dropdown>
        
            <el-table :data="tableData" height="220" style="width: 100%;">
            <el-table-column type="index" width="50" />
            <el-table-column prop="industry" label="所属行业" />
            <!-- <el-table-column prop="rank" label="行业内前百分比" /> -->
            <el-table-column prop="symbol" label="公司代码" />
            <el-table-column prop="name" label="公司名称"/>
            <el-table-column prop="namef" label="繁体公司名称"/>
            <el-table-column prop="esg" label="ESG总分"/>
            </el-table>
        </div>    


        <div class="row">
            <div class="col">
                <div class="comp-circle you">
                    1
                </div>

                <div class="text-center">
                    <el-button v-if="button1_show"  size="large" color="#000" plain round @click="add(1)">
                        Add Company
                    </el-button>
                </div>

                <div v-if="!button1_show">
                    <h1 style=""> {{ company1.name }} {{ company1.symbol }} </h1>
                    <h2 style="color:grey">{{ company1.industry }}</h2>
                    <el-divider />
                    <h2 >ESG RATING</h2>
                    <h1 style="color:#00a19b"> {{ company1.esg }}</h1>
                    <h2>位于行业前 {{ company1.esgRank }}%</h2>
                    
                    <!-- <el-divider /> -->
                    <div style="background:#00a19b; padding: 3%;">
                        <h1 style="color:white;margin-top: 0;">Environment</h1>
                        <!-- <h2 style="color:white">RATING</h2> -->
                        <h1 style="color:white"> {{ company1.EWords}}</h1>
                        <!-- <p style="color:white">位于行业前{{ company1.percentage }}%</p> -->

                        <el-divider />

                        <el-table :data="table1" style="width: 100%">
                        <el-table-column prop="e1" label="碳排放量"  />
                        <el-table-column prop="e2" label="环保资金"/>
                        <el-table-column prop="e3" label="污染废弃"/>
                        <el-table-column prop="e4" label="环境政策"/>
                        </el-table>
                        
                    </div>
                    <p></p>
                    <div style="background:#8e97d4; padding: 3%;">
                        <h1 style="color:white;margin-top: 0;">SOCIAL</h1>
                        <!-- <h2 style="color:white">RATING</h2> -->
                        <h1 style="color:white"> {{ company1.SWords }}</h1>
                        <!-- <p style="color:white">位于行业前{{ company1.percentage }}%</p> -->

                        <el-divider />

                        <el-table :data="table1" style="width: 100%">
                            <el-table-column prop="s1" label="社会进步"  />
                            <el-table-column prop="s2" label="社会福祉"/>
                            <el-table-column prop="s3" label="社会安全"/>
                            <el-table-column prop="s4" label="社会责任"/>
                        
                        </el-table>
                        
                    </div>

                    <p></p>
                    <div style="background:#3580BB; padding: 3%;">
                        <h1 style="color:white;margin-top: 0;">GOVERNANCE</h1>
                        <!-- <h2 style="color:white">RATING</h2> -->
                        <h1 style="color:white"> {{ company1.GWords }}</h1>
                        <!-- <p style="color:white">位于行业前{{ company1.percentage }}%</p> -->

                        <el-divider />

                        <el-table :data="table1" style="width: 100%">
                            <el-table-column prop="g1" label="企业管理层"  />
                            <el-table-column prop="g2" label="企业制度"/>
                            <el-table-column prop="g3" label="企业竞争"/>
                        </el-table>
                        
                    </div>
                    <el-divider />
                    <p style="font-size:large">新闻舆论得分</p>
                    <h2> {{ company1.emotion_score }}</h2>
                    <p>位于行业前 {{ company1.emotionRank }}%</p>
                    <el-table :data="table1" border style="width: 100%">
                        <el-table-column prop="EEmotion" label="Environment"  />
                        <el-table-column prop="SEmotion" label="Social"/>
                        <el-table-column prop="GEmotion" label="Government"/>
                    </el-table>

                    <el-divider />
                    <p style="font-size:large">其他评级机构综合评分</p>
                    <h2> {{ company1.thirdParty }}</h2>
                    <p>位于行业前 {{ company1.thirdPartyRank }}%</p>
                    <el-divider />

                    <p class="hands" style="color:grey" @click="this.$data.button1_show = true"> 
                        <el-icon><Delete /></el-icon> remove </p>
                </div>


            </div>
            <div class="col bg-grey">
                <div class="comp-circle other">
                    2
                </div>
                
                <div class="text-center">
                    <div class="text-center">
                        <el-button v-if="button2_show"  size="large" color="#000" plain round @click="add(2)">
                            Add Company
                        </el-button>
                    </div>
                </div>
                    
                <div v-if="!button2_show">
                    <h1 style=""> {{ company2.name }} {{ company2.symbol}} </h1>
                    <h2 style="color:grey">{{ company2.industry }}</h2>
                    <el-divider />
                    <h2 >ESG RATING</h2>
                    <h1 style="color:#00a19b"> {{ company2.esg }}</h1>
                    <h2>位于行业前 {{ company2.esgRank }}%</h2>
                    
                    <!-- <el-divider /> -->
                    <div style="background:#84C7B9; padding: 3%;">
                        <h1 style="color:white;margin-top: 0;">Environment</h1>
                        <!-- <h2 style="color:white">RATING</h2> -->
                        <h1 style="color:white"> {{ company2.EWords }}</h1>
                        <!-- <p style="color:white">位于行业前{{ company2.percentage }}%</p> -->

                        <el-divider />

                        <el-table :data="table2" style="width: 100%">
                        <el-table-column prop="e1" label="碳排放量"  />
                        <el-table-column prop="e2" label="环保资金"/>
                        <el-table-column prop="e3" label="污染废弃"/>
                        <el-table-column prop="e4" label="环境政策"/>
                        </el-table>
                        
                    </div>
                    <p></p>
                    <div style="background:#ACB4DE; padding: 3%;">
                        <h1 style="color:white;margin-top: 0;">SOCIAL</h1>
                        <!-- <h2 style="color:white">RATING</h2> -->
                        <h1 style="color:white"> {{ company2.SWords }}</h1>
                        <!-- <p style="color:white">位于行业前{{ company2.percentage }}%</p> -->

                        <el-divider />

                        <el-table :data="table2" style="width: 100%">
                            <el-table-column prop="s1" label="社会进步"  />
                            <el-table-column prop="s2" label="社会福祉"/>
                            <el-table-column prop="s3" label="社会安全"/>
                            <el-table-column prop="s4" label="社会责任"/>
                        
                        </el-table>
                        
                    </div>

                    <p></p>
                    <div style="background:#74A8CF; padding: 3%;">
                        <h1 style="color:white;margin-top: 0;">GOVERNANCE</h1>
                        <!-- <h2 style="color:white">RATING</h2> -->
                        <h1 style="color:white"> {{ company2.GWords }}</h1>
                        <!-- <p style="color:white">位于行业前{{ company2.percentage }}%</p> -->

                        <el-divider />

                        <el-table :data="table2" style="width: 100%">
                            <el-table-column prop="g1" label="企业管理层"  />
                            <el-table-column prop="g2" label="企业制度"/>
                            <el-table-column prop="g3" label="企业竞争"/>
                        </el-table>
                        
                    </div>

                    <el-divider />
                    <p style="font-size:large">新闻舆论得分</p>
                    <h2> {{ company2.emotion_score }}</h2>
                    <p>位于行业前 {{ company2.emotionRank }}%</p>
                    <el-table :data="table2" border style="width: 100%">
                        <el-table-column prop="EEmotion" label="Environment"  />
                        <el-table-column prop="SEmotion" label="Social"/>
                        <el-table-column prop="GEmotion" label="Government"/>
                    </el-table>

                    <el-divider />
                    <p style="font-size:large">其他评级机构综合评分</p>
                    <h2> {{ company2.thirdParty }}</h2>
                    <p>位于行业前 {{ company2.thirdPartyRank }}%</p>
                    <el-divider />

                    <p class="hands" style="color:grey" @click="this.$data.button2_show = true"> 
                        <el-icon><Delete /></el-icon> remove </p>
                </div>
            </div>

        </div>
        
        <div>
            <h1 style="color:#00a19b">对比结果参考</h1>
            <div v-if="button1_show == false && button2_show == false" style="font-size:20px">
                <nobr v-if="this.$data.company1.esg > this.$data.company2.esg" class="highlight" > {{ company1.name }}</nobr>
                <nobr v-if="this.$data.company1.esg < this.$data.company2.esg" class="highlight"  > {{ company2.name }} </nobr>
                <nobr v-if="this.$data.company1.esg != this.$data.company2.esg">的ESG综合评估结果更高</nobr>
                <br>
                <br>
                <nobr v-if="this.$data.company1.esg > this.$data.company2.esg" class="black"  > {{ company2.name }} </nobr>
                <nobr v-if="this.$data.company1.esg < this.$data.company2.esg" class="black"  > {{ company1.name }} </nobr>
                <nobr v-if="this.$data.company2.e1 > this.$data.company1.e1 + 0.1||
                            this.$data.company2.e2 > this.$data.company1.e2 + 0.1||
                            this.$data.company2.e3 > this.$data.company1.e3 + 0.1||
                            this.$data.company2.e4 > this.$data.company1.e4 + 0.1||
                            this.$data.company2.s1 > this.$data.company1.s1 + 0.1||
                            this.$data.company2.s2 > this.$data.company1.s2 + 0.1||
                            this.$data.company2.s3 > this.$data.company1.s3 + 0.1||
                            this.$data.company2.s4 > this.$data.company1.s4 + 0.1||
                            this.$data.company2.g1 > this.$data.company1.g1 + 0.1||
                            this.$data.company2.g2 > this.$data.company1.g2 + 0.1||
                            this.$data.company2.g3 > this.$data.company1.g3 + 0.1">在</nobr>

                <nobr v-if="this.$data.company2.e1 > this.$data.company1.e1 + 0.1" class="highlight" >碳排放量 </nobr>
                <nobr v-if="this.$data.company2.e2 > this.$data.company1.e2 + 0.1" class="highlight" >为环境环保提供资金 </nobr>
                <nobr v-if="this.$data.company2.e3 > this.$data.company1.e3 + 0.1" class="highlight" >污染和废弃物 </nobr>
                <nobr v-if="this.$data.company2.e4 > this.$data.company1.e4 + 0.1" class="highlight" >环境政策 </nobr>
                <br>
                <nobr v-if="this.$data.company2.s1 > this.$data.company1.s1 + 0.1" class="highlight" >共同富裕与科技创新 </nobr>
                <nobr v-if="this.$data.company2.s2 > this.$data.company1.s2 + 0.1" class="highlight" >慈善事业 </nobr>
                <nobr v-if="this.$data.company2.s3 > this.$data.company1.s3 + 0.1" class="highlight" >安全防范措施 </nobr>
                <nobr v-if="this.$data.company2.s4 > this.$data.company1.s4 + 0.1" class="highlight" >法律义务与政治责任 </nobr>
                <br>
                <nobr v-if="this.$data.company2.g1 > this.$data.company1.g1 + 0.1" class="highlight" >企业管理策略 </nobr>
                <nobr v-if="this.$data.company2.g2 > this.$data.company1.g2 + 0.1" class="highlight" >员工薪酬待遇 </nobr>
                <nobr v-if="this.$data.company2.g3 > this.$data.company1.g3 + 0.1" class="highlight" >反垄断与良性竞争 </nobr>
                
                <nobr v-if="this.$data.company2.e1 > this.$data.company1.e1 + 0.1||
                            this.$data.company2.e2 > this.$data.company1.e2 + 0.1||
                            this.$data.company2.e3 > this.$data.company1.e3 + 0.1||
                            this.$data.company2.e4 > this.$data.company1.e4 + 0.1||
                            this.$data.company2.s1 > this.$data.company1.s1 + 0.1||
                            this.$data.company2.s2 > this.$data.company1.s2 + 0.1||
                            this.$data.company2.s3 > this.$data.company1.s3 + 0.1||
                            this.$data.company2.s4 > this.$data.company1.s4 + 0.1||
                            this.$data.company2.g1 > this.$data.company1.g1 + 0.1||
                            this.$data.company2.g2 > this.$data.company1.g2 + 0.1||
                            this.$data.company2.g3 > this.$data.company1.g3">方面取得领先</nobr>
                <br>
                <br>

                <nobr v-if="this.$data.company2.e1 + 0.1 < this.$data.company1.e1||
                            this.$data.company2.e2 + 0.1 < this.$data.company1.e2||
                            this.$data.company2.e3 + 0.1 < this.$data.company1.e3||
                            this.$data.company2.e4 + 0.1 < this.$data.company1.e4||
                            this.$data.company2.s1 + 0.1 < this.$data.company1.s1||
                            this.$data.company2.s2 + 0.1 < this.$data.company1.s2||
                            this.$data.company2.s3 + 0.1 < this.$data.company1.s3||
                            this.$data.company2.s4 + 0.1 < this.$data.company1.s4||
                            this.$data.company2.g1 + 0.1 < this.$data.company1.g1||
                            this.$data.company2.g2 + 0.1 < this.$data.company1.g2||
                            this.$data.company2.g3 + 0.1 < this.$data.company1.g3">在</nobr>
                
                <nobr v-if="this.$data.company2.e1 + 0.1 < this.$data.company1.e1" class="black" >碳排放量 </nobr>
                <nobr v-if="this.$data.company2.e2 + 0.1 < this.$data.company1.e2" class="black" >为环境环保提供资金 </nobr>
                <nobr v-if="this.$data.company2.e3 + 0.1 < this.$data.company1.e3" class="black" >污染和废弃物 </nobr>
                <nobr v-if="this.$data.company2.e4 + 0.1 < this.$data.company1.e4" class="black" >环境政策 </nobr>
                <br>
                <nobr v-if="this.$data.company2.s1 + 0.1 < this.$data.company1.s1" class="black" >共同富裕与科技创新 </nobr>
                <nobr v-if="this.$data.company2.s2 + 0.1 < this.$data.company1.s2" class="black" >慈善事业 </nobr>
                <nobr v-if="this.$data.company2.s3 + 0.1 < this.$data.company1.s3" class="black" >安全防范措施 </nobr>
                <nobr v-if="this.$data.company2.s4 + 0.1 < this.$data.company1.s4" class="black" >法律义务与政治责任 </nobr>
                <br>
                <nobr v-if="this.$data.company2.g1 + 0.1 < this.$data.company1.g1" class="black" >企业管理策略 </nobr>
                <nobr v-if="this.$data.company2.g2 + 0.1 < this.$data.company1.g2" class="black" >员工薪酬待遇 </nobr>
                <nobr v-if="this.$data.company2.g3 + 0.1 < this.$data.company1.g3" class="black" >反垄断与良性竞争 </nobr>
                
                <nobr v-if="this.$data.company2.e1 + 0.1 < this.$data.company1.e1||
                            this.$data.company2.e2 + 0.1 < this.$data.company1.e2||
                            this.$data.company2.e3 + 0.1 < this.$data.company1.e3||
                            this.$data.company2.e4 + 0.1 < this.$data.company1.e4||
                            this.$data.company2.s1 + 0.1 < this.$data.company1.s1||
                            this.$data.company2.s2 + 0.1 < this.$data.company1.s2||
                            this.$data.company2.s3 + 0.1 < this.$data.company1.s3||
                            this.$data.company2.s4 + 0.1 < this.$data.company1.s4||
                            this.$data.company2.g1 + 0.1 < this.$data.company1.g1||
                            this.$data.company2.g2 + 0.1 < this.$data.company1.g2||
                            this.$data.company2.g3 + 0.1 < this.$data.company1.g3">方面仍然存在不足</nobr>
            </div>
        </div>
    </div>

    <!-- 底栏 -->
    <div style="position: static;background-color:#02040d;height: 200px;position: relative;">
        <p style="color:white;position: absolute;bottom: 10%;width:100%;text-align: center;">
            2022-2023第十八届“花旗杯”金融创新应用大赛参赛作品
        </p>
    </div>
</template>

<script>
import { ElMessage, ElMessageBox } from 'element-plus'
import { Delete,Plus,ArrowDown } from "@element-plus/icons";
import axios from 'axios'
export default 
{
    components:{
      Delete,
      Plus,
      ArrowDown
    },
    data(){
        return{
            button1_show: true,
            button2_show: true,
            id:0,
            company1:{},
            table1:[],
            table2:[],
            company2:{},
            tableData:[],
            industries:[],
        }
    },
    methods:{
        add(num){
                ElMessageBox.prompt('输入公司代码', 'Add Company', {
                    confirmButtonText: 'OK',
                    cancelButtonText: 'Cancel',
                })
                .then(({ value }) => {
                    // this.$data.id = value;
                    if(num == 1)
                    {
                        this.$data.table1.pop()
                        axios({
                            method: 'post',
                            url: '/company/getDetailedInfo',
                            params: {
                                symbol: value
                            }
                        }).then((res) => {
                            console.log('111', res.data)
                            if(res.data.result == 'symbol not exist')
                                alert('该公司代码不在范围内')
                            else{
                            this.$data.company1 = res.data
                            this.$data.table1.push(res.data)
                            console.log('1', this.$data.table1)
                            this.$data.button1_show= false;
                            }
                        }, error => {
                            console.log('错误', error.message)
                        })

                    }
                    if(num == 2)
                    {
                        this.$data.table2.pop()
                        axios({
                            method: 'post',
                            url: '/company/getDetailedInfo',
                            params: {
                                symbol: value
                            }
                        }).then((res) => {
                            console.log('111', res.data)
                            if(res.data.result == 'symbol not exist')
                                alert('该公司代码不在范围内')
                            else{
                            this.$data.company2 = res.data
                            this.$data.table2.push(res.data)
                            console.log('1', this.$data.table2)
                            this.$data.button2_show= false;
                            }
                        }, error => {
                            console.log('错误', error.message)
                        })
                    }
                })
                
        },
        getTop(industry){
                console.log(industry)
                // 获取前5%
                axios({
                    method: 'post',
                    url: '/company/getTopByIndustry',
                    params:{
                        industry:industry
                    }
                }).then((res) => {
                    console.log('111', res.data)
                    this.$data.tableData = res.data
                }, error => {
                    console.log('错误', error.message)
                })
            }
    },
    filters: {
        numFilter(value) {
            // 截取当前数据到小数点后两位
            let realVal = parseFloat(value).toFixed(2)
            return realVal
        }
    },
    mounted(){
        axios({
        method: 'get',
        url:'/keyword/getAllIndustry'
        }).then((res) => {
                console.log('industry', res.data)
                this.$data.industries = res.data
            }, error => {
                console.log('错误', error.message)
            })
    }
}


</script>

<style scoped>
.i_p {
    height: 30px;
    background-color: #8dcccc;
    margin: 0 10px;
    display: flex;
}

span {
    flex: 1;
    text-align: center;
    color: black;
    align-items: center;
    justify-content: center;
    display: flex;
    font-size: 11px;
    padding: 0 5px;
}
.cur{
    background-color: #fff;
    border: 2px solid black;
}
.hero-bg {
    width: 100%;
    height: 360px;
    background: url(../assets/green.jpg);
    background-size:cover;
    background-position:bottom;
}
.hero-bg h1{
    font-size: 42px;
    font-weight: 100;
    line-height: 55px;
    margin-bottom: 15px;
    color: white;
    display: block;
}
.hero-container{
    position: relative;
    padding-top: 3rem;
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
}
.compare-container{
    padding-top: 64px;
    padding-bottom: 64px;
}

.container{
    max-width: 55%;
    width: 100%;
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
}

.col{
    flex-basis: 0;
    flex-grow: 1;
    max-width: 100%;
    min-height: 500px;
}

.row{
    display: flex;
    flex-wrap: wrap;
    margin-right: -15px;
    margin-left: -15px;
}
.col{
    padding:2%;
}
.bg-grey{
    background-color: #f8f9fa;
}

.comp-circle {
    width: 15px;
    height: 15px;
    padding: 1rem;
    text-align: center;
    line-height: 15px;
    border-radius: 50%;
    font-weight:bold;
    font-size: 20px;
}

.you{
    background-color: #8dcccc;
}
.other{
    background-color:#e1f0f0;
}
.text-center {
    text-align: center !important;
}
.el-input {
    --el-input-focus-border:black;
}

.hands{
  cursor: pointer;  /*鼠标悬停变小手*/
}

.highlight{
    font-weight: bold;
    color: #00a19b;
    font-size: 25px;
    padding-right: 5px;
    padding-left: 5px;
}
.black{
    font-weight: bold;
    color: black;
    font-size: 25px;
    padding-right: 5px;
    padding-left: 5px;
}
</style>