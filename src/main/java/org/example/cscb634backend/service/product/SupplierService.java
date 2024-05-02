package org.example.cscb634backend.service.product;

import org.example.cscb634backend.dto.product.SupplierDto;
import org.example.cscb634backend.entity.product.Supplier;

import java.util.List;

public interface SupplierService {
	public Supplier createSupplier(SupplierDto supplierDto);
	
	List<SupplierDto> getAllSuppliers();
	
	SupplierDto getSupplierByNameOrId(SupplierDto dto);
	
	SupplierDto getSupplierById(Long id);
	
	Supplier updateSupplier(SupplierDto dto);
	
	void deleteSupplier(long id);
}
