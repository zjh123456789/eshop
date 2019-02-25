package com.twinking.eshop.common.utils;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * @Description: 封装各种生成唯一性ID算法的工具类.
 * @Autuor  朱景辉（1229585753@qq.com）
 * @Date 2018/9/26 09 39
 */
@Service
@Lazy(false)
public class IdGenUtils implements  SessionIdGenerator {

	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 使用SecureRandom随机生成Long. 
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return EncodesUtils.encodeBase62(randomBytes);
	}
	

	@Override
	public Serializable generateId(Session session) {
		return IdGenUtils.uuid();
	}
	
	public static void main(String[] args) {
		System.out.println(IdGenUtils.uuid());
		System.out.println(IdGenUtils.uuid().length());
		for (int i=0; i<1000; i++){
			System.out.println(IdGenUtils.randomLong() + "  " + IdGenUtils.randomBase62(5));
		}
	}

}
