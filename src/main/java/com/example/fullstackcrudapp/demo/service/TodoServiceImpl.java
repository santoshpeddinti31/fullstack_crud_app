package com.example.fullstackcrudapp.demo.service;

import com.example.fullstackcrudapp.demo.dao.TodoRepository;
import com.example.fullstackcrudapp.demo.entity.Todo;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

        Optional<Todo> findTodo=todoRepository.findById(theId);

        Todo todoQuery=null;

        if(findTodo.isPresent())
        {
            todoQuery=findTodo.get();
        }else {
            throw  new RuntimeException("Did not find employee id - "+theId);
        }
        return todoQuery;
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
