package com.demoapi.demorestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.demoapi.demorestfulapi.entities.User;

@EnableJpaRepositories
@Repository

public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * Sign in
	 * @param username
	 * @param password
	 * @return User
	 */
	public User findByUsernameAndPassword(String username, String password);
}
