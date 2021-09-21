package com.everisbootcamp.accountretire.Interface;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.everisbootcamp.accountretire.Data.Retire; 

public interface RetireRepository extends ReactiveMongoRepository<Retire, String> {
	
}
