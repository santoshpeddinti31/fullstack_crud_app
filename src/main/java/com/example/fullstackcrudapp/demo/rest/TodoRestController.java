package com.example.fullstackcrudapp.demo.rest;

import com.example.fullstackcrudapp.demo.entity.Todo;
import com.example.fullstackcrudapp.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoRestController {

    private TodoService todoService;

    @Autowired
    public TodoRestController(TodoService theTodoService)
    {
        this.todoService=theTodoService;
    }

    @GetMapping("/todos")
    public List<Todo> findAll()
    {
        return todoService.findAll();
    }

    @GetMapping("/todos/{todoId}")
    public Todo getTodo(@PathVariable int todoId)
    {
        Todo theTodo=todoService.findById(todoId);

        return theTodo;
    }

    @PostMapping("/todos")
    public Todo save(@RequestBody Todo theTodo)
    {
        Todo dbTodo=todoService.save(theTodo);
        return dbTodo;
    }

    @PutMapping("/todos")
    public Todo updateTodo(@RequestBody Todo theTodo)
    {
        Todo dbTodo=todoService.save(theTodo);

        return dbTodo;
    }

    @DeleteMapping("/todos/{theId}")
    public String deleteTodoId(@PathVariable int theId)
    {
        Todo theTodo=todoService.findById(theId);
        todoService.deleteById(theTodo.getId());
        return "Todo id is deleted -"+theId;
    }
}
