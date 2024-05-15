package org.example.cscb634backend.service.account;

import org.example.cscb634backend.entity.auth.MyUser;

public interface UserManagementService {
	MyUser assignSupplierToUser(Long userId,Long supplierId);
}
