package org.example.cscb634backend.repository;

import org.example.cscb634backend.entity.auth.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
	Optional<MyUser> findByUsername(String username);


}
