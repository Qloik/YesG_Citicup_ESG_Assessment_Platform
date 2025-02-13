import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Home from '../views/homepage.vue'
import home from '../views/home.vue'
import comp from '../views/company.vue'
import strategy from '../views/strategy.vue'
import detail from '../views/detail.vue'
import compare from '../views/compare.vue'
import news from '../views/news.vue'
import login from '../views/login.vue'
import team from '../views/team.vue'

const routes = [
  {
    path: '/',
    redirect:'/login'
  },
  {
    path:'/bar',
    component: Home,
    redirect:'/home',
    children: [
      { path:'/home',component:home },
      { path:'/company',component:comp },
      { path:'/strategy',component:strategy },
      { path:'/detail',component:detail },
      { path:'/compare',component:compare },
      { path:'/news',component:news },
      { path:'/team',component:team },
    ]
  },
  {
    path:'/login',
    component:login
  }
  // {
  //   path:'/demo',
  //   component: demo
  // }
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  // 如果访问的是登录界面则直接放行
  if (to.path === '/login') return next()
  //获取用户页面userid信息
  let userid = window.localStorage.getItem('userid')
  //如果userid数据为null则跳转到指定路径
  if (!userid) return next("/login")
  next()
})


export default router
