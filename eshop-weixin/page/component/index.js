const app = getApp()
Page({
  data: {
    imgUrls: [
      '/image/list3.png',
      '/image/list2.png',
      '/image/list4.png'
    ],
    indicatorDots: false,
    autoplay: false,
    interval: 3000,
    duration: 800,
    goodsList: []
  },
  onLoad : function(){
    var self = this;
    //获取首页最近新品信息
    wx.request({
      url: app.globalData.api.newList,
      success(res) {
        self.setData({
          goodsList: res.data.data
        })
      }
    })
  }
})