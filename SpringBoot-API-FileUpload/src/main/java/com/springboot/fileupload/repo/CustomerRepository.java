package com.springboot.fileupload.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fileupload.bo.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}
