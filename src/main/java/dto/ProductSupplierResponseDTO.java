package dto;

import java.math.BigDecimal;

public record ProductSupplierResponseDTO(
		
		Long id,
		BigDecimal supplierPrice,
		Integer leadTimeDays,
		ProductResponseDTO product,
		SupplierResponseDTO supplier
		
		) {

}
