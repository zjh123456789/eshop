package com.twinking.eshop.common.base.service;

import com.twinking.eshop.common.base.dao.AbstractDao;
import com.twinking.eshop.common.base.entity.DataEntity;
import com.twinking.eshop.common.base.entity.Page;
import com.twinking.eshop.common.exceptions.UnauthorizedException;
import com.twinking.eshop.common.utils.StringUtils;
import com.twinking.eshop.modules.order.entity.Order;
import com.twinking.eshop.modules.user.dao.UserDao;
import com.twinking.eshop.modules.user.entity.User;
import com.twinking.eshop.modules.user.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Service基类
 * @Autuor twinking
 * @version 2018年10月17日14:33:21
 */
@Transactional(readOnly = true, rollbackFor = Exception.class)
public abstract class AbstractService<D extends AbstractDao<T>, T extends DataEntity<T>> {
	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return dao.get(id);
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.get(entity);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(T entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
	
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(T entity) {
		dao.delete(entity);
	}

	/**
	 * 禁用数据
	 * @param entity
	 */
	@Transactional(rollbackFor = Exception.class)
	public void ban(T entity) {
		dao.deleteByLogic(entity.getId());
	}

	/**
	 * 启用数据
	 * @param entity
	 */
	@Transactional(rollbackFor = Exception.class)
	public void use(T entity) {
		dao.use(entity.getId());
	}
	
	
	/**
	 * 删除全部数据
	 * @param entitys
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteAll(Collection<T> entitys) {
		for (T entity : entitys) {
			dao.delete(entity);
		}
	}

	
	/**
	 * 获取单条数据
	 * @param propertyName
	 * @return
	 */
	public T findUniqueByProperty(String propertyName, Object value){
		return dao.findUniqueByProperty(propertyName, value);
	}


    /**
	 * api模块
     * 校验参数是否不为空 为空则抛出空指针异常
     * @param args
     */
	public void validationParameters(String... args) throws NullPointerException {
	    for (String s : args){
	        if(StringUtils.isBlank(s)){
	            throw new NullPointerException();
            }
        }
    }

	/**
	 * api模块
	 * 根据sn获取用户
	 * @param sn
	 * @throws UnauthorizedException sn不合法
	 */
    public User getUserBySN (String sn, UserDao userDao) throws UnauthorizedException {
		User user = UserUtils.getUserCacheByKey(sn);
		if(user == null){
			User u = new User();
			u.setSn(sn);
			user = userDao.getUserBySN(u);
			if(user == null){
				throw new UnauthorizedException("sn "+ sn +" 未找到用户");
			}
            UserUtils.addUserToCache(sn, user);
			return user;
		}
		return user;
	}

    /**
	 * api模块
     * 确保订单属于该用户
     * @param sn 用户标识
     * @param user 用户
     * @param order 订单
     * @throws UnauthorizedException sn不合法
     */
	public void ensureOrdersBelongToUser(String sn, User user, Order order) throws UnauthorizedException{
        if(!user.getId().equals(order.getUserId())){
            throw new UnauthorizedException("sn "+ sn +" 身份与订单不匹配");
        }
    }
	
}
