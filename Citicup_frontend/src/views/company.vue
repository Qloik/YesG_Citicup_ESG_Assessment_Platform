<template>
    <div v-loading="loading">
        <div class="header" style="margin:10px;">
            <div style="display: flex; justify-content: space-between;">
                <div>
                    <el-button @click="drawer = true" type="primary" style="margin-left: 16px;">
                        指标说明
                    </el-button>
                    <el-drawer title="评分评级参考依据" v-model="drawer" :with-header="false" >
                        <div style="word-break:break-all;word-wrap:break-word;">
                        <h1>评分评级参考依据</h1>
                        <h2>评分说明：按总分1分评分，第三方评级机构数据占20%，自评中议题广泛性和议题质量性评分各占40%</h2>
                        <h3>第一部分：参考性评分</h3>
                        <p>收集已有评级的数据，按百分制算并赋20%权重。</p>
                        <p>ESG评分参考网站：
                            <a href="https://www.syntaogf.com" target="blank">商道融绿</a>&nbsp
                            <a href="https://finance.sina.com.cn/esg/grade.shtml" target="blank">新浪财经</a>
                        </p>
                        <p>ESG评级参考网站：
                            <a href="https://www.wind.com.cn/portal/zh/ESG/index.html" target="blank">万得</a><br />
                            评级分数转换说明：评级分为AAA、AA、A、BBB、BB、B、CCC七档，换算为100分并赋予权重20%。例如某公司评级为BBB，则分数换算为4/7*100*20%=11.15。
                        </p>
                        <h3>第二部分：议题广泛性评分</h3>
                        <p>议题涉及广度占议题综合评分的40%。环境（E）、社会（S）、治理（G）分别是议题涉及广度的三大指标，
                            而三大指标下又有各自的指标内主题。分别按照主题内关键绩效指标进行给分。根据主题内关键绩效指标在
                            参考网站搜索，得出某公司某一大指标下涉及指标内主题占总指标主题的比例，以此占比赋分。（E/S/G分
                            别赋予1/3权重）。<br />
                        </p>
                        <p>评分体系的划分如下：<br>
                            环境（E）：碳排放量、为环境保护提供资金、污染和废弃物、环境政策。<br>
                            社会（S）：员工发展、供应链管理、产品管理、数据安全。<br>
                            治理（G）：公司治理、公司行为、公司竞争。<br>
                        </p>
                        <h3>第三部分：议题质量性评分</h3>
                        <p>根据商道融绿和新浪财经ESG网站中相关ESG词典关键词进行检索，进行情感分析，正面记为+1.负面记为-1,
                            综合得出E、S、G每个板块评分。数据来源：CSMAR。
                        </p>
                    </div>
                    </el-drawer>
                    <el-button type="primary" style="margin-left: 16px;" @click="exportCsv()">
                        导出文件
                    </el-button><el-button type="primary" style="margin-left: 16px;" @click="toggleSelection">
                        清空选择
                    </el-button>
                </div>
                <el-input @keyup.enter="handleEnter()" v-model="searchText" style="width: 15%;" placeholder="输入公司名或代码">
                    <template #prefix><el-icon>
                            <Search />
                        </el-icon></template>
                </el-input>
            </div>
        </div>
        <div class="body">
            <el-table ref="multipleTable" :data="companys" stripe style="width: 100%"
                @selection-change="handleSelectionChange" :row-key="getRowKeys" @filter-change="handleFilterChange"
                @sort-change="handleSortChange" :default-sort="{ prop: 'symbpl', order: 'ascending ' }">
                <el-table-column type="selection" width="55" :reserve-selection="true">
                </el-table-column>
                <el-table-column prop="symbol" label="股票代码" width="180">
                </el-table-column>
                <el-table-column prop="name" label="公司名称" width="180">
                    <!-- 跳转至公司详情 -->
                    <template #default="scope">
                        <a style="color:#1890ff;cursor:pointer" @click="intodetail(scope.row.symbol)">{{ scope.row.name
                        }}</a>
                    </template>
                </el-table-column>
                <!-- <el-table-column prop="industry" label="所属行业" :filter-method="filterTag" :filters="filterTagSet"> -->
                <el-table-column prop="industry" label="所属行业" :filters="filterTagSet" column-key="filterTag">
                    <template #default="scope">
                        <el-tag type='success'>{{ scope.row.industry }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="esg" label="ESG总评" :sortable="'custom'">
                </el-table-column>
                <el-table-column prop="third" label="参考性得分" :sortable="'custom'">
                </el-table-column>
                <el-table-column prop="words" label="议题广泛性" :sortable="'custom'">
                </el-table-column>
                <el-table-column prop="emotion" label="议题质量性" :sortable="'custom'">
                </el-table-column>
            </el-table>
            <el-pagination style="display: flex;justify-content: center;padding-top: 1%;" @size-change="sizeChange"
                @current-change="currentChange" :current-page="page" :page-size="size" :page-sizes="pageSizes"
                layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </div>
    </div>
</template>


<script>
import Papa from 'papaparse'
import axios from 'axios'
import { Search } from "@element-plus/icons";
import { column } from 'element-plus/es/components/table-v2/src/common';
export default {
    components: {
        Search
    },
    data() {
        return {
            page: 1, //第几页
            size: 20, //一页多少条
            total: 0, //总条目数
            pageSizes: [10, 20, 50, 100], //可选择的一页多少条
            drawer: false,
            searchResult: [],//搜索结果
            companys: [],//当前页面的公司数据
            allData: [],//公司数据全集
            multipleSelection: [],//选中的数据
            searchText: '',
            filterTagSet: [],//行业tag全集
            selectedTagSet: [],//选中的tag集合
            loading: true,
        }
    },
    methods: {
        intodetail(symbol) {
            this.$router.push({ path: "/detail", query: { symbol: symbol } });
        },
        handleEnter() {//按下回车后进行搜索
            console.log(this.searchText)
            this.searchResult = this.allData.filter(
                (data) =>
                    !this.searchText ||
                    data.name.toLowerCase().includes(this.searchText.toLowerCase()) ||
                    data.symbol.includes(this.searchText) ||
                    data.industry.includes(this.searchText)
            )
            this.getTabelData2()
        },
        toggleSelection() {//取消选定的表项
            this.$refs.multipleTable.clearSelection();
        },
        handleSelectionChange(val) {//已选择的表项
            this.multipleSelection = val;
        },
        //page改变时的回调函数，参数为当前页码
        currentChange(val) {
            this.page = val;
            this.getTabelData2();
        },
        //size改变时回调的函数，参数为当前的size
        sizeChange(val) {
            this.size = val;
            this.page = 1;
            this.getTabelData2();
        },
        getTabelData2() {//根据当前的searchText和page、size调整companys的内容
            let data = JSON.parse(JSON.stringify(this.searchResult))
            this.companys = data.splice(
                (this.page - 1) * this.size,
                this.size
            );
            this.total = this.searchResult.length
        },
        handlefilterChange(filterTag) {//筛选的函数
            let tmp = JSON.parse(JSON.stringify(filterTag))
            if (tmp.filterTag.length != 0) {
                this.searchResult = []
                for (var i = 0; i < this.allData.length; i++) {
                    for (var j = 0; j < tmp.filterTag.length; j++) {
                        if (this.allData[i].industry == tmp.filterTag[j]) {
                            this.searchResult.push(this.allData[i])
                        }
                    }
                }
                this.getTabelData2();
            }
            else {
                this.searchResult = this.allData
                this.page = 1
                this.getTabelData2();
            }
        },
        handleSortChange(item) {
            console.log(item.order, item.prop)
            if (item.order == "descending") {
                this.searchResult = this.searchResult.sort(function (a, b) {
                    let x = a[item.prop], y = b[item.prop]
                    if (x == null) x = 0
                    if (y == null) y = 0
                    return x - y
                })
            }
            else if (item.order == "ascending") {
                this.searchResult = this.searchResult.sort(function (a, b) {
                    let x = a[item.prop], y = b[item.prop]
                    if (x == null) x = 0
                    if (y == null) y = 0
                    return y - x
                })
            }
            else if (item.order == null) {
                this.searchResult = this.searchResult.sort(function (a, b) {
                    let x = a.symbol, y = b.symbol
                    if (x == null) x = 0
                    if (y == null) y = 0
                    return x - y
                })
            }
            this.getTabelData2()
        },
        exportCsv() {//导出csv文件
            var csv = Papa.unparse(this.multipleSelection);
            //定义文件内容，类型必须为Blob 否则createObjectURL会报错
            let content = new Blob([csv]);
            //生成url对象
            let urlObject = window.URL || window.webkitURL || window;
            let url = urlObject.createObjectURL(content);
            //生成<a></a>DOM元素
            let el = document.createElement("a");
            //链接赋值
            el.href = url;
            el.download = "公司导出.csv";
            //必须点击否则不会下载
            el.click();
            //移除链接释放资源
            urlObject.revokeObjectURL(url);
        },
        getRowKeys(row) {
            return row.symbol;
        },
    },
    mounted() {//获取数据并初始化companys
        axios({
            method: 'get',
            url: '/company/getAllScore'
        }).then((res) => {
            this.allData = res.data
            this.searchResult = this.allData;
            this.companys = this.searchResult.slice(
                (this.page - 1) * this.size,
                this.page * this.size
            );
            this.total = this.allData.length
            this.getTabelData2();
            this.$data.loading = false
        }, error => {
            console.log('错误', error.message)
        });

        axios({
            method: 'get',
            url: '/keyword/getAllIndustry'
        }).then((res) => {
            this.tags = res.data
            for (var i = 0; i < this.tags.length; i++) {
                this.filterTagSet.push(
                    { text: this.tags[i], value: this.tags[i] }
                )
            }
        }, error => {
            console.log('错误', error.message)
        });
    },
};
</script>



<style scoped>
.paginationClass {
    position: fixed;
    bottom: 0;
    height: 40px;
    width: 100%;
    text-align: center;
}
</style>