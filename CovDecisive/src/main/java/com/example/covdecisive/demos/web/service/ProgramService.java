package com.example.covdecisive.demos.web.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.covdecisive.demos.web.model.Program;
import com.example.covdecisive.demos.web.mapper.ProgramMapper;
import java.util.List;

@Service
public class ProgramService {
    @Autowired
    private ProgramMapper programMapper;

    public List<Program> getAll() {
        return programMapper.getAll();
    }

    public void insert(Program program) {
        programMapper.insert(program);
    }
}
