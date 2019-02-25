App({
  onLaunch: function () {
    console.log('App Launch')
    this.globalData.apiPrefix = this.globalData.url + this.globalData.apiPrefix;
    for (var key in this.globalData.api) {
      this.globalData.api[key] = this.globalData.apiPrefix + this.globalData.api[key];
      console.log("api :" + this.globalData.api[key]);
    }
    //判断购物车对象是否存在
    if(wx.getStorageSync('carts') == ''){
      wx.setStorageSync('carts', []);
    }
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  onShow: function () {
    console.log('App Show')
  },
  onHide: function () {
    console.log('App Hide')
  },
  globalData: {
    //dev url
    // url: 'http://localhost', 
    //pro url
    url: 'https://s.twinking.cn/eshop',
    apiPrefix: '/api/wx',
    hasLogin: false,
    userInfo: null,
    api: {
      // 获取sn 如未注册自动注册
      sn: '/sn',
      // 根据商品id获取在售商品详细信息
      goodsDetail: '/goods_detail',
      // 获取10个最新商品
      newList: '/new_list',
      // 获取推荐商品
      recommendList: '/recommend_list',
      // 获取首发商品
      firstList: '/first_list',
      // 获取限时抢购商品
      rushList: '/rush_list',
      // 获取基本用户信息
      userInfo: '/user_info',
      // 获取用户积分
      userIntegral: '/user_integral',
      // 更新用户信息
      updateUerInfo: '/update_user_info',
      // 获取所有在售商品类别  不含未分类
      goodsTypeList: '/goods_type_list',
      // 下单
      toOrder: '/order',
      // 支付
      toPay: '/pay',
      // 退款
      toRefund: '/refund',
      // 取消订单
      toCancel: '/cancel',
      // 订单列表
      orderList: '/order_list',
      // 订单详情
      orderDetail: '/order_detail'
    }
  }
})
