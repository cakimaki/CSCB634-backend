package org.example.cscb634backend.controller.admin;

import org.example.cscb634backend.dto.auth.MyUserDto;
import org.example.cscb634backend.dto.auth.MyUserFrontDto;
import org.example.cscb634backend.service.account.UserManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class UserManagementAdminController {
	private final UserManagementService userManagementService;
	
	public UserManagementAdminController(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}
	
	
	@PostMapping("/assign-role-ids")
	public ResponseEntity<MyUserFrontDto> assignRoleToUserByIds(@RequestBody Long userId, Long roleId){
		try{
			return ResponseEntity.ok(userManagementService.assignRoleToUserByIds(userId,roleId));
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("Assigning did not go through.");
		}
	}
}
