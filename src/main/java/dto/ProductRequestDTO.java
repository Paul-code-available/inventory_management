package dto;

import java.math.BigDecimal;

public record ProductRequestDTO(
		
		String name,
		BigDecimal salePrice,
		int stock,
		Long category
		
		) {

}
