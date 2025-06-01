package com.example.covdecisive.demos.web.mapper;
import java.util.List;
import org.apache.ibatis.annotations.*;
import com.example.covdecisive.demos.web.model.Program;

@Mapper
public interface ProgramMapper {
    @Select("SELECT * FROM programs")
    @Results({
            @Result(property = "programId", column = "program_id"),
            @Result(property = "programName", column = "program_name"),
            @Result(property = "version", column = "version"),
            @Result(property = "description", column = "description")
    })
    List<Program> getAll();

    @Insert("INSERT INTO programs (program_name, version, description) VALUES (#{programName}, #{version}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "programId")
    void insert(Program program);


}
