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
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {

        return userRepository.getOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {

        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public void updateUser(User oldU, User newU) {
        Optional<User> oUser = Optional.of(newU);
        oUser.get().setPassword(oldU.getPassword());
        oUser.get().setEmail(oldU.getEmail());
        oUser.get().setName(oldU.getName());
        oUser.get().setRoles(oldU.getRoles());
        userRepository.save(oUser.get());
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserByName(String name) {
        return userRepository.getUserByName(name);
    }
}
