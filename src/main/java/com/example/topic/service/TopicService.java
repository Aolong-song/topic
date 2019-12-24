package com.example.topic.service;

import com.example.topic.domain.TopicPo;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author 宋澳龙
 * @time 2019/12/11 12:00
 */
@Service
public interface TopicService {

    /**
     * 专题列表
     *
     * @return 专题列表
     */
    List<TopicPo> list();

    /**
     * 专题详情
     *
     * @param id 专题ID
     * @return 专题详情
     */
    TopicPo detail(Integer id);

    /**
     * 生成专题
     *
     * @param topicPo 专题信息
     * @return topic 专题信息
     */
    TopicPo create(TopicPo topicPo);

    /**
     * 更新专题信息
     *
     * @param topicPo 专题信息
     * @param id 专题ID
     * @return topic 专题信息
     */
    TopicPo update(TopicPo topicPo, Integer id);

    /**
     * 删除专题
     *
     * @param id 专题ID
     * @return int 状态信息 0表示删除失败 1表示删除成功
     */
    Integer delete(Integer id);

    /**
     * 专题查重 防止出现重复专题
     *
     * @param topicPo
     * @return true表示无重复 false表示数据库中有重复专题
     */
    boolean beExist(TopicPo topicPo);
}