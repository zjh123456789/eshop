const app = getApp()
Page({
  data:{
    picture : '',
    _type : 1,
    goodsList: {
      name: "",
      picture: "",
      list: []
    }
  },
  // 页面初始化 options为页面跳转所带来的参数
  onLoad:function(options){
    var self = this;
    self.setData({
      _type: options.type,
      picture: '/image/' + options.picture
    })
    
    if (self.data._type == 1){
      // 获取推荐商品
      self.getGoodsList(app.globalData.api.recommendList);
    } else if (self.data._type == 2) {
      // 获取首发商品
      self.getGoodsList(app.globalData.api.firstList);
    } else if (self.data._type == 3) {
      // 获取限时抢购商品
      self.getGoodsList(app.globalData.api.rushList);
    }
  },
  onReady:function(){
    // 页面渲染完成
  },
  onShow:function(){
    // 页面显示
  },
  onHide:function(){
    // 页面隐藏
  },
  onUnload:function(){
    // 页面关闭
  },
  getGoodsList(reqUrl){
    var self = this;
    wx.request({
      url: reqUrl,
      success(res) {
        self.setData({
          goodsList: res.data.data
        })
      }
    })
  }
})