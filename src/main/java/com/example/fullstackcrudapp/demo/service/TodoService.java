package com.example.fullstackcrudapp.demo.service;

import com.example.fullstackcrudapp.demo.entity.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> findAll();

    Todo findById(int theId);

    Todo save(Todo theTodo);

    void deleteById(int theId);
}
