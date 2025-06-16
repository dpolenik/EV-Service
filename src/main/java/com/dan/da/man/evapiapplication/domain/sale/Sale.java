package com.dan.da.man.evapiapplication.domain.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("sale")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    private Long id;
    @Column()
    private String vin;

    @Column()
    private Long vehicleId;
    @Column()
    private String county;
    @Column()
    private String city;
    @Column()
    private String state;

    @Column()
    private String postalCode;

    @Column()
    private String legislativeDistrict;

    @Column()
    private String dolVehicleId;

    @Column()
    private String vehicleLocation;

    @Column()
    private String electricUtility;

    @Column()
    private String censusTrack2020;
}
