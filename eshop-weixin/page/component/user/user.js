const app = getApp()
Page({
  data:{
    hasAddress:false,
    address:{},
    userInfo: {
      avatarUrl: "/image/defaultUserImg.png"
    },
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    detailUserInfo:[],
    orderList:[],
    integral: 0,
    index: 0
  },
  onLoad: function (){
    var self = this;
    this.getDetailUserInfo();
    /**
     * 获取用户信息
     */
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  onShow(){
    var self = this;
    //更新用户积分
    this.getIntegral();
    //加载订单列表
    this.getOrderList();
    //自动登录
    this.autoLogin();
    /**
     * 获取本地缓存 地址信息
     */
    wx.getStorage({
      key: 'address',
      success: function(res){
        self.setData({
          hasAddress: true,
          address: res.data
        })
      }
    })
  },
   /**
    * 请求获取订单列表信息
    */
  getOrderList(){
    var self = this;
    wx.getStorage({
      key: 'sn',
      success: function (res) {
        wx.request({
          url: app.globalData.api.orderList,
          data: {
            sn: res.data
          },
          success(res) {
            self.setData({
              orderList: res.data.data
            })
          }
        })
      },
    })
  },
  /**
   * 发起支付请求
   */
  payOrders(e){
    var self = this;
    var order = this.data.orderList[e.currentTarget.dataset.index];
    var sn = wx.getStorageSync("sn");
    wx.showModal({
      title: '支付提示',
      content: '只做支付演示，确认支付？',
      complete() {
        //模拟支付
        setTimeout(function(){

          wx.request({
            url: app.globalData.api.toPay,
            method: "POST",
            header: { "Content-Type": "application/x-www-form-urlencoded" },
            data: {
              sn: sn,
              orderId: order.orderId
            },
            success(res) {
              self.getOrderList();
              //更新用户积分
              self.getIntegral();
            }
          })

        },1000)
      }
    })
  },
  /**
   * 发起退款请求
   */
  refundOrders(e) {
    var self = this;
    var order = this.data.orderList[e.currentTarget.dataset.index];
    var sn = wx.getStorageSync("sn");
    wx.showModal({
      title: '提示',
      content: '即将发起退款申请，确定要申请退款？',
      complete() {
        //模拟支付
        wx.request({
          url: app.globalData.api.toRefund,
          method: "POST",
          header: { "Content-Type": "application/x-www-form-urlencoded" },
          data: {
            sn: sn,
            orderId: order.orderId
          },
          success(res) {
            self.getOrderList();
            //更新用户积分
            self.getIntegral();
          }
        })
      }
    })
  },
  getUserInfo: function (e) {
    //console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
    this.loinServer();
  },
  loinServer: function (){
    var self = this;
    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        wx.request({
          url: app.globalData.api.sn,
          data: {
            code: res.code,
            nickName: app.globalData.userInfo.nickName,
            gender: app.globalData.userInfo.gender
          },
          success: function (resSn) {
            wx.setStorageSync('sn', resSn.data.data.sn);
            //获取详情用户信息
            self.getDetailUserInfo();
            //更新用户积分
            self.getIntegral();
            self.getOrderList();
          }
        })
      }
    })
  },
  //获取详细用户信息
  getDetailUserInfo(){
    var self = this;
    var userInfo = wx.getStorageSync('detailUserInfo');
    //缓存没有则从服务器获取
    var sn = wx.getStorageSync('sn');
    if(sn != ''){
      wx.request({
        url: app.globalData.api.userInfo,
        data: {
          sn: sn
        }, success(res) {
          self.setData({
            detailUserInfo: res.data.data
          })
          wx.setStorageSync("detailUserInfo", res.data.data);
        }
      })
    }
  },
  //获取积分
  getIntegral(){
    var self = this;
    wx.getStorage({
      key: 'sn',
      success: function(res) {

        wx.request({
          url: app.globalData.api.userIntegral,
          data: {
            sn: res.data
          },success(res){
            self.setData({
              integral: res.data.data
            })
          }
        })
      },
    })
  },
  /**
   * 自动登录
   */
  autoLogin(){
    var self = this;
    wx.getStorage({
      key: 'sn',
      fail: function () {
        if (app.globalData.userInfo) {
          self.loinServer();
        }
      }
    })
  }
})