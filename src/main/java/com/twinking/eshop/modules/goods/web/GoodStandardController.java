package com.twinking.eshop.modules.goods.web;

import com.twinking.eshop.common.base.entity.Page;
import com.twinking.eshop.common.base.web.BaseController;
import com.twinking.eshop.common.constant.Constants;
import com.twinking.eshop.common.utils.MyBeanUtils;
import com.twinking.eshop.common.utils.StringUtils;
import com.twinking.eshop.modules.goods.entity.GoodsStandard;
import com.twinking.eshop.modules.goods.service.GoodsStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:  商品规格控制层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 23 13
 */
@Controller
@RequestMapping("goodsStandard")
public class GoodStandardController extends BaseController {
    
    @Autowired
    private GoodsStandardService goodsStandardService;

    /**
     * 商品规格列表
     */
    @RequestMapping(value = {"list", ""} )
    public String list(GoodsStandard goodsStandard, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<GoodsStandard> page = goodsStandardService.findPage(new Page<GoodsStandard>(request, response), goodsStandard);
        model.addAttribute("page", page);
        return "modules/goods/goodsStandardList";
    }


    /**
     * 查看，增加，编辑表单
     */
    @RequestMapping(value = "form")
    public String form(GoodsStandard goodsStandard, Model model) {
        if(StringUtils.isNotBlank(goodsStandard.getId())){
            goodsStandard = goodsStandardService.get(goodsStandard.getId());
        }
        model.addAttribute("goodsStandard", goodsStandard);
        return "modules/goods/goodsStandardForm";
    }

    /**
     * 保存表单页面
     */
    @RequestMapping(value = "save")
    public String save(GoodsStandard goodsStandard, Model model, RedirectAttributes redirectAttributes) throws Exception {
        if (!beanValidator(model, goodsStandard)){
            return form(goodsStandard, model);
        }
        String message = goodsStandardService.saveGoodsStandard(goodsStandard);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:/goodsStandard";
    }

    /**
     * 删除数据
     */
    @RequestMapping(value = "delete")
    public String delete(GoodsStandard goodsStandard, RedirectAttributes redirectAttributes) {
        if(Constants.GOODS_UNDEFINED_GOODS_STANDARD_ID.equals(goodsStandard.getId())) {
            addMessage(redirectAttributes, "无法删除未定义规格");
            return "redirect:/goodsStandard";
        }
        goodsStandardService.delete(goodsStandard);
        addMessage(redirectAttributes, "删除商品规格成功");
        return "redirect:/goodsStandard";
    }

    /**
     * 禁用
     */
    @RequestMapping(value = "ban")
    public String ban(GoodsStandard goodsStandard, RedirectAttributes redirectAttributes) {
        if(Constants.GOODS_UNDEFINED_GOODS_STANDARD_ID.equals(goodsStandard.getId())) {
            addMessage(redirectAttributes, "无法禁用未定义规格");
            return "redirect:/goodsStandard";
        }
        goodsStandardService.ban(goodsStandard);
        addMessage(redirectAttributes, "禁用成功");
        return "redirect:/goodsStandard";
    }

    /**
     * 启用
     */
    @RequestMapping(value = "use")
    public String use(GoodsStandard goodsStandard, RedirectAttributes redirectAttributes) {
        if(Constants.GOODS_UNDEFINED_GOODS_STANDARD_ID.equals(goodsStandard.getId())) {
            addMessage(redirectAttributes, "无法启用未定义规格");
            return "redirect:/goodsStandard";
        }
        goodsStandardService.use(goodsStandard);
        addMessage(redirectAttributes, "启用成功");
        return "redirect:/goodsStandard";
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "deleteAll")
    public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
        int effect = goodsStandardService.deleteAll(ids);
        addMessage(redirectAttributes, "批量删除 " +effect+ " 条数据成功");
        return "redirect:/goodsStandard";
    }
}
