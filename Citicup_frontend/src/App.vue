<template>
  <router-view/>
</template>
<script>
export default {
  name: 'App',
  provide(){
    return {
      reload: this.reload
    }
  },
  data(){
    return {
      isRouterAlive: true
    }
  },
  methods:{
    reload(){
      this.isRouterAlive = false
      this.$nextTick(function(){
        this.isRouterAlive = true
      })
    }
  },
}
</script>
<style lang="scss">
::-webkit-scrollbar {
  width: 0 !important;
}
::-webkit-scrollbar {
  width: 0 !important;height: 0;
}
</style>

