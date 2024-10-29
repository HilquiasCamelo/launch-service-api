package com.hilquiascamelo.launch_service_api.domain.mapper;

import com.hilquiascamelo.launch_service_api.domain.dto.DailyBalanceDto;
import com.hilquiascamelo.launch_service_api.domain.model.DailyBalance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DailyBalanceMapper extends EntityMapper<DailyBalanceDto, DailyBalance> {
}