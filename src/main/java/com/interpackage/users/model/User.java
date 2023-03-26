package com.interpackage.users.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "internal_user")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_user", nullable = false)
    private Long idUser;

    @Column (nullable = false, length = 75)
    private String name;

    @Column (nullable = false, length = 75)
    private String dpi;

    @Column (nullable = false, length = 500)
    private String address;

    @Column (nullable = false, length = 75)
    private String phone;

    @Column (nullable = false, length = 75)
    private String email;

    @ManyToOne
    @JoinColumn (name = "id_role", nullable = false)
    private Role role;
}
