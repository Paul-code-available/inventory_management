package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.CategoryCreateDTO;
import dto.CategoryResponseDTO;
import dto.CategoryUpdateDTO;
import dto.ProductCreateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import service.CategoryService;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
	
	private final CategoryService categoryService;

	@PostMapping
	public ResponseEntity<CategoryResponseDTO> create(@Valid @RequestBody CategoryCreateDTO dto) {
		
		CategoryResponseDTO response = categoryService.create(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id) {
		
		return ResponseEntity.ok(categoryService.findById(id));
		
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<Void> deactivate(@PathVariable Long id) {
		
		categoryService.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryResponseDTO> update(@PathVariable Long id, @RequestBody CategoryUpdateDTO dto) {
		
		CategoryResponseDTO response = categoryService.update(id, dto);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
}
