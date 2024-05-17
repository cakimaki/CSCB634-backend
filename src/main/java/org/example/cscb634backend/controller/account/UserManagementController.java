package org.example.cscb634backend.controller.account;

import org.example.cscb634backend.service.account.UserManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserManagementController {
	private final UserManagementService userManagementService;
	
	public UserManagementController(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}
	
	public ResponseEntity<String> assignSupplierToUser(Long userId, Long supplierId){
		try{
			userManagementService.assignSupplierToUser(userId,supplierId);
			return ResponseEntity.ok("Supplier has been asigned to user.");
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
}
