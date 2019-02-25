package com.twinking.eshop.modules.goods.web;

import com.twinking.eshop.common.base.entity.Page;
import com.twinking.eshop.common.base.web.BaseController;
import com.twinking.eshop.common.constant.Constants;
import com.twinking.eshop.common.utils.StringUtils;
import com.twinking.eshop.modules.goods.entity.GoodsType;
import com.twinking.eshop.modules.goods.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:  商品类型控制层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 23 13
 */
@Controller
@RequestMapping("goodsType")
public class GoodsTypeController extends BaseController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * 商品类型列表
     */
    @RequestMapping(value = {"list", ""} )
    public String list(GoodsType goodsType, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<GoodsType> page = goodsTypeService.findPage(new Page<GoodsType>(request, response), goodsType);
        model.addAttribute("page", page);
        return "modules/goods/goodsTypeList";
    }


    /**
     * 查看，增加，编辑表单
     */
    @RequestMapping(value = "form")
    public String form(GoodsType goodsType, Model model) {
        if(StringUtils.isNotBlank(goodsType.getId())){
            goodsType = goodsTypeService.get(goodsType.getId());
        }
        model.addAttribute("goodsType", goodsType);
        return "modules/goods/goodsTypeForm";
    }

    /**
     * 保存表单页面
     */
    @RequestMapping(value = "save")
    public String save(GoodsType goodsType, Model model, RedirectAttributes redirectAttributes) throws Exception {
        if (!beanValidator(model, goodsType)){
            return form(goodsType, model);
        }
        String message = goodsTypeService.saveGoodsType(goodsType);
        addMessage(redirectAttributes, message);
        return "redirect:/goodsType";
    }

    /**
     * 删除数据
     */
    @RequestMapping(value = "delete")
    public String delete(GoodsType goodsType, RedirectAttributes redirectAttributes) {
        if(Constants.GOODS_UNSORET_GOODS_TYPE_ID.equals(goodsType.getId())) {
            addMessage(redirectAttributes, "不允许删除未分类类型");
            return "redirect:/goodsType";
        }
        goodsTypeService.delete(goodsType);
        addMessage(redirectAttributes, "删除商品类型成功");
        return "redirect:/goodsType";
    }

    /**
     * 禁用
     */
    @RequestMapping(value = "ban")
    public String ban(GoodsType goodsType, RedirectAttributes redirectAttributes) {
        if(Constants.GOODS_UNSORET_GOODS_TYPE_ID.equals(goodsType.getId())) {
            addMessage(redirectAttributes, "不允许禁用未分类类型");
            return "redirect:/goodsType";
        }
        goodsTypeService.ban(goodsType);
        addMessage(redirectAttributes, "禁用成功");
        return "redirect:/goodsType";
    }

    /**
     * 启用
     */
    @RequestMapping(value = "use")
    public String use(GoodsType goodsType, RedirectAttributes redirectAttributes) {
        if(Constants.GOODS_UNSORET_GOODS_TYPE_ID.equals(goodsType.getId())) {
            addMessage(redirectAttributes, "无法启用未分类类型");
            return "redirect:/goodsType";
        }
        goodsTypeService.use(goodsType);
        addMessage(redirectAttributes, "启用成功");
        return "redirect:/goodsType";
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "deleteAll")
    public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
        int effect = goodsTypeService.deleteAll(ids);
        addMessage(redirectAttributes, "批量删除 " +effect+ " 条数据成功");
        return "redirect:/goodsType";
    }
    
}
