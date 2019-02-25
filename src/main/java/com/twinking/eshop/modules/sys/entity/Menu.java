package com.twinking.eshop.modules.sys.entity;

import com.twinking.eshop.common.base.entity.DataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:   菜单实体类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/11 09 26
 */
public class Menu extends DataEntity<Menu> {
    /**
     * 父级菜单
     */
    private Menu parent;
    /**
     * 所有父级编号
     */
    private String parentIds;
    /**
     * 父级菜单
     */
    private List<Menu> children = new ArrayList<>();
    /**
     * 父级id
     */
    private String parentId;
    /**
     * 标题
     */
    private String title;
    /**
     * 跳转链接
     */
    private String href;

    public Menu (){

    }

    public Menu(String id) {
        super(id);
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
