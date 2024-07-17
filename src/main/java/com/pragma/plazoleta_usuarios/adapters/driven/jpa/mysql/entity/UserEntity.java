package com.pragma.plazoleta_usuarios.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;
    @Column()
    private String lastName;

    @Column( unique = true)
    private String docNumber;
    @Column(length = 13)
    private String cellphone;

    @Column()
    private LocalDate birthDate;

    @Column(unique = true)
    private String email;

    @Column()
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;


}
