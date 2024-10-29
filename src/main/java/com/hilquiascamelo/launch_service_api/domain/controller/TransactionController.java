package com.hilquiascamelo.launch_service_api.domain.controller;

import com.hilquiascamelo.launch_service_api.application.exception.ResourceNotFoundException;
import com.hilquiascamelo.launch_service_api.application.service.TransactionService;
import com.hilquiascamelo.launch_service_api.domain.dto.TransactionDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/transaction")
@RestController
@Tag(name = "Transaction API", description = "API for managing daily financial transactions and balances.")
public class TransactionController {

    private static final Logger log
            =  LogManager. getLogger(TransactionController.class);

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDto> save(@RequestBody @Validated TransactionDto transactionDto) {
        TransactionDto saveDTO = transactionService.save(transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable("id") Long id) {
        TransactionDto transaction = transactionService.findById(id);
        return ResponseEntity.ok(transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(transactionService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        transactionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<TransactionDto>> pageQuery(TransactionDto transactionDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<TransactionDto> transactionPage = transactionService.findByCondition(transactionDto, pageable);
        return ResponseEntity.ok(transactionPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated TransactionDto transactionDto, @PathVariable("id") Long id) {
        transactionService.update(transactionDto, id);
        return ResponseEntity.ok().build();
    }
}
