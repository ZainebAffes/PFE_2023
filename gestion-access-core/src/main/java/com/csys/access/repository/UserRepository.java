package com.csys.access.repository;


import com.csys.access.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AccessControl entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
    Optional<User> findOneByUsername(String username);

}
