/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krzychu.tableapp.service.schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author LENOVO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResource {

    private String name;

    private float price;
}
