package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
