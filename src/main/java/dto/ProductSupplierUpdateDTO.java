package dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductSupplierUpdateDTO(
		
		BigDecimal supplierPrice,
		
		BigDecimal supplierSku,
		
		Integer leadTimeDays,
		
		Long productId,
		
		Long supplierId

		) {

}
