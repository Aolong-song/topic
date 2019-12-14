package com.example.topic.controller;

import com.example.topic.domain.Topic;
import com.example.topic.service.TopicService;
import com.example.topic.util.ResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 对象模型标准组
 * @time 2019/12/9
 */
@RestController
@RequestMapping("/topicsService")
public class TopicController {

    @Autowired
    private TopicService topicService;

    /**
     * 专题列表
     *
     * @param page  分页页数
     * @param limit 分页大小
     * @return 专题列表
     */
    @ApiOperation("用户获取专题列表 /list")
    @GetMapping("/")
    public Object list(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit){
        PageHelper.startPage(page,limit);
        List<Topic> topicList = topicService.list();
        PageInfo<Topic> topicPageInfo = new PageInfo<>(topicList);
        List<Topic> pageList = topicPageInfo.getList();
        Object retObj = ResponseUtil.ok(pageList);
        return retObj;
    }

    /**
     * 专题详情
     *
     * @param id 专题ID
     * @return 专题详情
     */
    @ApiOperation("用户获取专题详情 /detail")
    @GetMapping("/{id}")
    public Object detail(@NotNull @PathVariable(value = "id") Integer id) {
        Topic detail = topicService.detail(id);
        Object retObj;
        if(!detail.getId().equals(id)){
            retObj = ResponseUtil.updatedDataFailed();
        }else{
            retObj = ResponseUtil.ok(detail);
        }
        return retObj;
    }

    @ApiOperation("管理员添加专题 /create")
    /**
     @RequiresPermissions("admin:topic:create")
     @RequiresPermissionsDesc(menu = {"推广管理", "专题管理"}, button = "添加")
     */
    @PostMapping("/")
    public Object create(@RequestBody Topic topic) {
        Topic detail = topicService.create(topic);
        Object retObj = ResponseUtil.ok(detail);
        return retObj;
    }

    @ApiOperation("管理员编辑专题 /update")
    /**
     @RequiresPermissions("admin:topic:update")
     @RequiresPermissionsDesc(menu = {"推广管理", "专题管理"}, button = "编辑")
     */
    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id,@RequestBody Topic topic) {
        Topic detail = topicService.update(topic,id);
        Object retObj;
        if(detail.getId().equals(id)){
            retObj = ResponseUtil.ok(detail);
        }else {
            retObj = ResponseUtil.updatedDataFailed();
        }
        return retObj;
    }

    @ApiOperation("管理员删除专题 /delete")
    /**
     @RequiresPermissions("admin:topic:delete")
     @RequiresPermissionsDesc(menu = {"推广管理", "专题管理"}, button = "删除")
     */
    @DeleteMapping("/{id}")
    public Object delete( @PathVariable Integer id) {
        Object retObj;
        if(topicService.delete(id)==1){
            retObj = ResponseUtil.ok();
        }else {
            retObj = ResponseUtil.updatedDataFailed();
        }
        return retObj;
    }

}
