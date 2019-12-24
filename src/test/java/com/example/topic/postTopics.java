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
 * @date 2019/12/18 10:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class postTopics {
    @Value("http://${host}:${port}/topicService/topics")
    String url;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void createRight()throws Exception{
        /* 设置请求头部*/
        URI uri = new URI(url);
        HttpHeaders httpHeaders = testRestTemplate.headForHeaders(uri);
        TopicPo topicPo = new TopicPo();
        topicPo.setPicUrlList("https://www.baidu.com/");
        topicPo.setContent("百度1");
        HttpEntity httpEntity = new HttpEntity(topicPo,httpHeaders);

        /*exchange方法模拟HTTP请求*/
        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        /*取得响应体*/
        String body = response.getBody();
        Integer errno = JacksonUtil.parseInteger(body, "errno");
        Integer status = JacksonUtil.parseInteger(body, "status");
        assertEquals(200, errno);
        assertNotEquals(500, status);

        /*assert判断*/
        HashMap topic = JacksonUtil.parseObject(body, "data", HashMap.class);
        assertEquals("https://www.baidu.com/",topic.get("picUrlList"));
        assertEquals("百度1",topic.get("content"));
    }

    @Test
    public void createNull()throws Exception{
        /* 设置请求头部*/
        URI uri = new URI(url);
        HttpHeaders httpHeaders = testRestTemplate.headForHeaders(uri);
        TopicPo topicPo = new TopicPo();
        topicPo.setPicUrlList(null);
        topicPo.setContent(null);
        HttpEntity httpEntity = new HttpEntity(topicPo,httpHeaders);

        /*exchange方法模拟HTTP请求*/
        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        /*取得响应体*/
        String body = response.getBody();
        Integer errno = JacksonUtil.parseInteger(body, "errno");
        Integer status = JacksonUtil.parseInteger(body, "status");
        assertEquals(652, errno);
        assertNotEquals(500, status);
    }

    @Test
    public void createBeExist() throws Exception{
        /* 设置请求头部*/
        URI uri = new URI(url);
        HttpHeaders httpHeaders = testRestTemplate.headForHeaders(uri);
        TopicPo topicPo = new TopicPo();
        topicPo.setContent("百度1");
        topicPo.setPicUrlList("https://www.baidu.com/");
        HttpEntity httpEntity = new HttpEntity(topicPo,httpHeaders);

        /*exchange方法模拟HTTP请求*/
        ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        /*取得响应体*/
        String body = response.getBody();
        Integer errno = JacksonUtil.parseInteger(body, "errno");
        Integer status = JacksonUtil.parseInteger(body, "status");
        assertEquals(652, errno);
        assertNotEquals(500, status);
    }
}
