package com.nelson.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nelson.app.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
