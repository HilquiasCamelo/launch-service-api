package com.hilquiascamelo.launch_service_api.domain.dto;

import com.hilquiascamelo.launch_service_api.domain.model.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema(description = "Data Transfer Object for Transaction")
@Data
public class TransactionDto extends AbstractDto<Long> {

    @Schema(description = "ID da transação")
    private Long id;

    @NotNull(message = "Transaction type must not be null")
    @Schema(description = "Tipo da transação (deve ser não nulo)")
    private TransactionType type;

    @NotNull(message = "Amount must not be null")
    @Schema(description = "Valor da transação (deve ser não nulo)")
    private BigDecimal amount;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "Transaction date must not be null")
    @Schema(description = "Data da transação (deve ser não nulo)")
    private LocalDateTime transactionDate;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    @NotBlank(message = "Description must not be blank")
    @Schema(description = "Descrição da transação (deve ser não nula ou em branco)")
    private String description;
}
