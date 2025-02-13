<template>
    <div class="header" style="margin:10px;">
        <div style="display: flex; justify-content: space-between;">
            <div>
                <!-- <el-button @click="drawer = true" type="primary" style="margin-left: 16px;">
                    新闻说明
                </el-button>
                <el-drawer title="我是标题" v-model="drawer" :with-header="false">
                    <span>我来啦!</span>
                </el-drawer> -->
                <el-button type="primary" style="margin-left: 16px;" @click="exportCsv()">
                    导出全部
                </el-button>
            </div>
        </div>
    </div>
    <div class="body">
        <el-table ref="multipleTable" :data="news" stripe style="width: 100%" @selection-change="handleSelectionChange"
            :row-key="getRowKeys">
            <el-table-column type="selection" width="55" :reserve-selection="true">
            </el-table-column>
            <el-table-column prop="declaredate" label="发布日期" width="180">
            </el-table-column>
            <el-table-column prop="title" label="新闻标题" width="600">
                <!-- 跳转至新闻详情 -->
                <template #default="scope">{{ scope.row.title }}</template>
            </el-table-column>
            <el-table-column prop="symbol" label="标识符">
            </el-table-column>
            <el-table-column prop="emotion" label="情感分析">
                <template #default="scope">
                    <el-tag v-if="scope.row.emotion == 'Negative'" type="danger">{{ scope.row.emotion }}</el-tag>
                    <el-tag v-else-if="scope.row.emotion == 'Neutral'">{{ scope.row.emotion }}</el-tag>
                    <el-tag v-else-if="scope.row.emotion == 'Positive'" type="success">{{ scope.row.emotion }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="esg" label="ESG">
            </el-table-column>
        </el-table>
        <el-pagination style="display: flex;justify-content: center;padding-top: 1%;" @size-change="sizeChange"
            @current-change="currentChange" :current-page="page" :page-size="size" :page-sizes="pageSizes"
            layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
    </div>
</template>


<script>
import Papa from 'papaparse'
import axios from 'axios'
import { Search } from "@element-plus/icons";
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
            news: [],
            allData: [],
            multipleSelection: [],
        }
    },
    methods: {
        toggleSelection() {//取消选定的表项
            this.$refs.multipleTable.clearSelection();
        },
        handleSelectionChange(val) {//已选择的表项
            this.multipleSelection = val;
            console.log(this.multipleSelection)
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
            el.download = "新闻导出.csv";
            //必须点击否则不会下载
            el.click();
            //移除链接释放资源
            urlObject.revokeObjectURL(url);
        },
        //page改变时的回调函数，参数为当前页码
        currentChange(val) {
            this.page = val;
            console.log("page:" + this.page)
            this.getTabelData2();
        },
        //size改变时回调的函数，参数为当前的size
        sizeChange(val) {
            this.size = val;
            this.page = 1;
            this.getTabelData2();
        },
        getTabelData2() {
            axios({
                method: 'get',
                url: '/news-security/getAll',
                params: {
                    current: this.page,
                    size: this.size,
                },
            }).then((res) => {
                this.$data.news = res.data.records
                this.total = res.data.total
                console.log(this.total)
            }, error => {
                console.log('错误', error.message)
            });
        },
        getRowKeys(row) {
            return row.title;
        },
    },
    mounted() {//获取数据并初始化news
        this.getTabelData2();
    },
};
</script>