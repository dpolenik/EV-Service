package com.dan.da.man.evapiapplication.domain.sale;

import com.dan.da.man.evapiapplication.domain.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {
    private Sale sale;
    private Vehicle vehicle;

}
