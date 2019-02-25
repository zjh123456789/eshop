const app = getApp()
Page({
    data: {
        category: [],
        categoryCount:0,
        curIndex: 0,
        isScroll: false,
        toView: ''
    },
    onReady(){
      var self = this;
      //获取商品类别
      wx.request({
        url: app.globalData.api.goodsTypeList,
        success(res) {
          self.setData({
            category: res.data.data,
            categoryCount: res.data.data.typeList.length,
            toView: 'cid_' + res.data.data.typeList[0].id
          })
        }
      })
    },
    switchTab(e){
      //console.log(e)
      const self = this;
      this.setData({
        isScroll: true
      })
      setTimeout(function(){
        self.setData({
          toView: e.target.dataset.id,
          curIndex: e.target.dataset.index
        })
      },0)
      setTimeout(function () {
        self.setData({
          isScroll: false
        })
      },1)
    }
    
})