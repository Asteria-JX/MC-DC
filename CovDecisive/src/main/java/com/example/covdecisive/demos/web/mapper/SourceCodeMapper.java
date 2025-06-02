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

    @Select("SELECT code_content FROM source_code WHERE program_id = #{programId} AND file_path = #{filePath}")
    String selectCodeContent(@Param("programId") int programId, @Param("filePath") String filePath);

    @Update("UPDATE source_code SET code_content = #{codeContent} WHERE program_id = #{programId} AND file_path = #{filePath}")
    int updateCode(@Param("programId") int programId, @Param("filePath") String filePath, @Param("codeContent") String codeContent);

}
