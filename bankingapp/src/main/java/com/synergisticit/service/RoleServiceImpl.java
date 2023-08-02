package com.synergisticit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired 
	RoleRepository roleRepository;
	
	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role find(Long roleId) {
		Optional<Role> role = roleRepository.findById(roleId);
		if (role.isPresent()) {
			return role.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Role> findAll() {
		List<Role> roles = new ArrayList<>();
		
		Iterable<Role> iterable = roleRepository.findAll();
		Iterator<Role> itr = iterable.iterator();
		
		while(itr.hasNext()) {
			roles.add(itr.next());
		}
		
		return roles;
	}

	@Override
	public void delete(Long roleId) {
		roleRepository.deleteById(roleId);
	}
	
	@Override
	public boolean exists(Long roleId) {
		return roleRepository.existsById(roleId);
	}
	
}
