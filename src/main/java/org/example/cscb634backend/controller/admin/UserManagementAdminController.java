package org.example.cscb634backend.controller.admin;

import org.example.cscb634backend.dto.auth.MyUserDto;
import org.example.cscb634backend.dto.auth.MyUserFrontDto;
import org.example.cscb634backend.dto.auth.UserRoleDto;
import org.example.cscb634backend.service.account.UserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/admin")
public class UserManagementAdminController {
	private final UserManagementService userManagementService;
	
	public UserManagementAdminController(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}
	
	@PostMapping("/add-role")
	public ResponseEntity<MyUserFrontDto> addRoleToUserByEmail(@RequestBody UserRoleDto dto){
		try{
			return ResponseEntity.ok(userManagementService.addRoleToUser(dto.getEmail(),dto.getRole()));
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("Adding role to user crashed.");
		}
	}
	
	@PostMapping("/remove-role")
	public ResponseEntity<MyUserFrontDto> removeRoleFromUserByEmail(@RequestBody UserRoleDto dto){
		try{
			MyUserFrontDto updated = userManagementService.removeRoleFromUser(dto.getEmail(),dto.getRole());
			return ResponseEntity.ok(updated);
		}catch (NoSuchElementException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
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
