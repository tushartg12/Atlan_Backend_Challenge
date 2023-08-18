package com.atlan.backend.repository;

import com.atlan.backend.entity.CustomerDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerDetails,String> {
}
