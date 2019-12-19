package com.example.topic.feign;

import com.example.topic.domain.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 宋澳龙
 * @date 2019/12/17 17:59
 */
@Component
@FeignClient(name = "logService",url = "http://112.124.5.232:8029")
@RequestMapping("/logService")
public interface LogServiceApi {

    /**
     *  添加管理员日志
     * @param log 日志内容
     */
    @PostMapping("/log")
    void addLog(@RequestBody Log log);
}
