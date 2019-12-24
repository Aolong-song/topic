package com.example.topic.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 宋澳龙
 * @date 2019/12/20 20:26
 */
@Service
@FeignClient(name = "picturesService",url = "http://106.15.249.35:8031")
public interface PicturesServiceApi {
    /**
     *  上传图片
     * @param image 本地图片
     * @return pictureUrl 图片在服务器中的地址
     */
    @PostMapping("/picture")
    Object postPicture(@RequestParam("image") MultipartFile image);
}
