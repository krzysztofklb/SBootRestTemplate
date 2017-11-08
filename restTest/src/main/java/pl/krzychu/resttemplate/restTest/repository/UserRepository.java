/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.krzychu.resttemplate.restTest.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.krzychu.resttemplate.restTest.service.model.User;

/**
 *
 * @author Krzysztof
 */
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
