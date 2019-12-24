package com.example.topic.feign;

import com.example.topic.domain.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 宋澳龙
 * @date 2019/12/17 17:59
 */
@Service
@FeignClient(name = "logService",url = "http://47.96.159.71:8846")
public interface LogServiceApi {
    /**
     *  添加管理员日志
     * @param log 日志内容
     */
    @PostMapping("/log")
    void addLog(@RequestBody Log log);
}
