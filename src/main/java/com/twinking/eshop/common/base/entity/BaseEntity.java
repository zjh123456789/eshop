package com.twinking.eshop.common.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import com.twinking.eshop.common.utils.StringUtils;
import com.twinking.eshop.modules.user.entity.User;
import com.twinking.eshop.modules.user.utils.UserUtils;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Map;

/**
 * @Description:  Entity支持类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/9/26 09 39
 */
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 删除标记（0：正常；1：禁用 ；）
     */
    public static final String STATE_FLAG_NORMAL = "0";
    public static final String STATE_FLAG_DELETE = "1";

    /**
     * 实体编号（唯一标识）
     */
    protected String id;

    /**
     * 当前用户
     */
    protected User currentUser;

    /**
     * 当前实体分页对象
     */
    protected Page<T> page;

    /**
     * 自定义SQL（SQL标识，SQL内容）
     */
    protected Map<String, String> sqlMap;

    /**
     * 插入之前执行方法，子类实现
     */
    public abstract void preInsert();

    /**
     * 更新之前执行方法，子类实现
     */
    public abstract void preUpdate();

    public boolean getIsNewRecord() {
        return isNewRecord || StringUtils.isBlank(getId());
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }

    /**
     * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     */
    protected boolean isNewRecord = false;

    public BaseEntity() {

    }

    public BaseEntity(String id) {
        this();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取当前用户
     * @return
     */
    @JsonIgnore
    @XmlTransient
    public User getCurrentUser() {
        if(currentUser == null){
            currentUser = UserUtils.getCurrentUser();
        }
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @JsonIgnore
    @XmlTransient
    public Page<T> getPage() {
        if (page == null){
            page = new Page<T>();
        }
        return page;
    }

    public Page<T> setPage(Page<T> page) {
        this.page = page;
        return page;
    }

    /**
     * 获取自定义SQL
     * @return
     */
    @JsonIgnore
    @XmlTransient
    public Map<String, String> getSqlMap() {
        if (sqlMap == null){
            sqlMap = Maps.newHashMap();
        }
        return sqlMap;
    }

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }

}
