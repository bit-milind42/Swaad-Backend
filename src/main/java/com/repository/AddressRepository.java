

package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.milind.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
