package com.interpackage.users.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "permission")
public class Permission {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Long idPermission;

    @Column (nullable = false, length = 75)
    private String name;

    @Column (nullable = false, length = 500)
    private String description;
}
