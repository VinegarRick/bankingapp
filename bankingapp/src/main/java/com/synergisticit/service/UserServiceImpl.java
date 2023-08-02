package com.synergisticit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User u) {
		return userRepository.save(u);
	}

	@Override
	public User find(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}		
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		Iterable<User> iterable = userRepository.findAll();
		Iterator<User> itr = iterable.iterator();
		while (itr.hasNext()) {
			users.add(itr.next());
		}
		
		return users;
	}

	@Override
	public void delete(Long userId) {
		userRepository.deleteById(userId);
	}
	
	@Override
	public boolean exists(Long userId) {
		return userRepository.existsById(userId);
	}

}