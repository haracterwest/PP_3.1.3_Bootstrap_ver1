package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;


/**
 *  @Service - аннотация, объявляющая, что этот класс представляет собой сервис – компонент сервис-слоя.
 */

@Service

//класс, формирующий роли
public class RoleService {

    public RoleService() {
    }

    public ArrayList<String> getRoles() {
        ArrayList<String> list = new ArrayList<>();     //создание списка
        list.add("ROLE_ADMIN");                         //добавление роли ROLE_ADMIN
        list.add("ROLE_USER");                          //добавление роли ROLE_USER
        return list;                                    //возврат списка
    }
}
