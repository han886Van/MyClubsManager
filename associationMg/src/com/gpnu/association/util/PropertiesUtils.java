package com.gpnu.association.util;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;



public class PropertiesUtils {

    private static PropertiesUtils propertiesUtils;

    private static final String DEFAULT_PROPERTIES = "common.properties";

    private PropertiesUtils() {

    }

    public static PropertiesUtils getInstance() {
	if (propertiesUtils == null) {
	    propertiesUtils = new PropertiesUtils();
	}
	return propertiesUtils;
    }

    public static PropertiesConfiguration readProperties(String propertiesFile) {

	// 读取常规数据
	PropertiesConfiguration config = null;
	try {
	    config = StringUtils.isNotEmpty(propertiesFile) ? new PropertiesConfiguration(propertiesFile)
		    : new PropertiesConfiguration(DEFAULT_PROPERTIES);
	} catch (ConfigurationException e) {
	    e.printStackTrace();
	}
	return config;
    }
    
    public static PropertiesConfiguration readProperties() {

	return readProperties(null);
    }
    
    public static void main(String[] args) {
	System.out.println(readProperties().getString("file.project.path"));
	
    }

}
