package com.example.covdecisive.demos.web.mapper;
import java.util.List;
import com.example.covdecisive.demos.web.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Results(id = "UserMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "userType", column = "user_type")
    })
    @Select("SELECT * FROM users")
    List<User> getAllUsers();

    @ResultMap("UserMap")
    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(@Param("username") String username);
}
