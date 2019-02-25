package com.twinking.eshop.modules.integral.web;

import com.twinking.eshop.common.base.entity.Page;
import com.twinking.eshop.common.base.web.BaseController;
import com.twinking.eshop.common.utils.DateUtils;
import com.twinking.eshop.common.utils.StringUtils;
import com.twinking.eshop.common.utils.excel.ExportExcelUtils;
import com.twinking.eshop.modules.integral.entity.Integral;
import com.twinking.eshop.modules.integral.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:  积分详情控制层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 23 13
 */
@Controller
@RequestMapping("integral")
public class IntegralController extends BaseController {

    @Autowired
    private IntegralService integralService;

    /**
     * 积分详情列表
     */
    @RequestMapping(value = {"list", ""} )
    public String list(Integral integral, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Integral> page = integralService.findPage(new Page<Integral>(request, response), integral);
        model.addAttribute("page", page);
        return "modules/integral/integralList";
    }

    /**
     * 查看某用户积分详情列表
     */
    @RequestMapping(value = "detailIntegralList" )
    public String detailIntegralList(String userId, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Integral> list = integralService.findAllListByUserId(userId);
        model.addAttribute("list", list);
        return "modules/integral/integralDetailList";
    }

    /**
     * 查看，增加，编辑表单
     */
    @RequestMapping(value = "form")
    public String form(Integral integral, Model model) {
        if(StringUtils.isNotBlank(integral.getId())){
            integral = integralService.get(integral.getId());
        }
        model.addAttribute("integral", integral);
        return "modules/integral/integralForm";
    }

    /**
     * 删除数据
     */
    @RequestMapping(value = "delete")
    public String delete(Integral integral, RedirectAttributes redirectAttributes) {
        integralService.delete(integral);
        addMessage(redirectAttributes, "删除积分详情成功");
        return "redirect:/integral";
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "deleteAll")
    public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
        String idArray[] =ids.split(",");
        int effect = 0;
        for(String id : idArray){
            effect++;
            integralService.delete(new Integral(id));
        }
        addMessage(redirectAttributes, "批量删除 " +effect+ " 条数据成功");
        return "redirect:/integral";
    }

    /**
     * 导出excel文件
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(Integral integral, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "积分详情"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Integral> page = integralService.findPage(new Page<Integral>(request, response, -1), integral);
            new ExportExcelUtils("积分详情", Integral.class).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出积分详情记录失败！失败信息："+e.getMessage());
        }
        return "redirect:/integral";
    }

}
