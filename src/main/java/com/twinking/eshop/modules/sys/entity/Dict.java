package com.twinking.eshop.modules.sys.entity;

import com.twinking.eshop.common.base.entity.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @Description:   字典实体类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/11 09 26
 */
public class Dict extends DataEntity<Dict> {
    /**
     * 数据值
     */
	private String value;
    /**
     * 标签名
     */
	private String label;
    /**
     *  类型
     */
	private String type;
    /**
     * 描述
     */
	private String description;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 父级Id
     */
	private String parentId;

	public Dict() {
		super();
	}
	
	public Dict(String id){
		super(id);
	}
	
	public Dict(String value, String label){
		this.value = value;
		this.label = label;
	}
	
	@XmlAttribute
	@Length(min=1, max=100)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@XmlAttribute
	@Length(min=1, max=100)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Length(min=1, max=100)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlAttribute
	@Length(min=0, max=100)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Length(min=1, max=100)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Override
	public String toString() {
		return label;
	}
}