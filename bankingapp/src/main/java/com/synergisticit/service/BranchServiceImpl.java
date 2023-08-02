package com.synergisticit.service;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.synergisticit.domain.Branch;
import com.synergisticit.repository.BranchRepository;

public class BranchServiceImpl implements BranchService {

	@Autowired
	BranchRepository branchRepository;
	
	@Override
	public Branch save(Branch branch) {
		return branchRepository.save(branch);
	}

	@Override
	public Branch find(Long branchId) {
		Optional<Branch> branch = branchRepository.findById(branchId);
		if (branch.isPresent()) {
			return branch.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Branch> findAll() {
		List<Branch> branches = new ArrayList<>();
		Iterable<Branch> iterable = branchRepository.findAll();
		Iterator<Branch> iter = iterable.iterator();
		
		while (iter.hasNext()) {
			branches.add(iter.next());
		}
		
		return branches;
	}

	@Override
	public void delete(Long branchId) {
		branchRepository.deleteById(branchId);
	}

	@Override
	public boolean exists(Long branchId) {
		return branchRepository.existsById(branchId);
	}

}
