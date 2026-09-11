package com.paul.inventory_management.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
		
		LocalDateTime timeStamp,
		int status,
		String error,
		String message,
		String path
		
		) {

}
