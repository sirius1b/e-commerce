package com.sirius1b.order.exceptions.wrapper;

public class OrderNotFound extends RuntimeException {

    public OrderNotFound() {
        super();
    }

    public OrderNotFound(String msg) {
        super(msg);
    }
}
