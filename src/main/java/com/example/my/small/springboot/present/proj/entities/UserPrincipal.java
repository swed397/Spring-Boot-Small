package com.example.my.small.springboot.present.proj.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(schema = "movies", name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    //ToDo Fix circular
//    @ManyToMany(mappedBy = "userPrincipal")
//    private Set<Role> roles = new HashSet<>();
}
