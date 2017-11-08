/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krzychu.tableapp.service;

import com.krzychu.tableapp.repository.ItemRepository;
import com.krzychu.tableapp.service.schema.ItemEntity;
import com.krzychu.tableapp.service.schema.ItemResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class applicationService {

    @Autowired
    private ItemRepository repository;

    @PostConstruct
    public void init() {
        repository.saveAndFlush(new ItemEntity(null, "Kierownica", 99.99f));
        repository.saveAndFlush(new ItemEntity(null, "Fotel", 5.99f));
        repository.saveAndFlush(new ItemEntity(null, "Szyba", 654.99f));
    }

    public List<ItemEntity> getItems() {
        return new ArrayList<>(repository.findAll());
    }

    public ItemEntity getItem(long id) {
        return repository.findOne(id);
    }

    public ItemEntity postItem(ItemResource item) {

        return repository.saveAndFlush(new ItemEntity(item));
    }

    public ItemEntity putItem(ItemEntity item) {

        if (repository.findOne(item.getId()) != null) {
            return repository.saveAndFlush(item);
        } else {
            return null;
        }
    }

    public ItemEntity patchItem(long id, Map<String, String> args) {

        ItemEntity item = repository.findOne(id);

        args.keySet().stream().forEach((k) -> {
            switch (k) {
                case "name":
                    item.setName(args.get(k));
                    break;
                case "price":
                    item.setPrice(Long.valueOf(args.get(k)));
                    break;
                default:
            }
        });
        repository.saveAndFlush(item);

        return item;

    }

    public ItemEntity deleteItem(long id) {
        ItemEntity item = repository.findOne(id);
        repository.delete(id);
        return item;
    }

}
