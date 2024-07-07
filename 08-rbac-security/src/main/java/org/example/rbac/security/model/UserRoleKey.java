package org.example.rbac.security.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class UserRoleKey implements Serializable {
    private static final long serialVersionUID = -94609792969457171L;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;
}
