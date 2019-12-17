package com.example.topic.mapper;

import com.example.topic.domain.TopicPo;
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
    List<TopicPo> list();

    /**
     * 专题详情
     *
     * @param id 专题ID
     * @return 专题详情
     */
    TopicPo detail(@Param(value = "id") Integer id);

    /**
     * 生成专题
     *
     * @param topicPo 专题信息
     * @return 数据库操作状态 0-数据库操作失败 1-数据库操作成功
     */
    int create(TopicPo topicPo);

    /**
     * 更新专题
     *
     * @param topicPo 专题信息
     * @return 数据库操作状态 0-数据库操作失败 1-数据库操作成功
     */
    int update(TopicPo topicPo);

    /**
     * 删除专题
     *
     * @param id 专题ID
     * @return 数据库操作状态 0-数据库操作失败 1-数据库操作成功
     */
    int delete(@Param(value = "id") Integer id);

    /**
     * 专题查重 防止重复创建专题
     * @param topicPo
     * @return topic专题信息
     */
    TopicPo beExist(TopicPo topicPo);
}
