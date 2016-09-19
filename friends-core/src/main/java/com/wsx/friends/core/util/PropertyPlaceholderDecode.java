package com.wsx.friends.core.util;

import java.util.Properties;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 重写解析参数的方法，我们对配置文件的内容进行加密操作
 * @author wangshuaixin
 *
 */
public class PropertyPlaceholderDecode extends PropertyPlaceholderConfigurer {

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		Set<Object> keys = props.keySet();
		if (null != keys && keys.size() > 0) {
			for (Object obj : keys) {
				String key = String.valueOf(obj);
				if (key.startsWith(CommonFinalUtil.JDBC_USERNAME) 
						|| key.startsWith(CommonFinalUtil.JDBC_USERNAME)) {
					props.setProperty(key, DesBase64Util.decoder(props.getProperty(key)));
				}
			}
		}
		super.processProperties(beanFactoryToProcess, props);
	}
}
