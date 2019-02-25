package com.twinking.eshop.modules.sys.entity;

import com.twinking.eshop.common.base.entity.DataEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description:   用户角色实体类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/11 09 26
 */
public class Role extends DataEntity<Role> {
    /**
     * 角色名
     */
    private String name;
    /**
     * 拥有的菜单
     */
    private List<Menu> menus = new ArrayList<>();

    public Role() {
    }

    public Role(String id) {
        super(id);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
