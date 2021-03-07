package com.networth.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.networth.model.NetWorth;

public interface NetWorthRepo extends MongoRepository<NetWorth, Long>{
	
	@Query("Select * from Networth where id =:id" )
	Optional<NetWorth> findById(String id);

}
