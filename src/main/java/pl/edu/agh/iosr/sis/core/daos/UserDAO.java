package pl.edu.agh.iosr.sis.core.daos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.agh.iosr.sis.core.entities.User;

@Transactional
public interface UserDAO extends Repository<User, Long> {

	User save(User entity);

	List<User> findAll();

	Page<User> findAll(Pageable page);

	@Query("SELECT u FROM User u WHERE u.login = :login")
	User findByLogin(@Param("login") String login);

	@Query("SELECT u FROM User u WHERE u.id = :id")
	User findById(@Param("id") Long id);
}
