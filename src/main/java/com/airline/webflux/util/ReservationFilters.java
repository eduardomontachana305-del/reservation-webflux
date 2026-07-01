package com.airline.webflux.util;

import com.airline.webflux.model.ReservationEvent;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ReservationFilters {

    public static final Predicate<ReservationEvent> VALID_RESERVATION =
            r -> r.getPrice() > 0 && !r.getEmails().isEmpty();

    public static final Consumer<ReservationEvent> PRINT_EVENT =
            r -> System.out.println("Procesado: " + r.getId() + " - " + r.getPassengerName());
}