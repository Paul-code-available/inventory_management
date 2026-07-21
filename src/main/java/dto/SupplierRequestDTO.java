package dto;

public record SupplierRequestDTO(
		
		String companyName,
		String contactName, 
		String email,
		String phone,
		String taxId,
		String address
		
		) {

}
