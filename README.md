# pipi

### 接口通过Fiddler爬取 爬取自开眼APP 没有任何商用途径 纯粹自己模仿学习
### 接口都在代码中 （ServiceApi中）

## 自己的收获  
  1.结合饺子播放器实现视频播放功能,Glide图片加载  
  2.Recyclerview实现下拉刷新功能  
  3.retrofit+rxjava  
  4.点赞，踩的功能实现（获取评论接口未爬取成功 有待改进）   
  5.集成shareSDK实现第三方QQ登录（微信登录收费300故不实现）   
  6.实现分享视频给QQ好友  
  7.eventBus通信传递数据等  
  8.集成环信即时通讯 结合QQ登录返回的唯一标识 形成简单的账号系统  
  9.实现了1v1文字聊天功能，加好友全局提醒功能   
  10.实现了1v1视频聊天功能，全局接听    
  11.autolayout屏幕适配  
  12.学会制作.9.png图片  
  13.RxPermissions动态申请权限  
  14.好友列表侧滑删除  
  15.ZxingLite自定义扫码界面
  
## 修改日志

### 2019.05.29  
    *PiPi上线百度手机助手应用商店了  
    *修改App名称和图标（随便找的）
    *暂时去掉订阅功能（设计有问题，会闪退，日后修复）

### 2019.05.28
    *修改“我的”页面部分功能
    *修改添加好友UI，实现以扫码方式添加好友
    *新增“扫一扫”、“生成二维码”功能
    *添加ZxingLite依赖实现扫码功能
    *部分UI调整
    *不熟悉git今天冲突了半天...

### 2019.05.27
    *修改“我的”UI设计
    *加好友功能移到“消息”页面中
    *新增从图库选择更改头像功能（单击头像）
    *好友列表左滑增加“删除”、“备注”功能
    *有备注的好友显示备注

### 2019.05.27之前
    *模仿皮皮搞笑appUI实现
    *爬取开眼app接口获取数据源
    *集成饺子播放器实现观看视频功能
    *自定义view实现点赞效果
    *添加“分类页面”
    *实现上拉刷新功能
    *集成ShareSDK实现QQ登录和分享功能
    *集成环信IM实现简单的聊天功能
    *聊天界面实现，.9.png图片
    *加好友功能
    *视频聊天功能
    *视频聊天UI修改
    *等等等等...想不起来了

### 2019.xx.xx
    *之前没有写过提交记录，感觉越做功能越多了，比较复杂了，怕自己忘了从这次提交代码开始记录把
    *首先，这个APP没有后台的，账号系统和聊天什么的都是自己瞎设计出来的（QQ登录会获取唯一ID取前八位作为环信登录账号和密码哈哈）
    *接口都是自己爬取开眼APP的（没有任何商用，纯属自己学习，和朋友装装逼）
    *从五一假期开始做起这个Demo，最初只是想学习网络请求（Retrofit+rxjava）和视频播放功能
    *然而工作比较闲，就一点点的增加功能，一点点的完善，做成现在这个样子自己还是略有成就感的哈
    *采用了很多的开源库，非常感谢各位技术大牛的开源库
    
## 效果图 过段时间完善了上传
