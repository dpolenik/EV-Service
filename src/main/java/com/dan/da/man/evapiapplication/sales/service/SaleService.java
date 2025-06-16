package com.dan.da.man.evapiapplication.sales.service;

import com.dan.da.man.evapiapplication.domain.sale.SaleDTO;
import com.dan.da.man.evapiapplication.domain.sale.Sale;
import com.dan.da.man.evapiapplication.domain.vehicle.Vehicle;
import com.dan.da.man.evapiapplication.sales.data.persistent.repository.SaleRepository;
import com.dan.da.man.evapiapplication.sales.data.persistent.repository.VehicleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
@AllArgsConstructor
public class SaleService {
    private final SaleRepository saleRepo;
    private final VehicleRepository vehicleRepo;
    private final ObjectMapper objectMapper;
    public Mono<Sale> createSale(SaleDTO dto) {
        Mono<Vehicle> vehicleMono = vehicleRepo
                .findByMakeAndModelAndModelYear(dto.getVehicle().getMake(), dto.getVehicle().getModel(), dto.getVehicle().getModelYear());
        return vehicleMono.flatMap(v -> {
            Sale s = objectMapper.convertValue(dto.getSale(), Sale.class);
            s.setVehicleId(v.getId());
            return saleRepo.save(s);
        });
    }

    public Mono<SaleDTO> fetchByVin(String vin) {
        return saleRepo.findByVin(vin)
                .flatMap(sale -> vehicleRepo.findById(sale.getVehicleId())
                        .map(veh -> new SaleDTO(sale, veh)));
    }


    public Mono<Void> deleteSale(Long id) {
        return saleRepo.deleteById(id);
    }
}