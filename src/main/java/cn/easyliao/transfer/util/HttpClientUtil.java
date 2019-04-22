package cn.easyliao.transfer.util;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HttpClientUtil {

    private final static Log log = LogFactory.getLog(HttpClientUtil.class);

    public static String httpPost(String url, String data, String contentType) {
        return httpPost(url, data, null, null, contentType);
    }

    /**
     * 发送请求
     *
     * @param url         地址
     * @param data        提交数据
     * @param accessToken EC参数
     * @param corpId      EC参数
     * @param contentType 提交类型
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static String httpPost(String url, String data, String accessToken, String corpId, String contentType) {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);

        builder.setBodyEncoding("UTF-8");

        if (StringUtils.isNotEmpty(data))
            builder.setBody(data);

        if (StringUtils.isNotEmpty(accessToken))
            builder.addHeader("Authorization", accessToken);

        if (StringUtils.isNotEmpty(corpId))
            builder.addHeader("CORP_ID", corpId);

        if (StringUtils.isNotEmpty(contentType)) {
            builder.setHeader("Content-Type", contentType);
        } else {
            builder.setHeader("Content-Type", "application/json;charset=utf-8");
        }

        Future<Response> f;
        String body = "";
        try {
            f = builder.execute();
            body = f.get().getResponseBody("UTF-8");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        http.close();
        return body;
    }

    /**
     * 发送请求
     *
     * @param url         地址
     * @param contentType 提交类型
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static String httpGet(String url, String contentType) {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.prepareGet(url);

        builder.setBodyEncoding("UTF-8");

        if (StringUtils.isNotEmpty(contentType)) {
            builder.setHeader("Content-Type", contentType);
        } else {
            builder.setHeader("Content-Type", "application/json;charset=utf-8");
        }

        Future<Response> f;
        String body = "";
        try {
            f = builder.execute();
            body = f.get().getResponseBody("UTF-8");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        http.close();
        return body;
    }
}
