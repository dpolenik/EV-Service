package com.dan.da.man.evapiapplication.sales.data.persistent.repository;

import com.dan.da.man.evapiapplication.domain.vehicle.Vehicle;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VehicleRepository extends ReactiveCrudRepository<Vehicle, Long> {
    @Override
    @Cacheable(cacheNames = "vehicleBlob", key = "#p0")
    Mono<Vehicle> findById(Long id);

    @Override
    @CachePut(cacheNames = "vehicleBlob", key = "#result.id")
    Mono<Vehicle> save(Vehicle v);

    @Override
    @CacheEvict(cacheNames = "vehicleBlob", key = "#p0")
    Mono<Void> deleteById(Long id);

    @Cacheable(cacheNames = "vehicleByMakeModelYear", key = "#make + '|' + #model + '|' + #modelYear")
    Mono<Vehicle> findByMakeAndModelAndModelYear(String make, String model, String modelYear);

    @Override
    Flux<Vehicle> findAll();
}
