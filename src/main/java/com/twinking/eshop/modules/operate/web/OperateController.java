package com.twinking.eshop.modules.operate.web;

import com.twinking.eshop.common.base.entity.Page;
import com.twinking.eshop.common.base.web.BaseController;
import com.twinking.eshop.common.utils.DateUtils;
import com.twinking.eshop.common.utils.StringUtils;
import com.twinking.eshop.common.utils.excel.ExportExcelUtils;
import com.twinking.eshop.modules.operate.entity.Operate;
import com.twinking.eshop.modules.operate.service.OperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:  运营控制层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/20 13 29
 */
@Controller
@RequestMapping("operate")
public class OperateController extends BaseController {

    @Autowired
    private OperateService operateService;

    /**
     * 待出售商品列表
     */
    @RequestMapping(value = {"list", ""} )
    public String list(Operate operate, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Operate> page = operateService.findOperatePage(operate, request, response);
        model.addAttribute("page", page);
        return "modules/operate/operateList";
    }


    /**
     * 查看，增加，编辑表单
     */
    @RequestMapping(value = "form")
    public String form(Operate operate, Model model) {
        if(StringUtils.isNotBlank(operate.getId())){
            operate = operateService.get(operate.getId());
        }
        model.addAttribute("operate", operate);
        return "modules/operate/operateForm";
    }

    /**
     * 保存表单页面
     */
    @RequestMapping(value = "save")
    public String save(Operate operate, Model model, RedirectAttributes redirectAttributes) throws Exception {
        if (!beanValidator(model, operate)){
            return form(operate, model);
        }
       operateService.saveOperate(operate);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:/operate";
    }

    /**
     * 删除数据
     */
    @RequestMapping(value = "delete")
    public String delete(Operate operate, RedirectAttributes redirectAttributes) {
        operateService.delete(operate);
        addMessage(redirectAttributes, "删除待出售商品成功");
        return "redirect:/operate";
    }

    /**
     * 禁用
     */
    @RequestMapping(value = "ban")
    public String ban(Operate operate, RedirectAttributes redirectAttributes) {
        operateService.ban(operate);
        addMessage(redirectAttributes, "禁用成功");
        return "redirect:/operate";
    }

    /**
     * 启用
     */
    @RequestMapping(value = "use")
    public String use(Operate operate, RedirectAttributes redirectAttributes) {
        operateService.use(operate);
        addMessage(redirectAttributes, "启用成功");
        return "redirect:/operate";
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "deleteAll")
    public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
        int effect = operateService.deleteAll(ids);
        addMessage(redirectAttributes, "批量删除 " +effect+ " 条数据成功");
        return "redirect:/operate";
    }

    /**
     * 导出excel文件
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(Operate operate, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "待出售商品"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Operate> page = operateService.findPage(new Page<Operate>(request, response, -1), operate);
            new ExportExcelUtils("待出售商品", Operate.class).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出待出售商品记录失败！失败信息："+e.getMessage());
        }
        return "redirect:/operate";
    }

 
}
