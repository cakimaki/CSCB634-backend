package org.example.cscb634backend.service.auth;

import org.example.cscb634backend.dto.auth.RoleDto;
import org.example.cscb634backend.entity.auth.Role;
import org.example.cscb634backend.repository.auth.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{
	private final RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository){
		this.roleRepository = roleRepository;
	}
	
	@Override
	@Transactional
	public Role createRole(RoleDto roleDto) throws Exception {
		try {
			return roleRepository.findByName(roleDto.getName())
					.orElseGet(() -> roleRepository.save(new Role(roleDto.getName())));
		}catch (Exception e){
			throw new Exception("Unable to create role...", e);
		}
	}
	
	@Override
	public Role updateRole(RoleDto dto){
		Role role = findRole(dto);
		if(!(dto.getName().isEmpty())){
			role.setName(dto.getName());
		}
		if(!(dto.getDescription().isEmpty())){
			role.setDescription(dto.getDescription());
		}
		
		return roleRepository.save(role);
	}
	
	@Override
	@Transactional
	public List<RoleDto> getAllRoles(){
		List<Role> roles = roleRepository.findAll();
		return roles.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public RoleDto getRoleByNameOrId(RoleDto roleDto){
		Role role = findRole(roleDto);
		return convertToDto(role);
	}
	
	private RoleDto convertToDto(Role role){
		RoleDto dto = new RoleDto();
		dto.setId(role.getId());
		dto.setName(role.getName());
		dto.setDescription(role.getDescription());
		return dto;
	}
	
	public Role findRole(RoleDto dto){
		//if id is presented
		if(dto.getId() != null){
			Optional<Role> roleById = roleRepository.findById(dto.getId());
			if(roleById.isPresent()){
				return roleById.get();
			}
		}
		
		//if name is presented
		if(dto.getName()!=null){
			return roleRepository.findByName(dto.getName())
					.orElseThrow(()-> new NoSuchElementException("No such role with name: " + dto.getName()));
		}
		//if not found or nothing is presented
		throw new NoSuchElementException("No name or id specified/no role was found.");
	}
	
	
}
