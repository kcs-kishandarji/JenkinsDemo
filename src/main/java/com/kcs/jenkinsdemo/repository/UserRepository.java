package com.kcs.jenkinsdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kcs.jenkinsdemo.core.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findOneByName(String name);
}
