/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krzychu.tableapp.repository;

import com.krzychu.tableapp.service.schema.ItemEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LENOVO
 */
@Transactional

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

}
