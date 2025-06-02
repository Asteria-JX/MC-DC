package com.example.covdecisive.demos.web.controller;

import com.example.covdecisive.demos.web.service.SourceCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import com.example.covdecisive.demos.web.model.SourceCode;

@Controller
@Api(tags = "API接口")
@RestController
//Spring 中的一个注解，用于支持跨域请求
@CrossOrigin(origins = "*", maxAge = 3600)
public class SourceController {
    @Autowired
    private SourceCodeService sourceCodeService;

    @GetMapping("/flat")
    public List<String> getFlatPaths(@RequestParam int programId) {
        //System.out.println("选中项目id："+programId);
        List<SourceCode> list = sourceCodeService.getByProgramId(programId);
        return list.stream().map(SourceCode::getFilePath).collect(Collectors.toList());
    }

    // 获取指定 file_path 的代码内容
    @ApiOperation("获取指定 file_path 的代码内容")
    @GetMapping("/getCodeContent")
    public String getCodeContent(
            @RequestParam("programId") int programId,
            @RequestParam("filePath") String filePath) {
        String result = sourceCodeService.getCodeContent(programId, filePath);
        System.out.println(result);
        return result;
    }

    // 编辑修改代码内容
    @ApiOperation("编辑修改代码内容")
    @PostMapping("/updateCodeContent")
    public String updateCodeContent(@RequestBody SourceCode request) {
        int result = sourceCodeService.updateCodeContent(request.getProgramId(), request.getFilePath(), request.getCodeContent());
        return result > 0 ? "success" : "fail";
    }

}
