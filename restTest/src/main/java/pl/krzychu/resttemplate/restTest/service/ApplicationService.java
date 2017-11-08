/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.krzychu.resttemplate.restTest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzychu.resttemplate.restTest.repository.UserRepository;
import pl.krzychu.resttemplate.restTest.service.model.User;

/**
 *
 * @author Krzysztof
 */
@Service
public class ApplicationService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        userRepository.saveAndFlush(new User(null, "Krzysztof", "Klebens", null, null));
    }

    public List<User> getUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public User getUser(long id) {
        return userRepository.findOne(id);
    }

    public User postUser(User user) {
        return userRepository.saveAndFlush(new User(user));
    }

    public User deleteUser(long id) {
        User item = userRepository.findOne(id);
        userRepository.delete(id);
        return item;
    }

    public User putUser(User user) {
        if (userRepository.findOne(user.getId()) != null) {
            return userRepository.saveAndFlush(user);
        } else {
            return null;
        }
    }

    public User patchUser(long id, Map<String, String> args) {
        User user = userRepository.findOne(id);

        args.keySet().stream().forEach((k) -> {
            switch (k) {
                case "name":
                    user.setName(args.get(k));
                    break;
                case "surname":
                    user.setSurname(args.get(k));
                    break;
                case "phoneNumber":
                    user.setPhoneNumber(args.get(k));
                    break;
                case "description":
                    user.setDescription(args.get(k));
                    break;
                default:
            }
        });
        userRepository.saveAndFlush(user);

        return user;
    }

}
