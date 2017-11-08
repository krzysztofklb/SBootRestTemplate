/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krzychu.tableapp.service.schema;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LENOVO
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private float price;
    
    public ItemEntity(ItemResource item) {
        
        this.id = null;
        this.name = item.getName();
        this.price = item.getPrice();
    }
}
