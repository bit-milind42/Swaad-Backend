// package com.repository;
// import org.springframework.data.jpa.repository.JpaRepository;

// public class AddressRepository extends JpaRespository<address,Long>{
    
// }

package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.milind.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
