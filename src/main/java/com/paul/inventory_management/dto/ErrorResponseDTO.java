package com.paul.inventory_management.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
		
		int status,
		String message
		
		) {

}
