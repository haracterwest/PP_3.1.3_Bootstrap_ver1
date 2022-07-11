package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/** @Entity - аннотация, связывающая сущность Entity (POJO-класс) с БД, указывается над классом;
 *  @Table - указывает на имя таблицы, которая будет отображаться в этой сущности, указывается над классом;
 *  @Id - аннотация, оопределяющая primary key в entity bean;
 *  @Column - аннотация, которая используется для определения соответствия между атрибутами в классе сущности и полями в таблице данных;
 *  @GeneratedValue - задает стратегию создания основных ключей;
 */

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;


    public Role(Long id) {
        this.id = id;
    }

    public Role(String role) {
        this.role = role;
    }

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        role
                .replace("[", "")
                .replace("]", "");
        return role;

    }

    public String getRole() {
        return role.replace("[", "")
                .replace("]", "");

    }


    @Override
    public String getAuthority() {
        return role;
    }

}
