package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.User;

public interface UserService {
	User save(User u);
	User find(Long userId);
	List<User> findAll();
	void delete(Long userId);
	boolean exists(Long userId);
}
