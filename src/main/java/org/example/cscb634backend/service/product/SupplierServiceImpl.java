package org.example.cscb634backend.service.product;

import org.example.cscb634backend.dto.product.SupplierDto;
import org.example.cscb634backend.entity.product.Supplier;
import org.example.cscb634backend.repository.product.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService{
	private final SupplierRepository supplierRepository;
	
	@Autowired
	public SupplierServiceImpl(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}
	
	@Override
	public Supplier createSupplier(SupplierDto supplierDto) {
		if (supplierDto.getName() == null || supplierDto.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("No name entered for supplier.");
		}
		Supplier supplier = new Supplier();
		supplier.setName(supplierDto.getName());
		return supplierRepository.save(supplier);
	}
	
	@Override
	public List<SupplierDto> getAllSuppliers() {
		return supplierRepository.findAll().stream().map(this::convertToDto).toList();
	}
	
	@Override
	public SupplierDto getSupplierByNameOrId(SupplierDto dto) {
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
	
	@Override
	public SupplierDto getSupplierById(Long id){
		Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
		if(optionalSupplier.isPresent()){
			return convertToDto(optionalSupplier.get());
		}else{
			throw new NoSuchElementException("No supplier found with id: "+id);
		}
	}
	@Override
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
	
	@Override
	public void deleteSupplier(long id){
		Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
		if(optionalSupplier.isPresent()){
			Supplier supplier = optionalSupplier.get();
			supplier.setValid(false);
		}
	}
}
