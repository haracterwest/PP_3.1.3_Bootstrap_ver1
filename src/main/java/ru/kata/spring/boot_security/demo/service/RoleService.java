package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


/**
 *  @Service - аннотация, объявляющая, что этот класс представляет собой сервис – компонент сервис-слоя.
 *  @Transactional - перед исполнением метода помеченного данной аннотацией начинается транзакция,
 *  после выполнения метода транзакция коммитится, при выбрасывании RuntimeException откатывается.
 */

@Service
@Transactional
public class RoleService {

    public RoleService() {
    }

    public ArrayList<String> getRoles() {
        ArrayList<String> list = new ArrayList<>();
        list.add("ROLE_ADMIN");
        list.add("ROLE_USER");
        return list;
    }
}
