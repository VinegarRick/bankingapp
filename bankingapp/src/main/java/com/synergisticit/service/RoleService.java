package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Role;

public interface RoleService {
	Role save(Role role);
	Role find(Long roleId);
	List<Role> findAll();
	void delete(Long roleId);
	boolean exists(Long roleId);
}
