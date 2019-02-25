package com.twinking.eshop.modules.sys.dao;

import com.twinking.eshop.common.base.dao.AbstractDao;
import com.twinking.eshop.common.base.dao.MyBatisDao;
import com.twinking.eshop.modules.sys.entity.Menu;

import java.util.List;

/**
 * @Description:   菜单持久层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/11 09 26
 */
@MyBatisDao
public interface MenuDao extends AbstractDao<Menu> {

    public List<Menu> findByParentIdsLike(Menu menu);

    public int updateParentIds(Menu menu);
}
