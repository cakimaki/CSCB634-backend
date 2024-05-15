package org.example.cscb634backend.service.account;

import jakarta.persistence.EntityNotFoundException;
import org.example.cscb634backend.entity.auth.MyUser;
import org.example.cscb634backend.entity.product.Supplier;
import org.example.cscb634backend.repository.auth.MyUserRepository;
import org.example.cscb634backend.repository.product.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {
	
	private final MyUserRepository userRepository;
	private final SupplierRepository supplierRepository;
	
	public UserManagementServiceImpl(MyUserRepository userRepository, SupplierRepository supplierRepository) {
		this.userRepository = userRepository;
		this.supplierRepository = supplierRepository;
	}
	
	@Override
	public MyUser assignSupplierToUser(Long userId,Long supplierId){
		Supplier supplier = supplierRepository.findById(supplierId)
				.orElseThrow(()->new EntityNotFoundException("User not found with id: " + supplierId));
		MyUser user = userRepository.findById(userId)
				.orElseThrow(()-> new EntityNotFoundException("Supplier not found with id:" + userId));
		
		user.setSupplier(supplier);
		userRepository.save(user);
		return user;
	}
}
