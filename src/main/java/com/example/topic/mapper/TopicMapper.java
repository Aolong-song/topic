package com.example.topic.mapper;

import com.example.topic.domain.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author 宋澳龙
 * @time 2019/12/11 12:00
 */
@Mapper
public interface TopicMapper {

    /**
     * 专题列表
     *
     * @return 专题列表
     */
    List<Topic> list();

    /**
     * 专题详情
     *
     * @param id 专题ID
     * @return 专题详情
     */
    Topic detail(@Param(value = "id") Integer id);

    /**
     * 生成专题
     *
     * @param topic 专题信息
     */
    void create(Topic topic);

    /**
     * 更新专题
     *
     * @param topic 专题信息
     */
    void update(Topic topic);

    /**
     * 删除专题
     *
     * @param id 专题ID
     */
    void delete(@Param(value = "id") Integer id);
}
