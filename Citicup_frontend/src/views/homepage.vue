<template>
    <el-menu :default-active="activeIndex" class="el-menu-demo" background-color="#02040d" text-color="#fff" mode="horizontal"
            active-text-color="#fff" :router="true" :ellipsis="false" @select="handleSelect">
        
        <img class="hands" src="../assets/logo_white.png" style="width:100px; height:20px; padding-top: 17px; padding-left: 15px; padding-right: 15px;" @click="jumphome">
        
        <el-menu-item index="/company">ESG SUMMARY</el-menu-item>
        <!-- <el-menu-item index="/strategy">ESG STRATEGY</el-menu-item> -->
        <el-menu-item index="/compare">ESG COMPARISION</el-menu-item>
        <div class="flex-grow" />
        <!-- <el-menu-item index="3">README</el-menu-item> -->
        <el-menu-item index="/team">ABOUT US</el-menu-item>

    <el-dropdown style="padding-left:10px;padding-right:20px">
        <p style="color:white;font-size: large;">Hello, {{ name }}</p>
        <!-- <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" /> -->
        <template #dropdown>
          <el-dropdown-menu>
            <p style="padding-left:20px;padding-right: 20px;font-size: 15px;">ID:{{ id }}</p>
            <!-- <el-dropdown-item @click="selfInfo">个人主页</el-dropdown-item> -->
            <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
        </el-menu>

        <!-- 路由占位 -->
        <router-view></router-view>




</template>
  
<script>
import axios from 'axios'
export default 
{
    data(){
        return{
            id:'',
            name:''
        }
    },
    methods:{
        jumphome(){
            this.$router.push('/home')
        },
        logout(){
            window.localStorage.clear();
            axios({
                    method: 'post',
                    url: '/user/logout',
                    params:{
                        id:this.$data.id,
                    }
                }).then((res) => {
                    // console.log('logout', res.data)
                    this.$router.push('/login')
                }, error => {
                    console.log('错误', error.message)
                })

        }
    },
    mounted(){
        this.$data.id = window.localStorage.getItem('userid')
        this.$data.name = window.localStorage.getItem('name')
    }
}
</script>
  
<style scoped>
.flex-grow {
    flex-grow: 1;
}

</style>

