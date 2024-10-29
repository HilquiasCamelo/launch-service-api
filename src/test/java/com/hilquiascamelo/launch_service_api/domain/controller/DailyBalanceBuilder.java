package com.hilquiascamelo.launch_service_api.domain.controller;

import com.hilquiascamelo.launch_service_api.domain.dto.DailyBalanceDto;

import java.util.Collections;
import java.util.List;

public class DailyBalanceBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static DailyBalanceDto getDto() {
        DailyBalanceDto dto = new DailyBalanceDto();
        dto.setId(1L);
        return dto;
    }
}
