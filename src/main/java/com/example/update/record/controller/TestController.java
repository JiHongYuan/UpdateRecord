package com.example.update.record.controller;

import com.example.update.record.domain.Resource;
import com.example.update.record.service.ResourceService;
import com.example.update.record.utils.ActionHolderUtils;
import com.example.update.record.utils.SpringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jihongyuan
 * @date 2019/12/6 22:46
 */
@RestController
public class TestController {

    @RequestMapping("/t1")
    public String t1() {
        ResourceService resourceService = SpringUtils.getBean(ResourceService.class);
        Resource resource = new Resource();
        resource.setId(43L);
        resource.setName("testName2");
        resource.setPath("2");
        resourceService.updateByPrimaryKeySelective(resource);
        return resource.toString();
    }


    @RequestMapping("/t2")
    public String t2() {
        ResourceService resourceService = SpringUtils.getBean(ResourceService.class);
        Resource resource = new Resource();
        resource.setName("t2");
        resource.setPath("3");
        resourceService.insertSelective(resource);
        return "123";
    }
}
