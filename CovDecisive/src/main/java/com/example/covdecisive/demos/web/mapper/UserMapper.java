package com.example.covdecisive.demos.web.mapper;
import java.util.List;

import com.example.covdecisive.demos.web.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Results({
            @Result(property = "discipline",column ="discipline" ),
            @Result(property = "disciplineCode",column = "disciplineCode"),
            @Result(property = "fieldName",column = "fieldName"),
            @Result(property = "totalQuota",column = "totalQuota"),
            @Result(property = "recommendationQuota",column = "recommendationQuota"),
            @Result(property = "mentor",column = "mentor"),
            @Result(property = "course",column = "course"),
            @Result(property = "note",column = "note"),
    })
    @Select("SELECT * FROM User")
    List<User> getAllUsers();
}
