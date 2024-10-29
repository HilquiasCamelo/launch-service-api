package com.hilquiascamelo.launch_service_api.application.service;

import com.hilquiascamelo.launch_service_api.application.exception.ResourceNotFoundException;
import com.hilquiascamelo.launch_service_api.domain.dto.DailyBalanceDto;
import com.hilquiascamelo.launch_service_api.domain.mapper.DailyBalanceMapper;
import com.hilquiascamelo.launch_service_api.domain.model.DailyBalance;
import com.hilquiascamelo.launch_service_api.domain.repository.DailyBalanceRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@Transactional
public class DailyBalanceService {
    private final DailyBalanceRepository repository;
    private final DailyBalanceMapper dailyBalanceMapper;

    public DailyBalanceService(DailyBalanceRepository repository, DailyBalanceMapper dailyBalanceMapper) {
        this.repository = repository;
        this.dailyBalanceMapper = dailyBalanceMapper;
    }

    public DailyBalanceDto save(DailyBalanceDto dailyBalanceDto) {
        DailyBalance entity = dailyBalanceMapper.toEntity(dailyBalanceDto);
        return dailyBalanceMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public DailyBalanceDto findById(Long id) {
        return dailyBalanceMapper.toDto(repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    public Page<DailyBalanceDto> findByCondition(DailyBalanceDto dailyBalanceDto, Pageable pageable) {
        Page<DailyBalance> entityPage = repository.findAll(pageable);
        List<DailyBalance> entities = entityPage.getContent();
        return new PageImpl<>(dailyBalanceMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public DailyBalanceDto update(DailyBalanceDto dailyBalanceDto, Long id) {
        DailyBalanceDto data = findById(id);
        DailyBalance entity = dailyBalanceMapper.toEntity(dailyBalanceDto);
        BeanUtils.copyProperties(data, entity);
        return save(dailyBalanceMapper.toDto(entity));
    }
}
