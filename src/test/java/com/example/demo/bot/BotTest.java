package com.example.demo.bot;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.io.IOException;

public class BotTest {
    @Test
    public void whenPostJsonUsingHttpClient_thenCorrect() throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        String slackHook = "https://hooks.slack.com/services/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";
        HttpPost httpPost = new HttpPost(slackHook);

        String message = "slack bot test code";
        StringEntity params = new StringEntity("{\"text\" : \""+message+"\"}","UTF-8");
        httpPost.setEntity(params);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }

}
