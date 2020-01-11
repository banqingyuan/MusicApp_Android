package com.example.nojie.utility;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SendRequest {
    private String url;
    public SendRequest(String url){
        this.url = "http://qingyuaner.group:9001"+url;
    }
    public String sendRequest(){
        final String[] responseData = {null};
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();//新建一个OKHttp的对象
                    Request request = new Request.Builder()
                            .url(url)
                            .build();//创建一个Request对象
                    Response response = client.newCall(request).execute();//发送请求获取返回数据
                    responseData[0] = response.body().string();//处理返回的数据

                }catch (Exception e){
                    e.printStackTrace();
                    responseData[0] = url;
                }
            }
        }).start();
        while(responseData[0]==null){

        }
        return responseData[0];
    }
}
