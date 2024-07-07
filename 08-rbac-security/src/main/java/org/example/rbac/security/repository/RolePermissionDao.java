package org.example.rbac.security.repository;

import org.example.rbac.security.model.RolePermission;
import org.example.rbac.security.model.RolePermissionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RolePermissionDao extends JpaRepository<RolePermission, RolePermissionKey>, JpaSpecificationExecutor<RolePermission> {
}
