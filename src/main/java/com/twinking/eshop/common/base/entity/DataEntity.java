package com.twinking.eshop.common.base.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.twinking.eshop.common.utils.IdGenUtils;
import com.twinking.eshop.modules.user.entity.User;
import com.twinking.eshop.modules.user.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:  Entity父类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/9/26 09 39
 */
public abstract class DataEntity<T> extends BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 删除标记（0：正常；1：删除；2：审核）
     */
    protected String remarks;
    /**
     * 创建者
     */
    protected User createBy;
    /**
     * 创建日期
     */
    protected Date createDate;
    /**
     * 更新者
     */
    protected User updateBy;
    /**
     * 更新日期
     */
    protected Date updateDate;
    /**
     * 删除标记（0：正常；1：删除[ 如果是重要数据，则表示伪删除 ]）
     */
    protected String stateFlag;

    public DataEntity() {
        super();
        this.stateFlag = STATE_FLAG_NORMAL;
    }

    public DataEntity(String id) {
        super(id);
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert(){
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        if (!this.isNewRecord){
            setId(IdGenUtils.uuid());
        }
        User user = UserUtils.getCurrentUser();
        if (user != null && StringUtils.isNotBlank(user.getId())){
            this.updateBy = user;
            this.createBy = user;
        }
        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate(){
        User user = UserUtils.getCurrentUser();
        if (user != null && StringUtils.isNotBlank(user.getId())){
            this.updateBy = user;
        }
        this.updateDate = new Date();
    }

    @Length(min=0, max=255)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonIgnore
    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonIgnore
    public User getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @JsonIgnore
    @Length(min=1, max=1)
    public String getStateFlag() {
        return stateFlag;
    }

    public void setStateFlag(String stateFlag) {
        this.stateFlag = stateFlag;
    }
}
