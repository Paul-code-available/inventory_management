package dto;

import java.math.BigDecimal;

public record ProductSupplierRequestDTO(
		
		BigDecimal supplierPrice,
		Integer leadTimeDays,
		Long productId,
		Long supplierId

		) {

}
