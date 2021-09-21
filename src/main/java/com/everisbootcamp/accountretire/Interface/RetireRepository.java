package com.everisbootcamp.accountretire.Interface;

import com.everisbootcamp.accountretire.Data.Retire;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RetireRepository extends ReactiveMongoRepository<Retire, String> {}
