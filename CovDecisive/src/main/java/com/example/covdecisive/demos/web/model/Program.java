package com.example.covdecisive.demos.web.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programs")
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Integer programId;

    @Column(name = "program_name")
    private String programName;

    @Column(name = "version")
    private String version;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<Defect> defects = new ArrayList<>();
}