package com.hilquiascamelo.launch_service_api.domain.dto;

import com.hilquiascamelo.launch_service_api.domain.annotation.CheckDate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema(description = "Data Transfer Object for Daily Balance")
public class DailyBalanceDto extends AbstractDto<Long> {

    @Schema(description = "ID do saldo diário")
    private Long id;

    @CheckDate
    @NotNull(message = "Date must not be null")
    @Schema(description = "Data do saldo diário (deve ser não nula)")
    private LocalDate date;

    @NotNull(message = "Balance must not be null")
    @Schema(description = "Saldo diário (deve ser não nulo)")
    private BigDecimal balance;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "Creation date must not be null")
    @Schema(description = "Data de criação do saldo diário (deve ser não nula)")
    private LocalDateTime createdAt;
}
