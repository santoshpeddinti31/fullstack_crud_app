package com.example.fullstackcrudapp.demo.service;

import com.example.fullstackcrudapp.demo.dao.TodoRepository;
import com.example.fullstackcrudapp.demo.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;


    @Autowired
    public TodoServiceImpl(TodoRepository theTodoRepository)
    {
        this.todoRepository=theTodoRepository;
    }


    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo findById(int theId) {
        Optional<Todo> queryTodo=todoRepository.findById(theId);

        Todo findTodo=null;

        if(queryTodo.isPresent())
        {
            findTodo=queryTodo.get();
        }else {
            throw new RuntimeException("Todo id not found - "+theId);
        }
        return findTodo;
    }

    @Override
    public Todo save(Todo theTodo) {
        return todoRepository.save(theTodo);
    }

    @Override
    public void deleteById(int theId) {
       todoRepository.deleteById(theId);
    }
}
