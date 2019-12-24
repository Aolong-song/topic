package com.example.topic;

import com.example.topic.domain.TopicPo;
import com.example.topic.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author 宋澳龙
 * @date 2019/12/18 10:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class putTopicsId {
    @Value("http://${host}:${port}/topicService/topics/{id}")
    String url;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void updateRight()throws Exception{
        /* 设置请求头部*/
        URI uri = new URI(url.replace("{id}","1"));
        HttpHeaders httpHeaders = testRestTemplate.headForHeaders(uri);
        TopicPo topicPo = new TopicPo();
        topicPo.setPicUrlList("https://www.baidu.com/");
        topicPo.setContent("百度");
        HttpEntity httpEntity = new HttpEntity(topicPo,httpHeaders);

        /*exchange方法模拟HTTP请求*/
        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.PUT, httpEntity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        /*取得响应体*/
        String body = response.getBody();
        Integer errno = JacksonUtil.parseInteger(body, "errno");
        Integer status = JacksonUtil.parseInteger(body, "status");
        assertEquals(200, errno);
        assertNotEquals(500, status);

        /*assert判断*/
        HashMap topicPo1 = JacksonUtil.parseObject(body, "data", HashMap.class);
        assertEquals("https://www.baidu.com/",topicPo1.get("picUrlList"));
        assertEquals("百度",topicPo1.get("content"));
    }

    @Test
    public void updateId() throws Exception{
        /* 设置请求头部*/
        URI uri = new URI(url.replace("{id}","999"));
        HttpHeaders httpHeaders = testRestTemplate.headForHeaders(uri);
        TopicPo topicPo = new TopicPo();
        topicPo.setPicUrlList("https://www.baidu.com/");
        topicPo.setContent("百度");
        HttpEntity httpEntity = new HttpEntity(topicPo,httpHeaders);

        /*exchange方法模拟HTTP请求*/
        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.PUT, httpEntity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        /*取得响应体*/
        String body = response.getBody();
        Integer errno = JacksonUtil.parseInteger(body, "errno");
        Integer status = JacksonUtil.parseInteger(body, "status");
        assertEquals(650, errno);
        assertNotEquals(500, status);
    }

    @Test
    public void updateIdNegative() throws Exception{
        /* 设置请求头部*/
        URI uri = new URI(url.replace("{id}","-1"));
        HttpHeaders httpHeaders = testRestTemplate.headForHeaders(uri);
        TopicPo topicPo = new TopicPo();
        topicPo.setPicUrlList("https://www.baidu.com/");
        topicPo.setContent("百度");
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        /*exchange方法模拟HTTP请求*/
        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.PUT, httpEntity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        /*取得响应体*/
        String body = response.getBody();
        Integer errno = JacksonUtil.parseInteger(body, "errno");
        Integer status = JacksonUtil.parseInteger(body, "status");
        assertEquals(580, errno);
        assertNotEquals(500, status);
    }

}
