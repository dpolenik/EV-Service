package com.dan.da.man.evapiapplication.sales.data.persistent.repository;

import com.dan.da.man.evapiapplication.domain.sale.Sale;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SaleRepository extends ReactiveCrudRepository<Sale, Long> {
    Mono<Sale> findByVin(String vin);
    @Override
    <S extends Sale> Mono<S> save(S v);

}