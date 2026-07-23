package dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequestDTO(
		
		@NotNull(message = "El nombre es obligatorio")
		String name,
		
		@NotNull(message = "El precio de venta es obligatorio")
		BigDecimal salePrice,
		
		@NotNull(message = "El stock es obligatorio")
		@Positive(message = "El stock debe ser mayor a 0")
		int stock,
		
		@NotNull(message = "La categoria es obligatoria")
		Long categoryId
		
		) {

}
