const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  configureWebpack: {
    name:'YesG',
    externals: {
      AMap: 'AMap'
    }
  },
  devServer: {
    proxy: {
      '/api': {
        target: 'http://82.157.145.14:8090/',// 后端接口
        // target: 'http://localhost:8090/',// 后端接口
        changeOrigin: true, // 是否跨域
        pathRewrite: {
          '/api': ''
        }
      }
    }
  }
})
