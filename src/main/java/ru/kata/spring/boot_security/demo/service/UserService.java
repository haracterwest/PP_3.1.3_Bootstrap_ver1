package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 *  @Service - аннотация, объявляющая, что этот класс представляет собой сервис – компонент сервис-слоя.
 *  @Transactional - перед исполнением метода помеченного данной аннотацией начинается транзакция,
 *  после выполнения метода транзакция коммитится, при выбрасывании RuntimeException откатывается.
 *  @Autowired - отмечает конструктор, поле или метод как требующий автозаполнения инъекцией зависимости;
 */

@Service
public class UserService {      //формирование класса UserService

    private final UserRepository userRepository;

    @Autowired      //необходимо автозаполнение инъекцией зависимости конструктора UserService
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {     //метод поиска по id
        return userRepository.findById(id).get();   //возврат по id
    }

    public List<User> findAll() {       //метод формирования списка всех зеров
        return userRepository.findAll();    //возврат всех юзерв из репозитормя
    }

    @Transactional
    public User saveUser(User user) {       //создание нового юзера + пароль

        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Transactional
    public void updateUser(User oldU, User newU) {      //изменение данных юзера
        Optional<User> oUser = Optional.of(newU);
        oUser.get().setPassword(oldU.getPassword());
        oUser.get().setEmail(oldU.getEmail());
        oUser.get().setName(oldU.getName());
        oUser.get().setRoles(oldU.getRoles());
        userRepository.save(oUser.get());
    }

    @Transactional
    public void deleteById(Long id) {       //удаление юзера по id
        userRepository.deleteById(id);
    }

    public User getUserByName(String name) {    //поиск по имени
        return userRepository.getUserByName(name);
    }
}
