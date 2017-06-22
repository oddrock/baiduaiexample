package com.oddrock.baiduai;

public class MyProps {
	public static final PropertiesReader pr = new PropertiesReader("baiduaiexample.properties");
	public static final String APP_ID = pr.getValue("baidu.ai.appid");
    public static final String API_KEY = pr.getValue("baidu.ai.appkey");
    public static final String SECRET_KEY = pr.getValue("baidu.ai.secretkey");
}
