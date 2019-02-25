package com.twinking.eshop.modules.goods.web;

import com.google.common.collect.Lists;
import com.twinking.eshop.common.base.entity.Page;
import com.twinking.eshop.common.base.web.BaseController;
import com.twinking.eshop.common.utils.DateUtils;
import com.twinking.eshop.common.utils.StringUtils;
import com.twinking.eshop.common.utils.excel.ExportExcelUtils;
import com.twinking.eshop.modules.goods.entity.Goods;
import com.twinking.eshop.modules.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:  商品控制层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 23 13
 */
@Controller
@RequestMapping("goods")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 商品列表
     */
    @RequestMapping(value = {"list", ""} )
    public String list(Goods goods, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Goods> page = goodsService.findPage(new Page<Goods>(request, response), goods);
        model.addAttribute("page", page);
        return "modules/goods/goodsList";
    }


    /**
     * 查看，增加，编辑表单
     */
    @RequestMapping(value = "form")
    public String form(Goods goods, Model model) {
        if(StringUtils.isNotBlank(goods.getId())){
            goods = goodsService.get(goods.getId());
        }
        model.addAttribute("goods", goods);
        return "modules/goods/goodsForm";
    }

    /**
     * 保存表单
     */
    @RequestMapping(value = "save")
    public String save(Goods goods, Model model, RedirectAttributes redirectAttributes) throws Exception {
        if (!beanValidator(model, goods)){
            return form(goods, model);
        }
        goodsService.saveGoods(goods);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:/goods";
    }

    /**
     * 删除数据
     */
    @RequestMapping(value = "delete")
    public String delete(Goods goods, RedirectAttributes redirectAttributes) {
        goodsService.delete(goods);
        addMessage(redirectAttributes, "删除商品成功");
        return "redirect:/goods";
    }

    /**
     * 禁用
     */
    @RequestMapping(value = "ban")
    public String ban(Goods goods, RedirectAttributes redirectAttributes) {
        goodsService.ban(goods);
        addMessage(redirectAttributes, "禁用成功");
        return "redirect:/goods";
    }

    /**
     * 启用
     */
    @RequestMapping(value = "use")
    public String use(Goods goods, RedirectAttributes redirectAttributes) {
        goodsService.use(goods);
        addMessage(redirectAttributes, "启用成功");
        return "redirect:/goods";
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "deleteAll")
    public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
        int effect = goodsService.deleteAll(ids);
        addMessage(redirectAttributes, "批量删除 " +effect+ " 条数据成功");
        return "redirect:/goods";
    }

    /**
     * 导出excel文件
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(Goods goods, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "商品"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Goods> page = goodsService.findPage(new Page<Goods>(request, response, -1), goods);
            new ExportExcelUtils("商品", Goods.class).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出商品记录失败！失败信息："+e.getMessage());
        }
        return "redirect:/goods";
    }

    /**
     * 导入Excel数据
     */
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        String message = goodsService.importGoods(file);
        addMessage(redirectAttributes, message);
        return "redirect:/goods";
    }

    /**
     * 下载导入数据模板
     */
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "商品数据导入模板.xlsx";
            List<Goods> list = Lists.newArrayList();
            new ExportExcelUtils("数据", Goods.class, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
        }
        return "redirect:/goods";
    }
}
