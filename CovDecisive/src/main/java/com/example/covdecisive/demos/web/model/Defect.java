package com.example.covdecisive.demos.web.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "defects")
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Defect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "defect_id")
    private Integer defectId;

    @Column(name = "defect_number")
    private Integer defectNumber;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "program_id", referencedColumnName = "program_id")
    private Program program;
}