package com.hilquiascamelo.launch_service_api.domain.mapper;

import com.hilquiascamelo.launch_service_api.domain.dto.TransactionDto;
import com.hilquiascamelo.launch_service_api.domain.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends EntityMapper<TransactionDto, Transaction> {
}