package com.interpackage.users.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "role")
public class Role {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_role", nullable = false)
    private Long idUser;

    @Column (nullable = false, length = 75)
    private String name;
    @Column (nullable = true, length = 75)
    private String description;

}
