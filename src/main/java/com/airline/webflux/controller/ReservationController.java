package com.airline.webflux.controller;

import com.airline.webflux.model.ReservationEvent;
import com.airline.webflux.util.ReservationFilters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@RestController
public class ReservationController {

    @GetMapping(value = "/api/reservations/stream", produces = "application/x-ndjson")
    public Flux<ReservationEvent> stream() {

        return Flux.just(
                        new ReservationEvent("1", "Josue", 120.0, List.of("j@mail.com")),
                        new ReservationEvent("2", "Fabricio", -10.0, List.of()),
                        new ReservationEvent("3", "Rosario", 200.0, List.of("r@mail.com")),
                        new ReservationEvent("4", "Daniel", 0.0, List.of("d@mail.com")),
                        new ReservationEvent("5", "Eduardo", 180.0, List.of("e@mail.com"))
                )
                .filter(ReservationFilters.VALID_RESERVATION)

                .doOnNext(ReservationFilters.PRINT_EVENT)
                .defaultIfEmpty(new ReservationEvent("0", "DEFAULT", 1.0, List.of("d@mail.com")))
                .delayElements(Duration.ofSeconds(1));
    }
}