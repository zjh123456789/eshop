package com.twinking.eshop.common.listener;

import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * @Description: 自定义ContextLoaderListener
 * @Autuor  朱景辉（1229585753@qq.com）
 * @Date 2018/9/26 09 39
 */
public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		printStartMessage();
		return super.initWebApplicationContext(servletContext);
	}

	private void printStartMessage() {
		System.out.println("欢迎使用EShop");
		System.out.println(
				"    ┏┓　　　┏┓\n" +
						"  ┏┛┻━━━┛┻┓\n" +
						"  ┃　　　　　　　┃\n" +
						"  ┃　　　━　　　┃\n" +
						"  ┃　┳┛　┗┳　┃\n" +
						"  ┃　　　　　　　┃\n" +
						"  ┃　　　┻　　　┃\n" +
						"  ┗━┓　　　┏━┛\n" +
						"  　　┃　　　┃    神兽保佑\n" +
						"  　　┃　　　┃    代码无BUG！\n" +
						"  　　┃　　　┗━━━┓\n" +
						"  　　┃　　　　　　　┣┓\n" +
						"  　　┃　　　　　　　┏┛\n" +
						"  　　┗┓┓┏━┳┓┏┛\n" +
						"  　　　┃┫┫　┃┫┫\n" +
						"  　　　┗┻┛　┗┻┛");
		
	}
}
