package com.example.topic.service.serviceimpl;

import com.example.topic.mapper.TopicMapper;
import com.example.topic.domain.Topic;
import com.example.topic.service.TopicService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author 宋澳龙
 * @time 2019/12/11 12:00
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Resource
    private TopicMapper topicMapper;

    @Override
    public List<Topic> list(){
        return topicMapper.list();
    }
    @Override
    public Topic detail(Integer id){
        return topicMapper.detail(id);
    }


    @Override
    public Topic create( Topic topic) {
        topicMapper.create(topic);
        return topicMapper.detail(topic.getId());
    }

    @Override
    public Topic update(Topic topic, Integer id){
        if(topicMapper.detail(id)==null){
            topic.setId(-1);
            return topic;
        }else {
            topic.setId(id);
            topicMapper.update(topic);
            return topicMapper.detail(id);
        }
    }

    @Override
    public Integer delete(Integer id){
        if(topicMapper.detail(id)==null){
            return 0;
        }else{
            topicMapper.delete(id);
            return 1;
        }
    }
}
