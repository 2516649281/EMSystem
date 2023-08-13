# vue 移动端模板

#### 启动项目
1. npm i 安装依赖
1. npm run serve 启动项目
1. cd server  (接口目录)
1. npm i 
1. npm run dev


>#### 前言
花了半天的时间整理了一个 vue 移动端项目的模板，希望多还不熟悉 vue 项目搭建的小伙伴有些帮助，也欢迎各路大神提出宝贵的建议，本文章默认你已经对 webpack 和 vue 有一定的了解。

>#### 核心知识体系简介
1. vue-cli3 脚手架
    * 创建项目
    * 开发环境和生产环境配置
    * 配置跨域
    
2. vue-router 路由
    * 路由配置
    * 子路由配置
    * 路由守卫，设置页面标题和根据登录状态判断是否允许进入特定页面
    * 对router-link使用active-class,高亮当前路由
    
3. vuex 跨组件通信
    * vuex 配置
    * vuex 持久化
    * vuex 使用
    
4. rem 移动端适配
    * 通过 amfe-flexibe 设置 root 元素的字体大小
    * 通过 pxtorem 把 px 转成 rem, 无需手动书写 rem
    
5. axios 配置
    * 使用拦截器配置 baseURL 和给请求头加上 token
    * 使用拦截器对返回的数据进行处理
    * 挂载到 vue 原型上，方便使用
    
6. promise 使用
    
    * 在项目中使用 async await 把异步变成同步，编写和阅读更舒服
    
7. 有赞 vant-ui 库
    * 导航栏
    * 单元格
    * loading
    * ......
    
8. 使用keep-alive组件缓存某些组件(新增)

9. 添加小型服务器接口,用来编写本项目所需接口

    

>####  vue-cli3 脚手架
1. 具体操作,请移步[vue-cli3文档官网]( [https://cli.vuejs.org/zh/guide/installation.html](https://links.jianshu.com/go?to=https%3A%2F%2Fcli.vuejs.org%2Fzh%2Fguide%2Finstallation.html)
)
2. **开发环境和生产环境配置**
  * 根目录新建两文件 .env.development => 开发环境配置  .env.production => 生产环境配置
![image.png](https://upload-images.jianshu.io/upload_images/7177443-4f2c42f1c1bc4439.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
文件内容

```
// .env.development
VUE_APP_URL="https://www.easy-mock.com/mock/5cd62747d3a84206275d58d1/huruqing/api"
```

```
// .env.production
VUE_APP_URL="https://www.easy-mock.com/mock/5cd62747d3a84206275d58d1/huruqing/api"
```

3. **package.json 配置**
vue-cli3默认已经给你配置好了model, 开发环境对应development, 生产环境对应production,如果你不知道model,请移步[webpack文档官网]([https://www.webpackjs.com/](https://www.webpackjs.com/)
)
![image.png](https://upload-images.jianshu.io/upload_images/7177443-5e9e447da363cfca.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
4. 在你的项目中，通过  process.env.VUE_APP_URL 可以访问到你在环境配置文件中设置的变量
![](https://upload-images.jianshu.io/upload_images/7177443-d585aa842e655950.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![开发环境中的变量](https://upload-images.jianshu.io/upload_images/7177443-02325513adfb846f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>####  vue-router 路由
这里主要讲一下路由守卫的配置，先上图
![路由配置](https://upload-images.jianshu.io/upload_images/7177443-5f45913a5d70bf4d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
下面是路由守卫配置
![路由守卫](https://upload-images.jianshu.io/upload_images/7177443-a935a3506fd9f567.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>####  vuex 跨组件通信
1. 项目中有以下几个地方用到了 vuex
    * 设置登录状态
    * 设置用户名
    * 保存 token
2. 以登录为里，做个简单说明
![](https://upload-images.jianshu.io/upload_images/7177443-825e2e816a49413b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
登录成功时，修改登陆状态为 true
![image.png](https://upload-images.jianshu.io/upload_images/7177443-906c4cc3e5713830.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
退出登录时，修改登录状态为 false
![](https://upload-images.jianshu.io/upload_images/7177443-1da2a2e9208c1872.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
4. 获取 store 中 state 设置的变量通过 mapGetters, 看图
![](https://upload-images.jianshu.io/upload_images/7177443-61be719b7d9c8a43.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![在模板中使用](https://upload-images.jianshu.io/upload_images/7177443-8b2c372f88a0f59e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

3. vuex 持久化
    * 使用 vuex-persistedstate 插件对 vuex 的状态持久化
    * 等你的页面刷新时，状态依然存在
    * 本质上是这个插件帮你把状态都存到了 localStorage
配置方法，以下是最简单的配置
![](https://upload-images.jianshu.io/upload_images/7177443-0aa53dbad9057879.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
使用了 vuex-persistedstate 插件后，store 里的状态都存到了 localStorage
![image.png](https://upload-images.jianshu.io/upload_images/7177443-9cf6e3db49baf726.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>#### rem 移动端适配
适配原理我就不多说了,不清楚请看我的另外一篇文章[用rem编写移动端自适应网页](https://www.jianshu.com/p/91ac1690be89)(https://www.jianshu.com/p/91ac1690be89),这里说一下在vue-cli3中是如何配置的
1. 安装 amfe-flexible 插件 ```npm i amfe-flexible --save-dev```
2. 在 main.js 中导入
![ipone6](https://upload-images.jianshu.io/upload_images/7177443-05c1792eb5cfab79.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
3. 此时你的应用的 html 会自动根据手机的尺寸设置了 font-size, 如图
![](https://upload-images.jianshu.io/upload_images/7177443-5ea467230b5426e0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/7177443-ee3e3fda5ec57b39.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
4. 配置 pxtorem 插件，能帮你自动把 rem 转成 px, 具体配置如下，在项目根目录下的 vue.config.js（没有则新建）
里进行配置，如图
![](https://upload-images.jianshu.io/upload_images/7177443-2b6e79201971d86d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>####  axios 配置
1. axios 拦截器
能帮我们对数据进行一些统一的处理，比如后台给我们的数据里都是把数据包在 data 对象里面，而使用 axios, 则又会再用 data 把返回的数据再包一层，如果不统一处理以下，用起来很不爽。
2. 设置请求头，后台识别用户经常使用 token, 我们登陆的时候能拿到后台返回的 token, 然后存入 store 里，当我们发送请求时，我们可以给请求头统一加上 token, 如图
![](https://upload-images.jianshu.io/upload_images/7177443-2a42eb0010cde4a1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
3. 把 axios 的实例挂载到 vue 的实例上
![](https://upload-images.jianshu.io/upload_images/7177443-5163a952efbac676.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
发请求的时候，只需要使用 this.$axios.get 或者 this.$axios.post 就行了，是不是很方便
![](https://upload-images.jianshu.io/upload_images/7177443-5a0bd9bbec047aa7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>#### Promise 的使用
promise 是个好东西，可以把异步变同步，在项目中使用 async await 编写代码，那叫一个爽，具体用法如下：
![](https://upload-images.jianshu.io/upload_images/7177443-9f357fbd0a04215a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>#### 有赞 vant-ui 库
有赞是搞电商开发的,在公众号和小程序模板界算是做得比较不错的,vant-ui是他们开源出来的一个ui库,同时他们还有小程序的ui库,感兴趣的小伙伴可以去[看看](https://youzan.github.io/vant/)(https://youzan.github.io/vant/)

>


