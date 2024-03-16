package com.example.fullstackcrudapp.demo.service;

import com.example.fullstackcrudapp.demo.dao.CrudRepository;
import com.example.fullstackcrudapp.demo.entity.Todo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private CrudRepository crudRepository;

    @Autowired
    public TodoServiceImpl(CrudRepository theCrudRepository)
    {
        this.crudRepository=theCrudRepository;
    }

    @Override
    public List<Todo> findAll() {
        return crudRepository.findAll();
    }

    @Override
    public Todo findById(int theId) {
        return crudRepository.findById(theId);
    }

    @Override
    @Transactional
    public Todo save(Todo theTodo) {
        return crudRepository.save(theTodo);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        crudRepository.deleteById(theId);
    }

}
