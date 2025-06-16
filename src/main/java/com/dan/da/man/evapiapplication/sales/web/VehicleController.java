package com.dan.da.man.evapiapplication.sales.web;

import com.dan.da.man.evapiapplication.domain.vehicle.Vehicle;
import com.dan.da.man.evapiapplication.sales.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService svc;

    public VehicleController(VehicleService svc) {
        this.svc = svc;
    }

    @GetMapping
    public Flux<Vehicle> all() {
        return svc.all();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Vehicle>> one(@PathVariable Long id) {
        return svc.byId(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Vehicle>> create(@RequestBody Vehicle v) {
        return svc.create(v).map(o -> new ResponseEntity<>(o, HttpStatus.CREATED));
    }

    @PostMapping("/batch")
    public Flux<Vehicle> batch(@RequestBody Collection<Vehicle> vs) {
        return svc.createBatch(vs);
    }

    @PutMapping("/{id}/msrp")
    public Mono<Vehicle> update(@PathVariable Long id, @RequestBody Vehicle v) {
        return svc.update(id, v.getBaseMsrp());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return svc.delete(id);
    }
}