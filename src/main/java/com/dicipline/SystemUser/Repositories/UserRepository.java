package com.dicipline.SystemUser.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dicipline.SystemUser.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
