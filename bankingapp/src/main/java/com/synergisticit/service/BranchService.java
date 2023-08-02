package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Branch;

public interface BranchService {
	Branch save(Branch branch);
	Branch find(Long branchId);
	List<Branch> findAll();
	void delete(Long branchId);
	boolean exists(Long branchId);
}
