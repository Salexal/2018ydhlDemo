package com.example.a92385.a2018ydhldemo.HttpUtils;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    public static void sendOkHttp(String url, Map<String,String> map, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .post(RequestBody.create(JSON,new JSONObject(map).toString()))
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendOkHttp(String url, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://10.0.3.2:3003/"+url)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void putOkHttp(String url,String id,String license,String name,String Balance, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://10.0.3.2:3003/"+url)
                .put(new FormBody.Builder()
                        .add("id",id)
                        .add("License",license)
                        .add("owner",name)
                        .add("Balance",Balance)
                        .build())
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void putOkHttp(String url,String id,String lightId,String redLight, String yellowLight,String greenLight,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://10.0.3.2:3003/"+url)
                .put(new FormBody.Builder()
                        .add("id",id)
                        .add("lightId",lightId)
                        .add("redLight",redLight)
                        .add("yellowLight",yellowLight)
                        .add("greenLight",greenLight)
                        .build())
                .build();
        client.newCall(request).enqueue(callback);
    }
}
