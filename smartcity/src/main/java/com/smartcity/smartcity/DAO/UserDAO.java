package com.smartcity.smartcity.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartcity.smartcity.model.User;
import com.smartcity.smartcity.model.enums.Access;

@Transactional
@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

	User getUserByLogin(String login);

	List<User> getAllUsersByAccess(String access);

	void deleteByUserId(int userId);

	void deleteUsersByAccess(Access access);
}
