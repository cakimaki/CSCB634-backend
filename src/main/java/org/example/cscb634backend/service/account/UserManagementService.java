package org.example.cscb634backend.service.account;

import org.example.cscb634backend.dto.auth.MyUserDto;
import org.example.cscb634backend.dto.auth.MyUserFrontDto;
import org.example.cscb634backend.entity.auth.MyUser;

public interface UserManagementService {
	MyUser assignSupplierToUser(Long userId,Long supplierId);
	
	MyUserFrontDto assignRoleToUserByIds(Long userId, Long roleId);
	
	MyUserFrontDto addRoleToUser(String email, String role);
	
	MyUserFrontDto removeRoleFromUser(String email, String role);
}
