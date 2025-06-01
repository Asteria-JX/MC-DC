package com.example.covdecisive.demos.web.mapper;
import java.util.List;
import com.example.covdecisive.demos.web.model.SourceCode;
import org.apache.ibatis.annotations.*;


@Mapper
public interface SourceCodeMapper {

    @Insert("INSERT INTO source_code (program_id, file_path, code_content) VALUES (#{programId}, #{filePath}, #{codeContent})")
    void insert(SourceCode sourceCode);


    @Select("SELECT * FROM source_code WHERE program_id = #{programId}")
    @Results({
            @Result(property = "programId", column = "program_id"),
            @Result(property = "codeContent", column = "code_content"),
            @Result(property = "filePath", column = "file_path"),
            @Result(property = "codeId", column = "code_id")
    })
    List<SourceCode> getByProgramId(int programId);
}
