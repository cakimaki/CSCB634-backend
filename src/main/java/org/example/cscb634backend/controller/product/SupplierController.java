package org.example.cscb634backend.controller.product;

import org.example.cscb634backend.dto.product.SupplierDto;
import org.example.cscb634backend.service.product.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/supplier")
public class SupplierController {
	private final SupplierService supplierService;
	
	
	public SupplierController(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createSupplier(@RequestBody SupplierDto supplierDto){
		
		try {
			
			supplierService.createSupplier(supplierDto);
			return ResponseEntity.ok("Supplier was created.");
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("Supplier was not created.");
		}
	}
	
	
}
