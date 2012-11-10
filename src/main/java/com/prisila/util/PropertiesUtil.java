package com.prisila.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {
	
	private Properties properties;
	private final String nomeProperties = "aula.properties";
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	public PropertiesUtil() {
		this.properties = new Properties();
		InputStream in = getClass().getResourceAsStream(nomeProperties);
		try {
			this.properties.load(in);
			in.close();
		} catch (IOException e) {
			LOG.error("falha ao carregar properties", e);
		}
	}
	
	public String getProperty(String key) {
		return this.properties.getProperty(key);
	}
}