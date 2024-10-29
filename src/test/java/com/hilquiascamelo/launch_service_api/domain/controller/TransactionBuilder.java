package com.hilquiascamelo.launch_service_api.domain.controller;

import com.hilquiascamelo.launch_service_api.domain.dto.TransactionDto;

import java.util.Collections;
import java.util.List;

public class TransactionBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static TransactionDto getDto() {
        TransactionDto dto = new TransactionDto();
        dto.setId(1L);
        return dto;
    }
}
