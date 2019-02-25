package com.twinking.eshop.common.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.twinking.eshop.common.constant.Constants;
import com.twinking.eshop.common.redis.JedisUtils;
import com.twinking.eshop.modules.goods.dao.GoodsDao;
import com.twinking.eshop.modules.goods.dao.GoodsStandardDao;
import com.twinking.eshop.modules.goods.dao.GoodsTypeDao;
import com.twinking.eshop.modules.goods.entity.Goods;
import com.twinking.eshop.modules.goods.entity.GoodsStandard;
import com.twinking.eshop.modules.goods.entity.GoodsType;
import com.twinking.eshop.modules.order.enums.FinishStateEnum;
import com.twinking.eshop.modules.order.enums.TradeStateEnum;
import com.twinking.eshop.modules.sys.dao.DictDao;
import com.twinking.eshop.modules.sys.dao.RoleDao;
import com.twinking.eshop.modules.sys.entity.Dict;
import com.twinking.eshop.modules.sys.entity.Role;
import org.apache.commons.lang3.StringUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 字典工具类
 * @Autuor  朱景辉（1229585753@qq.com）
 * @Date 2018/9/26 09 39
 */
public class DictUtils {
	
	private static DictDao dictDao = SpringContextHolderUtils.getBean(DictDao.class);
    private static RoleDao roleDao = SpringContextHolderUtils.getBean(RoleDao.class);
    private static GoodsTypeDao goodsTypeDao = SpringContextHolderUtils.getBean(GoodsTypeDao.class);
	private static GoodsStandardDao goodsStandardDao = SpringContextHolderUtils.getBean(GoodsStandardDao.class);
    private static GoodsDao goodsDao = SpringContextHolderUtils.getBean(GoodsDao.class);

	public static final String CACHE_DICT_MAP = "dictMap";
	
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<Dict> getDictList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)JedisUtils.getObject(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())){
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			JedisUtils.setObject(CACHE_DICT_MAP, dictMap,0);//永不超时
		}
		List<Dict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}

	/*
	 * 反射根据对象和属性名获取属性值
	 */
	public static Object getValue(Object obj, String filed) {
		try {
			Class clazz = obj.getClass();
			PropertyDescriptor pd = new PropertyDescriptor(filed, clazz);
			Method getMethod = pd.getReadMethod();//获得get方法

			if (pd != null) {

				Object o = getMethod.invoke(obj);//执行get方法返回一个Object
				return o;

			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**自定义*/

	/**
	 * 获取男女选项
	 * @return
	 */
	public static List getSex(){
		List<Dict> list = new ArrayList<>();
		Dict man = new Dict("1","男");
		Dict women = new Dict("0","女");
		list.add(man);
		list.add(women);
		return list;
	}

	/**
	 * 获取角色选项 暂用
	 * @return
	 */
	public static List getRole(){
		List<Dict> list = new ArrayList<>();
		List<Role> roles = roleDao.findList(new Role());
		if(roles != null){
            for (int i = 0; i < roles.size(); i++) {
            	if(Constants.ROLE_CUSTOMER_ROLE_ID.equals(roles.get(i).getId())){
            		continue;
				}
                Dict admin = new Dict(roles.get(i).getId(), roles.get(i).getName());
                list.add(admin);
            }
        }
		return list;
	}

    /**
     * 获取商品类型
     * @return
     */
    public static List getGoodsType(){
        List<Dict> list = new ArrayList<>();
        List<GoodsType> goodsTypes = goodsTypeDao.findList(new GoodsType());
        if(goodsTypes != null){
            for (int i = 0; i < goodsTypes.size(); i++) {
                list.add(new Dict(goodsTypes.get(i).getId(), goodsTypes.get(i).getName()));
            }
        }
        return list;
    }

	/**
	 * 获取商品规格
	 * @return
	 */
	public static List getGoodsStandard(){
		List<Dict> list = new ArrayList<>();
		List<GoodsStandard> goodsTypes = goodsStandardDao.findList(new GoodsStandard());
		if(goodsTypes != null){
			for (int i = 0; i < goodsTypes.size(); i++) {
				list.add(new Dict(goodsTypes.get(i).getId(), goodsTypes.get(i).getName()));
			}
		}
		return list;
	}

    /**
     * 获取商品
     * @return
     */
    public static List getGoodsList(){
        List<Dict> list = new ArrayList<>();
        List<Goods> goodsTypes = goodsDao.findList(new Goods());
        if(goodsTypes != null){
            for (int i = 0; i < goodsTypes.size(); i++) {
                list.add(new Dict(goodsTypes.get(i).getId(), goodsTypes.get(i).getName()));
            }
        }
        return list;
    }

	/**
	 * 获取交易状态
	 * @return
	 */
	public static List getTradeStateList(){
		List<Dict> list = new ArrayList<>();
        TradeStateEnum[] payStateEnums = TradeStateEnum.values();
        for (TradeStateEnum payStateEnum : payStateEnums) {
            list.add(new Dict(payStateEnum.getKey(), payStateEnum.getValue()));
        }
		return list;
	}

    /**
     * 获取订单状态
     * @return
     */
    public static List getOrderStateList(){
        List<Dict> list = new ArrayList<>();
        FinishStateEnum[] finishStateEnums = FinishStateEnum.values();
        for (FinishStateEnum finishStateEnum : finishStateEnums) {
            list.add(new Dict(finishStateEnum.getKey(), finishStateEnum.getValue()));
        }
        return list;
    }


}
