package com.shaz.TodoFullStack.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shaz.TodoFullStack.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>
{
	 User findByUsername(String username);

}
