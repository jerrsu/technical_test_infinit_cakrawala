package com.technical.test.repository;

import com.technical.test.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jerry
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
