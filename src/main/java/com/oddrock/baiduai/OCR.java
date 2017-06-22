package com.oddrock.baiduai;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.nlp.ESimnetType;
import com.baidu.aip.ocr.AipOcr;

public class OCR {
    public static void main(String[] args) {
        // 初始化一个OcrClient
        AipOcr client = new AipOcr(MyProps.APP_ID, MyProps.API_KEY, MyProps.SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用通用识别接口
        String genFilePath = "c:\\456.jpg";
        JSONObject genRes = client.basicGeneral(genFilePath, new HashMap<String, String>());
        System.out.println(genRes.toString(2));
    }
}