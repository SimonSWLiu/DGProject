package com.onemenu.server.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
/**
 * onemenu server的上下文监听器
 * @author file
 * @since 2015-4-7 下午3:59:01
 * @version 1.0
 */
public class OneMenuContextLoaderListener extends ContextLoaderListener {
	
	private static final Logger logger = Logger.getLogger(OneMenuContextLoaderListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		if(logger.isDebugEnabled())
			logger.debug("begin~~~~~");
		super.contextInitialized(event);
		if(logger.isDebugEnabled())
			logger.debug("end~~~~~");
		
	}
	
	protected static class ContextHelp{
		public static final ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
	}

}
