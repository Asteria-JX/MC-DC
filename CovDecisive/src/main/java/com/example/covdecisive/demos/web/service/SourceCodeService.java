package com.example.covdecisive.demos.web.service;
import com.example.covdecisive.demos.web.mapper.SourceCodeMapper;
import com.example.covdecisive.demos.web.model.SourceCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SourceCodeService {
    @Autowired
    private SourceCodeMapper sourceCodeMapper;

    public void insert(SourceCode code) {
        sourceCodeMapper.insert(code);
    }

    public List<SourceCode> getByProgramId(int programId) {
        return sourceCodeMapper.getByProgramId(programId);
    }
}
