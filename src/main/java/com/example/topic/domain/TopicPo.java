package com.example.topic.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Author: 数据库与对象模型标准组
 * @Description: 专题信息
 * @Date: Created in 16:00 2019/12/11
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TopicPo {

    private Integer id;
    /**
     * 专题图片的url的list
     * JSON格式: {"pictures":[xxx,xxx]}, xxx为图片的url
     */
    private String picUrlList;
    /**
     * 专题的内容
     */
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;
    private Boolean beDeleted;

}
