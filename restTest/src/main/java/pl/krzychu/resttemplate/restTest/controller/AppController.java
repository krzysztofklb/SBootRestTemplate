/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.krzychu.resttemplate.restTest.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.krzychu.resttemplate.restTest.service.ApplicationService;
import pl.krzychu.resttemplate.restTest.service.model.User;

/**
 *
 * @author Krzysztof
 */
@RestController
public class AppController {

    @Autowired
    ApplicationService service;

    @RequestMapping(produces = {"application/json"}, method = RequestMethod.GET, path = "/users")
    public List<User> getUsers() {
        return service.getUsers();
    }

    @RequestMapping(produces = {"application/json"}, consumes = {"application/json"},
            method = RequestMethod.POST, path = "/users")
    public User postUser(@RequestBody User user) {
        return service.postUser(user);
    }

    @RequestMapping(produces = {"application/json"}, method = RequestMethod.DELETE, path = "/users/{id}")
    public User deleteUser(@PathVariable("id") long id) {
        return service.deleteUser(id);
    }

    @RequestMapping(produces = {"application/json"}, method = RequestMethod.PUT, path = "/users/{id}")
    public User putItem(@PathVariable("id") long id, @RequestBody User user) {
        return service.putUser(new User(id, user.getName(), user.getSurname(), user.getPhoneNumber(), user.getDescription()));
    }

    @RequestMapping(produces = {"application/json"}, method = RequestMethod.PATCH, path = "/users/{id}")
    public User patchItem(@PathVariable("id") long id, @RequestBody Map<String, String> args) {
        return service.patchUser(id, args);
    }

}
