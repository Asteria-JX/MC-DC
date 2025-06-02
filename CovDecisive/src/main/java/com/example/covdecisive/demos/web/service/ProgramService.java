package com.example.covdecisive.demos.web.service;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.covdecisive.demos.web.model.Program;
import com.example.covdecisive.demos.web.mapper.ProgramMapper;
import java.util.List;

@Service
public class ProgramService {
    @Autowired
    private ProgramMapper programMapper;

    public List<Program> getAll(Integer userId) {
        return programMapper.getAll(userId);
    }

    public void insert(Program program) {
        programMapper.insert(program);
    }
}
