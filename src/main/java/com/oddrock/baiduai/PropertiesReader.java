package com.oddrock.baiduai;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class PropertiesReader {
	private Properties properties;
	private String filePath;

	public PropertiesReader(String filePath) {
		this.filePath = filePath;
		loadProperties();
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 获取属�?文件的属性�?
	 * 
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		if (properties.containsKey(key)) {
			String value = properties.getProperty(key).trim();
			try {
				value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return value;
		} else {
			return "";
		}
	}

	public String getValue(String key, String defaultValue) {
		if (properties.containsKey(key)) {
			return properties.getProperty(key, defaultValue);
		} else {
			return defaultValue;
		}
	}

	/* 加载属�?文件 */
	public void loadProperties() {
		properties = new Properties();
		try {
			InputStream inputString = guessPropFile(filePath);
			properties.load(inputString);
			inputString.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	private InputStream guessPropFile(String propFile) {
		Class cls = PropertiesReader.class;
		try {
			

			ClassLoader loader = cls.getClassLoader();
			InputStream in = loader.getResourceAsStream(propFile);
			if (in != null) {
				return in;
			}
			Package pack = cls.getPackage();
			if (pack != null) {
				String packName = pack.getName();
				String path = "";
				if (packName.indexOf(".") < 0) {
					path = packName + "/";
				} else {
					int start = 0, end = 0;
					end = packName.indexOf(".");
					while (end != -1) {
						path = path + packName.substring(start, end) + "/";
						start = end + 1;
						end = packName.indexOf(".", start);
					}
					path = path + packName.substring(start) + "/";
				}
				in = loader.getResourceAsStream(path + propFile);
				if (in != null) {
					return in;
				}
			}
			File file = null;
			String curDir = System.getProperty("user.dir");
			file = new File(curDir, propFile);
			if (file.exists()) {
				return new FileInputStream(file);
			}
			String classpath = System.getProperty("java.class.path");
			String[] cps = classpath
					.split(System.getProperty("path.separator"));
			for (int i = 0; i < cps.length; i++) {
				file = new File(cps[i], propFile);
				if (file.exists()) {
					break;
				}
				file = null;
			}

			if (file != null) {
				return new FileInputStream(file);
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
