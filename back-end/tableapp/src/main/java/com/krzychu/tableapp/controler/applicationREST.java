/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krzychu.tableapp.controler;

import com.krzychu.tableapp.service.applicationService;
import com.krzychu.tableapp.service.schema.ItemEntity;
import com.krzychu.tableapp.service.schema.ItemResource;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@RestController
public class applicationREST {

    @Autowired
    applicationService service;

    @RequestMapping(produces = {"application/json"}, method = RequestMethod.GET, path = "/items")
    public List<ItemEntity> getItems() {

        return service.getItems();
    }

    @RequestMapping(produces = {"application/json"}, method = RequestMethod.GET, path = "/items/{id}")
    public ItemEntity getItems(@PathVariable("id") long id) {

        return service.getItem(id);
    }

    @RequestMapping(produces = {"application/json"}, consumes = {"application/json"},
            method = RequestMethod.POST, path = "/items")
    public ItemEntity postItem(@RequestBody ItemResource item) {
        return service.postItem(item);
    }

    @RequestMapping(produces = {"application/json"}, method = RequestMethod.PUT, path = "/items/{id}")
    public ItemEntity putItem(@PathVariable("id") long id, @RequestBody ItemResource item) {

        return service.putItem(new ItemEntity(id, item.getName(), item.getPrice()));
    }

    @RequestMapping(produces = {"application/json"}, method = RequestMethod.PATCH, path = "/items/{id}")
    public ItemEntity patchItem(@PathVariable("id") long id, @RequestBody Map<String, String> args) {
        return service.patchItem(id, args);
    }

    @RequestMapping(produces = {"application/json"}, method = RequestMethod.DELETE, path = "/items/{id}")
    public ItemEntity patchItem(@PathVariable("id") long id) {
        return service.deleteItem(id);
    }

}
