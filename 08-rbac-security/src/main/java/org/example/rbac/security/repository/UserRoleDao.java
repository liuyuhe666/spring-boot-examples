package org.example.rbac.security.repository;

import org.example.rbac.security.model.UserRole;
import org.example.rbac.security.model.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRoleDao extends JpaRepository<UserRole, UserRoleKey>, JpaSpecificationExecutor<UserRole> {

}
