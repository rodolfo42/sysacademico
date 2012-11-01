package com.prisila.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesLoader {

	private Properties properties;
	private final String nomeProperties = "aula.properties";
	private final Logger logger = Logger.getLogger(getClass());
	
	public PropertiesLoader() {
		this.properties = new Properties();
		InputStream in = getClass().getResourceAsStream(nomeProperties);
		try {
			this.properties.load(in);
			in.close();
		} catch (IOException e) {
			logger.error("falha carregando properties ", e);
		}
	}
	
	public String getValor(String key){
		return this.properties.getProperty(key);
	}
	
}
