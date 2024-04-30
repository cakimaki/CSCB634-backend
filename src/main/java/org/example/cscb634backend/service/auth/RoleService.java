package org.example.cscb634backend.service.auth;

import org.example.cscb634backend.dto.auth.RoleDto;
import org.example.cscb634backend.entity.auth.Role;

import java.util.List;

public interface RoleService {
	
	Role createRole(RoleDto roleDto) throws Exception;
	
	List<RoleDto> getAllRoles();
	
	Role updateRole(RoleDto dto);
	
	RoleDto getRoleByNameOrId(RoleDto roleDto);
}
