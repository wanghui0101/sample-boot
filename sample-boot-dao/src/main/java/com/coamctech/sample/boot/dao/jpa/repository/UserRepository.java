package com.coamctech.sample.boot.dao.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.sample.boot.dao.jpa.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, String> {

}
