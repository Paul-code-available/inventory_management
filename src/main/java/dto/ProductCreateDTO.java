package dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductCreateDTO(
		
		@NotNull(message = "Sku is required")
		String sku,
		
		@NotNull(message = "Name is required")
		String name,
		
		String brand,
		
		String description,
		
		@NotNull(message = "Purchase price is required")
		@Positive(message = "Purchase price have to be greater than zero")
		BigDecimal purchasePrice,
		
		@NotNull(message = "Sale price is required")
		@Positive(message = "Sale price have to be greater than zero")
		BigDecimal salePrice,
		
		@NotNull(message = "Stock is required")
		@Positive(message = "Stock have to be greater than zero")
		int stock,
		
		@NotNull(message = "Minimum stock is required")
		@Positive(message = "Minimum stock have to be greater than zero or zero")
		int minimumStock,
		
		String imgUrl,
		
		@NotNull(message = "Category is required")
		Long categoryId
		
		) {

}
