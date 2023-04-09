const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  pluginOptions: { //electron 打包字体图标不显示问题解决
    electronBuilder: {
      customFileProtocol: "./"
    }
  }

})
