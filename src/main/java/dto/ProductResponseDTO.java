package dto;

import java.math.BigDecimal;

public record ProductResponseDTO(
		
		Long productId,
		String name,
		BigDecimal salePrice,
		Integer stock,
		CategoryResponseDTO category
		
		) {

}
