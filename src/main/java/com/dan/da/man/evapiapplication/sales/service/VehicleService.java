package com.dan.da.man.evapiapplication.sales.service;

import com.dan.da.man.evapiapplication.domain.vehicle.Vehicle;
import com.dan.da.man.evapiapplication.sales.data.persistent.repository.VehicleRepository;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class VehicleService {
    private final VehicleRepository repo;

    public VehicleService(VehicleRepository repo) {
        this.repo = repo;
    }

    @Timed
    @Counted("vehicle.read")
    public Flux<Vehicle> all() {
        return repo.findAll();
    }

    @Timed
    @Counted("vehicle.read")
    public Mono<Vehicle> byId(Long id) {
        return repo.findById(id);
    }

    @Timed
    @Counted("vehicle.create")
    public Mono<Vehicle> create(Vehicle v) {
        return repo.findByMakeAndModelAndModelYear(v.getMake(),v.getModel(),v.getModelYear())
                .switchIfEmpty(repo.save(v));
    }

    @Timed
    @Counted("vehicle.create")
    public Flux<Vehicle> createBatch(Collection<Vehicle> vs) {
        return repo.saveAll(vs);
    }

    @Timed
    @Counted("vehicle.update")
    public Mono<Vehicle> update(Long id, BigDecimal msrp) {
        Mono<Vehicle> vehicle = repo.findById(id);
        return vehicle.flatMap((v) -> {
            v.setBaseMsrp(msrp);
            return repo.save(v);
        });
    }

    @Timed
    @Counted("vehicle.delete")
    public Mono<Void> delete(Long id) {
        return repo.deleteById(id);
    }
}