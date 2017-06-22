package com.oddrock.baiduai;

import org.json.JSONObject;

import com.baidu.aip.nlp.AipNlp;
import com.baidu.aip.nlp.ESimnetType;

public class NLP {
    public static void main(String[] args) {
        // 初始化一个NLPClient
        AipNlp client = new AipNlp(MyProps.APP_ID, MyProps.API_KEY, MyProps.SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        
        // 情感分析
        JSONObject response = client.sentimentClassify("请问一下公司的培训平台找谁，我现在没办法登陆上去");
        System.out.println(response.toString());
        
        /*// 词法分析
        String text = "百度是一家高科技公司";
        response = client.lexer(text);
        System.out.println(response.toString());
        
        // 评论观点抽取
        response = client.commentTag("这家餐馆味道不错", ESimnetType.FOOD);
        System.out.println(response.toString());
        response = client.commentTag("喜来登酒店不错", ESimnetType.HOTEL);
        System.out.println(response.toString());
        
        // 获取两个词的相似度结果
        response = client.wordSimEmbedding("糖", "牛奶");
        System.out.println(response.toString());*/
        
    }
}