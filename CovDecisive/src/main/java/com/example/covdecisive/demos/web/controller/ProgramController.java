package com.example.covdecisive.demos.web.controller;
import com.example.covdecisive.demos.web.service.SourceCodeService;
import com.example.covdecisive.demos.web.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.covdecisive.demos.web.model.Program;
import com.example.covdecisive.demos.web.model.SourceCode;
import com.example.covdecisive.demos.web.service.ProgramService;

import java.io.IOException;
import java.util.List;

@Controller
@Api(tags = "API接口")
@RestController
//Spring 中的一个注解，用于支持跨域请求
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProgramController {
    @Autowired
    private ProgramService programService;

    @Autowired
    private SourceCodeService sourceCodeService;

    @GetMapping("/all")
    public List<Program> getAllPrograms() {
        //System.out.println(programService.getAll());
        return programService.getAll();
    }

    @PostMapping("/uploadProject")
    public void uploadProject(@RequestParam("programName") String programName,
                              @RequestParam("files") List<MultipartFile> files) throws IOException {

        Program program = new Program();
        program.setProgramName(programName);
        program.setVersion("1.0");
        program.setDescription("上传生成");
        programService.insert(program); // 插入 programs 表

        int programId = program.getProgramId(); // 获取插入后的主键

        for (MultipartFile file : files) {
            String path = file.getOriginalFilename(); // 获取带路径的文件名
            String content = new String(file.getBytes());
            SourceCode code = new SourceCode();
            code.setProgramId(programId);
            code.setFilePath(path);
            code.setCodeContent(content);
            sourceCodeService.insert(code);
        }
    }

}
