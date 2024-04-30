package org.example.cscb634backend.repository.auth;

import org.example.cscb634backend.entity.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
	Optional<Role> findByName(String roleName);
}
