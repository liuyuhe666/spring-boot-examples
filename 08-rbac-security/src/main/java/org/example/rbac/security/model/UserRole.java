package org.example.rbac.security.model;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sec_user_role")
public class UserRole {
    /**
     * 主键
     */
    @EmbeddedId
    private UserRoleKey id;
}
