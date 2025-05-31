package com.example.covdecisive.demos.web.mapper;
import java.util.List;
import com.example.covdecisive.demos.web.model.SourceCode;
import org.apache.ibatis.annotations.*;


@Mapper
public interface SourceCodeMapper {

    @Insert("INSERT INTO source_code (program_id, file_path, code_content) VALUES (#{programId}, #{filePath}, #{codeContent})")
    void insert(SourceCode sourceCode);
}
