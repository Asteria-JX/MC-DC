package com.example.covdecisive.demos.web.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "test_resources")
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
//
//    @Column(name = "module")
//    private String module;
//
//    @Lob
//    @Column(name = "expected_result")
//    private String expectedResult;
//
//    @Column(name = "execution_status")
//    private String executionStatus; // 注意：此处未使用枚举，因数据库定义为 varchar(50)

//    @Lob
    @Column(name = "code_content")
    private String code_content;

    @JoinColumn(name = "program_id")
    private Integer program_id;

    @JoinColumn(name = "user_id")
    private Integer user_id;

    public String getCode_content() {
        return code_content;
    }

    public void setCode_content(String codeContent) {
        this.code_content = codeContent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProgram_id() {
        return program_id;
    }

    public void setProgram_id(Integer program_id) {
        this.program_id = program_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}