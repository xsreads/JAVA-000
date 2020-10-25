package io.xsreads.java.nio;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 使用 apache httpcomponents 中的 HttpClient 访问 http://localhost:8801
 * Created by xiushan on 2020/10/25.
 */
public class HttpClientApache {
    public static void main(String[] args) throws IOException {
        // create HttpClient object
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // create http Get request
        HttpGet httpGet = new HttpGet("http://localhost:8801");
        CloseableHttpResponse response = null;
        try {
            // exe request
            response = httpClient.execute(httpGet);
            // judge return status whether it is 200
            if (response.getStatusLine().getStatusCode() == 200) {
                // request entity content
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                // print entity content
                System.out.println(content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
    }
}
