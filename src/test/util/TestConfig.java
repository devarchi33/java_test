package test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestConfig {
	static Logger logger = LoggerFactory.getLogger(TestConfig.class);
	
	private static TestConfig config;
	private static String configFile;
	private Properties properties;
	
	public static TestConfig getInstance(){
		if(config == null){
			config = new TestConfig();
		}
		
		return config;
	}
	
	public static TestConfig getInstance(String configPath){
		if(config == null){
			configFile = configPath;
			config = new TestConfig();
		}
		
		return config;
	}

	
	private TestConfig(){
		properties = new Properties();
		
		// file read
		URL url = null;
		File src = new File(configFile);
		if(!src.exists()){
			logger.debug("####### can't find  config property src file...");
		}else{
			InputStream in = null;
			try {
				in = new FileInputStream(src);
				properties.load(in);
			} catch (FileNotFoundException e) {
				logger.debug("####### can't find jagent config properties...");
			} catch (IOException e) {
				logger.debug("####### can't load jagent config properties...");
			} finally{
				try{if(in != null) in.close();}catch(Exception e){}
			}
		}
	}
	
	public String getProperties(String key){
		logger.debug("### property[" + key + "], value[" + properties.getProperty(key) + "]");
		return (String) properties.get(key);
	}
}
