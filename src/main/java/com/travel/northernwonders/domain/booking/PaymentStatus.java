package com.travel.northernwonders.domain.booking;

public enum PaymentStatus {
    PAID("paid"),
    PENDING("pending");

    private String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
