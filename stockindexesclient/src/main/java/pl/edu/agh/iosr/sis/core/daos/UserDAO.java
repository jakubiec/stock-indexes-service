package pl.edu.agh.iosr.sis.core.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import pl.edu.agh.iosr.sis.core.entities.User;

public interface UserDAO extends Repository<User, Long> {

	User save(User entity);
	List<User> findAll();
	@Query("SELECT u FROM User u WHERE u.login = :login")
	User findByLogin(@Param("login")String login);
}