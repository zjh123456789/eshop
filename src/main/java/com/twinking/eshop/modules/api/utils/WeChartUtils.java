package com.twinking.eshop.modules.api.utils;

import com.twinking.eshop.common.constant.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @Description:  微信工具类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/23 13 37
 */
public class WeChartUtils {

    /**
     * Http请求
     */
    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * 获取OpendID
     * @param code 请求码
     * @return 用户唯一id 和 会话密钥
     */
    public static ResponseEntity getOpenId(String code) throws ResourceAccessException {
        HashMap<String, String> map = new HashMap<>();
        map.put("appid",Constants.WEIXIN_APPID_OPENID);
        map.put("secret",Constants.WEIXIN_SECRET_OPENID);
        map.put("js_code",code);
        map.put("grant_type",Constants.WEIXIN_GRANT_TYPE_OPENID);
        return restTemplate.getForEntity(Constants.URL_GET_WX_OPENID, String.class, map);
    }

}
