const { defineConfig } = require('@vue/cli-service')
const path = require('path');

module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave : false,
  configureWebpack: {
      resolve: {
        alias: {
          "@": path.join(__dirname, "src/")
        }
      }
  },
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:7070/api/',
        changeOrigin: true,
        pathRewrite:{ "^/api" : '' }
      }
    },
    headers: { "Access-Control-Allow-Origin": "*" }
  }
})
