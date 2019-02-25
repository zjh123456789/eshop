package com.twinking.eshop.modules.sys.service;

import com.twinking.eshop.common.base.service.AbstractService;
import com.twinking.eshop.modules.sys.dao.RoleDao;
import com.twinking.eshop.modules.sys.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Description:  TODO 角色业务层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/11 09 26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleService extends AbstractService<RoleDao, Role> {

    @Autowired
    private RoleDao roleDao;

}
