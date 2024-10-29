package com.hilquiascamelo.launch_service_api.domain.model;

import lombok.Getter;

@Getter
public enum TransactionType {
    CREDITO("Crédito"),
    DEBITO("Débito");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }
}

