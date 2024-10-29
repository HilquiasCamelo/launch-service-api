package com.hilquiascamelo.launch_service_api.application.service;

import com.hilquiascamelo.launch_service_api.application.exception.ResourceNotFoundException;
import com.hilquiascamelo.launch_service_api.domain.dto.TransactionDto;
import com.hilquiascamelo.launch_service_api.domain.mapper.TransactionMapper;
import com.hilquiascamelo.launch_service_api.domain.model.Transaction;
import com.hilquiascamelo.launch_service_api.domain.repository.TransactionRepository;
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
public class TransactionService {
    private final TransactionRepository repository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository repository, TransactionMapper transactionMapper) {
        this.repository = repository;
        this.transactionMapper = transactionMapper;
    }

    public TransactionDto save(TransactionDto transactionDto) {
        Transaction entity = transactionMapper.toEntity(transactionDto);
        return transactionMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public TransactionDto findById(Long id) {
        return transactionMapper.toDto(repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    public Page<TransactionDto> findByCondition(TransactionDto transactionDto, Pageable pageable) {
        Page<Transaction> entityPage = repository.findAll(pageable);
        List<Transaction> entities = entityPage.getContent();
        return new PageImpl<>(transactionMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public TransactionDto update(TransactionDto transactionDto, Long id) {
        TransactionDto data = findById(id);
        Transaction entity = transactionMapper.toEntity(transactionDto);
        BeanUtils.copyProperties(data, entity);
        return save(transactionMapper.toDto(entity));
    }
}
