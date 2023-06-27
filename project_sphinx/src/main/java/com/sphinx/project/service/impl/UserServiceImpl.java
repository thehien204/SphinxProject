package com.sphinx.project.service.impl;

import com.sphinx.project.model.User;
import com.sphinx.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserServiceImpl  {

    @Autowired(required = true)
    private UserRepo userRepo;
    
    @PersistenceContext(unitName = "entityManagerFactoryFormManagement")
    EntityManager entityManager;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
