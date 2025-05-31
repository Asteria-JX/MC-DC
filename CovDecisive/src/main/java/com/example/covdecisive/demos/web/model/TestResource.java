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

    @Column(name = "module")
    private String module;

    @Lob
    @Column(name = "expected_result")
    private String expectedResult;

    @Column(name = "execution_status")
    private String executionStatus; // 注意：此处未使用枚举，因数据库定义为 varchar(50)

    @Lob
    @Column(name = "code_content")
    private String codeContent;

    @ManyToOne
    @JoinColumn(name = "program_id", referencedColumnName = "program_id")
    private Program program;
}