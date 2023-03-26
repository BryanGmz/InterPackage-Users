package com.interpackage.users.model;

import com.interpackage.users.pk.PKRolePermission;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "role_permission")
@IdClass (PKRolePermission.class)
public class RolePermission {

    @Id
    @ManyToOne
    @JoinColumn (name = "id_role", nullable = false)
    private Role role;

    @Id
    @ManyToOne
    @JoinColumn (name = "id_permission", nullable = false)
    private Permission permission;

    @Column (nullable = false)
    private Boolean reading;

    @Column (nullable = false)
    private Boolean writing;

    @Column (nullable = false)
    private Boolean edition;

    @Column (nullable = false)
    private Boolean elimination;

    @Column (nullable = false)
    private Boolean export;
}
