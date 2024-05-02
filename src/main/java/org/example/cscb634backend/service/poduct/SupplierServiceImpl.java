package org.example.cscb634backend.service.poduct;

import org.example.cscb634backend.dto.product.SupplierDto;
import org.example.cscb634backend.entity.product.Supplier;
import org.example.cscb634backend.repository.product.SupplierRepository;
import org.hibernate.engine.transaction.jta.platform.internal.SunOneJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SupplierServiceImpl {
	private final SupplierRepository supplierRepository;
	
	@Autowired
	public SupplierServiceImpl(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}
	
	public Supplier createSupplier(SupplierDto supplierDto) {
		if (supplierDto.getName() == null || supplierDto.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("No name entered for supplier.");
		}
		Supplier supplier = new Supplier();
		supplier.setName(supplierDto.getName());
		return supplierRepository.save(supplier);
	}
	
	public List<SupplierDto> getAllSuppliers() {
		return supplierRepository.findAll().stream().map(this::convertToDto).toList();
	}
	
	public SupplierDto getSupplierByName(SupplierDto dto) {
		Supplier supplier = new Supplier();
		if (dto.getId() != null) {
			supplier = supplierRepository.findById(dto.getId())
					.orElseThrow(() -> new NoSuchElementException("No supplier found with such id: " + dto.getId()));
		} else if (dto.getName() != null) {
			supplier = supplierRepository.findByName(dto.getName())
					.orElseThrow(() -> new NoSuchElementException("No supplier found with such name: " + dto.getName()));
		} else {
			throw new IllegalArgumentException("No valid identifier (name or id) is provided.");
		}
		return convertToDto(supplier);
	}
	
	public Supplier updateSupplier(SupplierDto dto){
		Supplier supplier = new Supplier();
		
		supplier.setName(dto.getName());
		supplier.setValid(dto.getValid());
		supplier.setSoloPosting(dto.isSoloPosting());
		return supplierRepository.save(supplier);
	}
	private SupplierDto convertToDto(Supplier supplier) {
		SupplierDto dto = new SupplierDto();
		dto.setName(supplier.getName());
		return dto;
	}
}
