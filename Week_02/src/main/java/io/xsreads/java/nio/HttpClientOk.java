package io.xsreads.java.nio;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 使用 okhttp 中的 OkHttpClient 访问 http://localhost:8801
 * Created by xiushan on 2020/10/25.
 */
public class HttpClientOk {
    public static void main(String[] args) throws IOException  {
        // create OkHttpClient object
        OkHttpClient okHttpClient = new OkHttpClient();
        // create request
        Request request = new Request.Builder().url("http://localhost:8801").build();
        // exe request, return entity
        Response response = okHttpClient.newCall(request).execute();
        // judge whether success
        if (response.isSuccessful()) {
            // print entity content
            System.out.println(response.body().string());
        } else {
            System.out.println("request fail!");
        }
    }
}
