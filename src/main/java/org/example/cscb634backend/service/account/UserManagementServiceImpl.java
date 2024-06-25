package org.example.cscb634backend.service.account;

import jakarta.persistence.EntityNotFoundException;
import org.example.cscb634backend.dto.auth.MyUserFrontDto;
import org.example.cscb634backend.entity.auth.MyUser;
import org.example.cscb634backend.entity.auth.Role;
import org.example.cscb634backend.entity.product.Supplier;
import org.example.cscb634backend.repository.auth.MyUserRepository;
import org.example.cscb634backend.repository.auth.RoleRepository;
import org.example.cscb634backend.repository.product.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserManagementServiceImpl implements UserManagementService {
	
	private final MyUserRepository userRepository;
	private final SupplierRepository supplierRepository;
	private final RoleRepository roleRepository;
	
	public UserManagementServiceImpl(MyUserRepository userRepository, SupplierRepository supplierRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.supplierRepository = supplierRepository;
		this.roleRepository = roleRepository;
	}
	
	@Override
	public MyUser assignSupplierToUser(Long userId, Long supplierId) {
		Supplier supplier = supplierRepository.findById(supplierId)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + supplierId));
		MyUser user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("Supplier not found with id:" + userId));
		
		user.setSupplier(supplier);
		userRepository.save(user);
		return user;
	}
	
	@Override
	public MyUserFrontDto assignRoleToUserByIds(Long userId, Long roleId) {
		//fetch and check if existing
		MyUser user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("No user found with such id."));
		Role roleBeingAdded = roleRepository.findById(roleId)
				.orElseThrow(() -> new NoSuchElementException("No role found with such id."));
		
		//the roles list being updated.
		List<Role> rolesList = user.getRoleList();
		rolesList.add(roleBeingAdded);
		user.setRoleList(rolesList);
		userRepository.save(user);
		
		//convert it to dto
		return convertToFrontDto(user);
	}
	
	@Override
	public MyUserFrontDto addRoleToUser(String email, String role) {
		MyUser user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User with specified email is not found."));
		
		Role roleObject = roleRepository.findByName(role)
				.orElseThrow(() -> new RuntimeException("Role not found with such name."));
		//add role
		if (user.getRoleList().contains(roleObject)) {
			throw new IllegalArgumentException("Role '" + role + "' has already been asigned to user with email '" + email + "'");
		}
		user.getRoleList().add(roleObject);
		
		MyUser updatedUser = userRepository.save(user);
		
		return convertToFrontDto(updatedUser);
	}
	
	@Override
	public MyUserFrontDto removeRoleFromUser(String email, String role) {
		MyUser user = userRepository.findByEmail(email)
				.orElseThrow(() -> new NoSuchElementException("User with specified email is not found."));
		Role roleObject = roleRepository.findByName(role)
				.orElseThrow(() -> new NoSuchElementException("Role with specified name is not found"));
		
		if (!user.getRoleList().contains(roleObject)) {
			throw new NoSuchElementException("Role '" + role + "' is not assigned to user with email '" + email + "'");
		}
		
		user.getRoleList().remove(roleObject);
		
		MyUser updatedUser = userRepository.save(user);
		
		return convertToFrontDto(updatedUser);
	}
	
	//id email role-list conversion
	private MyUserFrontDto convertToFrontDto(MyUser user) {
		return new MyUserFrontDto(
				user.getId(),
				user.getEmail(),
				user.getRoleList()
		);
	}
}
