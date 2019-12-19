package com.example.topic.service.serviceimpl;

import com.example.topic.domain.TopicPo;
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
    public List<TopicPo> list(){
        return topicMapper.list();
    }

    @Override
    public TopicPo detail(Integer id){
        return topicMapper.detail(id);
    }

    @Override
    public TopicPo create( TopicPo topicPo) {
        if(topicMapper.create(topicPo)==0){
            topicPo.setId(-1);
            return topicPo;
        }else {
            return topicMapper.detail(topicPo.getId());
        }
    }

    @Override
    public TopicPo update(TopicPo topicPo, Integer id){
            topicPo.setId(id);
            if(topicMapper.update(topicPo)==0){
                topicPo.setId(-1);
                return  topicPo;
            }
            return topicMapper.detail(id);
    }

    @Override
    public Integer delete(Integer id){
        if(topicMapper.detail(id)==null){
            return 0;
        }else{
            return topicMapper.delete(id);
        }
    }

    @Override
    public boolean beExist(TopicPo topicPo){
        if(topicMapper.beExist(topicPo)==null){
            return true;
        }else{
            return false;
        }
    }
}
