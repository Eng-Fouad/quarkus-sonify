package io.quarkiverse.sonify.demo;

import java.util.concurrent.atomic.AtomicInteger;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkiverse.sonify.PlaySound;

@ApplicationScoped
public class OrderService {

    private final AtomicInteger orders = new AtomicInteger();

    /** Every call is heard in dev mode. In production the annotation does nothing. */
    @PlaySound("order-placed")
    public int place() {
        return orders.incrementAndGet();
    }
}
