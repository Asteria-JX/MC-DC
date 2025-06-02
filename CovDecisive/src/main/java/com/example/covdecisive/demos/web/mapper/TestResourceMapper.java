package com.example.covdecisive.demos.web.mapper;
import java.util.List;

import com.example.covdecisive.demos.web.model.TestResource;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TestResourceMapper {

    @Insert("INSERT INTO test_resources (name, program_id, user_id,code_content) VALUES (#{name}, #{program_id}, #{user_id},#{code_content})")
    void insert(TestResource resource);


    @Select("SELECT * FROM test_resources where user_id=#{user_id} and program_id=#{program_id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "code_content", column = "code_content"),
            @Result(property = "program_id", column = "program_id"),
            @Result(property = "user_id", column = "user_id")
    })
    List<TestResource> getTestAll(@Param("user_id")Integer user_id,@Param("program_id")Integer program_id);
}
