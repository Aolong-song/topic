package com.example.topic.controller;

import com.example.topic.domain.Log;
import com.example.topic.domain.TopicPo;
import com.example.topic.feign.LogServiceApi;
import com.example.topic.service.TopicService;
import com.example.topic.util.FileUtils;
import com.example.topic.util.ResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 对象模型标准组
 * @time 2019/12/9
 */
@RestController
@RequestMapping("/topicService")
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private LogServiceApi logServiceApi;
    @Value("${web.path}")
    private String path;

    /**
     * 管理员获取专题列表
     *
     * @param page  分页页数
     * @param limit 分页大小
     * @return 专题列表
     */
    @ApiOperation("管理员获取专题列表 /adminList")
    @GetMapping("/admin/topics")
    public Object adminList(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit){
        Object retObj;
        Log log = new Log();
        log.setType(0);
        log.setActions("管理员获取专题列表");
        PageHelper.startPage(page,limit);
        List<TopicPo> topicList = topicService.list();
        PageInfo<TopicPo> topicPageInfo = new PageInfo<>(topicList);
        List<TopicPo> pageList = topicPageInfo.getList();
        retObj = ResponseUtil.ok(pageList);
        log.setStatusCode(1);
        logServiceApi.addLog(log);
        return retObj;
    }

    /**
     * 管理员获取专题详情
     *
     * @param id 专题ID
     * @return 专题详情
     */
    @ApiOperation("管理员获取专题详情 /detail")
    @GetMapping("/admin/topics/{id}")
    public Object adminDetail(@NotNull @PathVariable(value = "id") Integer id) {
        Object retObj;
        Log log = new Log();
        log.setType(0);
        log.setActions("管理员获取专题详情");
        TopicPo detail = topicService.detail(id);
        if(detail==null){
            retObj = ResponseUtil.fail(650,"该话题是无效话题");
            log.setStatusCode(0);
        }else{
            retObj = ResponseUtil.ok(detail);
            log.setStatusCode(1);
        }
        logServiceApi.addLog(log);
        return retObj;
    }

    /**
     * 用户获取专题列表
     *
     * @param page  分页页数
     * @param limit 分页大小
     * @return 专题列表
     */
    @ApiOperation("用户获取专题列表 /list")
    @GetMapping("/topics")
    public Object list(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit){
        Object retObj;
        PageHelper.startPage(page,limit);
        List<TopicPo> topicList = topicService.list();
        PageInfo<TopicPo> topicPageInfo = new PageInfo<>(topicList);
        List<TopicPo> pageList = topicPageInfo.getList();
        retObj = ResponseUtil.ok(pageList);
        return retObj;
    }

//    /**
//     * 上传图片到服务器 返回图片地址
//     *
//     * @param file
//     * @return url 图片在服务器上的地址
//     */
//    @ApiOperation("上传图片")
//    @PostMapping("/pic")
//    public Object pic(@RequestParam("file") MultipartFile file){
//        String localPath = "/picture";
//        String fileName = file.getOriginalFilename();
//        String newFile = FileUtils.upload(file,localPath,fileName);
//        Object retObj;
//        if(newFile != null){
//            retObj = ResponseUtil.ok(localPath+"\\"+newFile);
//        }else{
//            retObj = ResponseUtil.fail(505,"图片上传失败");
//        }
//        return retObj;
//    }

    /**
     * 专题详情
     *
     * @param id 专题ID
     * @return 专题详情
     */
    @ApiOperation("用户获取专题详情 /detail")
    @GetMapping("/topics/{id}")
    public Object detail(@NotNull @PathVariable(value = "id") Integer id) {
        Object retObj;
        TopicPo detail = topicService.detail(id);
        if(detail==null){
            retObj = ResponseUtil.fail(650,"该话题是无效话题");
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
    @PostMapping("/topics")
    public Object create(@RequestBody TopicPo topicPo) {
        Object retObj;
        Log log = new Log();
        log.setType(1);
        log.setActions("管理员添加专题");
        if(topicService.beExist(topicPo)){
            TopicPo detail = topicService.create(topicPo);
            if(detail.getId()==-1){
                retObj = ResponseUtil.fail(652,"话题添加失败");
                log.setStatusCode(0);
            }else{
                retObj = ResponseUtil.ok(detail);
                log.setStatusCode(1);
            }
        }else{
            retObj = ResponseUtil.fail(652,"话题添加失败");
            log.setStatusCode(0);
        }
        logServiceApi.addLog(log);
        return retObj;
    }

    @ApiOperation("管理员编辑专题 /update")
    /**
     @RequiresPermissions("admin:topic:update")
     @RequiresPermissionsDesc(menu = {"推广管理", "专题管理"}, button = "编辑")
     */
    @PutMapping("/topics/{id}")
    public Object update(@PathVariable Integer id,@RequestBody TopicPo topicPo) {
        Object retObj;
        Log log = new Log();
        log.setType(2);
        log.setActions("管理员编辑专题");
        if(topicService.detail(id)==null){
            retObj = ResponseUtil.fail(650,"该话题是无效话题");
            log.setStatusCode(0);
        }else{
            TopicPo detail = topicService.update(topicPo,id);
            if(detail.getId()==-1){
                retObj = ResponseUtil.fail(651,"话题更新失败");
                log.setStatusCode(0);
            }else {
                retObj = ResponseUtil.ok(detail);
                log.setStatusCode(1);
            }
        }
        logServiceApi.addLog(log);
        return retObj;
    }

    @ApiOperation("管理员删除专题 /delete")
    /**
     @RequiresPermissions("admin:topic:delete")
     @RequiresPermissionsDesc(menu = {"推广管理", "专题管理"}, button = "删除")
     */
    @DeleteMapping("/topics/{id}")
    public Object delete( @PathVariable Integer id) {
        Object retObj;
        Log log = new Log();
        log.setType(3);
        log.setActions("管理员删除专题");
        if(topicService.detail(id)==null){
            retObj = ResponseUtil.fail(650,"该话题是无效话题");
            log.setStatusCode(0);
        }else{
            if(topicService.delete(id)==1){
                retObj = ResponseUtil.ok();
                log.setStatusCode(1);
            }else{
                retObj = ResponseUtil.fail(653,"话题删除失败");
                log.setStatusCode(0);
            }
        }
        logServiceApi.addLog(log);
        return retObj;
    }
}
