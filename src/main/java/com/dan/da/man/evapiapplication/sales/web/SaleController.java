package com.dan.da.man.evapiapplication.sales.web;

import com.dan.da.man.evapiapplication.domain.sale.SaleDTO;
import com.dan.da.man.evapiapplication.domain.sale.Sale;
import com.dan.da.man.evapiapplication.sales.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sales")
@AllArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public Mono<ResponseEntity<Sale>> create(@RequestBody SaleDTO req) {
        return saleService.createSale(req)
                .map(v -> ResponseEntity.status(HttpStatus.CREATED).body(v));
    }

    @GetMapping
    public Mono<ResponseEntity<SaleDTO>> byVin(@RequestParam String vin) {
        return saleService.fetchByVin(vin)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long id) {
        return saleService.deleteSale(id)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
