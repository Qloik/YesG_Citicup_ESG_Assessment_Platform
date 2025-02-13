import { createApp } from 'vue'
import ElementPlus from 'element-plus';
import 'element-plus/theme-chalk/index.css';
import App from './App.vue';
import router from './router'
import './all.css'
import * as echarts from 'echarts'
import axios from 'axios'



axios.defaults.baseURL = '/api'

 
const app = createApp(App)
app.use(ElementPlus).use(router)
app.mount('#app')
app.config.globalProperties.$axios=axios;  //配置axios的全局引用
