package dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductSupplierRequestDTO(
		
		@NotNull(message = "El precio del proveedor es obligatorio")
		@Positive(message = "El precio del proveedor debe ser mayor a 0")
		BigDecimal supplierPrice,
		
		@NotNull(message = "El tiempo de entrega es obligatorio")
		Integer leadTimeDays,
		
		@NotNull(message = "El producto es obligatorio")
		Long productId,
		
		@NotNull(message = "El proveedor es obligatorio")
		Long supplierId

		) {

}
