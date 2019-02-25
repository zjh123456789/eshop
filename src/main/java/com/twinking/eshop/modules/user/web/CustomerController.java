package com.twinking.eshop.modules.user.web;

import com.twinking.eshop.common.base.entity.Page;
import com.twinking.eshop.common.base.web.BaseController;
import com.twinking.eshop.common.constant.Constants;
import com.twinking.eshop.common.utils.DateUtils;
import com.twinking.eshop.common.utils.StringUtils;
import com.twinking.eshop.common.utils.excel.ExportExcelUtils;
import com.twinking.eshop.modules.user.entity.User;
import com.twinking.eshop.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:  会员管理控制层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/10 09 26
 */
@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 会员列表
     */
    @RequestMapping(value = {"list", ""} )
    public String list(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<User> page = userService.findCustomerPage(new Page<User>(request, response), user);
        model.addAttribute("page", page);
        return "modules/user/customerList";
    }


    /**
     * 查看，编辑会员
     */
    @RequestMapping(value = "form")
    public String form(User user, Model model) {
        if(StringUtils.isNotBlank(user.getId())){
            user = userService.get(user.getId());
        }
        model.addAttribute("user", user);
        return "modules/user/customerForm";
    }

    /**
     * 保存表单页面
     */
    @RequestMapping(value = "save")
    public String save(User user, Model model, RedirectAttributes redirectAttributes) throws Exception {
        if (!beanValidator(model, user)){
            return form(user, model);
        }
        String message = userService.save0rUpdateUser(user,Constants.ROLE_CUSTOMER_ROLE_ID);
        addMessage(redirectAttributes, "保存成功");
        return "redirect:/customer";
    }

    /**
     * 禁用
     */
    @RequestMapping(value = "ban")
    public String ban(User user, RedirectAttributes redirectAttributes) {
        userService.ban(user);
        addMessage(redirectAttributes, "禁用成功");
        return "redirect:/customer";
    }

    /**
     * 启用
     */
    @RequestMapping(value = "use")
    public String use(User user, RedirectAttributes redirectAttributes) {
        userService.use(user);
        addMessage(redirectAttributes, "启用成功");
        return "redirect:/customer";
    }

    /**
     * 查看，增加，编辑表单
     */
    @RequestMapping(value = "delete")
    public String delete(User user, RedirectAttributes redirectAttributes) {
        userService.delete(user);
        addMessage(redirectAttributes, "删除会员成功");
        return "redirect:/customer";
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
            userService.delete(new User(id));
        }
        addMessage(redirectAttributes, "批量删除 " +effect+ " 条数据成功");
        return "redirect:/customer";
    }

    /**
     * 导出excel文件
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "会员"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<User> page = userService.findCustomerPage(new Page<User>(request, response, -1), user);
            new ExportExcelUtils("会员", User.class).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出会员记录失败！失败信息："+e.getMessage());
        }
        return "redirect:/customer";
    }

}
