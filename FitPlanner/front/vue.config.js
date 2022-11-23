const { defineConfig } = require('@vue/cli-service')
const path = require('path');

module.exports = defineConfig({
    transpileDependencies: true,
    lintOnSave : false,
    outputDir:"../src/main/resources/static",
    assetsDir: './assets',
    configureWebpack: {
      resolve: {
        alias: {
          "@": path.join(__dirname, "src/")
        }
      }
    },
    devServer: {
        headers: {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, PATCH, OPTIONS",
            "Access-Control-Allow-Headers": "X-Requested-With, content-type, Authorization"
        },
        proxy: "http://localhost:7070/",
        hot: true
    },
    pluginOptions: {
        i18n: {
            locale: 'fr',
            fallbackLocale: 'en',
            localeDir: 'assets/locales',
            enableInSFC: true
        }
    }
})
