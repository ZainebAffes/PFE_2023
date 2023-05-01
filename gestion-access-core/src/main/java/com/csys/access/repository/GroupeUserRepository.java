package com.csys.access.repository;

import com.csys.access.domain.GroupeUser;
import java.lang.String;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the GroupeUser entity.
 */
@Repository
public interface GroupeUserRepository extends JpaRepository<GroupeUser, String> {
    
}

