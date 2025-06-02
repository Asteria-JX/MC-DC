package com.example.covdecisive.demos.web.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.covdecisive.demos.web.service.TestResourceService;
import com.example.covdecisive.demos.web.model.TestResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Api(tags = "API接口")
@RestController
//Spring 中的一个注解，用于支持跨域请求
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestResourceController {
    @Autowired
    private TestResourceService testResourceService;

    @PostMapping("/uploadTestResources")
    public void uploadTestResources(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("program_id") Integer programId,
            @RequestParam("user_id") Integer userId) throws IOException {
        List<TestResource> resources = new ArrayList<>();
        for (MultipartFile file : files) {
            TestResource resource = new TestResource();
            String content = new String(file.getBytes());
            resource.setName(file.getOriginalFilename());
            resource.setProgram_id(programId);
            resource.setUser_id(userId);
            resource.setCode_content(content);
            // 这里你可以扩展保存 file 到磁盘的逻辑
            resources.add(resource);
        }
        testResourceService.batchInsert(resources);
        //return ResponseEntity.ok("测试用例上传成功");
    }

    @GetMapping("/testall")
    public List<TestResource> getTestAll(@RequestParam Integer user_id, @RequestParam Integer program_id) {

        return testResourceService.getTestAll(user_id, program_id);
    }

}
