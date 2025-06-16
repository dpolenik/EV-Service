package com.dan.da.man.evapiapplication.domain.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    private Long id;
    @Column
    private String make;
    @Column
    private String model;
    @Column
    private String modelYear;
    @Column
    private String evType;
    @Column
    private String cafvEligibility;
    @Column
    private Integer electricRange;
    @Column
    private BigDecimal baseMsrp;
}