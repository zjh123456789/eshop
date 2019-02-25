package com.twinking.eshop.modules.sys.web;

import com.twinking.eshop.common.base.web.BaseController;
import com.twinking.eshop.modules.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:  TODO 角色控制层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/11 09 26
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

}
