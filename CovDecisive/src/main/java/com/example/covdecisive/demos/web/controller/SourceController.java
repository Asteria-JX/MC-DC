package com.example.covdecisive.demos.web.controller;

import com.example.covdecisive.demos.web.service.SourceCodeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.stream.Collectors;
import com.example.covdecisive.demos.web.model.SourceCode;
import org.springframework.web.bind.annotation.RestController;

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


}
