package org.example.cscb634backend.controller.auth;

import com.sun.jdi.InternalException;
import org.example.cscb634backend.dto.auth.RoleDto;
import org.example.cscb634backend.entity.auth.Role;
import org.example.cscb634backend.service.auth.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
public class RoleController {
	private final RoleService roleService;
	
	@Autowired
	public RoleController(RoleService roleService){
		this.roleService = roleService;
	}
	
	@PostMapping("/admin/create-role")
	public ResponseEntity<Role> createRole(@RequestBody RoleDto roleDto) {
		try {
			return ResponseEntity.ok(roleService.createRole(roleDto));
		} catch (Exception e) {
			throw new InternalException();
		}
	}
}
