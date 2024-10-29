package com.hilquiascamelo.launch_service_api.domain.controller;

import com.hilquiascamelo.launch_service_api.application.exception.ResourceNotFoundException;
import com.hilquiascamelo.launch_service_api.application.service.DailyBalanceService;
import com.hilquiascamelo.launch_service_api.domain.dto.DailyBalanceDto;
import io.swagger.v3.oas.annotations.Operation;
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

@RequestMapping("/api/daily-balance")
@RestController
@Tag(name = "Daily Balance API", description = "API for managing daily balances")
public class DailyBalanceController {

    private static final Logger log
            =  LogManager. getLogger(DailyBalanceController.class);

    private final DailyBalanceService dailyBalanceService;

    public DailyBalanceController(DailyBalanceService dailyBalanceService) {
        this.dailyBalanceService = dailyBalanceService;
    }

    @PostMapping
    @Operation(summary = "Save Daily Balance", description = "Saves a new daily balance entry")
    public ResponseEntity<DailyBalanceDto> save(@RequestBody @Validated DailyBalanceDto dailyBalanceDto) {
        DailyBalanceDto savedDto = dailyBalanceService.save(dailyBalanceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Find Daily Balance by ID", description = "Retrieves a daily balance entry by its ID")
    public ResponseEntity<DailyBalanceDto> findById(@PathVariable("id") Long id) {
        DailyBalanceDto dailyBalance = dailyBalanceService.findById(id);
        return ResponseEntity.ok(dailyBalance);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Daily Balance", description = "Deletes a daily balance entry by its ID")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(dailyBalanceService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        dailyBalanceService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    @Operation(summary = "Paginated Daily Balances", description = "Retrieves a paginated list of daily balances based on conditions")
    public ResponseEntity<Page<DailyBalanceDto>> pageQuery(DailyBalanceDto dailyBalanceDto,
                                                           @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<DailyBalanceDto> dailyBalancePage = dailyBalanceService.findByCondition(dailyBalanceDto, pageable);
        return ResponseEntity.ok(dailyBalancePage);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Daily Balance", description = "Updates an existing daily balance entry by its ID")
    public ResponseEntity<Void> update(@RequestBody @Validated DailyBalanceDto dailyBalanceDto, @PathVariable("id") Long id) {
        dailyBalanceService.update(dailyBalanceDto, id);
        return ResponseEntity.ok().build();
    }
}
