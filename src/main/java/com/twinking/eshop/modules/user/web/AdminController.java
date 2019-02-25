package com.twinking.eshop.modules.user.web;

import com.google.common.collect.Lists;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:  管理员控制层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/10 09 26
 */
@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 管理员列表
     */
    @RequestMapping(value = {"list", ""} )
    public String list(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<User> page = userService.findAdminPage(user, request, response);
        model.addAttribute("page", page);
        return "modules/user/adminList";
    }


    /**
     * 查看，编辑管理员
     */
    @RequestMapping(value = "form")
    public String form(User user, Model model) {
        if(StringUtils.isNotBlank(user.getId())){
            user = userService.get(user.getId());
        }
        model.addAttribute("user", user);
        return "modules/user/adminForm";
    }

    /**
     * 编辑，新增管理员
     */
    @RequestMapping(value = "save")
    public String save(User user, Model model, RedirectAttributes redirectAttributes) throws Exception {
        if (!beanValidator(model, user)){
            return form(user, model);
        }
        String message = userService.save0rUpdateUser(user,null);
        addMessage(redirectAttributes, message);
        return "redirect:/admin";
    }

    /**
     * 删除管理员
     */
    @RequestMapping(value = "delete")
    public String delete(User user, RedirectAttributes redirectAttributes) {
        String message = userService.deleteAdmin(user);
        addMessage(redirectAttributes, "删除管理员成功");
        return "redirect:/admin";
    }

    /**
     * 禁用
     */
    @RequestMapping(value = "ban")
    public String ban(User user, RedirectAttributes redirectAttributes) {
        if(Constants.USER_SUPER_ADMIN_ID.equals(user.getId())) {
            addMessage(redirectAttributes, "无法禁用超级管理员");
            return "redirect:/admin";
        }
        userService.ban(user);
        addMessage(redirectAttributes, "禁用成功");
        return "redirect:/admin";
    }

    /**
     * 启用
     */
    @RequestMapping(value = "use")
    public String use(User user, RedirectAttributes redirectAttributes) {
        if(Constants.USER_SUPER_ADMIN_ID.equals(user.getId())) {
            addMessage(redirectAttributes, "无法启用超级管理员");
            return "redirect:/admin";
        }
        userService.use(user);
        addMessage(redirectAttributes, "启用成功");
        return "redirect:/admin";
    }

    /**
     * 批量删除
     */
    @RequestMapping(value = "deleteAll")
    public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
        int effect = userService.deleteAll(ids);
        addMessage(redirectAttributes, "批量删除 " +effect+ " 条数据成功");
        return "redirect:/admin";
    }

    /**
     * 导出excel文件
     */
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "管理员"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<User> page = userService.findPage(new Page<User>(request, response, -1), user);
            new ExportExcelUtils("管理员", User.class).setDataList(page.getList()).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出管理员记录失败！失败信息："+e.getMessage());
        }
        return "redirect:/admin";
    }

    /**
     * 导入Excel数据
     */
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
        String message = userService.importAdmin(file);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/admin";
    }

    /**
     * 下载导入数据模板
     */
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "管理员数据导入模板.xlsx";
            List<User> list = Lists.newArrayList();
            new ExportExcelUtils("数据", User.class, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
        }
        return "redirect:/admin";
    }
}
